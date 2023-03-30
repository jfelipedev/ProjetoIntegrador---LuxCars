package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.CategoryValidation;

public class CategoryValidationTest {
    @Test
    void validationTextValid() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationText("Olá Mundo!", 2, 25);
        Assertions.assertNull(error);
    }

    @Test
    void validationTextEmpty() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationText("", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo não pode está vazio!", error);
    }

    @Test
    void validationTextCharMin() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationText("O", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser maior do que 2 caractreres!", error);
    }

    @Test
    void validationTextCharMax() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationText("Olá Mundo, eu sou texto muito grande!", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser menor do que 25 caractreres!", error);
    }

    @Test
    void categoryInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            new CategoryValidation(new InputCategoryDto("", "Carro"));
        });
    }

    @Test
    void categoryValid() {
        Assertions.assertDoesNotThrow(() -> {
            new CategoryValidation(new InputCategoryDto("Carros conversiveis", "Canversivel"));
        });
    }
}
