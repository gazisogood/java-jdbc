package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.dao.implementations.MessagesRepositoryJdbcImpl;
import edu.school21.chat.models.Message;

import javax.sql.DataSource;
import java.util.Optional;

public class Program {
    public static void main(String[] args) {

        HikariConfig config = new HikariConfig("/config.properties");
        DataSource dataSource = new HikariDataSource(config);
        MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);

        Optional<Message> messageOptional = messagesRepository.findById(7L);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setText("Bye");
            message.setDateTime(null);
            messagesRepository.update(message);
        }
    }
}
