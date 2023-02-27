package projeto.integrador.equipe1.carrosluxo.Dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserRoles;

public class GetMeUserDto {
    private String firstName;
    private String surname;
    private String email;
    private String role;

    public GetMeUserDto(String firstName, String surname, String email, String role) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public GetMeUserDto(UserEntity userEntity) {
        this.firstName = userEntity.getFirstName();
        this.surname = userEntity.getSurname();
        this.email = userEntity.getEmail();
        switch (userEntity.getRoles()){
            case ROLE_USER:
                this.role = "Usuário";
                break;
            case ROLE_ADMIN:
                this.role = "Administrador";
                break;
            default:
                this.role = "Cargo Desconhecido";
        }
    }

    public GetMeUserDto() {
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

    public String getRoles() {
        return role;
    }

    public void setRoles(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "GetMeUserDto{" +
                "firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
