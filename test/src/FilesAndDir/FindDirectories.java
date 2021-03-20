package FilesAndDir;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Create by peng on 2021/3/20.
 */
public class FindDirectories {
    public static void main(String[] args) throws IOException {
        Path dir = Paths.get(args.length == 0 ? System.getProperty("user.home") : args[0]);
        Files.walkFileTree(dir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException{
                if(attrs.isDirectory())
                    System.out.println(file);
                return FileVisitResult.CONTINUE;
            }
            @Override
            public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
