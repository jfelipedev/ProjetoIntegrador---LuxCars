package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Entity.CitiesEntity;

import java.util.Optional;

public interface CarRepository extends CrudRepository<CarEntity, Long> {
    Optional<Boolean> existsByNameCar(String nameCar);

    Optional<CarEntity> findByNameCar(String nameCar);

    Optional<CarEntity[]> findAllByCategory(CategoryEntity category);

    Optional<CarEntity[]> findAllByCities(CitiesEntity cities);

    Optional<CarEntity[]> findAllByCategoryAndCities(CategoryEntity category, CitiesEntity cities);

    @Query("SELECT c FROM cars c WHERE highlight=true")
    Optional<CarEntity[]> findAllHighlight();
}
