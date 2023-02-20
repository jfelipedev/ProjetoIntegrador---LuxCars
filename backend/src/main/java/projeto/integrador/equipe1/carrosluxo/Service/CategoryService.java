package projeto.integrador.equipe1.carrosluxo.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.GlobalException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Repository.CategoryRepository;
import projeto.integrador.equipe1.carrosluxo.Validation.CategoryValidation;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    Logger logger = LoggerFactory.getLogger(GlobalException.class);

    public String create(CategoryDto category) throws Exception {
        new CategoryValidation(category);
        if(!categoryRepository.existsByDescritpion(category.getDescritpion())){
            categoryRepository.save(category.toEntity());
            logger.info(category.getDescritpion() + " foi adicionado!");
            return "A categoria foi cadastrado com sucesso!";
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
    public String update(long id, CategoryDto category) throws Exception {
        new CategoryValidation(category);
        if(categoryRepository.existsById(id)){
            CategoryEntity categoryEntity = new CategoryEntity(category);
            categoryEntity.setId(id);
            categoryRepository.save(categoryEntity);
            logger.info(category.getDescritpion() + "foi atualizado!");
            return "Esta categoria foi atualizado!";
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

    public List<CategoryEntity> all(){
        logger.trace("Todas as categorias foram exibidas!");
        return (List<CategoryEntity>) categoryRepository.findAll();
    }
}
