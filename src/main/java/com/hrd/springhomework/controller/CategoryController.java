package com.hrd.springhomework.controller;

import com.hrd.springhomework.helper.UploadImage;
import com.hrd.springhomework.repository.model.Article;
import com.hrd.springhomework.repository.model.Category;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class CategoryController {
//    @GetMapping("/category/create")
//    public String create(Model model) {
//        model.addAttribute("article", new Category());
//        return "/categories/create";
//    }
//
//    @DeleteMapping("/category/{id}")
//    public String delete(@PathVariable int id) {
////        articleService.remove(id);
//        return "redirect:/article";
//    }
//
//    @GetMapping("/article/show/{id}")
//    public String show(@PathVariable int id, Model model) {
//        Article article = articleService.find(id);
//        System.out.println("ID = " + id);
//        System.out.println(article.toString());
//        model.addAttribute("article", article);
//        return "/articles/show";
//    }
//
//    @GetMapping("/article/edit/{id}")
//    public String edit(ModelMap model, @PathVariable int id) {
//        model.addAttribute("article", articleService.find(id));
//        model.addAttribute("org.springframework.validation.BindingResult.article", model.get("errorObject"));
//        return "/articles/edit";
//    }
//
//    @PostMapping("/article/update")
//    public String update(@Valid @ModelAttribute Article article,
//                         BindingResult bindingResult,
//                         RedirectAttributes redirectAttributes,
//                         MultipartFile file) {
//
//        if (bindingResult.hasErrors()) {
//            System.out.println("there is an error");
//            redirectAttributes.addFlashAttribute("errorObject", bindingResult);
//            return "redirect:/article/edit/" + article.getId();
//        }
//
//        String currentImage = articleService.find(article.getId()).getImage();
//
//        if (!UploadImage.upload(article, file)) {
//            article.setImage(currentImage);
//        }
//
//        articleService.update(article);
//        return "redirect:/";
//    }
//
//    @PostMapping("/article/add")
//    public String add(@Valid @ModelAttribute Article article,
//                      BindingResult bindingResult,
//                      RedirectAttributes redirectAttributes,
//                      MultipartFile file) {
//
//        if (bindingResult.hasErrors()) {
//            redirectAttributes.addFlashAttribute("errorObject", bindingResult);
//            return "/articles/create";
//        }
//
//        UploadImage.upload(article, file);
//        articleService.add(article);
//        return "redirect:/article/create";
//    }
}
