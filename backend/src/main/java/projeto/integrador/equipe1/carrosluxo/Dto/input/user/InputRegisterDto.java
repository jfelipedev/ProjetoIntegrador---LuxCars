package projeto.integrador.equipe1.carrosluxo.Dto.input.user;

public class InputRegisterDto {
    private String firstName;
    private String surname;
    private String email;
    private String password;

    public InputRegisterDto(String firstName, String surname, String email, String password) {
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
        this.password = password;
    }

    public InputRegisterDto() {
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
