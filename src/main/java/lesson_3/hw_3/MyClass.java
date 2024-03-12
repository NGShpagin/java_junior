package lesson_3.hw_3;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

public class MyClass {

    public static Path saveObjInFile(Student student) {
        Path path = Path.of("src/main/java/lesson_3/hw_3/" + student.getClass().getName() + " " + UUID.randomUUID().toString() + ".txt");
        try (OutputStream outputStream = Files.newOutputStream(path)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(student);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
        return path;
    }

    public static Student readStudentAndDeleteFile(Path path) {
        Student student = null;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(path));
            student = (Student) objectInputStream.readObject();
            Files.delete(path);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        } catch (IOException e) {
            System.out.println("Файл \"" + path + "\" не найден");
        }
        return student;
    }
}
