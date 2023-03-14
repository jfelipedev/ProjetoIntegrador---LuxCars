package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryValidation {
    private static final int descritpionCharactersMinimum = 10;
    private static final int descritpionCharactersMaximum = 200;
    private static final int qualificationCharactersMinimum = 3;
    private static final int qualificationCharactersMaximum = 100;
    private static final int urlCharactersMaximum = 255;
    private static final String regexpUrlAllowed = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";

    public CategoryValidation(InputCategoryDto category) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorDescritpion = validationText(category.getDescritpion(), descritpionCharactersMinimum, descritpionCharactersMaximum);
        String errorQualification = validationText(category.getQualification(), qualificationCharactersMinimum, qualificationCharactersMaximum);
        String errorUrl = validationUrl(category.getUrlImage());
        if (!(errorDescritpion == null && errorQualification == null && errorUrl == null)) {
            ErrorCategoryDto errorCategoryDto = new ErrorCategoryDto(errorDescritpion, errorQualification, errorUrl);
            throw new BadRequestException(objectMapper.writeValueAsString(errorCategoryDto));
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

    public String validationUrl(String url) {
        if (url.trim().isBlank()) {
            return "A url não pode ser vazio";
        } else if (url.trim().length() > urlCharactersMaximum) {
            return "A url dever ter menor do que " + urlCharactersMaximum + " caracteres!";
        } else if (!isValidUrl(url)) {
            return "Esta url é invalido";
        }
        return null;
    }

    public boolean isValidUrl(String url) {
        Pattern pattern = Pattern.compile(regexpUrlAllowed);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
