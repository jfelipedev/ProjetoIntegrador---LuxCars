package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputLoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.UserValidation;

public class UserValidationTest {

    @Test
    void validationNameValid() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationName("primeiro nome", "João da Silva");
        Assertions.assertNull(error);
    }

    @Test
    void validationNamecharactersEmpty() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationName("primeiro nome", "");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("O primeiro nome não pode está vazio!", error);
    }

    @Test
    void validationNameCharactersAllowed() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationName("primeiro nome", "123");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("O primeiro nome contém caracteres invalidos!", error);
    }

    @Test
    void validationNameCharactersMin() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationName("primeiro nome", "1");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("O primeiro nome dever ser maior do que 2 caractreres!", error);
    }

    @Test
    void validationNameCharactersMax() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationName("primeiro nome", "cjnvfvjfnvhfvhnfyvfnvyfnvygfnyvgnyfnvygfnyvjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjnyfnvyfnfvfbgrbhfbvhfdvbfhdbvfdfhvbfhdvbhfbdhvbfdhvb");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("O primeiro nome dever ser menor do que 100 caractreres!", error);
    }

    @Test
    void validationNameWordMin() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationName("primeiro nome", "João d");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("O primeiro nome nome contém palavras menor do que 2 caractres!", error);
    }

    @Test
    void validationPasswordValid() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationPassword("Password");
        Assertions.assertNull(error);
    }

    @Test
    void validationPasswordCharactersEmpty() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationPassword("");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("A senha não pode ser vazio!", error);
    }

    @Test
    void validationPasswordCharactersMin() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationPassword("Pass");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("A senha dever ter mais do que 8 caracteres!", error);
    }

    @Test
    void validationPasswordCharactersMax() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationPassword("PasswordPasswordPasswordPasswordPassword");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("A senha dever ter menor do que 20 caracteres!", error);
    }

    @Test
    void validationEmailValid() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationEmail("viniciusocker@mail.com");
        Assertions.assertNull(error);
    }

    @Test
    void validationEmailEmpty() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationEmail("");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("O email não pode ser vazio!", error);
    }

    @Test
    void validationEmailCharactersAllowed() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationEmail("viniciusockermail.com");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("Este email inserido é invalido!", error);
    }

    @Test
    void validationEmailCharactersMax() {
        UserValidation userValidation = new UserValidation();
        String error = userValidation.validationEmail("kfejgmuthmeuhtmrehgvmrvhumghrmhugmhremhgvuremgumvegrumvmg@tmhregvtsgfdgfdsgfdsghdsfhgdfgfdsgfdhgyfhdghfdhgydfshgyfdgshfdsgdfhgfdhgbfhdsgbhfdghfbdgrmgev.utreghvr.fgfdgfdgf.dfdgfdgfd.dfgfdsgfds.fdgdfgfdg.dfgfdgs.eghyrengvurhug.mail.com");
        Assertions.assertNotNull(error);
        Assertions.assertEquals("O email não pode ser maior do que 200 caracteres!", error);
    }

    @Test
    void validationLengthValid() {
        UserValidation userValidation = new UserValidation();
        Boolean error = userValidation.validationLength("teste tes".split(" "), 3L);
        Assertions.assertTrue(error);
    }

    @Test
    void validationLengthInvalid() {
        UserValidation userValidation = new UserValidation();
        Boolean error = userValidation.validationLength("teste ts".split(" "), 3L);
        Assertions.assertFalse(error);
    }

    @Test
    void isValidValid() {
        UserValidation userValidation = new UserValidation();
        Boolean error = userValidation.isValid("teste", "^[a-z]{1,}$");
        Assertions.assertTrue(error);
    }

    @Test
    void isValidInvalid() {
        UserValidation userValidation = new UserValidation();
        Boolean error = userValidation.isValid("teste9", "^[a-z]{1,}$");
        Assertions.assertFalse(error);
    }

    @Test
    void loginInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            new UserValidation(new InputLoginDto("d", "d"));
        });
    }

    @Test
    void loginValid() {
        Assertions.assertDoesNotThrow(() -> {
            new UserValidation(new InputLoginDto("user@mail.com", "12345678"));
        });
    }

    @Test
    void registerInvalid() {
        Assertions.assertThrows(BadRequestException.class, () -> {
            new UserValidation(new InputRegisterDto("d", "d", "d", "d"));
        });
    }

    @Test
    void RegisterValid() {
        Assertions.assertDoesNotThrow(() -> {
            new UserValidation(new InputRegisterDto("João", "da silva", "user@mail.com", "12345678"));
        });
    }
}
