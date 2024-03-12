package lesson_3.seminar_3;

import java.io.Serializable;

public class Department implements Serializable {

    private final String name;

    public Department(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "name='" + name + '\'' +
                '}';
    }
}
