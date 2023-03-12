package projeto.integrador.equipe1.carrosluxo.Dto.output.user;

import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

public class OutputRegisterDto {
    private OutputUserDto user;
    private String jwt;

    public OutputRegisterDto() {
    }

    public OutputRegisterDto(UserEntity user, String jwt) {
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
