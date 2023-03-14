package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

public class CityValidation {
    private static final int nameCityCharactersMinimum = 2;
    private static final int nameCityCharactersMaximum = 100;
    private static final int countryCharactersMinimum = 2;
    private static final int countryCharactersMaximum = 100;

    public CityValidation(InputCityDto city) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorNameCity = validationText(city.getNameCity(), nameCityCharactersMinimum, nameCityCharactersMaximum);
        String errorCountry = validationText(city.getCountry(), countryCharactersMinimum, countryCharactersMaximum);
        if (!(errorNameCity == null && errorCountry == null)) {
            ErrorCityDto errorCityDto = new ErrorCityDto(errorNameCity, errorCountry);
            throw new BadRequestException(objectMapper.writeValueAsString(errorCityDto));
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
