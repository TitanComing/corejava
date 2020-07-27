package Process;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * Created by peng on 2020/7/27.
 */
public class ReadDir {
    public static void main(String[] args) throws IOException, InterruptedException {
        Process p = new ProcessBuilder("cmd.exe","/C","dir")
                .directory(Paths.get("C:\\Users\\peng\\Desktop").toFile())
                .start();
        try (var in = new Scanner(p.getInputStream()))
        {
            while (in.hasNextLine())
                System.out.println(in.nextLine());
        }
        int result = p.waitFor();
        System.out.println("Exit value: " + result);
    }
}
