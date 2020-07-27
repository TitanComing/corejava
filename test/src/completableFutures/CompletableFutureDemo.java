package completableFutures;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by peng on 2020/7/27.
 */
public class CompletableFutureDemo {
    private static final Pattern IMG_PATTERN = Pattern.compile(
            "[<]\\s*[iI][mM][gG]\\s*[^>]*[sS][rR][cC]\\s*[=]\\s*['\"]([^'\"]*)['\"][^>]*[>]"
    );
    private ExecutorService executorService = Executors.newCachedThreadPool();
    private URL urlToProcess;

    public CompletableFuture<String> readPage(URL url){
        return CompletableFuture.supplyAsync(() ->
            {
                try {
                    String contents = new String(url.openStream().readAllBytes(), StandardCharsets.UTF_8);
                    System.out.println("Read page from " + url);
                    return contents;
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            }, executorService);
    }

    public List<URL> getImageURLs(String webpage)
    {
        try {
            ArrayList<URL> result = new ArrayList<>();
            Matcher matcher = IMG_PATTERN.matcher(webpage);
            while (matcher.find())
            {
                URL url = new URL(urlToProcess, matcher.group(1));
                result.add(url);
            }
            System.out.println("Found URLs: " + result);
            return result;
        }catch (IOException e){
            throw new UncheckedIOException(e);
        }
    }

    public CompletableFuture<List<BufferedImage>> getImages(List<URL> urls)
    {
        return CompletableFuture.supplyAsync(() ->
        {
            try
            {
                ArrayList<BufferedImage> result = new ArrayList<BufferedImage>();
                for (URL url : urls)
                {
                    result.add(ImageIO.read(url));
                    System.out.println("Loaded " + url);
                }
                return result;
            }
            catch (IOException e)
            {
                throw new UncheckedIOException(e);
            }
        }, executorService);
    }

    public void saveImages(List<BufferedImage> images)
    {
        System.out.println("Saving " + images.size() + " images");
        try
        {
            for (int i = 0; i < images.size(); i++)
            {
                String filename = "E:\\workspace\\test\\sava" + (i + 1) + ".png";
                ImageIO.write(images.get(i), "PNG", new File(filename));
            }
        }
        catch (IOException e)
        {
            throw new UncheckedIOException(e);
        }
        executorService.shutdown();
    }

    public void run(URL url)
            throws IOException, InterruptedException
    {
        urlToProcess = url;
        CompletableFuture.completedFuture(url)
                .thenComposeAsync(this::readPage, executorService)
                .thenApply(this::getImageURLs)
                .thenCompose(this::getImages)
                .thenAccept(this::saveImages);

      /*
      // or use the experimental HTTP client:

      HttpClient client = HttpClient.newBuilder().executor(executor).build();
      HttpRequest request = HttpRequest.newBuilder(urlToProcess.toURI()).GET()
         .build();
      client.sendAsync(request, BodyProcessor.asString())
         .thenApply(HttpResponse::body).thenApply(this::getImageURLs)
         .thenCompose(this::getImages).thenAccept(this::saveImages);
      */
    }

    public static void main(String[] args)
            throws IOException, InterruptedException
    {
        new CompletableFutureDemo().run(new URL("http://horstmann.com/index.html"));
    }

}
