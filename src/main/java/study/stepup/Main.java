package study.stepup;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Student st1 = new Student("Pasha");
        System.out.println(st1.hashCode());
        List<Integer> marks = Arrays.asList(5,5,5,5,5);
        for (int mark : marks) {
            st1.addMark(mark);
        }
        System.out.println(st1);
        st1.addMark(5);
        System.out.println(st1);
        st1.getMarks().add(2);
        System.out.println(st1);
    }
}
