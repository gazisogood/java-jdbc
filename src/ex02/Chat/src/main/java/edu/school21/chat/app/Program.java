package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.dao.implementations.MessagesRepositoryJdbcImpl;
import edu.school21.chat.models.Room;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Program {
    public static void main(String[] args) {

        User creator = new User(1L, "arboriob", "arboriob", new ArrayList<>(), new ArrayList<>());
        User author = creator;
        Room room = new Room(1L, "genom", creator, new ArrayList<>());
        Message message = new Message(null, author, room, "Hello", LocalDateTime.now());

        HikariConfig config = new HikariConfig("/config.properties");
        DataSource dataSource = new HikariDataSource(config);

        MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        messagesRepository.save(message);
        System.out.println(message.getId());
    }
}
