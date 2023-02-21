package projeto.integrador.equipe1.carrosluxo.Repository;

import org.springframework.data.repository.CrudRepository;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;

public interface CarRepository extends CrudRepository<CarEntity, Long> {
    CarEntity findById(long id);
    boolean existsByNameCar(String nameCar);
}
