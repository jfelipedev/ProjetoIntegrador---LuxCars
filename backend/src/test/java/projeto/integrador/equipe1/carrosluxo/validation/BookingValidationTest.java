package projeto.integrador.equipe1.carrosluxo.validation;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import projeto.integrador.equipe1.carrosluxo.Dto.input.booking.InputBookingDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Validation.BookingValidation;
import projeto.integrador.equipe1.carrosluxo.utils.ResultCheckConvert;
import projeto.integrador.equipe1.carrosluxo.utils.ResultConvertStringToDate;

import java.text.SimpleDateFormat;

public class BookingValidationTest {
    void isValidValid() {
        BookingValidation bookingValidation = new BookingValidation();
        String texto = bookingValidation.isValid("teste", "^[a-z]{1,}$", "teste");
        Assertions.assertNull(texto);
    }

    @Test
    void isValidInvalid() {
        BookingValidation bookingValidation = new BookingValidation();
        String texto = bookingValidation.isValid("teste teste", "^[a-z]{1,}$", "teste");
        Assertions.assertEquals("Este campo teste contém caractres invalidos!", texto);
    }

    @Test
    void convertStringToDateValid() {
        BookingValidation bookingValidation = new BookingValidation();
        ResultConvertStringToDate result = bookingValidation.convertStringToDate("22/12/2021", new SimpleDateFormat("dd/MM/yyyy"));
        Assertions.assertEquals("Wed Dec 22 00:00:00 BRT 2021", result.getDate().toString());
        Assertions.assertNull(result.getError());
    }

    @Test
    void convertStringToDateInvalid() {
        BookingValidation bookingValidation = new BookingValidation();
        ResultConvertStringToDate result = bookingValidation.convertStringToDate("2212/2021", new SimpleDateFormat("dd/MM/yyyy"));
        Assertions.assertNull(result.getDate());
        Assertions.assertEquals("Erro a converter: Unparseable date: \"2212/2021\"", result.getError());
    }

    @Test
    void checkConvertValid() {
        BookingValidation bookingValidation = new BookingValidation();
        Assertions.assertDoesNotThrow(() -> {
            ResultCheckConvert check = bookingValidation.checkConvert("23/12/2000", "25/12/2001", "15:00:00", new SimpleDateFormat("dd/MM/yyyy"), new SimpleDateFormat("HH:mm:ss"));
            Assertions.assertEquals("Sat Dec 23 00:00:00 BRST 2000", check.getStartDate().toString());
            Assertions.assertEquals("Tue Dec 25 00:00:00 BRST 2001", check.getEndDate().toString());
        });
    }

    @Test
    void checkConvertInvalid() {
        Assertions.assertEquals("{\"startDate\":\"Erro a converter: Unparseable date: \\\"2312/2000\\\"\",\"startTime\":\"Erro a converter: Unparseable date: \\\"15:0000\\\"\",\"endDate\":\"Erro a converter: Unparseable date: \\\"25/12001\\\"\",\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            BookingValidation bookingValidation = new BookingValidation();
            bookingValidation.checkConvert("2312/2000", "25/12001", "15:0000", new SimpleDateFormat("dd/MM/yyyy"), new SimpleDateFormat("HH:mm:ss"));
        }).getMessage());
    }

    @Test
    void bookingInvalidFormat() {
        Assertions.assertEquals("{\"startDate\":\"Este campo Data de inicio contém caractres invalidos!\",\"startTime\":\"Este campo Hora de inicio contém caractres invalidos!\",\"endDate\":\"Este campo Data de fim contém caractres invalidos!\",\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            new BookingValidation(new InputBookingDto("48/55/55", "48/55/55", "48/55/55", 1L));
        }).getMessage());
    }

    @Test
    void bookingInvalidEndStart() {
        Assertions.assertEquals("{\"startDate\":null,\"startTime\":\"Data de fim não pode ser antes do inicio\",\"endDate\":null,\"idCar\":null,\"idUser\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            new BookingValidation(new InputBookingDto("09/09/2100", "29:59:59", "09/09/2000", 1L));
        }).getMessage());
    }
}
