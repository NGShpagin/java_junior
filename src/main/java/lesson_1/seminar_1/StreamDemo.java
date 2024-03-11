package lesson_1.seminar_1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {
    public static void main(String[] args) {
        List<Integer> numbers = Stream.generate(() -> ThreadLocalRandom.current().nextInt(1000))
                .limit(100)
                .toList();
        System.out.println(numbers);

        List<Product> products = generateRandomProducts();

        // Найти и распечатать все товары из категории "Электроника" дешевле 20_000 рублей
        products.stream()
                .filter(it -> it.getCategory().equals("Электроника"))
                .filter(it -> it.getCost() < 20_000)
                .forEach(System.out::println);

        // Всем товарам из категории "Продукты" повысить стоимость на 5%
        products.stream()
                .filter(it -> it.getCategory().equals("Продукты"))
                .peek(it -> it.setCost(it.getCost() * 1.05))
                .forEach(System.out::println);

        // Все продукты, начинающиеся на М и собрать их в список
        List<Product> productsOnM = products.stream()
                .filter(it -> it.getName().startsWith("М"))
                .toList();
//                .collect(Collectors.toCollection(ArrayList::new));

        System.out.println(productsOnM);

        System.out.println();
        Optional<Product> product = products.stream()
                .filter(it -> it.getName().startsWith("М"))
                .findFirst();

        product.ifPresent(System.out::println);
        product.ifPresentOrElse(System.out::println, () -> System.out.println("Объекта нет"));


    }

    private static List<Product> generateRandomProducts() {
        return List.of(
                new Product("Молок", 70, "Продукты"),
                new Product("Мясо", 130, "Продукты"),
                new Product("Хлеб", 30, "Продукты"),

                new Product("Компьютер", 70_000, "Электроника"),
                new Product("Наушники", 15_000, "Электроника"),

                new Product("Шампунь", 350, "Косметика"),

                new Product("Диван", 24_000, "Мебель")
        );
    }

    static class Product {
        private final String name;
        private double cost;
        private String category;

        public Product(String name, double cost, String category) {
            this.name = name;
            this.cost = cost;
            this.category = category;
        }

        public String getName() {
            return name;
        }

        public double getCost() {
            return cost;
        }

        public String getCategory() {
            return category;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        @Override
        public String toString() {
            return String.format("[%s] (cost = %f, category = %s)", name, cost, category);
        }
    }
}
