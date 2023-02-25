package projeto.integrador.equipe1.carrosluxo.Controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import projeto.integrador.equipe1.carrosluxo.Dto.CarDto;
import projeto.integrador.equipe1.carrosluxo.Dto.CarFullDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CarEntity;
import projeto.integrador.equipe1.carrosluxo.Service.CarService;

import java.util.List;

@RestController
@SecurityRequirement(name = "Bearer Authentication")
@Tag(name = "Car", description = "Carros")

public class CarController {
    @Autowired
    private CarService carService;

    @GetMapping(value = "/car")
    @Operation(summary = "Exibir lista de todas as carros", tags = {"Car"})
    public List<CarFullDto> all() {
        return carService.all();
    }

    @PostMapping(value = "/car")
    @Operation(summary = "Registrar uma novo carro", tags = {"Car"})
    public String create(@RequestBody CarDto carDto) throws Exception {
        return carService.create(carDto);
    }

    @GetMapping(value = "/car/{id}")
    @Operation(summary = "Exibir um carro especifico", tags = {"Car"})
    public CarDto read(@PathVariable int id) throws Exception {
        return carService.read(id);
    }

    @PutMapping(value = "/car/{id}")
    @Operation(summary = "Atualizar um carro especificp", tags = {"Car"})
    public String update(@PathVariable int id, @RequestBody CarDto carDto) throws Exception {
        return carService.update(id, carDto);
    }

    @DeleteMapping(value = "/car/{id}")
    @Operation(summary = "Remover uma carro especificp", tags = {"Car"})
    public String delete(@PathVariable int id) throws Exception {
        return carService.delete(id);
    }
}
