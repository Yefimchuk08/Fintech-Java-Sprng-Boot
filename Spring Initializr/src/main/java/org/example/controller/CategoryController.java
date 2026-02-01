package org.example.crudapp.controller;

import lombok.RequiredArgsConstructor;
import org.example.crudapp.entity.Category;
import org.example.crudapp.service.CategoryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService service;

    // READ
    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", service.getAll());
        model.addAttribute("category", new Category());
        return "categories";
    }

    // CREATE + UPDATE
    @PostMapping
    public String save(@ModelAttribute Category category) {
        service.save(category);
        return "redirect:/categories";
    }

    // DELETE
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        service.delete(id);
        return "redirect:/categories";
    }

    // EDIT
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("category", service.getById(id));
        model.addAttribute("categories", service.getAll());
        return "categories";
    }
}
