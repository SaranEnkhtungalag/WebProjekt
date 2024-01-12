package htwberlin.webtech_Projekt.web.Repositories;
//It allows to perform database operations on the ShoppingList entity.

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {

    boolean existsShoppingListByShoppingName(String shoppingName);

}
