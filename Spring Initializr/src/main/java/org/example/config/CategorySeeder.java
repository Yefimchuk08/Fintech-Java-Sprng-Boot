package org.example.crudapp.config;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.crudapp.entity.Category;
import org.example.crudapp.repository.CategoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        // Перевіряємо чи є категорії
        if (categoryRepository.count() == 0) {
            Faker faker = new Faker();
            List<Category> categories = new ArrayList<>();

            // Згенеруємо 10 категорій
            for (int i = 0; i < 10; i++) {
                Category category = new Category();
                category.setName(faker.commerce().department());
                categories.add(category);
            }

            categoryRepository.saveAll(categories);
            System.out.println("Seed: додано 10 категорій у базу!");
        } else {
            System.out.println("Категорії вже є, сід не потрібен");
        }
    }
}
