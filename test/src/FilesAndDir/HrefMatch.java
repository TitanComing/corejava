package FilesAndDir;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

/**
 * Create by peng on 2021/3/20.
 */
public class HrefMatch {
    public static void main(String[] args){
        try {
            String urlString;
            if (args.length > 0) urlString = args[0];
            else urlString = "http://openjdk.java.net/";

            // read contents of URL
            InputStream in = new URL(urlString).openStream();
            String input = new String(in.readAllBytes(), StandardCharsets.UTF_8);

            // search for all occurrences of pattern
            String patternString = "<a\\s+href\\s*=\\s*(\"[^\"]*\"|[^\\s>]*)\\s*>";
            Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
            pattern.matcher(input)
                    .results()
                    .map(MatchResult::group)
                    .forEach(System.out::println);
        } catch (IOException | PatternSyntaxException e) {
            e.printStackTrace();
        }
    }
}
