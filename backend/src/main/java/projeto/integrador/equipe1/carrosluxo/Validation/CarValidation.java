package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCarDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.time.LocalDate;

public class CarValidation {
    private static final int nameCarCharactersMinimum = 5;
    private static final int nameCarCharactersMaximum = 100;
    private static final int descritpionCharactersMinimum = 5;
    private static final int descritpionCharactersMaximum = 2000;

    public CarValidation(InputCarDto car) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorNameCar = validationText(car.getNameCar(), nameCarCharactersMinimum, nameCarCharactersMaximum);
        String errorDescritpion = validationText(car.getDescritpion(), descritpionCharactersMinimum, descritpionCharactersMaximum);
        String errorYear = validationYear(car.getYear());
        String errorPrice = validationPrice(car.getPrice());

        if (!(errorNameCar == null)) {
            ErrorCarDto errorCarDto = new ErrorCarDto(errorNameCar, null, errorDescritpion, errorPrice, errorYear, null, null);
            throw new BadRequestException(objectMapper.writeValueAsString(errorCarDto));
        }
    }

    private String validationPrice(Double price) {
        if(price == null){
            return "Este campo não pode está vazio!";
        }
        else if (price < 200){
            return "Aviso: o valor do preço deve ser maior ou igual a R$200 para ser válido!";
        }
        else if (price > 99999.99){
            return "Aviso: o valor do preço deve ser menor ou igual a R$99999.99 para ser válido!";
        }
        return null;
    }

    public String validationYear(Integer year){
        LocalDate dataAtual = LocalDate.now();
        int anoAtual = dataAtual.getYear();
        if(year == null){
            return "Este campo não pode está vazio!";
        } else if(year < 1904){
            return "Aviso: o valor do ano deve ser maior ou igual a 1900 para ser válido!";
        } else if(year > anoAtual){
            return "Aviso: o valor do ano deve ser menor ou igual a " + anoAtual + " para ser válido!";
        } else{
            return null;
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
