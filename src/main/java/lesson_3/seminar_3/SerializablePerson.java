package lesson_3.seminar_3;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class SerializablePerson implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;
    private final String name;
    private final int age;

    private final List<String> tags;
    private transient final Department department;

    public SerializablePerson(String name, int age, Department department, List<String> tags) {
        this.name = name;
        this.age = age;
        this.tags = tags;
        this.department = department;
    }

    public SerializablePerson(String name, int age, Department department) {
        this(name, age, department, List.of());
    }

    public SerializablePerson(String name) {
        this(name, 10, null, List.of());
    }

    public String getName() {
        return name;
    }

    public List<String> getTags() {
        return tags;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "SerializablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", department=" + department +
                ", tags=" + tags +
                '}';
    }
}
