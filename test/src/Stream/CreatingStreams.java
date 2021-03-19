package Stream;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * Create by peng on 2021/3/19.
 */
public class CreatingStreams {
    public static <T> void show(String title, Stream<T> stream){
        final int SIZE = 10;
        List<T> firstElements = stream
                .limit(SIZE + 1)
                .collect(Collectors.toList());
        System.out.println(title + ": ");
        for(int i = 0; i < firstElements.size(); i++){
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(".\\gutenberg\\alice30.txt");
        String contents = Files.readString(path);

        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);
        Stream<String> song = Stream.of("gently", "down", "the", "stream");
        show("song", song);
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        Stream<BigInteger> bigintegers = Stream.iterate(BigInteger.ONE,
                n -> n.add(BigInteger.ONE));
        show("bigintegers", bigintegers);

        Stream<Integer> integers = Stream.iterate(1,
                n -> n + 1);
        show("integers", integers);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+").splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path))
        {
            show("lines", lines);
        }

        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(),false);
        show("rootDirectories", rootDirectories);

        Stream<int[]> fibonacci = Stream.iterate(new int[]{0,1},
                n -> new int[]{n[1], n[0] + n[1]});
        List<int[]> listfibint = fibonacci.limit(20).collect(Collectors.toList());
        for (int[] fibint:listfibint) {
            for (int n:fibint) {
                System.out.print(n + " ");
            }
            System.out.println();
        }
    }
}
