package edu.school21.chat.models;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Message {
    private Long id;
    private User author;
    private Room room;
    private String text;
    private LocalDateTime dateTime;

    public Message(Long id, User author, Room room, String text, LocalDateTime dateTime) {
        this.id = id;
        this.author = author;
        this.room = room;
        this.text = text;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getAuthor() {
        return author;
    }

    public Room getRoom() {
        return room;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Message : {\n\tid=" + this.id
                + ",\n\tauthor=" + this.author
                + ",\n\troom=" + this.room
                + ",\n\ttext=\"" + this.text + "\""
                + ",\n\tdateTime=" + this.dateTime.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")) + "\n}";
    }

    @Override
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Message)) return false;
        final Message other = (Message) o;
        if (!Objects.equals(this.id, other.id)) return false;
        if (!Objects.equals(this.author, other.author)) return false;
        if (!Objects.equals(this.room, other.room)) return false;
        if (!Objects.equals(this.text, other.text)) return false;
        if (!Objects.equals(this.dateTime, other.dateTime)) return false;
        return true;
    }

    @Override
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object id = this.id;
        result = result * PRIME + (id == null ? 43 : id.hashCode());
        final Object author = this.author;
        result = result * PRIME + (author == null ? 43 : author.hashCode());
        final Object room = this.room;
        result = result * PRIME + (room == null ? 43 : room.hashCode());
        final Object text = this.text;
        result = result * PRIME + (text == null ? 43 : text.hashCode());
        final Object dateTime = this.dateTime;
        result = result * PRIME + (dateTime == null ? 43 : dateTime.hashCode());
        return result;
    }
}
