package com.devunited.examenfinalprog4.Model;

import com.devunited.examenfinalprog4.model.Users;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserModelTest {
    @InjectMocks
    private Users users;

    @Test
    public void testGetterAndSetter() {
        int id = 17;
        String name = "Veronica Lodge";
        String username = "veronica";
        LocalDate birthday = LocalDate.of(1997, 11, 18);
        String email = "lodgevr@example.com";
        String password = "veronica1032";

        users.setId(id);
        users.setName(name);
        users.setUsername(username);
        users.setBirthday(birthday);
        users.setEmail(email);
        users.setPassword(password);

        assertEquals(id, users.getId());
        assertEquals(name, users.getName());
        assertEquals(username, users.getUsername());
        assertEquals(birthday, users.getBirthday());
        assertEquals(email, users.getEmail());
        assertEquals(password, users.getPassword());
    }

    @Test
    public void testToString() {
        int id = 17;
        String name = "Veronica Lodge";
        String username = "veronica";
        LocalDate birthday = LocalDate.of(1997, 11, 18);
        String email = "lodgevr@example.com";
        String password = "veronica1032";

        users = new Users(id, name, username, birthday, email, password);

        String expectedToString = "Users(id=17, name=Veronica Lodge, username=veronica, " +
                "birthday=1997-11-18, email=lodgevr@example.com, password=veronica1032)";

        assertEquals(expectedToString, users.toString());
    }

    @Test
    public void testAllArgsConstructor() {
        int id = 17;
        String name = "Veronica Lodge";
        String username = "veronica";
        LocalDate birthday = LocalDate.of(1997, 11, 18);
        String email = "lodgevr@example.com";
        String password = "veronica1032";

        users = new Users(id, name, username, birthday, email, password);

        assertEquals(id, users.getId());
        assertEquals(name, users.getName());
        assertEquals(username, users.getUsername());
        assertEquals(birthday, users.getBirthday());
        assertEquals(email, users.getEmail());
        assertEquals(password, users.getPassword());
    }
}

