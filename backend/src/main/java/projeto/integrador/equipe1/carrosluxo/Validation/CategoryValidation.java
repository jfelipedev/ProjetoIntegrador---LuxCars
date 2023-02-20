package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.ErrorCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.ErrorLoginDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CategoryValidation {
    private static final int descritpionCharactersMinimum = 5;
    private static final int descritpionCharactersMaximum = 200;
    private static final int modelCharactersMinimum = 5;
    private static final int modelCharactersMaximum = 100;
    private static final int urlCharactersMaximum = 255;
    private static final String textCharactersAllowed = "^[A-Za-z0-9áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛãẽĩõũÃẼĨÕŨçÇ]{1,}$";
    private static final String regexpUrlAllowed = "^https?:\\/\\/(?:www\\.)?[-a-zA-Z0-9@:%._\\+~#=]{1,256}\\.[a-zA-Z0-9()]{1,6}\\b(?:[-a-zA-Z0-9()@:%_\\+.~#?&\\/=]*)$";

    public CategoryValidation(CategoryDto categoryDto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorDescritpion = validationText(categoryDto.getDescritpion(), descritpionCharactersMinimum, descritpionCharactersMaximum);
        String errorModel = validationText(categoryDto.getModel(), modelCharactersMinimum, modelCharactersMaximum);
        String errorUrl = validationUrl(categoryDto.getUrlImage());
        if (!(errorDescritpion == null && errorModel == null && errorUrl == null)){
            ErrorCategoryDto errorCategoryDto = new ErrorCategoryDto(errorDescritpion, errorModel, errorUrl);
            throw new BadRequestException(objectMapper.writeValueAsString(errorCategoryDto));
        }
    }

    public String validationText(String text, int textCharactersMinimum, int textCharactersMaximum){
        if(text.trim().isBlank()){
            return "Este campo não pode está vazio!";
        }
        else if(text.trim().length() < textCharactersMinimum) {
            return "Este campo dever ser maior do que " + textCharactersMinimum + " caractreres!";
        }
        else if(text.trim().length() > textCharactersMaximum){
            return "Este campo dever ser menor do que " + textCharactersMaximum + " caractreres!";
        }
        String list[] = text.split(" ");
        for (String item:list) {
            if(!isValid(item, textCharactersAllowed)){
                return  "Este campo contém caracteres invalidos!";
            }
        }
        return null;
    }
    public String validationUrl(String url){
        if (url.trim().isBlank()){
            return "A url não pode ser vazio";
        } else if (url.trim().length() > urlCharactersMaximum) {
            return "A url dever ter menor do que " + urlCharactersMaximum + " caracteres!";
        } else if (!isValidUrl(url)){
            return "Esta url é invalido";
        }
        return null;
    }
    public boolean isValid(String text, String regexp){
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
    public boolean isValidUrl(String url){
        Pattern pattern = Pattern.compile(regexpUrlAllowed);
        Matcher matcher = pattern.matcher(url);
        return matcher.find();
    }
}
