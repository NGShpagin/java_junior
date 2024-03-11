package seminar_2;

import lesson_2.seminar_2.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PersonTest {

    @BeforeEach
    void beforeEachTest() {

    }

    @AfterEach
    void afterEachTest() {

    }

    @Test
    void testToString() {
        Person person = new Person("Igor", 20);

        Assertions.assertEquals(person.toString(), "Igor - [20]");
    }
}
