package com.hrd.springhomework.controller;

import com.hrd.springhomework.helper.UploadImage;
import com.hrd.springhomework.repository.model.Article;
import com.hrd.springhomework.repository.model.Category;
import com.hrd.springhomework.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
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

    private CategoryService categoryService;

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping("/category")
    public String index(Model model){
        model.addAttribute("categories", categoryService.findAll());
        return "categories/index";
    }

    @GetMapping("/category/create")
    public String create(ModelMap model) {
        model.addAttribute("category", new Category());
        return "/categories/create";
    }

    @PostMapping("/category/add")
    public String add(@Valid @ModelAttribute Category category,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorObject", bindingResult);
            return "/categories/create";
        }
        categoryService.add(category);
        return "redirect:/category/create";
    }

    @DeleteMapping("/category/{id}")
    public String delete(@PathVariable int id) {
        categoryService.remove(id);
        return "redirect:/category";
    }
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
    @GetMapping("/category/edit/{id}")
    public String edit(ModelMap model, @PathVariable int id) {
        model.addAttribute("category", categoryService.find(id));
        model.addAttribute("org.springframework.validation.BindingResult.category", model.get("errorObject"));
        return "/categories/edit";
    }

    @PostMapping("/category/update")
    public String update(@Valid @ModelAttribute Category category,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes) {

        if (bindingResult.hasErrors()) {
            System.out.println("there is an error");
            redirectAttributes.addFlashAttribute("errorObject", bindingResult);
            return "redirect:/category/edit/" + category.getId();
        }

        categoryService.update(category);
        return "redirect:/category";
    }

}
