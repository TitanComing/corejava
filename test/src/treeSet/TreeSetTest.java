package treeSet;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Create by peng on 2020/7/24.
 */
public class TreeSetTest {
    public static void main(String[] args){
        TreeSet<Item> parts = new TreeSet<>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4562));
        parts.add(new Item("Modem", 9912));
        System.out.println(parts);

        TreeSet<Item> sortByDescription = new TreeSet<Item>(Comparator.comparing(Item::getDescription));

        sortByDescription.addAll(parts);
        System.out.println(sortByDescription);

    }
}
