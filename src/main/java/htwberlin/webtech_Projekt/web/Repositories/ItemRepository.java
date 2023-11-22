package htwberlin.webtech_Projekt.web.Repositories;

import htwberlin.webtech_Projekt.web.Entities.ItemEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findAllByItemID(Long itemID);


}
