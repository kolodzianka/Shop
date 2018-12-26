package pl.kolodzianka.jsonUtils;

import pl.kolodzianka.entities.User;

import java.util.List;

public class JsonUserLists {

    List<User> users;

    public JsonUserLists(List<User> users) {
        this.users = users;
    }

    public JsonUserLists() {
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "JsonUserLists{" +
                "users=" + users +
                '}';
    }
}
