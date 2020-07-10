import java.util.Scanner;

/**
 * Create by peng on 2020/7/10.
 */
public class NewInputTest {
    public static void main(String[] args)
    {
        Scanner in = new Scanner(System.in);
        System.out.println("name?");
        String name = in.nextLine();
        System.out.println("age?");
        int age = in.nextInt();
        System.out.println("Hello, " + name + ". Next year, you'll be " + (age + 1));
    }
}
