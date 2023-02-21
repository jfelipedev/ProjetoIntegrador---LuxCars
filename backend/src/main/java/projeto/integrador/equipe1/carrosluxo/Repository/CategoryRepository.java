package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {
    CategoryEntity findById(long id);
    boolean existsByDescritpion(String descritpion);
}
