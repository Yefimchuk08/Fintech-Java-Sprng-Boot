package org.example.crudapp.service;

import org.springframework.stereotype.Service;

@Service
public class ImageService {

    public String saveImageFromUrl(String imageUrl) {
        // Тут може бути реальне завантаження картинки
        // Для seed достатньо повернути URL
        return imageUrl;
    }
}
