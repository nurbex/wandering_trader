package com.wandering.trader.controllers;

import com.wandering.trader.domain.Image;
import com.wandering.trader.services.FileStorageService;
import com.wandering.trader.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/admin/files")
public class FileHandlerController {
    @Autowired
    FileStorageService fileStorageService;

    @Autowired
    ImageService imageService;

    @GetMapping
    public String showFilesAndUpload(Model model){

        model.addAttribute("files", imageService.getImageList());

        return "file_list_upload";
    }
    @PostMapping
    public String uploadFiles(@RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes){
        System.out.println("content type "+file.getContentType());

        if (file.getContentType().contains("image")){
           fileStorageService.store(file);
           imageService.create(new Image(file.getOriginalFilename(), String.valueOf((fileStorageService.buildUrl(file.getOriginalFilename())))));
            redirectAttributes.addFlashAttribute("message", "Uploaded successfully");
        } else {
            redirectAttributes.addFlashAttribute("error", "I accept only Images.");
        }
        return "redirect:/admin/files";
    }

    @GetMapping("/delete")
    public String deleteImage(@RequestParam String key, RedirectAttributes redirectAttributes){

        if(!imageService.findImageByKey(key).isEmpty()){
            imageService.deleteImage(key);
            fileStorageService.deleteFileByKey(key);
        }

        redirectAttributes.addFlashAttribute("message", key+" is deleted successfully");

        return "redirect:/admin/files";
    }
}
