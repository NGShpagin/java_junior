package lesson_2.seminar_2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Reflection {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException, NoSuchFieldException {

        List<Person> persons = new ArrayList<>();

//        Person person = new Person("Igor");
//        System.out.println(person);

        Class<Person> personClass = Person.class;
        Constructor<Person> constructor = personClass.getDeclaredConstructor(String.class);
        Constructor<Person> constructor1 = personClass.getConstructor(String.class, int.class);

        constructor.setAccessible(true);
        Person person1 = constructor.newInstance("Igor"); // new Person("Igor")
        Person person2 = constructor1.newInstance("Vladimir", 40); // new Person("Igor", 40)
        System.out.println(person1);
        System.out.println(person2);

        Class<? super Head> superClass = Head.class.getSuperclass();
        Constructor<? super Head> declaredConstructor = superClass.getDeclaredConstructor(String.class);
        declaredConstructor.setAccessible(true);
        Object o = declaredConstructor.newInstance("abcd");
        System.out.println(o);

        Field ageField = personClass.getDeclaredField("age");
        Field nameField = personClass.getDeclaredField("name");
        ageField.setAccessible(true);
        nameField.setAccessible(true);
        ageField.set(person1, 160);
        nameField.set(person1, "Nikolay");
        System.out.println(person1);

        Method toStringMethod = personClass.getMethod("toString");
        Object toStringResult = toStringMethod.invoke(person1);
        System.out.println(toStringResult);

        Person person3 = Person.randomPerson();
        System.out.println(person3);

        Method randomPersonMethod = personClass.getMethod("randomPerson");
        Object randomPersonResult = randomPersonMethod.invoke(null);
        System.out.println(randomPersonResult);
    }
}
