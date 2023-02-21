package projeto.integrador.equipe1.carrosluxo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.CarDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.GlobalException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CarRepository;

import java.util.List;

@Service
public class CarService {
    @Autowired
    private CarRepository carRepository;
    Logger logger = LoggerFactory.getLogger(GlobalException.class);

    public String create(CarDto carDto) throws Exception {
        if(!carRepository.existsByNameCar(carDto.getNameCar())){
            carRepository.save(carDto.toEntity());
            logger.info(carDto.getNameCar() + " foi adicionado!");
            return "Este carro foi cadastrado com sucesso!";
        }
        throw new BadRequestException("Este carro já está cadastrado!");
    }
    public CarDto read(long id) throws Exception {
        logger.trace("O carro com id + " + id + " foi exibindo!");
        if(carRepository.existsById(id)){
            return new CarDto(carRepository.findById(id));
        }
        throw new ResourceNotFoundException("Este carro não existir");
    }
    public String update(long id, CarDto carDto) throws Exception {
        if(carRepository.existsById(id)){
            if(carRepository.existsByNameCar(carDto.getNameCar())) {
                throw new BadRequestException("Este carro já está cadastrado!");
            }
            CarEntity carEntity = new CarEntity(carDto);
            carEntity.setId(id);
            carRepository.save(carEntity);
            logger.info(carEntity.getNameCar() + " foi atualizado!");
            return "Este carro foi atualizado!";
        }
        throw new ResourceNotFoundException("Este carro não existir");
    }
    public String delete(long id) throws Exception {
        if(carRepository.existsById(id)) {
            carRepository.deleteById(id);
            logger.info("O carro com a id " + id + " foi deletado!");
            return "Este carro foi deletado com sucesso!";
        }
        throw new ResourceNotFoundException("Este carro não existir");
    }
    public List<CarEntity> all(){
        logger.trace("Todos os carros foram exibidas!");
        return (List<CarEntity>) carRepository.findAll();
    }
}