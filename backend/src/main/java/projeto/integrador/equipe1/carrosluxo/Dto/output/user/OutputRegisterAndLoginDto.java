package projeto.integrador.equipe1.carrosluxo.Dto.output.user;

import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

public class OutputRegisterAndLoginDto {
    private OutputUserDto user;
    private String jwt;

    public OutputRegisterAndLoginDto() {
    }

    public OutputRegisterAndLoginDto(UserEntity user, String jwt) {
        this.user = new OutputUserDto(user);
        this.jwt = jwt;
    }

    public OutputUserDto getUser() {
        return user;
    }

    public void setUser(OutputUserDto user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
