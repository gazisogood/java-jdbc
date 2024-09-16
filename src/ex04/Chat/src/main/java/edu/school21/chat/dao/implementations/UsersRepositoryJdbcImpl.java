package edu.school21.chat.dao.implementations;

import edu.school21.chat.interfaces.UsersRepository;
import edu.school21.chat.models.Room;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersRepositoryJdbcImpl implements UsersRepository {
    private final DataSource dataSource;

    public UsersRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<User> findAll(int page, int size) {
        List<User> usersList = new ArrayList<>();
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement("WITH cte AS(" +
                    "SELECT room.id AS room_id, room.name AS room_name, room.creator, message.author\n" +
                    "FROM room\n" + "JOIN message ON room.id = message.room)" +
                    "SELECT users.id, users.login, users.password,\n" +
                    "CASE\n" +
                        "WHEN cte.author = users.id THEN 'author'\n" +
                        "WHEN cte.creator = users.id THEN 'creator'\n" +
                        "ELSE 'other'\n" +
                    "END AS status, cte.room_id, cte.room_name\n" +
                    "FROM users\n" +
                    "JOIN cte ON users.id = cte.creator OR users.id = cte.author\n" +
                    "LIMIT ? OFFSET ?;"
            );
            preparedStatement.setInt(1, size);
            preparedStatement.setInt(2, (page - 1) * size);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User(
                            resultSet.getLong("id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            new ArrayList<>(),
                            new ArrayList<>()
                    );
                    Room room = new Room(
                            resultSet.getLong("room_id"),
                            resultSet.getString("room_name"),
                            user,
                            new ArrayList<>()
                    );
                    if (resultSet.getString("status").equals("author")) {
                        user.addSocializedRooms(room);
                    } else {
                        user.addCreatedRooms(room);
                    }
                    usersList.add(user);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Connection failed: " + e.getMessage());
        }
        return usersList;
    }
}
