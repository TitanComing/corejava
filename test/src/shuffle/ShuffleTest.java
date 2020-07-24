package shuffle;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Create by peng on 2020/7/24.
 */
public class ShuffleTest {
    public static void main(String[] args){
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i <= 49; i++)
            numbers.add(i);
        Collections.shuffle(numbers);
        List<Integer> winningCombination = numbers.subList(0,6);
        Collections.sort(winningCombination);
        System.out.println(winningCombination);
    }
}
