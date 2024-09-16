package edu.school21.chat.dao.implementations;

import edu.school21.chat.interfaces.MessagesRepository;
import edu.school21.chat.exceptions.NotSavedSubEntityException;
import edu.school21.chat.models.Room;
import edu.school21.chat.models.Message;
import edu.school21.chat.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Optional;

public class MessagesRepositoryJdbcImpl implements MessagesRepository {
    private final DataSource dataSource;

    public MessagesRepositoryJdbcImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Optional<Message> findById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("Message ID cannot be null.");
        }
        Optional<Message> result = Optional.empty();
        try (Connection connection = dataSource.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT m.id as message_id, m.text, m.date_time, " +
                     "u.id as user_id, u.login, u.password, " +
                     "r.id as chatroom_id, r.name as chatroom_name " +
                     "FROM message m " +
                     "JOIN users u ON m.author = u.id " +
                     "JOIN room r ON m.room = r.id " +
                     "WHERE m.id = ?")
        ) {
            preparedStatement.setLong(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    User author = new User(
                            resultSet.getLong("user_id"),
                            resultSet.getString("login"),
                            resultSet.getString("password"),
                            null,
                            null
                    );
                    Room chatroom = new Room(
                            resultSet.getLong("chatroom_id"),
                            resultSet.getString("chatroom_name"),
                            null,
                            null
                    );

                    LocalDateTime dateTime = resultSet.getTimestamp("date_time").toLocalDateTime();
                    result = Optional.of(new Message(
                            resultSet.getLong("message_id"),
                            author,
                            chatroom,
                            resultSet.getString("text"),
                            dateTime
                    ));
                }
            }
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
        return result;
    }

    @Override
    public void save(Message message) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO message(author, room, text) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, message.getAuthor().getId());
            statement.setLong(2, message.getRoom().getId());
            statement.setString(3, message.getText());
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new NotSavedSubEntityException("Data not inserted.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    message.setId(generatedKeys.getLong(1));
                } else {
                    throw new NotSavedSubEntityException("Failed to save message, no ID obtained.");
                }
            }
        } catch (SQLException e) {
            throw new NotSavedSubEntityException("Connection failed: " + e);
        }
    }
}
