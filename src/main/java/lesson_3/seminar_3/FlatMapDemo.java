package lesson_3.seminar_3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class FlatMapDemo {
    public static void main(String[] args) {
        List<String> langs = List.of("java", "c#", "python", "c++", "kotlin");

        List<Integer> list = langs.stream()
                .flatMap(lang -> lang.chars().boxed())
                .toList();

        List<Department> departments = new ArrayList<>();

        List<Employee> employeeList = departments.stream()
                .map(Department::getEmployees)
                .flatMap(Collection::stream)
                .toList();
    }

    static class Department {
        private List<Employee> employees;

        public List<Employee> getEmployees() {
            return employees;
        }
    }

    static class Employee {

    }

}
