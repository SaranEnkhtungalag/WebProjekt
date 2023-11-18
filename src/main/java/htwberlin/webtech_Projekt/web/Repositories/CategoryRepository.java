package htwberlin.webtech_Projekt.web.Repositories;

import htwberlin.webtech_Projekt.web.Entities.CategoryEntity;
import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    List<CategoryEntity> findAllByCategoryID(Long categoryID);
    boolean existsCategoryByCategoryName(String categoryName);


}
