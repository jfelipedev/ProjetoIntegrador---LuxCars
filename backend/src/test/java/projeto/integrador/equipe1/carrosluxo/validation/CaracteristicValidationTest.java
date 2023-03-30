package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.CaracteristicValidation;

public class CaracteristicValidationTest {
    @Test
    void validationTextValid() {
        CaracteristicValidation caracteristicValidation = new CaracteristicValidation();
        String error = caracteristicValidation.validationText("Ola Mundo", 2, 25, "^[A-Za-z ]{1,}$");
        Assertions.assertNull(error);
    }

    @Test
    void validationTextEmpty() {
        CaracteristicValidation caracteristicValidation = new CaracteristicValidation();
        String error = caracteristicValidation.validationText("", 2, 25, "");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo não pode está vazio!", error);
    }

    @Test
    void validationTextCharMin() {
        CaracteristicValidation caracteristicValidation = new CaracteristicValidation();
        String error = caracteristicValidation.validationText("Ola", 5, 25, "");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser maior do que 5 caractreres!", error);
    }

    @Test
    void validationTextCharMax() {
        CaracteristicValidation caracteristicValidation = new CaracteristicValidation();
        String error = caracteristicValidation.validationText("ola mundo", 2, 5, "");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser menor do que 5 caractreres!", error);
    }

    @Test
    void validationTextCharAllowed() {
        CaracteristicValidation caracteristicValidation = new CaracteristicValidation();
        String error = caracteristicValidation.validationText("Olá mundo!", 2, 25, "^[a-z]{1,}$");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo contém caracteres invalidos!", error);
    }

    @Test
    void isValidValid() {
        CaracteristicValidation caracteristicValidation = new CaracteristicValidation();
        Boolean error = caracteristicValidation.isValid("teste", "^[a-z]{1,}$");
        Assertions.assertTrue(error);
    }

    @Test
    void isValidInvalid() {
        CaracteristicValidation caracteristicValidation = new CaracteristicValidation();
        Boolean error = caracteristicValidation.isValid("teste teste", "^[a-z]{1,}$");
        Assertions.assertFalse(error);
    }

    @Test
    void caracteristicInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            new CaracteristicValidation(new InputCaracteristicDto("Teste", "teste icon"));
        });
    }

    @Test
    void caracteristicValid() {
        Assertions.assertDoesNotThrow(() -> {
            new CaracteristicValidation(new InputCaracteristicDto("Teste", "m/s"));
        });
    }
}
