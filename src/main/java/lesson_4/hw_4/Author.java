package lesson_4.hw_4;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "author")
@Data // setters, getters, toString
@NoArgsConstructor
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name", nullable = false)
    private String name;

//    @OneToMany(cascade = CascadeType.ALL)
//    private List<Book> books;

    public Author(String name) {
        this.name = name;
    }
}
