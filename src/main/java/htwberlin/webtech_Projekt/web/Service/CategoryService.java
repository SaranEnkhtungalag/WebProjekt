package htwberlin.webtech_Projekt.web.Service;

import htwberlin.webtech_Projekt.web.Entities.Category;
import htwberlin.webtech_Projekt.web.Entities.CategoryEntity;
import htwberlin.webtech_Projekt.web.Repositories.CategoryRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Service
public class CategoryService {

    @PersistenceContext
    private EntityManager entityManager;

    private final CategoryRepository repo;

    public CategoryService(CategoryRepository repo) {
        this.repo = repo;
    }

    public List<CategoryEntity> findAll(){
        List<CategoryEntity> categories = repo.findAll();
        return categories.stream().map(categoryEntity -> new CategoryEntity(
                categoryEntity.getCategoryID(),
                categoryEntity.getCategoryName()
        )).collect(Collectors.toList());
    }


    public CategoryEntity findById(Long id) {
        CategoryEntity categoryEntity = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
        return new CategoryEntity(categoryEntity.getCategoryID(), categoryEntity.getCategoryName());
    }

    public CategoryEntity save(CategoryEntity newCategory) {
        CategoryEntity categoryEntity = new CategoryEntity(newCategory.getCategoryID(), newCategory.getCategoryName());
        return mapToCategory(repo.save(categoryEntity));
    }


    public CategoryEntity update(Long id, CategoryEntity updatedCategory) {
        CategoryEntity existingCategory = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Update the existing category
        existingCategory.setCategoryName(updatedCategory.getCategoryName());

        return mapToCategory(repo.save(existingCategory));
    }



    private CategoryEntity mapToCategory(CategoryEntity categoryEntity) {
        return new CategoryEntity(categoryEntity.getCategoryID(), categoryEntity.getCategoryName());
    }

    // this method is used to predefine categories in CategoryRestController
    public boolean existsByName(String categoryName) {
        return repo.existsCategoryByCategoryName(categoryName);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public void deleteAllCategories() {
        repo.deleteAll();

        // Reset the sequence for the ID column (assuming it's auto-incrementing)
        //resetCategorySequence();
    }

    /*
    // to reset the categories IDs
    private void resetCategorySequence() {
        // The exact syntax might depend on the database you're using.
        // Below is an example for PostgreSQL; adjust it based on your database.

        String resetSequenceQuery = "ALTER SEQUENCE category_id_seq RESTART WITH 1";

        // Execute the native query to reset the sequence
        entityManager.createNativeQuery(resetSequenceQuery).executeUpdate();
    }

     */

}
