package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

public class CategoryValidation {
    private static final int descritpionCharactersMinimum = 10;
    private static final int descritpionCharactersMaximum = 200;
    private static final int qualificationCharactersMinimum = 3;
    private static final int qualificationCharactersMaximum = 100;

    public CategoryValidation() {
    }

    public CategoryValidation(InputCategoryDto category) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorDescritpion = validationText(category.getDescritpion(), descritpionCharactersMinimum, descritpionCharactersMaximum);
        String errorQualification = validationText(category.getQualification(), qualificationCharactersMinimum, qualificationCharactersMaximum);
        if (!(errorDescritpion == null && errorQualification == null)) {
            ErrorCategoryDto errorCategoryDto = new ErrorCategoryDto(errorDescritpion, errorQualification);
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
}
