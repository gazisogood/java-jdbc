package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.models.Message;
import edu.school21.chat.dao.implementations.MessagesRepositoryJdbcImpl;

import javax.sql.DataSource;
import java.util.Optional;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        System.out.println("Enter a message ID: ");
        Scanner scanner = new Scanner(System.in);
        Long id = scanner.nextLong();
        HikariConfig config = new HikariConfig("/config.properties");
        DataSource dataSource = new HikariDataSource(config);
        MessagesRepositoryJdbcImpl messagesRepository = new MessagesRepositoryJdbcImpl(dataSource);
        Optional<Message> messageOptional = messagesRepository.findById(id);
        if (messageOptional.isPresent()) {
            System.out.println(messageOptional.get());
        } else {
            System.out.println("Message not found");
        }
        scanner.close();
    }
}
