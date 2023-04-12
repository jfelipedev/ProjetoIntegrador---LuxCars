package projeto.integrador.equipe1.carrosluxo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import projeto.integrador.equipe1.carrosluxo.Dto.input.caracteristic.InputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.caracteristic.OutputCaracteristicReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.image.OutputImageReadDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CaracteristicEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.ImagesEntity;
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
    @Autowired
    private UploadService uploadService;

    public OutputCaracteristicCreateOrUpdateDto create(InputCaracteristicDto caracteristic) throws Exception {
        new CaracteristicValidation(caracteristic);
        logger.info(caracteristic.getName() + " foi adicionado!");
        return new OutputCaracteristicCreateOrUpdateDto(caracteristicRepository.save(new CaracteristicEntity(caracteristic)));
    }

    public OutputCaracteristicReadDto read(Long id) throws Exception {
        logger.trace("A caracteristica com id + " + id + " foi exibindo!");
        if (caracteristicRepository.existsById(id)) {
            return new OutputCaracteristicReadDto(caracteristicRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta caracteristica não está registrado!");
    }

    public OutputCaracteristicCreateOrUpdateDto update(Long id, InputCaracteristicDto caracteristic) throws Exception {
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

    public String delete(Long id) throws Exception {
        if (caracteristicRepository.existsById(id)) {
            caracteristicRepository.deleteById(id);
            logger.info("A caracteristica com a id " + id + " foi deletado!");
            return "Esta caracteristica foi deletado com sucesso!";
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

    public OutputCaracteristicReadDto upload(Long id, MultipartFile file) throws Exception {
        logger.trace("Salvando a  imagem da caracteristica do id " + id);
        if (!caracteristicRepository.existsById(id)) {
            throw new ResourceNotFoundException("Não existir está caracteristica!");
        }
        CaracteristicEntity caracteristic = caracteristicRepository.findById(id).get();
        if (!caracteristic.getIcon().isEmpty()) {
            logger.info("Imagem " + caracteristic.getIcon() + ": " + uploadService.deleteFile(caracteristic.getIcon()));
        }
        caracteristic.setIcon(uploadService.uploadFile(file, "image", id, 100L, 100L, 100L, 100L));
        caracteristicRepository.save(caracteristic);
        return new OutputCaracteristicReadDto(caracteristic);
    }
}
