package com.wandering.trader.services;

import com.wandering.trader.domain.Image;
import com.wandering.trader.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    @Autowired
    ImageRepository imageRepository;

    public void create(Image image){
        imageRepository.save(image);
    }

    public List<Image> getImageList(){
        return imageRepository.findAll();
    }

    public void deleteImage(String key){
        imageRepository.deleteById(key);
    }

    public Optional<Image> findImageByKey(String key){
        return imageRepository.findById(key);
    }
}
