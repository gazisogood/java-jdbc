package edu.school21.chat.app;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.school21.chat.dao.implementations.UsersRepositoryJdbcImpl;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.util.List;

public class Program {
    public static void main(String[] args) {

        HikariConfig config = new HikariConfig("/config.properties");
        DataSource dataSource = new HikariDataSource(config);
        UsersRepositoryJdbcImpl usersRepository = new UsersRepositoryJdbcImpl(dataSource);

        List<User> usersList;
        usersList = usersRepository.findAll(1, 2);
        for (User user : usersList) {
            System.out.println(user.toString());
        }
    }
}
