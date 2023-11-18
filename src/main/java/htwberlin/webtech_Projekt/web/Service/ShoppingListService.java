package htwberlin.webtech_Projekt.web.Service;
//This is a service class responsible for implementing business logic related to shopping lists.

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;


@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository repo;

    public ShoppingList save(ShoppingList shoppingList) {
        return repo.save(shoppingList);
    }

    public ShoppingList get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException("Shopping list not found"));
    }

    public List<ShoppingList> getAllShoppingLists() {
        return (List<ShoppingList>) repo.findAll();
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public ShoppingList update(Long id, ShoppingList updatedShoppingList) {
        ShoppingList existingShoppingList = get(id);

        if (updatedShoppingList.getShoppingName() != null) {
            existingShoppingList.setShoppingName(updatedShoppingList.getShoppingName());
        }
        if (updatedShoppingList.isDone() != existingShoppingList.isDone()) {
            existingShoppingList.setDone(updatedShoppingList.isDone());
        }
        if (updatedShoppingList.getDeadline() != null) {
            existingShoppingList.setDeadline(updatedShoppingList.getDeadline());
        }
        return repo.save(existingShoppingList);
    }


}
