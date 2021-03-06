package map;

import java.util.HashMap;

/**
 * Create by peng on 2020/7/24.
 */
public class MapTest {
    public static void main(String[] args){
        HashMap<String, Employee> staff = new HashMap<>();

        staff.put("144-25-5464", new Employee("Amy Lee"));
        staff.put("567-24-2546", new Employee("Harry Hacker"));
        staff.put("157-62-7935", new Employee("Gary Cooper"));
        staff.put("456-62-5527", new Employee("Francesca Cruz"));

        System.out.println(staff);

        staff.remove("567-24-2546");

        staff.put("456-62-5527", new Employee("Francesca Miller"));

        System.out.println(staff.get("157-62-7935"));

        System.out.println(staff.getOrDefault("111-62-7935",new Employee("default")));

        staff.forEach((k, v) ->
                System.out.println("key=" + k + ", value=" + v));
    }
}
