package htwberlin.webtech_Projekt.web.api;

import htwberlin.webtech_Projekt.web.Entities.Category;
import htwberlin.webtech_Projekt.web.Service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;


@RestController
public class CategoryRestController {

    @Autowired
    private CategoryService categoryService;

    private Logger logger = LoggerFactory.getLogger(CategoryRestController.class);

    private List<Category> categories;

    // the constructor can be deleted if we don't want to define an already existing categories
    public CategoryRestController(CategoryService categoryService) {
        //categories = new ArrayList<>();
        //categories.add(new Category(1L,"Lebensmittel"));
        //categories.add(new Category(2L, "Reinigunsmittel"));
        //categories.add(new Category(3L, "Kleidung"));

        this.categoryService = categoryService;
    }

    @PostConstruct
    public void init() {
        // code to add default categories

        // Check if categories already exist
        if (!categoryService.existsByName("Lebensmittel")) {
            categoryService.save(new Category(1L,"Lebensmittel"));
        }

        if (!categoryService.existsByName("Reinigungsmittel")) {
            categoryService.save(new Category(2L,"Reinigungsmittel"));
        }

        if (!categoryService.existsByName("Kleidung")) {
            categoryService.save(new Category(3L,"Kleidung"));
        }

    }

    /*
    @GetMapping("/categories")
    public ResponseEntity<List<Category>> fetchCategories() {
        return ResponseEntity.ok(categoryService.findAll());
    }
     */

    @PostMapping("/categories")
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category createdCategory = categoryService.save(category);
        return ResponseEntity.ok(createdCategory);
    }

    @GetMapping("/categories")
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categories = categoryService.findAll();
        return ResponseEntity.ok(categories);
    }

    @GetMapping("/categories/{id}")
    public ResponseEntity<Category> getCategory(@PathVariable Long id) {
        Category category = categoryService.findById(id);
        return ResponseEntity.ok(category);
    }


    @PutMapping("/categories/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        Category updatedCategoryResult = categoryService.update(id, updatedCategory);
        return ResponseEntity.ok(updatedCategoryResult);
    }

    @DeleteMapping("/categories/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id) {
        categoryService.delete(id);
        return ResponseEntity.ok("Category deleted successfully");
    }

    @DeleteMapping("/categories/deleteAll")
    public ResponseEntity<String> deleteAllCategories() {
        categoryService.deleteAllCategories();
        return ResponseEntity.ok("All categories deleted successfully");
    }



}
