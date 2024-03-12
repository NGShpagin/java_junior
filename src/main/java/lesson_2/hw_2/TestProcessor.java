package lesson_2.hw_2;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class TestProcessor {

    /**
     * Данный метод находит все void-методы без аргументов в классе, и запускает их
     * Для запуска создается тестовый объект с помощью конструктора без аргументов
     */
    public static void runTest(Class<?> testClass) {
        final Constructor<?> declaredConstructor;
        try {
            declaredConstructor = testClass.getDeclaredConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalStateException("Для класса \"" + testClass.getName() + "\" не найден конструктор без аргументов");
        }

        final Object testObj;
        try {
            testObj = declaredConstructor.newInstance();
        } catch (InvocationTargetException | InstantiationException | IllegalAccessException e) {
            throw new RuntimeException("Не удалось создать объект класса \"" + testClass.getName() + "\"");
        }


        List<Method> testMethods = new ArrayList<>();
        List<Method> beforeMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class) && !method.isAnnotationPresent(Skip.class)) {
                checkTestMethod(method);
                testMethods.add(method);
            } else if (method.isAnnotationPresent(BeforeEach.class)) {
                checkTestMethod(method);
                beforeMethods.add(method);
            } else if (method.isAnnotationPresent(AfterEach.class)) {
                checkTestMethod(method);
                afterMethods.add(method);
            }
        }

        testMethods.sort(Comparator.comparingInt(m -> m.getAnnotation(Test.class).order()));

        testMethods.forEach(testMethod -> {
            beforeMethods.forEach(beforeMethod -> runTest(beforeMethod, testObj));
            runTest(testMethod, testObj);
            afterMethods.forEach(afterMethod -> runTest(afterMethod, testObj));
        });
    }

    private static void checkTestMethod(Method method) {
        if (!method.getReturnType().isAssignableFrom(void.class) || method.getParameterCount() != 0) {
            throw new IllegalStateException("Метод \"" + method.getName() + "\" должен быть void и не иметь аргументов");
        }
    }

    private static void runTest(Method testMethod, Object obj) {
        try {
            testMethod.invoke(obj);
        } catch (IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException("Не удалось запустить тестовый метод \"" + testMethod.getName() + "\"");
        }
    }
}
