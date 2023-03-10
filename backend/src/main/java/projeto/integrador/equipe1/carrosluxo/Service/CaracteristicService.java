package projeto.integrador.equipe1.carrosluxo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicReadDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CaracteristicRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.CaracteristicValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class CaracteristicService {
    Logger logger = LoggerFactory.getLogger(CaracteristicService.class);
    @Autowired
    private CaracteristicRepository caracteristicRepository;

    public OutputCaracteristicCreateOrUpdateDto create(InputCaracteristicDto caracteristic) throws Exception {
        new CaracteristicValidation(caracteristic);
        logger.info(caracteristic.getName() + " foi adicionado!");
        return new OutputCaracteristicCreateOrUpdateDto(caracteristicRepository.save(new CaracteristicEntity(caracteristic)));
    }

    public OutputCaracteristicReadDto read(long id) throws Exception {
        logger.trace("A caracteristica com id + " + id + " foi exibindo!");
        if (caracteristicRepository.existsById(id)) {
            return new OutputCaracteristicReadDto(caracteristicRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta caracteristica não está registrado!");
    }

    public OutputCaracteristicCreateOrUpdateDto update(long id, InputCaracteristicDto caracteristic) throws Exception {
        new CaracteristicValidation(caracteristic);
        if (caracteristicRepository.existsById(id)) {
            CaracteristicEntity caracteristicEntity = new CaracteristicEntity(caracteristic);
            caracteristicEntity.setId(id);
            caracteristicRepository.save(caracteristicEntity);
            logger.info(caracteristic.getName() + " foi atualizado!");
            return new OutputCaracteristicCreateOrUpdateDto(caracteristicRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta caracteristica não está registrado!");
    }

    public String delete(long id) throws Exception {
        if (caracteristicRepository.existsById(id)) {
            caracteristicRepository.deleteById(id);
            logger.info("A caracteristica com a id " + id + " foi deletado!");
            return "Esta caracteristica foi deleto com sucesso!";
        }
        throw new ResourceNotFoundException("Esta caracteristica não está registrado!");
    }

    public List<OutputCaracteristicDto> all() {
        logger.trace("Todos as caracteristicas foram exibidas!");
        List<OutputCaracteristicDto> list = new ArrayList();
        for (CaracteristicEntity caracteristicEntity : caracteristicRepository.findAll()) {
            list.add(new OutputCaracteristicDto(caracteristicEntity));
        }
        return list;
    }
}
