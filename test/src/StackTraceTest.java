import java.util.Scanner;

/**
 * Create by peng on 2020/7/20.
 */
public class StackTraceTest {
    public static void main(String[] args){
        try (Scanner in = new Scanner(System.in)){
            System.out.print("Enter n:");
            int n = in.nextInt();
            factorial(n);
        }
    }

    public static int factorial(int n){
        System.out.println("factorial(" + n + "):");
        StackWalker stackWalker = StackWalker.getInstance();
        stackWalker.forEach(System.out::println);
        int r;
        if(n <= 1) {
            r = 1;
        } else {
            r = n * factorial(n-1);
        }
        System.out.println("return " + r);
        return r;
    }

}
