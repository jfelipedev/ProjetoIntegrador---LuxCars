package projeto.integrador.equipe1.carrosluxo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.input.image.InputImageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorImageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageReadDto;
import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CarRepository;
import projeto.integrador.equipe1.carrosluxo.Repository.ImageRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.ImageValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class ImageService {
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(ImageService.class);
    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private CarRepository carRepository;

    public OutputImageCreateOrUpdateDto create(InputImageDto image) throws Exception {
        new ImageValidation(image);
        logger.info(image.getTitle() + " foi adicionado!");
        if (carRepository.existsById(image.getIdCar())) {
            return new OutputImageCreateOrUpdateDto(imageRepository.save(new ImagesEntity(image)));
        } else {
            throw new BadRequestException(objectMapper.writeValueAsString(new ErrorImageDto(null, null, "Este carro não Existir")));
        }
    }

    public OutputImageReadDto read(long id) throws Exception {
        logger.trace("A imagem com id + " + id + " foi exibindo!");
        if (imageRepository.existsById(id)) {
            return new OutputImageReadDto(imageRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta imagem não está registrada!");
    }

    public OutputImageCreateOrUpdateDto update(long id, InputImageDto image) throws Exception {
        new ImageValidation(image);
        if (imageRepository.existsById(id)) {
            if (!carRepository.existsById(image.getIdCar())) {
                throw new BadRequestException(objectMapper.writeValueAsString(new ErrorImageDto(null, null, "Este carro não Existir")));
            }
            ImagesEntity imagesEntity = new ImagesEntity(image);
            imagesEntity.setId(id);
            imageRepository.save(imagesEntity);
            logger.info(image.getTitle() + " foi atualizado!");
            return new OutputImageCreateOrUpdateDto(imageRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta imagem não está registrado!");
    }

    public String delete(long id) throws Exception {
        if (imageRepository.existsById(id)) {
            imageRepository.deleteById(id);
            logger.info("A imagem com a id " + id + " foi deletado!");
            return "Esta imagem foi deletado com sucesso!";
        }
        throw new ResourceNotFoundException("Esta imagem não está registrado!");
    }

    public List<OutputImageDto> all() {
        logger.trace("Todas as imagens foram exibidas!");
        List<OutputImageDto> list = new ArrayList();
        for (ImagesEntity image : imageRepository.findAll()) {
            list.add(new OutputImageDto(image));
        }
        return list;
    }
}
