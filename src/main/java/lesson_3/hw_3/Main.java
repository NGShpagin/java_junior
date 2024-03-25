package lesson_3.hw_3;

import java.nio.file.Path;
import java.util.function.Consumer;

/**
 * Написать класс с двумя методами:
 * 1. принимает объекты, имплементирующие интерфейс serializable, и сохраняющие их в файл. Название файл - class.getName() + "_" + UUID.randomUUID().toString()
 * 2. принимает строку вида class.getName() + "_" + UUID.randomUUID().toString() и загружает объект из файла и удаляет этот файл.
 * Что делать в ситуациях, когда файла нет или в нем лежит некорректные данные - подумать самостоятельно.
 */

public class Main {
    public static void main(String[] args) {
        Student student1 = new Student("Igor");

        Path path = MyClass.saveObjInFile(student1);
        Student readStudent = MyClass.readStudentAndDeleteFile(path);
        System.out.println(readStudent);
    }


}
