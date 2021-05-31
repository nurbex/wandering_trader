package com.wandering.trader.services;

import com.wandering.trader.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class FileStorageService {

    @Value("${app.upload.location}")
    public String storageLocation;

    public void init(){}

    public void store(MultipartFile file) {
        Path uploadLocation = Paths.get(storageLocation
                + "\\"
                + StringUtils.cleanPath(file.getOriginalFilename()));

        try {
            Files.copy(file.getInputStream(), uploadLocation, StandardCopyOption.REPLACE_EXISTING);
            //System.out.println("URI will be "+ buildUrl(file.getOriginalFilename()).getURI());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Path buildUrl(String filename){

        Path path = Paths.get("\\images\\").resolve(filename);
/*        try {
            Resource resource = new UrlResource(path.toUri());
            return resource;
        } catch (MalformedURLException e) {
            throw new RuntimeException(e.getMessage());
        }*/
        return path;
    }


/*    public Stream<Path> loadAll() {
        Path root = Paths.get(storageLocation);
        try {
            return Files.walk(root, 1).filter(path -> !path.equals(root)).map(root :: relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load files");
        }
    }*/

    public void deleteFileByKey(String key){
        try {
            Files.delete(Paths.get(storageLocation + "\\" + key));
            System.out.println((Paths.get(storageLocation + "\\" + key)).toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
