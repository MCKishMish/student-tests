package study.stepup;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

public class StudentTests {

    @Test
    public void marksInRange() {
        List<Integer> marks = Arrays.asList(2, 3, 4, 5);
        Student student = new Student("Pasha");
        for (int mark : marks) {
            student.addMark(mark);
        }
        Assertions.assertEquals(marks,student.getMarks());
    }

    @Test
    public void marksNotInRange() {
        List<Integer> marks = Arrays.asList(0, 1, 6, 7);
        Student student = new Student("Pasha");
        for (int mark : marks) {
            Assertions.assertThrows(IllegalArgumentException.class,()-> student.addMark(mark));
        }
    }

    @Test
    public void marksEncapsulation() {
        Student st1 = new Student("Pasha");
        st1.addMark(5);
        st1.getMarks().add(2);
        Assertions.assertEquals(Arrays.asList(5),st1.getMarks());
    }

    @Test
    public void nameChanging() {
        Student st1 = new Student("Pasha");
        st1.setName("Sasha");
        Assertions.assertEquals("Sasha",st1.getName());
    }

    @Test
    public void toStringRepresentation() {
        Student st1 = new Student("Pasha");
        List<Integer> marks = Arrays.asList(5,5,5,5,5);
        for (int mark : marks) {
            st1.addMark(mark);
        }
        Assertions.assertEquals("Student{name=Pasha, marks=[5, 5, 5, 5, 5]}",st1.toString());
    }

    @Test
    public void equalsCheck() {
        Student st1 = new Student("Pasha");
        Student st2 = new Student("Pasha");
        List<Integer> marks = Arrays.asList(5,5,5,5,5);
        for (int mark : marks) {
            st1.addMark(mark);
            st2.addMark(mark);
        }
        Assertions.assertTrue(st1.equals(st1));
        Assertions.assertTrue(st1.equals(st2));
    }

    @Test
    public void notEqualsCheck() {
        Student st1 = new Student("Pasha");
        Student st2 = new Student("Sasha");
        Assertions.assertFalse(st1.equals(st2));
        Assertions.assertFalse(st1.equals(null));
    }
}
