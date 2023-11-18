package htwberlin.webtech_Projekt.web.Service;

import htwberlin.webtech_Projekt.web.Entities.Category;
import htwberlin.webtech_Projekt.web.Entities.CategoryEntity;
import htwberlin.webtech_Projekt.web.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import htwberlin.webtech_Projekt.web.Entities.Category;
import htwberlin.webtech_Projekt.web.Entities.CategoryEntity;
import htwberlin.webtech_Projekt.web.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;


@Service
public class CategoryService {

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<Category> findAll(){
        List<CategoryEntity> categories = repo.findAll();
        return categories.stream().map(categoryEntity -> new Category(
                categoryEntity.getCategoryID(),
                categoryEntity.getCategoryName()
        )).collect(Collectors.toList());
    }


    public Category findById(Long id) {
        CategoryEntity categoryEntity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new Category(categoryEntity.getCategoryID(), categoryEntity.getCategoryName());
    }

    public Category save(Category category) {
        CategoryEntity categoryEntity = new CategoryEntity(category.getCategoryID() , category.getCategoryName());
        return mapToCategory(repo.save(categoryEntity));
    }

    public Category update(Long id, Category updatedCategory) {
        CategoryEntity existingCategory = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Update the existing category
        existingCategory.setCategoryName(updatedCategory.getCategoryName());

        return mapToCategory(repo.save(existingCategory));
    }

    private Category mapToCategory(CategoryEntity categoryEntity) {
        return new Category(categoryEntity.getCategoryID(), categoryEntity.getCategoryName());
    }

    public boolean existsByName(String categoryName) {
        return repo.existsCategoryByCategoryName(categoryName);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAllCategories() {
        repo.deleteAll();
    }

}
