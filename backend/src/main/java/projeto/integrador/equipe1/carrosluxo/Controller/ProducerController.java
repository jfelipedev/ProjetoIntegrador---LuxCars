package projeto.integrador.equipe1.carrosluxo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projeto.integrador.equipe1.carrosluxo.Dto.ProducerDto;
import projeto.integrador.equipe1.carrosluxo.Entity.ProducerEntity;
import projeto.integrador.equipe1.carrosluxo.Service.ProducerService;

import java.util.List;

@RestController
public class ProducerController {
    @Autowired
    ProducerService producerService;

    @GetMapping("/producer")
    List<ProducerEntity> all(){
        return producerService.all();
    }

    @PostMapping("/producer")
    String create(@RequestBody ProducerDto producerDto){
        return producerService.create(producerDto);
    }

    @GetMapping("/producer/{id}")
    ProducerDto read(@PathVariable int id){
        return producerService.read(id);
    }

    @PutMapping("/producer/{id}")
    String update(@PathVariable int id, @RequestBody ProducerDto producerDto){
        return producerService.update(id, producerDto);
    }

    @DeleteMapping("/producer/{id}")
    String delete(@PathVariable int id){
        return producerService.delete(id);
    }
}
