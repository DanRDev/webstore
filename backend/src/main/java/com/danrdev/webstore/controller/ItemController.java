package com.danrdev.webstore.controller;

import com.danrdev.webstore.model.Item;
import com.danrdev.webstore.service.ItemService;
import org.antlr.v4.runtime.misc.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/upload")
    public String uploadForm(Model model) {
        model.addAttribute("image", new Item());
        return "upload";
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file, Model model) {
        try {
            itemService.saveItem(file);
            model.addAttribute("message", "Image uploaded successfully!");
        } catch (IOException e) {
            model.addAttribute("message", "Failed to upload image: " + e.getMessage());
        }
        return "upload";
    }

    @GetMapping("/items")
    public String listItems(Model model) {

        Map<Long, Pair<String ,String>> items = itemService.getAllItems().stream().collect(Collectors.toMap(
                Item::getId,
                item -> new Pair<>(item.getName(), Base64.getEncoder().encodeToString(item.getData()))
        ));
        model.addAttribute("items", items); // Add the list to the model
        return "itemList"; // Return the name of the Thymeleaf template (itemList.html)
    }

    @GetMapping("/items/{id}")
    public String viewItem(@PathVariable("id") Long id, Model model) throws IOException {
        Item item = itemService.getItemById(id).orElseThrow(() -> new RuntimeException("Item not found"));
        if (item.getData() != null) {
            String base64Image = Base64.getEncoder().encodeToString(item.getData());
            model.addAttribute("base64Image", base64Image);
        }
        model.addAttribute("item", item);
        return "view";
    }
}