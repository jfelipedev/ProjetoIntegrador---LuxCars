package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;

import java.util.Optional;

public interface CityRepository extends CrudRepository<CitiesEntity, Long> {
    Optional<Boolean> existsByNameCity(String nameCity);

    Optional<CitiesEntity> findByNameCity(String nameCity);
}
