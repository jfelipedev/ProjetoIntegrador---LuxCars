package projeto.integrador.equipe1.carrosluxo.Entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID_user")
    private int id;
    @Column(name = "first_name")
    private String firstName;
    private String surname;
    private String email;
    private String password;

    @Column(name = "data_subscribe", columnDefinition = "TIMESTAMP")
    private LocalDateTime dataSubscribe;

    public UserEntity() {
    }

    public UserEntity(int id, String firstName, String surname, String email, String password, LocalDateTime dataSubscribe) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
        this.dataSubscribe = dataSubscribe;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDataSubscribe() {
        return dataSubscribe;
    }

    public void setDataSubscribe(LocalDateTime dataSubscribe) {
        this.dataSubscribe = dataSubscribe;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", dataSubscribe=" + dataSubscribe +
                '}';
    }
}
