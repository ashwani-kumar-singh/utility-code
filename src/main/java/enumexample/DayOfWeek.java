package enumexample;

import java.util.Objects;

/**
 * 1. It is collection of constant for which values cannot be changed.
 * 2. It's constants are static & final by defaults
 * 3. It can not extends any class , as it is internally extends java.lang.Enum class
 * 4. It can implement interfaces
 * 5. It can have variables , constructors, methods
 * 6. It can not be initiated (as it's constructor will be private only, even if you provide default constructor,
 * in bytecode it will be converted to private).
 * 7. No other class can extend Enum class.
 * 8. It can have abstract methods, all the constants should implements the abstract methods.
 * 9. To define a method for the whole class (enum) we need to make it static , otherwise
 * It'll be applicable for all the constants and constant can override method if required.
 * 10. Advantages:
 * a. It has better readability.
 * b. We have control on what value we can pass
 * <p>
 * in parameter.
 */
public enum DayOfWeek implements LowerCaseInterface {
    MONDAY("Monday", "1st day of the week") {
        @Override
        public void abstractDummyMethod() {
            System.out.println("Monday Abstract Dummy method");
        }

        @Override
        public void dummyMethod() {
            System.out.println("Monday Dummy method");
        }
    },
    TUESDAY("Tuesday", "2nd day of the week"){
        @Override
        public void abstractDummyMethod() {
            System.out.println("Tuesday Abstract Dummy method");
        }
    },
    WEDNESDAY("Wednesday", "3rd day of the week"){
        @Override
        public void abstractDummyMethod() {
            System.out.println("Wednesday Abstract Dummy method");
        }
    },
    THURSDAY("Thursday", "4th day of the week"){
        @Override
        public void abstractDummyMethod() {
            System.out.println("Thursday Abstract Dummy method");
        }
    },
    FRIDAY("Friday", "5th day of the week"){
        @Override
        public void abstractDummyMethod() {
            System.out.println("Friday Abstract Dummy method");
        }
    },
    SATURDAY("Saturday", "6th day of the week") {
        @Override
        public void abstractDummyMethod() {
            System.out.println("Saturday Abstract Dummy method");
        }

        @Override
        public void dummyMethod() {
            System.out.println("Saturday Dummy method");
        }
    },
    SUNDAY("Sunday", "7th day of the week") {
        @Override
        public void abstractDummyMethod() {
            System.out.println("Sunday Abstract Dummy method");
        }

        @Override
        public void dummyMethod() {
            System.out.println("Sunday Dummy method");
        }
    };

    private String value;
    private String comment;

    private DayOfWeek(String value, String comment) {
        this.value = value;
        this.comment = comment;
    }

    // overriding LowerCaseInterface method
    @Override
    public String toLower() {
        return this.name().toLowerCase();
    }

    // abstract method

    public abstract void abstractDummyMethod();

    // static method common to all constants
    public static DayOfWeek getDayOfWeek(String dayOfWeek) {
        for (DayOfWeek day : DayOfWeek.values()) {
            if (Objects.equals(day.getValue(), dayOfWeek))
                return day;
        }
        return null;
    }

    // non-static dummy method
    public void dummyMethod() {
        System.out.println("Dummy method");
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
