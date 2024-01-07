package htwberlin.webtech_Projekt.web.Repositories;
//It allows to perform database operations on the ShoppingList entity.

import htwberlin.webtech_Projekt.web.Entities.ShoppingList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import htwberlin.webtech_Projekt.web.Entities.ShoppingList; // Ensure this import is correct

@Repository
public interface ShoppingListRepository extends CrudRepository<ShoppingList, Long> {


}
