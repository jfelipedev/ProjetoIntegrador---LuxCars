package projeto.integrador.equipe1.carrosluxo.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Repository.CategoryRepository;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    public CategoryRepository categoryRepository;

    public String create(CategoryDto category){
        if(!categoryRepository.existsByDescritpion(category.getDescritpion())){
            categoryRepository.save(category.toEntity());
            return "O nome da fabricante foi cadastrado com sucesso!";
        }
        return "Esta fabricante já está cadastrado!";
    }

    public CategoryDto read(long id){
        //if(producerRepository.existsById(id)){
            return new CategoryDto(categoryRepository.findById(id));
        //}
    }
    public String update(long id, CategoryDto producer){
        if(categoryRepository.existsById(id)){
            CategoryEntity categoryEntity = new CategoryEntity(producer);
            categoryEntity.setId(id);
            categoryRepository.save(categoryEntity);
            return "Dados da fabricante foi atualizado!";
        }
        return "Fabricante não localizado!";
    }
    public String delete(long id){
        if(categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return "Fabricante deletado!";
        }
        return "Fabricante não localizado!";
    }

    public List<CategoryEntity> all(){
        return (List<CategoryEntity>) categoryRepository.findAll();
    }
}
