package Zip;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by peng on 2021/3/22.
 */
public class ZipTest {
    public static void main(String[] args) throws IOException {
        String zipname = args[0];
        showContents(zipname);
        System.out.println("---");
        showContents2(zipname);
    }

    public static void showContents(String zipname) throws IOException {
        // Here, we use the classic zip API
        try (ZipInputStream zin = new ZipInputStream(new FileInputStream(zipname)))
        {
            ZipEntry entry;
            while ((entry = zin.getNextEntry()) != null){
                System.out.println(entry.getName());
                Scanner in = new Scanner(zin, StandardCharsets.UTF_8);
                while (in.hasNextLine()){
                    System.out.println("   " + in.nextLine());
                }
                // DO NOT CLOSE in
                zin.closeEntry();
            }
        }
    }

    public static void showContents2(String zipname) throws IOException
    {
        FileSystem fs = FileSystems.newFileSystem(Paths.get(zipname), null);
        Files.walkFileTree(fs.getPath(".\\gutenberg\\alice30.txt"), new SimpleFileVisitor<Path>()
        {
            public FileVisitResult visitFile(Path path, BasicFileAttributes attrs)
                    throws IOException
            {
                System.out.println(path);
                for (String line : Files.readAllLines(path, Charset.forName("UTF-8")))
                    System.out.println("   " + line);
                return FileVisitResult.CONTINUE;
            }
        });
    }

}
