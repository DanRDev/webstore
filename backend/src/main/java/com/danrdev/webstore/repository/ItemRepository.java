package com.danrdev.webstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.danrdev.webstore.model.Item;
@Repository

public interface ItemRepository extends JpaRepository<Item, Long> {

}
