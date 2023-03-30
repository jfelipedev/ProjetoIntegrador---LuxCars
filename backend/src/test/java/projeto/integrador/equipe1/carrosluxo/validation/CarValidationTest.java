package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarCaracteristicDTO;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.CarValidation;

import java.util.HashSet;

public class CarValidationTest {
    @Test
    void validationTextValid() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationText("Olá Mundo!", 2, 25);
        Assertions.assertNull(error);
    }

    @Test
    void validationTextEmpty() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationText("", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo não pode está vazio!", error);
    }

    @Test
    void validationTextCharMin() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationText("O", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser maior do que 2 caractreres!", error);
    }

    @Test
    void validationTextCharMax() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationText("Olá Mundo, eu sou texto muito grande!", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser menor do que 25 caractreres!", error);
    }

    @Test
    void validationPriceValid() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationPrice(200.0);
        Assertions.assertNull(error);
    }

    @Test
    void validationPriceNull() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationPrice(null);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo não pode está vazio!", error);
    }

    @Test
    void validationPriceMin() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationPrice(199.99);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Aviso: o valor do preço deve ser maior ou igual a R$200 para ser válido!", error);
    }

    @Test
    void validationPriceMax() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationPrice(10000000000.0);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Aviso: o valor do preço deve ser menor ou igual a R$9.999.999.999,99 para ser válido!", error);
    }

    @Test
    void validationYearValid() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationYear(1900);
        Assertions.assertNull(error);
    }

    @Test
    void validationYearNull() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationYear(null);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo não pode está vazio!", error);
    }

    @Test
    void validationYearMin() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationYear(1899);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Aviso: o valor do ano deve ser maior ou igual a 1900 para ser válido!", error);
    }

    @Test
    void validationYearMax() {
        CarValidation carValidation = new CarValidation();
        String error = carValidation.validationYear(carValidation.getAnoAtual() + 1);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Aviso: o valor do ano deve ser menor ou igual a 2023 para ser válido!", error);
    }

    @Test
    void carInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            new CarValidation(new InputCarDto("d", "d", 20.0, 1850, Boolean.TRUE, 1, 1, list));
        });
    }

    @Test
    void carValid() {
        Assertions.assertDoesNotThrow(() -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            new CarValidation(new InputCarDto("Ferrati GT 300", "Um carro conversivel", 1500.0, 2011, Boolean.TRUE, 1, 1, list));
        });
    }
}
