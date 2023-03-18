package projeto.integrador.equipe1.carrosluxo.Dto.input.user;

public class InputLoginDto {
    private String email;
    private String password;

    public InputLoginDto(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public InputLoginDto() {
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
}
