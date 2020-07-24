package set;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Create by peng on 2020/7/24.
 */
public class SetTest {
    public static void main(String[] args){
        HashSet<String> words = new HashSet<>();
        long totalTime = 0L;

        try(Scanner in = new Scanner(System.in)){
            while (in.hasNext()){
                String word = in.next();
                if(word.equalsIgnoreCase("q")){
                    break;
                }
                long callTime = System.currentTimeMillis();
                words.add(word);
                callTime = System.currentTimeMillis() - callTime;
                totalTime += callTime;
            }
        }

        Iterator<String> iterator = words.iterator();
        for(int i = 1; i <= 20 && iterator.hasNext(); i++){
            System.out.println(iterator.next());
        }
        System.out.println(". . .");
        System.out.println(words.size() + " distinct words. " + totalTime + " milliseconds.");
    }
}
