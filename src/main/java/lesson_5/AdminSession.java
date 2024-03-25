package lesson_5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

public class AdminSession {

    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("localhost", Server.PORT);
        AtomicBoolean isWorked = new AtomicBoolean(true);

        // Поток на чтение
        new Thread(() -> {
            try (Scanner input = new Scanner(clientSocket.getInputStream())) {
                while (true) {
                    String getInput = input.nextLine();
                    if (Objects.equals(getInput, "kicked")) {
                        System.out.println("Вы были исключены Админом");
                        clientSocket.close();
                        break;
                    } else if (Objects.equals(getInput, "q")) {
                        System.out.println("Вы отключились от сервера");
                        break;
                    } else System.out.println(getInput);
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        // Поток на запись
        new Thread(() -> {
            try (PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
                 Scanner consoleScanner = new Scanner(System.in)) {
                output.println("%CREATE_ADMIN");
                while (true) {
                    String consoleInput = consoleScanner.nextLine();
                    if (!consoleInput.isEmpty()) {
                        output.println(consoleInput);
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(() -> {
            if (!isWorked.get()) {

            }
        });
    }
}
