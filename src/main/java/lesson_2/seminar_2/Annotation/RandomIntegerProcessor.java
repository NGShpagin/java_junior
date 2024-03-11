package lesson_2.seminar_2.Annotation;

import java.lang.reflect.Field;
import java.util.concurrent.ThreadLocalRandom;

public class RandomIntegerProcessor {
    // Найти все int-поля класса с аннотацией RandomInteger, и заполнить их рандомными значениями
    public static void processObject(Object object) {
        Class<?> objClass = object.getClass();
        for (Field objDeclareField : objClass.getDeclaredFields()) {
            if (int.class.isAssignableFrom(objDeclareField.getType()) && objDeclareField.isAnnotationPresent(RandomInteger.class)) {
                RandomInteger annotation = objDeclareField.getAnnotation(RandomInteger.class);
                int minValue = annotation.minValue();
                int maxValue = annotation.maxValue();

                int randomValue = ThreadLocalRandom.current().nextInt(minValue, maxValue);
                objDeclareField.setAccessible(true);
                try {
                    objDeclareField.set(object, randomValue);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
    }
}
