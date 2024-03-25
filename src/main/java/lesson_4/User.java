package lesson_4;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
// Публичный конструктор без аргумента
// getters и setters для каждого поля
// У каждой сущности (entity) есть первичный ключ
public class User {
    public User() {}

    public User(Long id) {
        this.id = id;
    }

    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
