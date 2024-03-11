package lesson_1.seminar_1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

public class Main {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>(List.of("java", "c#", "c++", "python", "kotlin", "php", "pascal"));
        System.out.println(strings);

        strings.sort((o1, o2) -> o1.length() - o2.length());
        System.out.println(strings);

        MyInterface myInterface = it -> System.err.println(it);
//        myInterface.foo("abcde");
//        myInterface.foo("abcde");
//        myInterface.foo("abcde");

        // void run() -> Runnable
        // T get() -> Supplier
        // void accept(T value) -> Consumer
        // R apply(T value) -> Function

        Runnable runnable = () -> System.out.println(ThreadLocalRandom.current().nextInt(100));
        for (int i = 0; i < 10; i++) {
            runnable.run();
        }

        Function<String, Integer> stringLengthExtractor = String::length;
        System.out.println(stringLengthExtractor.apply("abcdefg"));
        System.out.println(stringLengthExtractor.apply("java"));
        System.out.println(stringLengthExtractor.apply("kotlin"));

        Supplier<Integer> javaLength = "java"::length;
        System.out.println(javaLength.get());

        // string --> boolean
        Predicate<String> isBestLanguage = "java"::equals;
        System.out.println(isBestLanguage.test("java"));
        System.out.println(isBestLanguage.test("c++"));
        System.out.println(isBestLanguage.test("python"));

        Function<String, String> func = it -> "Добрый день, " + it + "!";
        UnaryOperator<String> unaryOperator = it -> it.toUpperCase();
        System.out.println(func.apply("Николай"));
        System.out.println(unaryOperator.apply("nik"));


//        Supplier<Person> personGenerator = Person::new;
        Function<String, Person> personFunction = Person::new;

//        Person person = personGenerator.get();
        Person person = personFunction.apply("Nikolay");
        System.out.println(person);

//        person = personGenerator.get();
        person = personFunction.apply("Vladimir");
        System.out.println(person);

    }

    static Integer extractStringLength(String arg) {
        return arg.length();
    }

    public static class Person {
        private static long counter = 1;
        private String name;

        private Supplier<String> wordGenerator;

        public Person(String name) {
            this.name = "Person #" + counter++ + " " + name;
            this.wordGenerator = this::generateNextWord;
        }

        public void saySomething() {
            System.out.println(wordGenerator.get());
        }

        private String generateNextWord() {
            return "EFFECTIVE RANDOM WORD";
        }

        @Override
        public String toString() {
            return name;
        }
    }

    interface Interface<T> {
        T get();
    }

    interface MyInterface {
        void foo(String arg);
    }
}
