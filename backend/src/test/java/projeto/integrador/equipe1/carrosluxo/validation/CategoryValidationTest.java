package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.image.InputImageDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.CarValidation;
import projeto.integrador.equipe1.carrosluxo.Validation.CategoryValidation;
import projeto.integrador.equipe1.carrosluxo.Validation.ImageValidation;

import java.util.HashSet;

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
    void validationUrlValid() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationUrl("http://img.carlux.com/teste.png");
        Assertions.assertNull(error);
    }

    @Test
    void validationUrlEmpty() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationUrl("");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("A url não pode ser vazio!", error);
    }

    @Test
    void validationUrlMax() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationUrl("http://img.carlux.com/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste.png");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("A url dever ter menor do que 255 caracteres!", error);
    }

    @Test
    void validationUrlInvalid() {
        CategoryValidation categoryValidation = new CategoryValidation();
        String error = categoryValidation.validationUrl("http//img.carlux.com/teste.png");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Esta url é invalido!", error);
    }

    @Test
    void isValidUrlValid() {
        CategoryValidation categoryValidation = new CategoryValidation();
        Boolean error = categoryValidation.isValidUrl("http://img.carlux.com/teste.png");
        Assertions.assertTrue(error);
    }

    @Test
    void isValidUrlInvalid() {
        CategoryValidation categoryValidation = new CategoryValidation();
        Boolean error = categoryValidation.isValidUrl("http//img.carlux.com/teste.png");
        Assertions.assertFalse(error);
    }

    @Test
    void categoryInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            new CategoryValidation(new InputCategoryDto("", "", "Carro"));
        });
    }

    @Test
    void categoryValid() {
        Assertions.assertDoesNotThrow( () -> {
            new CategoryValidation(new InputCategoryDto("Carros conversiveis", "http://img.carlux.com/category/conversivel.png", "Canversivel"));
        });
    }
}
