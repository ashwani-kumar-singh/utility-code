package enumexample;

import java.util.Arrays;

public class EnumDemo {
    public static void main(String[] args) {
        DayOfWeek dayOfWeek = DayOfWeek.getDayOfWeek("Monday");
        System.out.println(Arrays.toString(DayOfWeek.values()));
        System.out.println(dayOfWeek);
        System.out.println(dayOfWeek.getComment());
        System.out.println(dayOfWeek.ordinal());
        System.out.println(dayOfWeek.toLower());
        System.out.println(dayOfWeek.getComment());

        DayOfWeek.SUNDAY.dummyMethod();
        DayOfWeek.THURSDAY.dummyMethod();

        DayOfWeek.SUNDAY.abstractDummyMethod();
        DayOfWeek.THURSDAY.abstractDummyMethod();
    }
}
