package projeto.integrador.equipe1.carrosluxo.Dto.output;

import projeto.integrador.equipe1.carrosluxo.Entity.ContactUsMessageEntity;

public class OutputContactUsMessageDto {
    private long id;
    private String email;
    private String title;
    private String body;

    public OutputContactUsMessageDto() {
    }

    public OutputContactUsMessageDto(long id, String email, String title, String body) {
        this.id = id;
        this.email = email;
        this.title = title;
        this.body = body;
    }

    public OutputContactUsMessageDto(ContactUsMessageEntity message) {
        this.id = message.getId();
        this.email = message.getEmail();
        this.title = message.getTitle();
        this.body = message.getBody();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
