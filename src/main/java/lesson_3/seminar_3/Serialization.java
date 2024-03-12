package lesson_3.seminar_3;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Serialization {
    public static void main(String[] args) throws IOException {

//        Path path = Path.of("pom.xml");

//        InputStream inputStream = Files.newInputStream(path);
//        BufferedInputStream bufferedInputStream = new BufferedInputStream(Files.newInputStream(path));
//        int read = inputStream.read();
//        byte[] bytes = inputStream.readAllBytes();
//        String content = new String(bytes);
//        inputStream.close();


//        Path path1 = Path.of("src/main/java/lesson_3/output.txt");
//
//        try (OutputStream outputStream = Files.newOutputStream(path1, StandardOpenOption.APPEND)) {
//            outputStream.write("my content".getBytes());
//        }

        Path.of("newFile.txt");


        SerializablePerson igorPerson = new SerializablePerson("Igor", 180, new Department("Financial"), List.of("13123", "sdjgd", "sdfl"));
//        System.out.println(igorPerson);

        Path serializablePersonPath = Path.of("src/main/java/lesson_3/serializable-person.txt");

        try (OutputStream outputStream = Files.newOutputStream(serializablePersonPath)) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
            objectOutputStream.writeObject(igorPerson);
        }

        SerializablePerson deserializablePerson;
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(serializablePersonPath));
            deserializablePerson = (SerializablePerson) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e.getMessage());
        }

        System.out.println(deserializablePerson);
    }
}
