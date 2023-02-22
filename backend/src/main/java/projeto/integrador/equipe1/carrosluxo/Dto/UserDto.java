package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

public class UserDto {
    private String firstName;
    private String surname;
    private String email;

    public UserDto() {
    }

    public UserDto(String firstName, String surname, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    public UserDto(UserEntity userEntity) {
        this.firstName = userEntity.getFirstName();
        this.surname = userEntity.getEmail();
        this.email = userEntity.getEmail();
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

}
