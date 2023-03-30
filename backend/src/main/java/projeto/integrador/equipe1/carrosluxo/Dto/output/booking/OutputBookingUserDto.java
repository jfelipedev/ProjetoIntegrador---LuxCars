package projeto.integrador.equipe1.carrosluxo.Dto.output.booking;

import projeto.integrador.equipe1.carrosluxo.Entity.UserEntity;

public class OutputBookingUserDto {
    private long id;
    private String firstName;
    private String surname;
    private String email;

    public OutputBookingUserDto(long id, String firstName, String surname, String email) {
        this.id = id;
        this.firstName = firstName;
        this.surname = surname;
        this.email = email;
    }

    public OutputBookingUserDto() {
    }

    public OutputBookingUserDto(UserEntity user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.surname = user.getSurname();
        this.email = user.getEmail();
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
}
