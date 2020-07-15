
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by peng on 2020/7/13.
 */
public class NewTest {
    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        StringBuilder append = sb.append("test: ");
        sb.append(System.getProperty("user.dir"));
        System.out.println(sb.toString());

        Scanner in = new Scanner(System.in);
        while (in.hasNext()){
            String next = in.next();
            if(next.equalsIgnoreCase("q")){
                break;
            }
            switch (next){
                case "yes":
                    System.out.println("hello, yes");
                    break;
                case "no":
                    System.out.println("hello, no");
                    break;
                default:
                    System.out.println("nothing");
                    break;
            }
        }
    }
}
