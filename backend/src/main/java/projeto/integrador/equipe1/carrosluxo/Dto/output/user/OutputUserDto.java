package projeto.integrador.equipe1.carrosluxo.Dto.output.user;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import projeto.integrador.equipe1.carrosluxo.Controller.UserController;
import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserRoleEntity;
import projeto.integrador.equipe1.carrosluxo.Repository.UserRolesRepository;

public class OutputUserDto {
    Logger logger = LoggerFactory.getLogger(OutputUserDto.class);
    private long id;
    private String firstName;
    private String surname;
    private String email;
    private String role;

    public OutputUserDto() {
    }

    public OutputUserDto(long id, String firstName, String surname, String email, String role) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.role = role;
    }

    public OutputUserDto(UserEntity user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
        switch (user.getRole().getRoleName()) {
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
}
