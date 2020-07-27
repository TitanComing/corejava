package executors;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.Callable;
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
                    if(Thread.currentThread().isInterrupted()){
                        System.out.println("Search in " + path + " canceled.");
                        return null;
                    }
                }
                throw new NoSuchElementException();
            }
        };
    }



}
