package projeto.integrador.equipe1.carrosluxo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import projeto.integrador.equipe1.carrosluxo.Dto.CategoryDto;
import projeto.integrador.equipe1.carrosluxo.Entity.CategoryEntity;
import projeto.integrador.equipe1.carrosluxo.Service.CategoryService;

import java.util.List;

@RestController
public class ProducerController {
    @Autowired
    CategoryService categoryService;

    @GetMapping("/producer")
    List<CategoryEntity> all(){
        return categoryService.all();
    }

    @PostMapping("/producer")
    String create(@RequestBody CategoryDto categoryDto) throws Exception {
        return categoryService.create(categoryDto);
    }

    @GetMapping("/producer/{id}")
    CategoryDto read(@PathVariable int id) throws Exception {
        return categoryService.read(id);
    }

    @PutMapping("/producer/{id}")
    String update(@PathVariable int id, @RequestBody CategoryDto categoryDto) throws Exception {
        return categoryService.update(id, categoryDto);
    }

    @DeleteMapping("/producer/{id}")
    String delete(@PathVariable int id) throws Exception {
        return categoryService.delete(id);
    }
}
