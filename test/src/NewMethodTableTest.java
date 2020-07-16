import java.lang.reflect.Method;

/**
 * Create by peng on 2020/7/16.
 */
public class NewMethodTableTest {
    public static void main(String[] args) throws ReflectiveOperationException {
        Method square = NewMethodTableTest.class.getDeclaredMethod("square", double.class);
        Method sqrt = Math.class.getDeclaredMethod("sqrt", double.class);

        printTable(1,10,10,square);
        printTable(1,10,10,sqrt);
    }

    public static double square(double x) {
        return x * x;
    }

    public static void printTable(double from, double to, int n, Method f) throws ReflectiveOperationException {
        System.out.println(f);

        double dx = (to - from) / (n - 1);

        for(double x = from; x <= to; x += dx){
            double y = (Double) f.invoke(null,x);
            System.out.printf("%10.4f | %10.4f%n", x, y);
        }

    }

}
