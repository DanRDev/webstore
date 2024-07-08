package com.example.site.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.site.model.Item;
@Repository

public interface ItemRepository extends JpaRepository<Item, Long> {

}
