package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.image.InputImageDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.ImageValidation;

public class ImageValidationTest {
    @Test
    void validationTextValid() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationText("Olá Mundo!", 2, 25);
        Assertions.assertNull(error);
    }

    @Test
    void validationTextEmpty() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationText("", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo não pode está vazio!", error);
    }

    @Test
    void validationTextCharMin() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationText("O", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser maior do que 2 caractreres!", error);
    }

    @Test
    void validationTextCharMax() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationText("Olá mundo, sou texto muito grande!", 2, 25);
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este campo dever ser menor do que 25 caractreres!", error);
    }

    @Test
    void validationUrlValid() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationUrl("http://img.carlux.com/teste.png");
        Assertions.assertNull(error);
    }

    @Test
    void validationUrlEmpty() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationUrl("");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("A url não pode ser vazio!", error);
    }

    @Test
    void validationUrlMax() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationUrl("http://img.carlux.com/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste/teste.png");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("A url dever ter menor do que 255 caracteres!", error);
    }

    @Test
    void validationUrlInvalid() {
        ImageValidation imageValidation = new ImageValidation();
        String error = imageValidation.validationUrl("http//img.carlux.com/teste.png");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Esta url é invalido!", error);
    }

    @Test
    void isValidUrlValid() {
        ImageValidation imageValidation = new ImageValidation();
        Boolean error = imageValidation.isValidUrl("http://img.carlux.com/teste.png");
        Assertions.assertTrue(error);
    }

    @Test
    void isValidUrlInvalid() {
        ImageValidation imageValidation = new ImageValidation();
        Boolean error = imageValidation.isValidUrl("http//img.carlux.com/teste.png");
        Assertions.assertFalse(error);
    }

    @Test
    void loginInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            new ImageValidation(new InputImageDto("", "", 0));
        });
    }
}
