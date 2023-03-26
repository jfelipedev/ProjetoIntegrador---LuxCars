package projeto.integrador.equipe1.carrosluxo.Validation;

import com.fasterxml.jackson.databind.ObjectMapper;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorBookingDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.booking.InputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.utils.ResultCheckConvert;
import projeto.integrador.equipe1.carrosluxo.utils.ResultConvertStringToDate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BookingValidation {
    private static final String formatDate = "^[0-3]{1}[0-9]{1}\\/[0-1]{1}[0-9]{1}\\/[0-9]{4,}$";
    private static final String formatTime = "^[0-2]{1}[0-9]{1}:[0-5]{1}[0-9]{1}:[0-5]{1}[0-9]{1}$";
    ObjectMapper objectMapper = new ObjectMapper();

    public BookingValidation() {
    }

    public BookingValidation(InputBookingDto inputBookingDto) throws Exception {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        String errorStart = isValid(inputBookingDto.getStartDate(), formatDate, "Data de inicio");
        String errorEnd = isValid(inputBookingDto.getEndDate(), formatDate, "Data de fim");
        String errorTime = isValid(inputBookingDto.getStartTime(), formatTime, "Hora de inicio");
        if (!(errorEnd == null && errorStart == null && errorTime == null)) {
            ErrorBookingDto error = new ErrorBookingDto(errorStart, errorTime, errorEnd, null, null);
            throw new BadRequestException(objectMapper.writeValueAsString(error));
        }
        ResultCheckConvert check = checkConvert(inputBookingDto.getStartDate(), inputBookingDto.getEndDate(), inputBookingDto.getStartTime(), dateFormat, timeFormat);
        Date startDate = check.getStartDate();
        Date endDate = check.getEndDate();
        if (startDate.after(endDate)) {
            ErrorBookingDto error = new ErrorBookingDto(null, "Data de fim não pode ser antes do inicio", null, null, null);
            throw new BadRequestException(objectMapper.writeValueAsString(error));
        }
    }

    public ResultCheckConvert checkConvert(String inputStart, String inputEnd, String inputTime, SimpleDateFormat date, SimpleDateFormat time) throws Exception {
        Date startDate = null;
        Date endDate = null;
        String errorStart;
        String errorEnd;
        String errorTime;
        ResultConvertStringToDate start = convertStringToDate(inputStart, date);
        startDate = start.getDate();
        errorStart = start.getError();
        ResultConvertStringToDate end = convertStringToDate(inputEnd, date);
        endDate = end.getDate();
        errorEnd = end.getError();
        ResultConvertStringToDate timeStart = convertStringToDate(inputTime, time);
        errorTime = timeStart.getError();
        if (!(errorEnd == null && errorStart == null && errorTime == null)) {
            ErrorBookingDto error = new ErrorBookingDto(errorStart, errorTime, errorEnd, null, null);
            throw new BadRequestException(objectMapper.writeValueAsString(error));
        }
        return new ResultCheckConvert(startDate, endDate);
    }

    public ResultConvertStringToDate convertStringToDate(String text, SimpleDateFormat format) {
        ResultConvertStringToDate result = new ResultConvertStringToDate();
        try {
            result.setDate(format.parse(text));
        } catch (ParseException e) {
            result.setError("Erro a converter: " + e.getMessage());
        }
        return result;
    }

    public String isValid(String text, String regexp, String campo) {
        Pattern pattern = Pattern.compile(regexp, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(text);
        if (!matcher.find()) {
            return "Este campo " + campo + " contém caractres invalidos!";
        } else {
            return null;
        }
    }
}
