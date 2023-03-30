package projeto.integrador.equipe1.carrosluxo.Dto.input.car;

public class InputCarCaracteristicDTO {
    Long id;
    String value;

    public InputCarCaracteristicDTO() {
    }

    public InputCarCaracteristicDTO(Long id, String value) {
        this.id = id;
        this.value = value;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
