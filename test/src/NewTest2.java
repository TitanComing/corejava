import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by peng on 2020/7/13.
 */
public class NewTest2 {
    public static void main(String[] args){
        int[] a = new int[10];
        for (int i = 0; i < a.length; i++){
            a[i] = i;
        }
        int[] b = Arrays.copyOf(a, a.length);
        System.out.println(Arrays.binarySearch(b,5));
        Arrays.sort(a);
        System.out.println(Arrays.toString(a));
    }
}
