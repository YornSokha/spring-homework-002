package com.hrd.springhomework.controller;

import com.hrd.springhomework.repository.CategoryRepository.CategoryRepository;
import com.hrd.springhomework.service.ArticleService.ArticleService;
import com.hrd.springhomework.service.CategoryService.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller
public class AppController {

    ArticleService articleService;
    CategoryService categoryService;

//    @Autowired
//    public void setArticleService(ArticleService articleService){
//        this.articleService = articleService;
//    }
//    @Autowired
//    public void setCategoryService(CategoryService)
//
//
//    @GetMapping("")
//    public String index(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
//        System.out.println("Page : " + page + ", Limit : " + limit);
//        page--;
//        System.out.println(articleService.findAll().size());
//        // page - 1 because paginate array list starts with 0, so need to minus 1
//        model.addAttribute("articles", articleService.findAll());
//        model.addAttribute("categories", categoryRepository)
////        model.addAttribute("totalRecord", ArticleRepositoryImp.count);
////        model.addAttribute("currentPage", ArticleRepositoryImp.currentPage + 1);
////        int lastPage = (ArticleRepositoryImp.count / limit) + (ArticleRepositoryImp.count % limit == 0 ? 0 : 1);
////        model.addAttribute("lastPage", lastPage);
//        return "/articles/index";
//    }
}
