package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

import java.time.LocalDateTime;

public class UserDto {
    private String firstName;
    private String surname;
    private String email;
    private LocalDateTime dataSubscribe;

    public UserDto() {
    }

    public UserDto(String firstName, String surname, String email, LocalDateTime dataSubscribe) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.dataSubscribe = dataSubscribe;
    }

    public UserDto(UserEntity userEntity){
        this.firstName = userEntity.getFirstName();
        this.surname = userEntity.getEmail();
        this.email = userEntity.getEmail();
        this.dataSubscribe = userEntity.getDataSubscribe();
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

    public LocalDateTime getDataSubscribe() {
        return dataSubscribe;
    }

    public void setDataSubscribe(LocalDateTime dataSubscribe) {
        this.dataSubscribe = dataSubscribe;
    }
}
