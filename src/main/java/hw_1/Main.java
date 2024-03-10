package hw_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Stream;

/**
 * 0.1. Посмотреть разные статьи на Хабр.ру про Stream API
 * 0.2. Посмотреть видеоролики на YouTube.com Тагира Валеева про Stream API
 * <p>
 * 1. Создать список из 1_000 рандомных чисел от 1 до 1_000_000
 * 1.1 Найти максимальное
 * 2.2 Все числа, большие, чем 500_000, умножить на 5, отнять от них 150 и просуммировать
 * 2.3 Найти количество чисел, квадрат которых меньше, чем 100_000
 * <p>
 * 2. Создать класс Employee (Сотрудник) с полями: String name, int age, double salary, String department
 * 2.1 Создать список из 10-20 сотрудников
 * 2.2 Вывести список всех различных отделов (department) по списку сотрудников
 * 2.3 Всем сотрудникам, чья зарплата меньше 10_000, повысить зарплату на 20%
 * 2.4 * Из списка сотрудников с помощью стрима создать Map<String, List<Employee>> с отделами и сотрудниками внутри отдела
 * 2.5 * Из списока сорудников с помощью стрима создать Map<String, Double> с отделами и средней зарплатой внутри отдела
 */

public class Main {
    public static void main(String[] args) {

        // Задание 1
        List<Integer> numbers = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1, 1_000))
                .limit(10)
                .toList();
        System.out.println(numbers);
        System.out.println();
        Integer maxNum = numbers.stream().max(Integer::compare).get();
        System.out.println("Max number = " + maxNum);
        System.out.println();
        int listSum = numbers.stream()
                .filter(number -> number > 500)
                .map(number -> (number * 5) - 150)
                .peek(System.out::println)
                .mapToInt(Integer::intValue).sum();
        System.out.println("Сумма всех чисел, больше, чем 500_000, умноженные на 5 и уменьшены на 150: " + listSum);
        System.out.println();
        long numCounter = numbers.stream()
                .filter(number -> Math.pow(number, 2) > 100_000)
                .peek(System.out::println)
                .count();
        System.out.println("Количество чисел, квадрат которых меньше, чем 100_000: " + numCounter);


        // Задание 2
        Map<Integer, String> departments = Map.of(
                1, "Supplies",
                2, "Quality",
                3, "Financial");
        List<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            employees.add(
                    new Employee(String.format("Employee %s", i + 1),
                            new Random().nextInt(25, 50),
                            new Random().nextInt(5_000, 15_000),
                            departments.get(new Random().nextInt(1, 3))));
        }

        employees.forEach(System.out::println);
        System.out.println();
        employees.stream()
                .filter(employee -> employee.getSalary() < 10_000)
                .peek(employee -> employee.setSalary(Math.round(employee.getSalary() * 1.2)))
                .forEach(System.out::println);
        System.out.println();
        employees.forEach(System.out::println);
    }
}
