package edu.school21.chat.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Room {
    private Long id;
    private String name;
    private User creator;
    private List<Message> messages = new ArrayList<>();

    public Room(Long id, String name, User owner, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.creator = owner;
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getOwner() {
        return creator;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public String toString() {
        return "{id=" + this.id
                + ",name=" + this.name
                + ",creator=" + this.creator
                + ",messages=" + (this.messages != null ? this.messages.size() : 0) + "}";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Room)) return false;
        final Room other = (Room) o;
        if (!Objects.equals(this.id, other.id)) return false;
        if (!Objects.equals(this.name, other.name)) return false;
        if (!Objects.equals(this.creator, other.creator)) return false;
        if (!((Object) this.messages).equals(other.messages)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object id = this.id;
        result = result * PRIME + (id == null ? 43 : id.hashCode());
        final Object name = this.name;
        result = result * PRIME + (name == null ? 43 : name.hashCode());
        final Object creator = this.creator;
        result = result * PRIME + (creator == null ? 43 : creator.hashCode());
        final Object messages = this.messages;
        result = result * PRIME + messages.hashCode();
        return result;
    }
}
