package projeto.integrador.equipe1.carrosluxo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarCaracteristicDTO;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarReadDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CarCaracteristicEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.*;
import projeto.integrador.equipe1.carrosluxo.Validation.CarValidation;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CarService {
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(CarService.class);
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CityRepository cityRepository;
    @Autowired
    private CaracteristicRepository caracteristicRepository;
    @Autowired
    private CarCaracteristicRepository carCaracteristicRepository;
    @Autowired
    private BookingService bookingService;

    public OutputCarCreateOrUpdateDto create(InputCarDto car) throws Exception {
        new CarValidation(car);
        ErrorCarDto error = new ErrorCarDto();
        if (!categoryRepository.existsById(car.getIdCategory())) {
            error.setCategory("Esta categoria não existe!");
        }
        if (!cityRepository.existsById(car.getIdCity())) {
            error.setCity("Esta cidade não está registrado!");
        }
        for (InputCarCaracteristicDTO caracteristic : car.getInputCarCaracteristicDTO()) {
            if (!caracteristicRepository.existsById(caracteristic.getId())) {
                error.setCaracteristics("Contém uma caracteristica que não existir!");
                break;
            }
        }
        if (!((car.getHighlight() == Boolean.TRUE) || (car.getHighlight() == Boolean.FALSE))) {
            error.setHighlight("O destaque precisar ser verdadeiro ou falso!");
        }
        if (!(error.getNameCar() == null && error.getCategory() == null && error.getCity() == null && error.getCaracteristics() == null && error.getHighlight() == null)) {
            throw new BadRequestException(objectMapper.writeValueAsString(error));
        }
        CarEntity carEntity = carRepository.save(new CarEntity(car));
        logger.info(car.getNameCar() + " foi adicionado!");
        for (InputCarCaracteristicDTO caracteristic : car.getInputCarCaracteristicDTO()) {
            CarCaracteristicEntity carCaracteristic = new CarCaracteristicEntity();
            carCaracteristic.setCar(carEntity);
            carCaracteristic.setCaracteristic(caracteristicRepository.findById(caracteristic.getId()).get());
            carCaracteristic.setValue(caracteristic.getValue());
            carCaracteristicRepository.save(carCaracteristic);
        }
        return new OutputCarCreateOrUpdateDto(carRepository.findByNameCar(car.getNameCar()).get());
    }

    public OutputCarReadDto read(long id) throws Exception {
        logger.trace("O carro com id + " + id + " foi exibindo!");
        if (carRepository.existsById(id)) {
            return new OutputCarReadDto(carRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Este carro não existir");
    }

    public OutputCarCreateOrUpdateDto update(long id, InputCarDto car) throws Exception {
        new CarValidation(car);
        if (!carRepository.existsById(id)) {
            throw new ResourceNotFoundException("Este carro não existir");
        }
        ErrorCarDto error = new ErrorCarDto();
        if (!categoryRepository.existsById(car.getIdCategory())) {
            error.setCategory("Esta categoria não existe!");
        }
        if (!cityRepository.existsById(car.getIdCity())) {
            error.setCity("Esta cidade não está registrado!");
        }
        for (InputCarCaracteristicDTO caracteristic : car.getInputCarCaracteristicDTO()) {
            if (!caracteristicRepository.existsById(caracteristic.getId())) {
                error.setCaracteristics("Contém uma caracteristica que não existir!");
                break;
            }
        }
        if (!((car.getHighlight() == Boolean.TRUE) || (car.getHighlight() == Boolean.FALSE))) {
            error.setHighlight("O destaque precisar ser verdadeiro ou falso!");
        }
        if (!(error.getNameCar() == null && error.getCategory() == null && error.getCity() == null && error.getCaracteristics() == null && error.getHighlight() == null)) {
            throw new BadRequestException(objectMapper.writeValueAsString(error));
        }
        CarEntity carEntity = new CarEntity(car);
        carEntity.setId(id);
        carRepository.save(carEntity);
        for (CarCaracteristicEntity carCaracteristic : carCaracteristicRepository.findAllByCar(carEntity).get()){
            carCaracteristicRepository.delete(carCaracteristic);
        }
        for (InputCarCaracteristicDTO caracteristic : car.getInputCarCaracteristicDTO()) {
            CarCaracteristicEntity carCaracteristic = new CarCaracteristicEntity();
            carCaracteristic.setCar(carEntity);
            carCaracteristic.setCaracteristic(caracteristicRepository.findById(caracteristic.getId()).get());
            carCaracteristic.setValue(caracteristic.getValue());
            carCaracteristicRepository.save(carCaracteristic);
        }
        logger.info(carEntity.getNameCar() + " foi atualizado!");
        return new OutputCarCreateOrUpdateDto(carRepository.findById(id).get());
    }

    public String delete(long id) throws Exception {
        if (carRepository.existsById(id)) {
            carRepository.deleteById(id);
            logger.info("O carro com a id " + id + " foi deletado!");
            return "Este carro foi deletado com sucesso!";
        }
        throw new ResourceNotFoundException("Este carro não existir");
    }

    public List<OutputCarDto> allHighlight() {
        List<OutputCarDto> list = new ArrayList();
        for (CarEntity car : carRepository.findAllHighlight().get()) {
            list.add(new OutputCarDto(car));
        }
        return list;
    }

    public List<OutputCarDto> all(Long idCategory, Long idCity, LocalDate start, LocalDate end) throws Exception {
        logger.trace("Todos os carros foram exibidas!");
        logger.info("Pesquisando os carros por categoria: " + idCategory + " e cidade: " + idCity);
        if (idCategory == null && idCity == null) {
            List<OutputCarDto> list = new ArrayList();
            for (CarEntity car : bookingService.getAvailableCars((List<CarEntity>) carRepository.findAll(), start, end)) {
                list.add(new OutputCarDto(car));
            }
            return list;
        } else if (idCategory == null) {
            if (!carRepository.existsById(idCity)) {
                throw new ResourceNotFoundException("Esta cidade não existir!");
            }
            List<OutputCarDto> list = new ArrayList();
            CitiesEntity cities = new CitiesEntity();
            cities.setId(idCity);
            for (CarEntity car : bookingService.getAvailableCars(List.of(carRepository.findAllByCities(cities).get()), start, end)) {
                list.add(new OutputCarDto(car));
            }
            return list;
        } else if (idCity == null) {
            List<OutputCarDto> list = new ArrayList();
            if (!carRepository.existsById(idCategory)) {
                throw new ResourceNotFoundException("Esta categoria não existir!");
            }
            CategoryEntity category = new CategoryEntity();
            category.setId(idCategory);
            for (CarEntity car : bookingService.getAvailableCars(List.of(carRepository.findAllByCategory(category).get()), start, end)) {
                list.add(new OutputCarDto(car));
            }
            return list;
        } else {
            Boolean errorCategory = false;
            Boolean errorCity = false;

            if (!carRepository.existsById(idCategory)) {
                errorCategory = true;
            }
            if (!carRepository.existsById(idCity)) {
                errorCity = true;
            }

            if (errorCity == true && errorCategory == true) {
                throw new ResourceNotFoundException("Esta cidade e categoria não existir!");
            } else if (errorCity == false && errorCategory == true) {
                throw new ResourceNotFoundException("Esta categoria não existir!");
            } else if (errorCity == true && errorCategory == false) {
                throw new ResourceNotFoundException("Esta cidade não existir!");
            }

            List<OutputCarDto> list = new ArrayList();
            CitiesEntity cities = new CitiesEntity();
            cities.setId(idCity);
            CategoryEntity category = new CategoryEntity();
            category.setId(idCategory);
            for (CarEntity car : bookingService.getAvailableCars(List.of(carRepository.findAllByCategoryAndCities(category, cities).get()), start, end)) {
                list.add(new OutputCarDto(car));
            }
            return list;
        }
    }
}
