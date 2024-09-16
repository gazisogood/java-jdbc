package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class User {
    private final Long id;
    private final String login;
    private final String password;
    private List<Room> createdRooms = new ArrayList<>();
    private List<Room> rooms = new ArrayList<>();

    public User(Long id, String login, String password, List<Room> createdRooms, List<Room> socializedRooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.rooms = socializedRooms;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public List<Room> getCreatedRooms() {
        return createdRooms;
    }

    public List<Room> getSocializedRooms() {
        return rooms;
    }


    @Override
    public String toString() {
        return "{id=" + this.id
                + ",login=" + this.login
                + ",password=" + this.password
                + ",createdRooms=" + (this.createdRooms != null ? this.createdRooms.size() : 0)
                + ",rooms=" + (this.rooms != null ? this.rooms.size() : 0) + "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof User)) return false;
        final User other = (User) o;
        if (!Objects.equals(this.id, other.id)) return false;
        if (!Objects.equals(this.login, other.login)) return false;
        if (!Objects.equals(this.password, other.password)) return false;
        if (!Objects.equals(this.createdRooms, other.createdRooms)) return false;
        if (!Objects.equals(this.rooms, other.rooms)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object id = this.id;
        result = result * PRIME + (id == null ? 43 : id.hashCode());
        final Object login = this.login;
        result = result * PRIME + (login == null ? 43 : login.hashCode());
        final Object password = this.password;
        result = result * PRIME + (password == null ? 43 : password.hashCode());
        final Object createdRooms = this.createdRooms;
        result = result * PRIME + (createdRooms == null ? 43 : createdRooms.hashCode());
        final Object rooms = this.rooms;
        result = result * PRIME + (rooms == null ? 43 : rooms.hashCode());
        return result;
    }
}
