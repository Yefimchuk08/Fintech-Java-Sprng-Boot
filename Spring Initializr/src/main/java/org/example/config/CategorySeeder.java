package org.example.crudapp.config;

import com.github.javafaker.Faker;
import lombok.RequiredArgsConstructor;
import org.example.crudapp.entity.Category;
import org.example.crudapp.repository.CategoryRepository;
import org.example.crudapp.service.ImageService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CategorySeeder implements CommandLineRunner {

    private final CategoryRepository categoryRepository;
    private final ImageService imageService;

    @Override
    public void run(String... args) {

        if (categoryRepository.count() == 0) {
            Faker faker = new Faker();
            List<Category> categories = new ArrayList<>();

            for (int i = 0; i < 10; i++) {
                Category category = new Category();
                category.setName(faker.commerce().department());

                String imageUrl = imageService.saveImageFromUrl(
                        "https://picsum.photos/300/300?random=" + i
                );

                category.setImageUrl(imageUrl);
                categories.add(category);
            }

            categoryRepository.saveAll(categories);
            System.out.println("Seed: додано 10 категорій з фото!");
        } else {
            System.out.println("Категорії вже існують, seed не потрібен");
        }
    }
}
