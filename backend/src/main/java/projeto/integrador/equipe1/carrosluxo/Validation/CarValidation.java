package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.time.LocalDate;

public class CarValidation {
    private static final Long nameCarCharactersMinimum = 5L;
    private static final Long nameCarCharactersMaximum = 100L;
    private static final Long descritpionCharactersMinimum = 5L;
    private static final Long descritpionCharactersMaximum = 2000L;

    public CarValidation() {
    }

    public CarValidation(InputCarDto car) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorNameCar = validationText(car.getNameCar(), nameCarCharactersMinimum, nameCarCharactersMaximum);
        String errorDescritpion = validationText(car.getDescritpion(), descritpionCharactersMinimum, descritpionCharactersMaximum);
        String errorYear = validationYear(car.getYear());
        String errorPrice = validationPrice(car.getPrice());

        if (!(errorNameCar == null && errorDescritpion == null && errorYear == null && errorPrice == null)) {
            ErrorCarDto errorCarDto = new ErrorCarDto(errorNameCar, null, errorDescritpion, errorPrice, errorYear, null, null, null);
            throw new BadRequestException(objectMapper.writeValueAsString(errorCarDto));
        }
    }

    public String validationPrice(Double price) {
        if (price == null) {
            return "Este campo não pode está vazio!";
        } else if (price < 200) {
            return "Aviso: o valor do preço deve ser maior ou igual a R$200 para ser válido!";
        } else if (price > 9999999999.99) {
            return "Aviso: o valor do preço deve ser menor ou igual a R$9.999.999.999,99 para ser válido!";
        }
        return null;
    }

    public String validationYear(Integer year) {
        if (year == null) {
            return "Este campo não pode está vazio!";
        } else if (year < 1900) {
            return "Aviso: o valor do ano deve ser maior ou igual a 1900 para ser válido!";
        } else if (year > getAnoAtual()) {
            return "Aviso: o valor do ano deve ser menor ou igual a " + getAnoAtual() + " para ser válido!";
        } else {
            return null;
        }
    }

    public String validationText(String text, Long textCharactersMinimum, Long textCharactersMaximum) {
        if (text.trim().isBlank()) {
            return "Este campo não pode está vazio!";
        } else if (text.trim().length() < textCharactersMinimum) {
            return "Este campo dever ser maior do que " + textCharactersMinimum + " caractreres!";
        } else if (text.trim().length() > textCharactersMaximum) {
            return "Este campo dever ser menor do que " + textCharactersMaximum + " caractreres!";
        }
        return null;
    }

    public int getAnoAtual() {
        LocalDate dataAtual = LocalDate.now();
        int anoAtual = dataAtual.getYear();
        return anoAtual;
    }
}
