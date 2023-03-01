package projeto.integrador.equipe1.carrosluxo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.CarFullDto;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryFullDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.GlobalException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CategoryRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.CategoryValidation;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    Logger logger = LoggerFactory.getLogger(GlobalException.class);

    public CarFullDto create(CategoryDto category) throws Exception {
        new CategoryValidation(category);
        if (!categoryRepository.existsByQualification(category.getQualification())) {
            categoryRepository.save(category.toEntity());
            logger.info(category.getQualification() + " foi adicionado!");
            return categoryRepository.findByQualification(category.getQualification());
        }
        throw new BadRequestException("Esta categoria já está cadastrado!");
    }

    public CategoryDto read(long id) throws Exception {
        logger.trace("A categoria com id + " + id + "Foi exibindo!");
        if(categoryRepository.existsById(id)){
            return new CategoryDto(categoryRepository.findById(id));
        }
        throw new ResourceNotFoundException("Esta categoria não existir");
    }
    public CategoryFullDto update(long id, CategoryDto category) throws Exception {
        new CategoryValidation(category);
        if(categoryRepository.existsById(id)){
            if(!categoryRepository.findById(id).getQualification().equals(category.getQualification())) {
                logger.info("Modificando a qualification do id " + id);
                if(categoryRepository.existsByQualification(category.getQualification())){
                    throw new BadRequestException("Erro: qualification especificado já está em uso");
                }
            }
            CategoryEntity categoryEntity = category.toEntity();
            categoryEntity.setId(id);
            categoryRepository.save(categoryEntity);
            logger.info(category.getQualification() + "foi atualizado!");
            return new CategoryFullDto(categoryRepository.findById(id));
        }
        throw new ResourceNotFoundException("Esta categoria não existir");
    }
    public String delete(long id) throws Exception {
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            logger.info("A categoria com a id " + id + " foi deletado!");
            return "Esta categoria foi deletado com sucesso!";
        }
        throw new ResourceNotFoundException("Esta categoria não existir");
    }

    public List<CategoryFullDto> all(){
        logger.trace("Todas as categorias foram exibidas!");
        List<CategoryFullDto> list = new ArrayList();
        for(CategoryEntity category: categoryRepository.findAll()){
            list.add(new CategoryFullDto(category));
        }
        return list;
    }
}
