package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorContactUsMessageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.InputContactUsMessageDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ContactUsMessageValidation {
    private static final int emailCharacterMaximum = 200;
    private static final String emailAllowed = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";
    public ContactUsMessageValidation() {
    }

    public ContactUsMessageValidation(InputContactUsMessageDto contactUsMessage) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorEmail = validationEmail(contactUsMessage.getEmail());
        String errorTitle = validationText(contactUsMessage.getTitle(), 10, 300);
        String errorBody = validationText(contactUsMessage.getBody(), 50, 6000);
        if (!(errorEmail == null && errorBody == null && errorTitle == null)) {
            ErrorContactUsMessageDto error = new ErrorContactUsMessageDto(errorEmail, errorTitle, errorBody);
            throw new BadRequestException(objectMapper.writeValueAsString(error));
        }
    }

    public String validationText(String text, int textCharactersMinimum, int textCharactersMaximum) {
        if (text.trim().isBlank()) {
            return "Este campo não pode está vazio!";
        } else if (text.trim().length() < textCharactersMinimum) {
            return "Este campo dever ser maior do que " + textCharactersMinimum + " caractreres!";
        } else if (text.trim().length() > textCharactersMaximum) {
            return "Este campo dever ser menor do que " + textCharactersMaximum + " caractreres!";
        }
        return null;
    }

    public String validationEmail(String email) {
        if (email.trim().isBlank()) {
            return "O email não pode ser vazio!";
        } else if (!isValid(email, emailAllowed)) {
            return "Este email inserido é invalido!";
        } else if (email.trim().length() > emailCharacterMaximum) {
            return "O email não pode ser maior do que 200 caracteres!";
        }
        return null;
    }

    public boolean isValid(String text, String regexp) {
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
