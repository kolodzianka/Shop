package pl.kolodzianka.entities;

import java.util.Objects;

public class User {

    private Long id;
    private String username;
    private String password;
    private Double totalCashSpend;
    private String name;
    private String surname;
    private String role;

    public User() {
    }

    public User(Long id, String username, String password, Double totalCashSpend, String name, String surname, String role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.totalCashSpend = totalCashSpend;
        this.name = name;
        this.surname = surname;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Double getTotalCashSpend() {
        return totalCashSpend;
    }

    public void setTotalCashSpend(Double totalCashSpend) {
        this.totalCashSpend = totalCashSpend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", totalCashSpend=" + totalCashSpend +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(username, user.username) &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {

        return Objects.hash(username, password);
    }
}
