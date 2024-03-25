package lesson_5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", Server.PORT);

        // Поток на чтение
        new Thread(() -> {
            try (Scanner input = new Scanner(client.getInputStream())) {
                while (true) {
                    System.out.println(input.nextLine());
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // Поток на запись
        new Thread(() -> {
            try (PrintWriter output = new PrintWriter(client.getOutputStream(), true);
                 Scanner consoleScanner = new Scanner(System.in)) {
                while (true) {
                    String consoleInput = consoleScanner.nextLine();
                    if (!consoleInput.isEmpty()) {
                        output.println(consoleInput);
                    } else if (Objects.equals("q", consoleInput)) {
                        System.out.println("Отключение от сервера");
                        client.close();
                        break;
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
