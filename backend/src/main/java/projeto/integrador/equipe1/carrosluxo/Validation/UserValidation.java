package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorLoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputLoginDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.user.InputRegisterDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidation {

    private static final Long nameCharactersMinimum = 2L;
    private static final Long nameCharactersMaximum = 100L;
    private static final String nameCharactersAllowed = "^[A-Za-záéíóúÁÉÍÓÚàèìòùÀÈÌÒÙâêîôûÂÊÎÔÛãẽĩõũÃẼĨÕŨçÇ-]{1,}$";
    private static final Long passwordCharacterMinimum = 8L;
    private static final Long passwordCharacterMaximum = 20L;
    private static final Long emailCharacterMaximum = 200L;
    private static final String emailAllowed = "^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$";

    public UserValidation() {
    }

    public UserValidation(InputLoginDto login) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorEmail = validationEmail(login.getEmail());
        String errorPassword = validationPassword(login.getPassword());
        if (!(errorEmail == null && errorPassword == null)) {
            ErrorLoginDto errorLoginDto = new ErrorLoginDto(errorEmail, errorPassword);
            throw new BadRequestException(objectMapper.writeValueAsString(errorLoginDto));
        }
    }

    public UserValidation(InputRegisterDto registerDto) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String errorFirstName = validationName("primeiro nome", registerDto.getFirstName());
        String errorSurname = validationName("sobrenome", registerDto.getSurname());
        String errorEmail = validationEmail(registerDto.getEmail());
        String errorPassword = validationPassword(registerDto.getPassword());
        if (!(errorFirstName == null && errorSurname == null && errorEmail == null && errorPassword == null)) {
            ErrorRegisterDto errorRegisterDto = new ErrorRegisterDto(errorFirstName, errorSurname, errorEmail, errorPassword);
            throw new BadRequestException(objectMapper.writeValueAsString(errorRegisterDto));
        }
    }

    public String validationName(String campo, String name) {
        if (name.trim().isBlank()) {
            return "O " + campo + " não pode está vazio!";
        } else if (name.trim().length() < nameCharactersMinimum) {
            return "O " + campo + " dever ser maior do que " + nameCharactersMinimum + " caractreres!";
        } else if (name.trim().length() > nameCharactersMaximum) {
            return "O " + campo + " dever ser menor do que " + nameCharactersMaximum + " caractreres!";
        } else if (!validationLength(name.trim().split(" "), 2L)) {
            return "O " + campo + " nome contém palavras menor do que 2 caractres!";
        }
        String list[] = name.split(" ");
        for (String item : list) {
            if (!isValid(item, nameCharactersAllowed)) {
                return "O " + campo + " contém caracteres invalidos!";
            }
        }
        return null;
    }


    public String validationPassword(String password) {
        if (password.trim().isBlank()) {
            return "A senha não pode ser vazio!";
        } else if (password.trim().length() < passwordCharacterMinimum) {
            return "A senha dever ter mais do que " + passwordCharacterMinimum + " caracteres!";
        } else if (password.trim().length() > passwordCharacterMaximum) {
            return "A senha dever ter menor do que " + passwordCharacterMaximum + " caracteres!";
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

    public boolean validationLength(String[] lista, Long min) {
        boolean tmp = true;
        for (String item : lista) {
            if (item.length() < min) {
                tmp = false;
            }
        }
        return tmp;
    }

    public boolean isValid(String text, String regexp) {
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        return matcher.find();
    }
}
