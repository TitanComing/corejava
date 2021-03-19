package Stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Create by peng on 2021/3/19.
 */
public class CountLongWords {
    public static void main(String[] args) throws IOException {
        System.out.println("用户的当前工作目录: "+System.getProperty("user.dir"));

        String contents  = Files.readString(
                Paths.get(".\\gutenberg\\alice30.txt"));

        //List<String> words = List.of(contents.split("\\PL+"));
        List<String> words = Arrays.asList(contents.split("\\PL+"));

        long count = 0;
        for(String w: words){
            if(w.length() > 12) count++;
        }
        System.out.println(count);

        count = words.stream().filter(w -> w.length() > 12).count();
        System.out.println(count);

        count = words.parallelStream().filter(w -> w.length() > 12).count();
        System.out.println(count);

    }
}
