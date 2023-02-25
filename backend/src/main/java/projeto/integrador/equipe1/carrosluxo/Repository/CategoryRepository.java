package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

import java.util.List;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity findById(long id);
    List<CategoryEntity> findAll();

    boolean existsByQualification(String qualification);
}
