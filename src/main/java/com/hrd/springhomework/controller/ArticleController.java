package com.hrd.springhomework.controller;

import com.hrd.springhomework.helper.UploadImage;
import com.hrd.springhomework.repository.ArticleRepositoryImp;
import com.hrd.springhomework.repository.model.Article;
import com.hrd.springhomework.service.ArticleService.ArticleService;
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
public class ArticleController {

    private ArticleService articleService;
    private CategoryService categoryService;

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
//        generateRecord();
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService){
        this.categoryService = categoryService;
    }

//    private void generateRecord() {
//        String[] imageNames = {"731cc916-7a4e-4574-9a21-f012e03ad17f.jpg",
//                "08d6a795-5973-4681-b885-ad85e9b8ca3f.jpg",
//                "673c875b-0598-45a1-a260-0e4baf971059.jpg",
//                "340d2d90-efa0-4831-8340-a2d911be506e.jpg",
//                "4751943d-5818-4666-bdd8-22350fe3679c.jpg",
//                "cedab41e-6176-452f-8264-d086d98dd550.jpg"};
//        String description = "Dogs (Canis lupus familia1ris) are domesticated mammals, " +
//                "not natural wild animals. They were originally bred from wolves. They have " +
//                "been bred by humans for a long time, and were the first animals ever to be domesticated.";
//        for (int i = 0; i < 78; i++) {
//            int index = i % imageNames.length;
//            articleService.add(new Article(i + 1, "A story of a dog", "Yorn Sokha", description, imageNames[index]));
//        }
//    }

    @GetMapping({"", "/article"})
    public String index(Model model, @RequestParam(defaultValue = "1") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        System.out.println("Page : " + page + ", Limit : " + limit);
        page--;
        System.out.println(articleService.findAll().size());
        // page - 1 because paginate array list starts with 0, so need to minus 1
        model.addAttribute("articles", articleService.findAll());
//        model.addAttribute("totalRecord", ArticleRepositoryImp.count);
//        model.addAttribute("currentPage", ArticleRepositoryImp.currentPage + 1);
//        int lastPage = (ArticleRepositoryImp.count / limit) + (ArticleRepositoryImp.count % limit == 0 ? 0 : 1);
//        model.addAttribute("lastPage", lastPage);
        return "/articles/index";
    }

    @GetMapping("/article/create")
    public String create(Model model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryService.findAll());
        return "/articles/create";
    }

    @DeleteMapping("/article/{id}")
    public String delete(@PathVariable int id) {
        System.out.println(id);
        articleService.remove(id);
        return "redirect:/article";
    }

    @GetMapping("/article/show/{id}")
    public String show(@PathVariable int id, Model model) {
        Article article = articleService.find(id);
        System.out.println("ID = " + id);
        System.out.println(article.toString());
        model.addAttribute("article", article);
        return "/articles/show";
    }

    @GetMapping("/article/edit/{id}")
    public String edit(ModelMap model, @PathVariable int id) {
        model.addAttribute("article", articleService.find(id));
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("org.springframework.validation.BindingResult.article", model.get("errorObject"));
        return "/articles/edit";
    }

    @PostMapping("/article/update")
    public String update(@Valid @ModelAttribute Article article,
                         BindingResult bindingResult,
                         RedirectAttributes redirectAttributes,
                         MultipartFile file) {

        if (bindingResult.hasErrors()) {
            System.out.println("there is an error");
            redirectAttributes.addFlashAttribute("errorObject", bindingResult);
            return "redirect:/article/edit/" + article.getId();
        }

        String currentImage = articleService.find(article.getId()).getImage();

        if (!UploadImage.upload(article, file)) {
            article.setImage(currentImage);
        }

        articleService.update(article);
        return "redirect:/";
    }

    @PostMapping("/article/add")
    public String add(@Valid @ModelAttribute Article article,
                      BindingResult bindingResult,
                      RedirectAttributes redirectAttributes,
                      MultipartFile file) {

        if (bindingResult.hasErrors()) {
            redirectAttributes.addFlashAttribute("errorObject", bindingResult);
            return "/articles/create";
        }

        System.out.println(article);

        UploadImage.upload(article, file);
        articleService.add(article);
        return "redirect:/article/create";
    }


    @GetMapping("navbar")
    public String getNabar() {
        return "fragment/navbar :: navbar";
    }

    @GetMapping("navbar-dropdown")
    public String getDropDownNavbar() {
        return "fragment/navbar :: navbar-dropdown";
    }
}
