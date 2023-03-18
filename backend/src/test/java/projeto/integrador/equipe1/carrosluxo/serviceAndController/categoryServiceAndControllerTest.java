package projeto.integrador.equipe1.carrosluxo.serviceAndController;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import projeto.integrador.equipe1.carrosluxo.Controller.CategoryController;
import projeto.integrador.equipe1.carrosluxo.Dto.input.category.InputCategoryDto;
import projeto.integrador.equipe1.carrosluxo.Dto.input.city.InputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryAllDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.category.OutputCategoryReadDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityCreateOrUpdateDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityDto;
import projeto.integrador.equipe1.carrosluxo.Dto.output.city.OutputCityReadDto;
import projeto.integrador.equipe1.carrosluxo.Exception.BadRequestException;
import projeto.integrador.equipe1.carrosluxo.Exception.ResourceNotFoundException;
import projeto.integrador.equipe1.carrosluxo.Service.CategoryService;

import java.util.List;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class categoryServiceAndControllerTest {
    @Autowired
    CategoryService categoryService;

    @Autowired
    CategoryController categoryController;

    @Test
    void createValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCategoryCreateOrUpdateDto category = categoryService.create(new InputCategoryDto("Carros SUVs","http://img.carlux.com/suvs.png","survs"));
            Assertions.assertEquals(3, category.getId());
        });
    }

    @Test
    void createInvalid() {
        Assertions.assertEquals("{\"descritpion\":null,\"qualification\":\"Esta categoria já está cadastrado!\",\"urlImage\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            categoryService.create(new InputCategoryDto("Carros sem o teto", "http://teste.com/teste.png", "Conversivel"));
        }).getMessage());
    }

    @Test
    void readValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCategoryReadDto category = categoryService.read(1);
            Assertions.assertEquals("Conversivel", category.getQualification());
        });
    }

    @Test
    void readInvalid() {
        Assertions.assertEquals("Esta categoria não existir", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.read(10);
        }).getMessage());
    }

    @Test
    void updateValid() {
        Assertions.assertDoesNotThrow(() -> {
            OutputCategoryCreateOrUpdateDto category = categoryService.update(1, new InputCategoryDto("Carros sem o teto", "http://img.carlux.com/category/suvs.png","SUVs"));
            Assertions.assertEquals("Carros sem o teto", category.getDescritpion());
            Assertions.assertEquals("SUVs", category.getQualification());
            category = categoryService.update(1, new InputCategoryDto("São carros suvs!", "http://img.carlux.com/category/suvs.png","SUVs"));
            Assertions.assertEquals("São carros suvs!", category.getDescritpion());
            Assertions.assertEquals("SUVs", category.getQualification());
            category = categoryService.update(1, new InputCategoryDto("Carros sem o teto", "http://img.carlux.com/category/conversivel.png","Conversivel"));
            Assertions.assertEquals("Carros sem o teto", category.getDescritpion());
            Assertions.assertEquals("Conversivel", category.getQualification());
        });
    }

    @Test
    void updateInvalidIdCategory() {
        Assertions.assertEquals("Esta categoria não existir", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.update(10, new InputCategoryDto("São carros suvs!", "http://img.carlux.com/category/suvs.png","SUVs"));
        }).getMessage());
    }

    @Test
    void updateInvalidQualification() {
        Assertions.assertEquals("{\"descritpion\":null,\"qualification\":\"qualification especificado já está em uso\",\"urlImage\":null}", Assertions.assertThrows(BadRequestException.class, () -> {
            categoryService.update(1, new InputCategoryDto("São carros sedans", "http://teste.com/sedans.png", "Sedans"));
        }).getMessage());
    }

    @Test
    void deleteValid() {
        Assertions.assertDoesNotThrow(() -> {
            String categoty = categoryService.delete(1);
            Assertions.assertEquals("Esta categoria foi deletado com sucesso!", categoty);
        });
    }

    @Test
    void deleteInvalid() {
        Assertions.assertEquals("Esta categoria não existir", Assertions.assertThrows(ResourceNotFoundException.class, () -> {
            categoryService.delete(10);
        }).getMessage());
    }

    @Test
    void serviceAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            List<OutputCategoryAllDto> list = categoryService.all();
            Assertions.assertEquals(2,list.size());
            Assertions.assertEquals(1,list.get(0).getId());
            Assertions.assertEquals(2,list.get(1).getId());
            Assertions.assertEquals("Conversivel", list.get(0).getQualification());
            Assertions.assertEquals("Sedans", list.get(1).getQualification());
        });
    }

    @Test
    void ControllerAllTest() {
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<List<OutputCategoryAllDto>> response = (ResponseEntity<List<OutputCategoryAllDto>>) categoryController.all();
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals(1, response.getBody().get(0).getId());
            Assertions.assertEquals("Conversivel", response.getBody().get(0).getQualification());
        });
    }

    @Test
    void controllerCreateTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCategoryCreateOrUpdateDto> response = (ResponseEntity<OutputCategoryCreateOrUpdateDto>) categoryController.create(new InputCategoryDto("Carros SUVs","http://img.carlux.com/suvs.png","survs"));
            Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());
            Assertions.assertEquals(3, response.getBody().getId());
        });
    }

    @Test
    void controllerReadTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCategoryReadDto> response = (ResponseEntity<OutputCategoryReadDto>) categoryController.read(1);
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("Conversivel", response.getBody().getQualification());
        });
    }

    @Test
    void controllerUpdateTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<OutputCategoryCreateOrUpdateDto> response = (ResponseEntity<OutputCategoryCreateOrUpdateDto>) categoryController.update(1, new InputCategoryDto("Carros SUVs","http://img.carlux.com/suvs.png","survs"));
            Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());
            Assertions.assertEquals("survs", response.getBody().getQualification());
        });
    }

    @Test
    void controllerDeleteTest(){
        Assertions.assertDoesNotThrow(() -> {
            ResponseEntity<String> response = (ResponseEntity<String>) categoryController.delete(1);
            Assertions.assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
            Assertions.assertEquals("Esta categoria foi deletado com sucesso!", response.getBody());
        });
    }
}
