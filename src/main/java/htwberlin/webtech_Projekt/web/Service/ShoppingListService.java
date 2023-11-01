package htwberlin.webtech_Projekt.web.Service;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import htwberlin.webtech_Projekt.web.Repositories.ShoppingListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {

    @Autowired
    ShoppingListRepository repo;

    public ShoppingList save(ShoppingList shoppingList) {
        return repo.save(shoppingList);
    }

    public ShoppingList get(Long id) {
        return repo.findById(id).orElseThrow(() -> new RuntimeException());
    }
}
