package projeto.integrador.equipe1.carrosluxo.Dto;

import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.UserRoles;

public class RegisterFullDto {
    private UserFullDto user;
    private String jwt;

    public RegisterFullDto() {
    }

    public RegisterFullDto(UserEntity user, String jwt) {
        this.user = new UserFullDto(user);
        this.jwt = jwt;
    }

    public UserFullDto getUser() {
        return user;
    }

    public void setUser(UserFullDto user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }

    @Override
    public String toString() {
        return "RegisterFullDto{" +
                "user=" + user +
                ", jwt='" + jwt + '\'' +
                '}';
    }
}
