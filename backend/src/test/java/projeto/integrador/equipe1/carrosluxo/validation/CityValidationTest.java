package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.CityValidation;

public class CityValidationTest {
    @Test
    void validationTextValid() {
        CityValidation cityValidation = new CityValidation();
        String error = cityValidation.validationText("Olá Mundo!", 2L, 25L);
        Assertions.assertNull(error);
    }

    @Test
    void validationTextEmpty() {
        CityValidation cityValidation = new CityValidation();
        String error = cityValidation.validationText("", 2L, 25L);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo não pode está vazio!", error);
    }

    @Test
    void validationTextCharMin() {
        CityValidation cityValidation = new CityValidation();
        String error = cityValidation.validationText("O", 2L, 25L);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser maior do que 2 caractreres!", error);
    }

    @Test
    void validationTextCharMax() {
        CityValidation cityValidation = new CityValidation();
        String error = cityValidation.validationText("Olá Mundo, eu sou texto muito grande!", 2L, 25L);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser menor do que 25 caractreres!", error);
    }

    @Test
    void cityInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            new CityValidation(new InputCityDto("", "Brasil"));
        });
    }

    @Test
    void cityValid() {
        Assertions.assertDoesNotThrow(() -> {
            new CityValidation(new InputCityDto("Rio de janeiro", "Brasil"));
        });
    }
}
