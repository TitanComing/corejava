import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Create by peng on 2020/7/14.
 */
public class ListTest {
    public static void main(String[] args) {
        List<String> parameterList = Arrays.asList("1","2","2","3","3","4","4","5","6","7","8");
        List<String> resultList = parameterList.stream().collect(Collectors.collectingAndThen(Collectors
                .groupingBy(Function.identity(), Collectors.counting()), map->{
            map.values().removeIf(size -> size == 1);
            List<String> tempList = new ArrayList<>(map.keySet());
            return tempList;
        }));
        System.out.println(resultList);
    }
}
