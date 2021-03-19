package ParallelStreams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.*;

/**
 * Create by peng on 2021/3/19.
 */
public class ParallelStreams {
    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".\\gutenberg\\alice30.txt");
        String contents = Files.readString(path);
        List<String> wordList = List.of(contents.split("\\PL+"));

        Map<Integer, Long> shortWordCounts = wordList.parallelStream()
                .filter(s -> s.length() <10)
                .collect(groupingBy(String::length, counting()));
        System.out.println(shortWordCounts);

        //异步执行，多线程并发情况下，输出的结果可能是不同的
        Map<Integer, List<String>> result = wordList.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        result = wordList.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        result = wordList.parallelStream().collect(
                Collectors.groupingByConcurrent(String::length));
        System.out.println(result.get(14));

        //同步执行-输出的结果是相同的
        result = wordList.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(result.get(14));

        result = wordList.stream().collect(Collectors.groupingBy(String::length));
        System.out.println(result.get(14));

        Map<Integer, Long> wordCounts = wordList.parallelStream().collect(
                groupingByConcurrent(String::length, counting()));
        System.out.println(wordCounts);
    }
}
