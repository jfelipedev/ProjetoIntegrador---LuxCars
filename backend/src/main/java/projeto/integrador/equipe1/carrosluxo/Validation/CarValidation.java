package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.error.ErrorCarDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

public class CarValidation {
    private static final int nameCarCharactersMinimum = 5;
    private static final int nameCarCharactersMaximum = 100;

    public CarValidation(InputCarDto car) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorNameCar = validationText(car.getNameCar(), nameCarCharactersMinimum, nameCarCharactersMaximum);

        if (!(errorNameCar == null)) {
            ErrorCarDto errorCarDto = new ErrorCarDto(errorNameCar, null, null, null);
            throw new BadRequestException(objectMapper.writeValueAsString(errorCarDto));
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
