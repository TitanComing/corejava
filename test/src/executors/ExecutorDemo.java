package executors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Create by peng on 2020/7/27.
 */
public class ExecutorDemo {
    public static long occurrences(String word, Path path){
        try (Scanner in = new Scanner(path)){
            int count = 0;
            while (in.hasNext()){
                if(in.next().equals(word)){
                    count++;
                }
            }
            return count;
        }catch (IOException e){
            e.printStackTrace();
            return 0;
        }
    }

    public static Set<Path> descendants(Path rootDir) throws IOException
    {
        try (Stream<Path> entries = Files.walk(rootDir))
        {
            return entries.filter(Files::isRegularFile)
                    .collect(Collectors.toSet());
        }
    }

    public static Callable<Path> searchForTask(String word, Path path){
        return () -> {
            try (Scanner in = new Scanner(path)){
                while (in.hasNext()){
                    if(in.next().equals(word)){
                        return path;
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        System.out.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }

    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        try (Scanner in = new Scanner(System.in)) {
            System.out.print("Enter base directory (e.g. /opt/jdk-9-src): ");
            String start = in.nextLine();
            System.out.print("Enter keyword (e.g. volatile): ");
            String word = in.nextLine();

            Set<Path> files = descendants(Path.of(start));
            ArrayList<Callable<Long>> tasks = new ArrayList<>();
            for (Path file : files) {
                Callable<Long> task = () -> occurrences(word, file);
                tasks.add(task);
            }

            // use a single thread executor instead to see if multiple threads
            // speed up the search
            ExecutorService executor = Executors.newCachedThreadPool();
            //ExecutorService executor = Executors.newSingleThreadExecutor();

            Instant startTime = Instant.now();
            List<Future<Long>> results = executor.invokeAll(tasks);
            long total = 0;
            for (Future<Long> result : results) {
                total += result.get();
            }
            Instant endTime = Instant.now();
            System.out.println("Occurrences of " + word + ": " + total);
            System.out.println("Time elapsed: "
                    + Duration.between(startTime, endTime).toMillis() + " ms");

            List<Callable<Path>> searchTasks = new ArrayList<Callable<Path>>();
            for (Path file : files)
                searchTasks.add(searchForTask(word, file));
            Path found = executor.invokeAny(searchTasks);
            System.out.println(word + " occurs in: " + found);

            if (executor instanceof ThreadPoolExecutor) {// the single thread executor isn't
                System.out.println("Largest pool size: "
                        + ((ThreadPoolExecutor) executor).getLargestPoolSize());
            }

            executor.shutdown();

        }
    }


}
