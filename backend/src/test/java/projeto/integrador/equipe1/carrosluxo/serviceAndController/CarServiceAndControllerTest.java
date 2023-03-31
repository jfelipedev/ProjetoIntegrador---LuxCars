package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Controller.CarController;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarCaracteristicDTO;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarReadDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Service.CarService;

import java.util.Date;
import java.util.HashSet;
import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CarServiceAndControllerTest {
    @Autowired
    CarService carService;

    @Autowired
    CarController carController;

    @Test
    void createValid() {
        Assertions.assertDoesNotThrow(() -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            OutputCarCreateOrUpdateDto car = carService.create(new InputCarDto("BMW M3", "uma carro alemão", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
            Assertions.assertEquals(3, car.getId());
        });
    }

    @Test
    void createInvalidIdCategory() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":\"Esta categoria não existe!\",\"descritpion\":null,\"price\":null,\"year\":null,\"city\":null,\"caracteristics\":null,\"highlight\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            carService.create(new InputCarDto("BMW M3", "uma carro alemão", 512.24, 2018, Boolean.TRUE, 5L, 1L, list));
        }).getMessage());
    }

    @Test
    void createInvalidIdCity() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":null,\"descritpion\":null,\"price\":null,\"year\":null,\"city\":\"Esta cidade não está registrado!\",\"caracteristics\":null,\"highlight\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            carService.create(new InputCarDto("BMW M3", "uma carro alemão", 512.24, 2018, Boolean.TRUE, 1L, 5L, list));
        }).getMessage());
    }

    @Test
    void createInvalidIdCaracteristic() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":null,\"descritpion\":null,\"price\":null,\"year\":null,\"city\":null,\"caracteristics\":\"Contém uma caracteristica que não existir!\",\"highlight\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(10L, null));
            carService.create(new InputCarDto("BMW M3", "uma carro alemão", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
        }).getMessage());
    }

    @Test
    void createInvalidHighlight() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":null,\"descritpion\":null,\"price\":null,\"year\":null,\"city\":null,\"caracteristics\":null,\"highlight\":\"O destaque precisar ser verdadeiro ou falso!\"}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            carService.create(new InputCarDto("BMW M3", "uma carro alemão", 512.24, 2018, null, 1L, 1L, list));
        }).getMessage());
    }

    @Test
    void readValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCarReadDto car = carService.read(1L);
            Assertions.assertEquals("Audi M6", car.getNameCar());
        });
    }

    @Test
    void readInvalid() {
        Assertions.assertEquals("Este carro não existir", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carService.read(10L);
        }).getMessage());
    }

    @Test
    void updateValid() {
        Assertions.assertDoesNotThrow(() -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            OutputCarCreateOrUpdateDto car = carService.update(1L, new InputCarDto("BMW M2", "Eu sou um audi", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
            Assertions.assertEquals("BMW M2", car.getNameCar());
            Assertions.assertEquals("Eu sou um audi", car.getDescritpion());
            car = carService.update(1L, new InputCarDto("BMW M2", "Um carro alemão", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
            Assertions.assertEquals("BMW M2", car.getNameCar());
            Assertions.assertEquals("Um carro alemão", car.getDescritpion());
            car = carService.update(1L, new InputCarDto("Audi M6", "Eu sou um audi", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
            Assertions.assertEquals("Audi M6", car.getNameCar());
            Assertions.assertEquals("Eu sou um audi", car.getDescritpion());
        });
    }

    @Test
    void updateInvalidIdCar() {
        Assertions.assertEquals("Este carro não existir", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            OutputCarCreateOrUpdateDto car = carService.update(10L, new InputCarDto("BMW M2", "Eu sou um audi", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
        }).getMessage());
    }

    @Test
    void updateInvalidIdCategory() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":\"Esta categoria não existe!\",\"descritpion\":null,\"price\":null,\"year\":null,\"city\":null,\"caracteristics\":null,\"highlight\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            OutputCarCreateOrUpdateDto car = carService.update(1L, new InputCarDto("BMW M2", "Eu sou um audi", 512.24, 2018, Boolean.TRUE, 10L, 1L, list));
        }).getMessage());
    }

    @Test
    void updateInvalidIdCity() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":null,\"descritpion\":null,\"price\":null,\"year\":null,\"city\":\"Esta cidade não está registrado!\",\"caracteristics\":null,\"highlight\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            OutputCarCreateOrUpdateDto car = carService.update(1L, new InputCarDto("BMW M2", "Eu sou um audi", 512.24, 2018, Boolean.TRUE, 1L, 10L, list));
        }).getMessage());
    }

    @Test
    void updateInvalidIdCaracteristics() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":null,\"descritpion\":null,\"price\":null,\"year\":null,\"city\":null,\"caracteristics\":\"Contém uma caracteristica que não existir!\",\"highlight\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(10L, null));
            OutputCarCreateOrUpdateDto car = carService.update(1L, new InputCarDto("BMW M2", "Eu sou um audi", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
        }).getMessage());
    }

    @Test
    void updateInvalidHighlight() {
        Assertions.assertEquals("{\"nameCar\":null,\"category\":null,\"descritpion\":null,\"price\":null,\"year\":null,\"city\":null,\"caracteristics\":null,\"highlight\":\"O destaque precisar ser verdadeiro ou falso!\"}", Assertions.assertThrows(BadRequestException.class, () -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            OutputCarCreateOrUpdateDto car = carService.update(1L, new InputCarDto("BMW M2", "Eu sou um audi", 512.24, 2018, null, 1L, 1L, list));
        }).getMessage());
    }

    @Test
    void deleteValid() {
        Assertions.assertDoesNotThrow(() -> {
            String categoty = carService.delete(1L);
            Assertions.assertEquals("Este carro foi deletado com sucesso!", categoty);
        });
    }

    @Test
    void deleteInvalid() {
        Assertions.assertEquals("Este carro não existir", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carService.delete(10L);
        }).getMessage());
    }

    @Test
    void allServiceHighlightTest() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCarDto> list = carService.allHighlight();
            Assertions.assertEquals(1, list.size());
            Assertions.assertEquals(2, list.get(0).getId());
            Assertions.assertEquals("Ferrari GT 400", list.get(0).getNameCar());
        });
    }

    @Test
    void allValid() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCarDto> list = carService.all(null, null, null, null);
            Assertions.assertEquals(2, list.size());
            Assertions.assertEquals(1, list.get(0).getCategory().getId());
            Assertions.assertEquals(2, list.get(1).getCategory().getId());
            Assertions.assertEquals(2, list.get(0).getCity().getId());
            Assertions.assertEquals(1, list.get(1).getCity().getId());
        });
    }

    @Test
    void allIdCategoryValid() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCarDto> list = carService.all(1L, null, null, null);
            Assertions.assertEquals(1, list.size());
            Assertions.assertEquals(1, list.get(0).getCategory().getId());
            Assertions.assertEquals(2, list.get(0).getCity().getId());
        });
    }

    @Test
    void allIdcategoryInvalid() {
        Assertions.assertEquals("Esta categoria não existir!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carService.all(10L, null, null, null);
        }).getMessage());
    }

    @Test
    void allIdCityValid() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCarDto> list = carService.all(null, 1L, null, null);
            Assertions.assertEquals(1, list.size());
            Assertions.assertEquals(2, list.get(0).getCategory().getId());
            Assertions.assertEquals(1, list.get(0).getCity().getId());
        });
    }

    @Test
    void allIdcityInvalid() {
        Assertions.assertEquals("Esta cidade não existir!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carService.all(null, 10L, null, null);
        }).getMessage());
    }

    @Test
    void allIdcategoryAndIdcityValid() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCarDto> list = carService.all(2L, 1L, null, null);
            Assertions.assertEquals(1, list.size());
            Assertions.assertEquals(2, list.get(0).getCategory().getId());
            Assertions.assertEquals(1, list.get(0).getCity().getId());
        });
    }

    @Test
    void allIdcategoryInvalidAndIdcityValid() {
        Assertions.assertEquals("Esta categoria não existir!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carService.all(10L, 1L, null, null);
        }).getMessage());
    }

    @Test
    void allIdcategoryValidAndIdcityInvalid() {
        Assertions.assertEquals("Esta cidade não existir!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carService.all(1L, 10L, null, null);
        }).getMessage());
    }

    @Test
    void allIdcategoryAndIdcityInvalid() {
        Assertions.assertEquals("Esta cidade e categoria não existir!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carService.all(10L, 10L, null, null);
        }).getMessage());
    }

    @Test
    void allControllerHighlightTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<OutputCarDto>> response = (ResponseEntity<List<OutputCarDto>>) carController.allHighlight();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(1, response.getBody().size());
            Assertions.assertEquals(2, response.getBody().get(0).getId());
            Assertions.assertEquals("Ferrari GT 400", response.getBody().get(0).getNameCar());
        });
    }

    @Test
    void ControllerAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<OutputCarDto>> response = (ResponseEntity<List<OutputCarDto>>) carController.all(null, null, null, null);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(2, response.getBody().size());
            Assertions.assertEquals(1, response.getBody().get(0).getId());
            Assertions.assertEquals("Audi M6", response.getBody().get(0).getNameCar());
        });
    }

    @Test
    void controllerCreateTest() {
        Assertions.assertDoesNotThrow(() -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            ResponseEntity<OutputCarCreateOrUpdateDto> response = (ResponseEntity<OutputCarCreateOrUpdateDto>) carController.create(new InputCarDto("BMW M3", "uma carro alemão", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            Assertions.assertEquals(3, response.getBody().getId());
        });
    }

    @Test
    void controllerReadTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCarReadDto> response = (ResponseEntity<OutputCarReadDto>) carController.read(1L);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("Audi M6", response.getBody().getNameCar());
        });
    }

    @Test
    void controllerUpdateTest() {
        Assertions.assertDoesNotThrow(() -> {
            HashSet<InputCarCaracteristicDTO> list = new HashSet<>();
            list.add(new InputCarCaracteristicDTO(1L, null));
            ResponseEntity<OutputCarCreateOrUpdateDto> response = (ResponseEntity<OutputCarCreateOrUpdateDto>) carController.update(1L, new InputCarDto("BMW M2", "Eu sou um audi", 512.24, 2018, Boolean.TRUE, 1L, 1L, list));
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("BMW M2", response.getBody().getNameCar());
        });
    }

    @Test
    void controllerDeleteTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<String> response = (ResponseEntity<String>) carController.delete(1L);
            Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            Assertions.assertEquals("Este carro foi deletado com sucesso!", response.getBody());
        });
    }

    @Test
    void ControlleravailabilityByCarValid() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<Date[]>> response = (ResponseEntity<List<Date[]>>) carController.availabilityByCar(1L);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(1, response.getBody().size());
        });
    }

    @Test
    void ControlleravailabilityByCarInvalid() {
        Assertions.assertEquals("Este carro não está registrado!", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            carController.availabilityByCar(10L);
        }).getMessage());
    }

    @Test
    void controlleravailabilityTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<Date[]>> response = (ResponseEntity<List<Date[]>>) carController.availability();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(1, response.getBody().size());
        });
    }
}
