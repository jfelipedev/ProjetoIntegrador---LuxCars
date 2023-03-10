package projeto.integrador.equipe1.carrosluxo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.input.car.InputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.Car.OutputCarReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.error.ErrorCarDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CarRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.CaracteristicRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.CategoryRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.CityRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.CarValidation;

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

    public OutputCarCreateOrUpdateDto create(InputCarDto car) throws Exception {
        new CarValidation(car);
        if (carRepository.existsByNameCar(car.getNameCar()).get()) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto("Este carro já está cadastrado!", null, null, null)));
        }
        if (!categoryRepository.existsById(car.getIdCategory())) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto(null, "Esta categoria não existe!", null, null)));
        }
        if (!cityRepository.existsById(car.getIdCity())) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto(null, null, "Esta cidade não está registrado!", null)));
        }
        for (Long idCaCaracteristic : car.getIdCaracteristics()) {
            if (!caracteristicRepository.existsById(car.getIdCity())) {
                throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto(null, null, null, "Contém uma caracteristica que não existir!")));
            }
        }
        carRepository.save(new CarEntity(car));
        logger.info(car.getNameCar() + " foi adicionado!");
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
        if (carRepository.existsByNameCar(car.getNameCar()).get()) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto("Este carro já está cadastrado!", null, null, null)));
        }
        if (!categoryRepository.existsById(car.getIdCategory())) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto(null, "Esta categoria não existe!", null, null)));
        }
        if (!cityRepository.existsById(car.getIdCity())) {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto(null, null, "Esta cidade não está registrado!", null)));
        }
        for (Long idCaCaracteristic : car.getIdCaracteristics()) {
            if (!caracteristicRepository.existsById(car.getIdCity())) {
                throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCarDto(null, null, null, "Contém uma caracteristica que não existir!")));
            }
        }
        CarEntity carEntity = new CarEntity(car);
        carEntity.setId(id);
        carRepository.save(carEntity);
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

    public  List<OutputCarDto> allHighlight(){
        List<OutputCarDto> list = new ArrayList();
        for (CarEntity car : carRepository.findAllHighlight().get()) {
            list.add(new OutputCarDto(car));
        }
        return list;
    }

    public List<OutputCarDto> all(Long idCategory, Long idCity) throws Exception {
        logger.trace("Todos os carros foram exibidas!");
        logger.info("Pesquisando os carros por categoria: " + idCategory + " e cidade: " + idCity);
        if (idCategory == null && idCity == null) {
            List<OutputCarDto> list = new ArrayList();
            for (CarEntity car : carRepository.findAll()) {
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
            for (CarEntity car : carRepository.findAllByCities(cities).get()) {
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
            for (CarEntity car : carRepository.findAllByCategory(category).get()) {
                list.add(new OutputCarDto(car));
            }
            return list;
        } else {
            if (!carRepository.existsById(idCategory)) {
                throw new ResourceNotFoundException("Esta categoria não existir!");
            }
            if (!carRepository.existsById(idCity)) {
                throw new ResourceNotFoundException("Esta cidade não existir!");
            }
            List<OutputCarDto> list = new ArrayList();
            CitiesEntity cities = new CitiesEntity();
            cities.setId(idCity);
            CategoryEntity category = new CategoryEntity();
            category.setId(idCategory);
            for (CarEntity car : carRepository.findAllByCategoryAndCities(category, cities).get()) {
                list.add(new OutputCarDto(car));
            }
            return list;
        }
    }
}
