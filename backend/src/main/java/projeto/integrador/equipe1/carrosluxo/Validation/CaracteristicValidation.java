package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CaracteristicValidation {
    private static final int nameCharactersMinimum = 2;
    private static final int nameCharactersMaximum = 255;
    private static final int unitCharactersMinimum = 1;
    private static final int unitCharactersMaximum = 10;
    private static final String unitCharactersAllowed = "^[A-Za-z/]{1,}$";

    public CaracteristicValidation() {
    }

    public CaracteristicValidation(InputCaracteristicDto caracteristic) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorName = validationText(caracteristic.getName(), nameCharactersMinimum, nameCharactersMaximum, "");
        String errorUnit = null;
        if(caracteristic.getUnitOfMeasurement() != null){
            errorUnit = validationText(caracteristic.getUnitOfMeasurement(), unitCharactersMinimum, unitCharactersMaximum, unitCharactersAllowed);
        }
        if (!(errorName == null && errorUnit == null)) {
            ErrorCaracteristicDto errorCaracteristicDto = new ErrorCaracteristicDto(errorName, errorUnit);
            throw new BadRequestException(objectMapper.writeValueAsString(errorCaracteristicDto));
        }
    }

    public String validationText(String text, int textCharactersMinimum, int textCharactersMaximum, String textCharactersAllowed) {
        if (text.trim().isBlank()) {
            return "Este campo não pode está vazio!";
        } else if (text.trim().length() < textCharactersMinimum) {
            return "Este campo dever ser maior do que " + textCharactersMinimum + " caractreres!";
        } else if (text.trim().length() > textCharactersMaximum) {
            return "Este campo dever ser menor do que " + textCharactersMaximum + " caractreres!";
        } else if (textCharactersAllowed.length() != 0) {
            if (!isValid(text, textCharactersAllowed)) {
                return "Este campo contém caracteres invalidos!";
            }
        }
        return null;
    }

    public boolean isValid(String text, String regexp) {
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
