package projeto.integrador.equipe1.carrosluxo.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryAllDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.error.ErrorCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CategoryRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.CategoryValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    ObjectMapper objectMapper = new ObjectMapper();
    Logger logger = LoggerFactory.getLogger(CategoryService.class);
    @Autowired
    private CategoryRepository categoryRepository;

    public OutputCategoryCreateOrUpdateDto create(InputCategoryDto category) throws Exception {
        new CategoryValidation(category);
        if (!categoryRepository.existsByQualification(category.getQualification()).get()) {
            categoryRepository.save(new CategoryEntity(category));
            logger.info(category.getQualification() + " foi adicionado!");
            return new OutputCategoryCreateOrUpdateDto(categoryRepository.findByQualification(category.getQualification()).get());
        }
        throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCategoryDto(null, "Esta categoria já está cadastrado!", null)));
    }

    public OutputCategoryReadDto read(long id) throws Exception {
        logger.trace("A categoria com id + " + id + "Foi exibindo!");
        if (categoryRepository.existsById(id)) {
            return new OutputCategoryReadDto(categoryRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta categoria não existir");
    }

    public OutputCategoryCreateOrUpdateDto update(long id, InputCategoryDto category) throws Exception {
        new CategoryValidation(category);
        if (categoryRepository.existsById(id)) {
            if (!categoryRepository.findById(id).get().getQualification().equals(category.getQualification())) {
                logger.info("Modificando a qualification do id " + id);
                if (categoryRepository.existsByQualification(category.getQualification()).get()) {
                    throw new BadRequestException(objectMapper.writeValueAsString(new ErrorCategoryDto(null, "qualification especificado já está em uso", null)));
                }
            }
            CategoryEntity categoryEntity = new CategoryEntity(category);
            categoryEntity.setId(id);
            categoryRepository.save(categoryEntity);
            logger.info(category.getQualification() + "foi atualizado!");
            return new OutputCategoryCreateOrUpdateDto(categoryRepository.findById(id).get());
        }
        throw new ResourceNotFoundException("Esta categoria não existir");
    }

    public String delete(long id) throws Exception {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            logger.info("A categoria com a id " + id + " foi deletado!");
            return "Esta categoria foi deletado com sucesso!";
        }
        throw new ResourceNotFoundException("Esta categoria não existir");
    }

    public List<OutputCategoryAllDto> all() {
        logger.trace("Todas as categorias foram exibidas!");
        List<OutputCategoryAllDto> list = new ArrayList();
        for (CategoryEntity category : categoryRepository.findAll()) {
            list.add(new OutputCategoryAllDto(category, category.getCars().size()));
        }
        return list;
    }
}
