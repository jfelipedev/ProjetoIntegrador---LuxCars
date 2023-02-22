package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.CustomerEntity;

import java.time.LocalDateTime;

public class CustomerDto {
    private String firstName;
    private String surname;
    private String email;

    public CustomerDto() {
    }

    public CustomerDto(String firstName, String surname, String email) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    public CustomerDto(CustomerEntity customerEntity){
        this.firstName = customerEntity.getFirstName();
        this.surname = customerEntity.getEmail();
        this.email = customerEntity.getEmail();
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
