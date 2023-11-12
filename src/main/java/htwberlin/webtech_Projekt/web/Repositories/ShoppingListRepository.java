package htwberlin.webtech_Projekt.web.Repositories;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

}
