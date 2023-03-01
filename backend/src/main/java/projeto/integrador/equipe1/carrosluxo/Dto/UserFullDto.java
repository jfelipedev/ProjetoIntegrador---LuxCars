package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

public class UserFullDto {
    private long id;
    private String firstName;
    private String surname;
    private String email;
    private String role;

    public UserFullDto() {
    }

    public UserFullDto(long id, String firstName, String surname, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public UserFullDto(UserEntity userEntity) {
        this.id = userEntity.getId();
        this.firstName = userEntity.getFirstName();
        this.surname = userEntity.getEmail();
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

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "UserFullDto{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
