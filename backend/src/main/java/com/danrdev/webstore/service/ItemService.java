package com.danrdev.webstore.service;

import com.danrdev.webstore.repository.ItemRepository;
import com.danrdev.webstore.model.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService {
    @Autowired
    ItemRepository itemRepository;

    public void saveItem(MultipartFile file) throws IOException {
        Item image = new Item();
        image.setName(file.getOriginalFilename());
        image.setData(file.getBytes());
        itemRepository.save(image);
    }

    public Optional<Item> getItemById(Long id) {
        return itemRepository.findById(id);
    }

    public List<Item> getItemsList() {
        return itemRepository.findAll();
    }

    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }
}
