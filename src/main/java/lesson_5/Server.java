package lesson_5;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

public class Server {

    private static long clientIdCounter = 1L;
    public static final int PORT = 8181;

    private static final Map<Long, User> users = new HashMap<>();
    private static final Map<Long, Socket> clientSockets = new HashMap<>();

    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Сервер запущен на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                long userId = clientIdCounter++;

                User user = new User(userId, clientSocket);

                System.out.println("Подключился новый пользователь [" + user + "]");
                users.put(userId, user);

                Thread clientThread = new Thread(() -> {
                    try (Scanner input = user.getInput(); PrintWriter output = user.getOutput()) {

                        output.println("Подключение к серверу [" + serverSocket + "] произошло успешно. " +
                                "Список всех пользователей: " + users);

                        while (true) {
                            String clientInput = input.nextLine();

                            if (Objects.equals(clientInput, "%CREATE_ADMIN")) user.setRole(Role.ADMIN);
                            else if (Objects.equals(clientInput, "q")) {
                                // TODO разослать всем остальным
                                users.remove(userId);
                                users.values().forEach(client ->
                                        client.getOutput().println("Пользователь [" + clientSocket + "] отключился"));
                                System.out.println("Пользователь [" + clientSocket + "] отключился. " +
                                        "Список всех пользователей: " + users);
                                break;
                            }

                            // Проверка на возможную отправку сообщения другому пользователю
                            else if (clientInput.charAt(0) == '@'
                                    && Character.isDigit(clientInput.charAt(1))
                                    && users.containsKey(Long.parseLong(clientInput.substring(1, 2)))) {
                                long destinationId = Long.parseLong(clientInput.substring(1, 2));
                                User destinationClient = users.get(destinationId);
                                destinationClient.getOutput().println(clientInput);
                            }

                            // Проверка на возможное исключение пользователя Админом
                            else if (clientInput.startsWith("@kick")
                                    && clientInput.length() == 7
                                    && user.getRole() == Role.ADMIN) {

                                char destinationUser = clientInput.charAt(clientInput.length() - 1);

                                if (Long.parseLong(String.valueOf(destinationUser)) != userId
                                        && Character.isDigit(destinationUser)
                                        && users.containsKey(Long.parseLong(String.valueOf(destinationUser)))) {
                                    long destinationId = Long.parseLong(String.valueOf(destinationUser));
                                    User destinationClient = users.get(destinationId);
                                    destinationClient.getOutput().println("kicked");
                                    users.remove(destinationId);
                                    System.out.println("Пользователь [" + destinationClient + "] исключен Админом. " +
                                            "Список всех пользователей: " + users);
                                    clientSockets.get(destinationId).close();
                                    clientSockets.remove(destinationId);
                                } else {
                                    System.out.println(user + ": " + clientInput);
                                }
                            }

                            // В остальных случаях
                            else {
                                System.out.println(user + ": " + clientInput);
                            }
                        }
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
                clientThread.start();

                clientSockets.put(userId, clientSocket);
            }
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}