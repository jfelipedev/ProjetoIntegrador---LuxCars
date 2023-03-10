package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<CategoryEntity, Long> {

    Optional<Boolean> existsByQualification(String qualification);

    Optional<CategoryEntity> findByQualification(String qualification);
}
