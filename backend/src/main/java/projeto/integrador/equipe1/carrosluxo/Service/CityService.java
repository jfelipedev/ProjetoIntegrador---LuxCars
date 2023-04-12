package projeto.integrador.equipe1.carrosluxo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityReadDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CityRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.CityValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class CityService {
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(CityService.class);
    @Autowired
    private CityRepository cityRepository;

    public OutputCityCreateOrUpdateDto create(InputCityDto city) throws Exception {
        new CityValidation(city);
        if (!cityRepository.existsByNameCity(city.getNameCity()).get()) {
            cityRepository.save(new CitiesEntity(city));
            logger.info(city.getNameCity() + " foi adicionado!");
            return new OutputCityCreateOrUpdateDto(cityRepository.findByNameCity(city.getNameCity()).get());
        }
        throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCityDto("Esta cidade já está cadastrada!", null)));
    }

    public OutputCityReadDto read(Long id) throws Exception {
        logger.trace("A cidade com id + " + id + " foi exibindo!");
        if (cityRepository.existsById(id)) {
            return new OutputCityReadDto(cityRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta cidade não está registrada!");
    }

    public OutputCityCreateOrUpdateDto update(Long id, InputCityDto city) throws Exception {
        new CityValidation(city);
        if (cityRepository.existsById(id)) {
            if (!cityRepository.findById(id).get().getNameCity().equals(city.getNameCity())) {
                logger.info("Modificando o nome da cidade do id " + id);
                if (cityRepository.existsByNameCity(city.getNameCity()).get()) {
                    throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCityDto("Já tem o nome registrado nessa cidade!", null)));
                }
            }
            CitiesEntity citiesEntity = new CitiesEntity(city);
            citiesEntity.setId(id);
            cityRepository.save(citiesEntity);
            logger.info(city.getNameCity() + " foi atualizado!");
            return new OutputCityCreateOrUpdateDto(cityRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta cidade não está registrado!");
    }

    public String delete(Long id) throws Exception {
        if (cityRepository.existsById(id)) {
            cityRepository.deleteById(id);
            logger.info("A cidade com a id " + id + " foi deletado!");
            return "Esta cidade foi deletado com sucesso!";
        }
        throw new ResourceNotFoundException("Esta cidade não está registrado!");
    }

    public List<OutputCityDto> all() {
        logger.trace("Todas as cidades foram exibidas!");
        List<OutputCityDto> list = new ArrayList();
        for (CitiesEntity city : cityRepository.findAll()) {
            list.add(new OutputCityDto(city));
        }
        return list;
    }
}
