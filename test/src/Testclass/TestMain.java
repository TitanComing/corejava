package Testclass;

import java.util.Objects;

/**
 * Created by peng on 2020/7/15.
 */
public class TestMain {
    public static void main(String[] args){
        Class2 class2 = new Class2();
        Class3 class3 = new Class3();

        Class2 newClass2 = (Class2) class3;

        System.out.println(Objects.equals(class2.getTest1(),newClass2.getTest1()));
    }
}
