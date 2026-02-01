package org.example.crudapp.service;

import lombok.RequiredArgsConstructor;
import org.example.crudapp.entity.Category;
import org.example.crudapp.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository repository;

    public List<Category> getAll() {
        return repository.findAll();
    }

    public Category getById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));
    }

    public void save(Category category) {
        repository.save(category);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
