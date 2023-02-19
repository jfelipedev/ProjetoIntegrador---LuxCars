package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.ErrorLoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.ErrorRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.LoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.RegisterDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CustomerValidation {

    private static final int nameCharactersMinimum = 2;
    private static final int nameCharactersMaximum = 100;
    private static final String nameCharactersAllowed = "^[A-Za-z0-9áéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛãẽĩõũÃẼĨÕŨçÇ]{1,}$";

    private static final int passwordCharacterMinimum = 8;
    private static final int passwordCharacterMaximum = 20;

    private static final String emailAllowed = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
            + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

    public CustomerValidation(LoginDto loginDto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorEmail = validationEmail(loginDto.getEmail());
        String errorPassword = validationPassword(loginDto.getPassword());
        if (!(errorEmail == null && errorPassword == null)){
            ErrorLoginDto errorLoginDto = new ErrorLoginDto(errorEmail, errorPassword);
            throw new BadRequestException(objectMapper.writeValueAsString(errorLoginDto));
        }
    }

    public CustomerValidation(RegisterDto registerDto) throws Exception{
        ObjectMapper objectMapper = new ObjectMapper();
        String errorFirstName = validationName(registerDto.getFirstName());
        String errorSurname = validationName(registerDto.getSurname());
        String errorEmail = validationEmail(registerDto.getEmail());
        String errorPassword = validationPassword(registerDto.getPassword());
        if (!(errorFirstName == null && errorSurname == null && errorEmail == null && errorPassword == null)){
            ErrorRegisterDto errorRegisterDto = new ErrorRegisterDto(errorFirstName, errorSurname, errorEmail, errorPassword);
            throw new BadRequestException(objectMapper.writeValueAsString(errorRegisterDto));
        }
    }

    public String validationName(String name){
        if(name.trim().isBlank()){
            return "O primeiro nome não pode está vazio!";
        }
        else if(name.trim().length() < nameCharactersMinimum) {
            return "O primeiro nome dever ser maior do que " + nameCharactersMinimum + " caractreres!";
        }
        else if(name.trim().length() > nameCharactersMaximum){
            return "O primeiro nome dever ser menor do que " + nameCharactersMaximum + " caractreres!";
        }
        else if(!validationLength(name.trim().split(" "), 2)){
                return "O primeiro nome contém palavras menor do que 2 caractres";
        }
        String list[] = name.split(" ");
        for (String item:list) {
            if(!isValid(item, nameCharactersAllowed)){
                return  "O primeiro nome contém caracteres invalidos!";
            }
        }
        return null;
    }


    private String validationPassword(String password){
        if (password.trim().isBlank()){
            return "A senha não pode ser vazio";
        } else if (password.trim().length() < passwordCharacterMinimum ){
            return "A senha dever ter mais do que " + passwordCharacterMinimum + " caracteres!";
        } else if (password.trim().length() > passwordCharacterMaximum) {
            return "A senha dever ter menor do que " + passwordCharacterMaximum + " caracteres!";
        }
        return null;
    }

    private String validationEmail(String email){
        if (email.trim().isBlank()){
            return "O email não pode ser vazio";
        }
        else if (isValid(email, emailAllowed)){
            return "Este email inserido é invalido";
        }
        return null;
    }

    private boolean validationLength(String[] lista, int min){
        boolean tmp = true;
        for (String item: lista) {
            if(item.length() < min){
                tmp = false;
            }
        }
        return tmp;
    }

    public boolean isValid(String text, String regexp){
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
