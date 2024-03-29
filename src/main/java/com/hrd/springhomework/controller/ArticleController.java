package com.hrd.springhomework.controller;

import com.hrd.springhomework.helper.UploadImage;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {

    private ArticleService articleService;
    private CategoryService categoryService;
    // this article is use to store temporary error data while adding article
    private Article article = new Article();

    @Autowired
    public void setArticleService(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping({"", "/article"})
    public String index(@ModelAttribute Article article,
                        Model model,
                        @RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "10") Integer limit,
                        HttpServletRequest httpServletRequest) {

        String queryString = httpServletRequest.getQueryString();
        // check condition whether request is filter or simple
        if (queryString != null && queryString.substring(0, 5).equals("title")) {
            ///////////////////// FILTER ///////////////////////////////////
            List<Article> articleList = articleService.filter(article);
            model.addAttribute("articles", articleList);
            model.addAttribute("currentPage", -1);
            model.addAttribute("lastPage", -1);
            ///////////////////////// END FILTER ////////////////////////////
        } else {
            /////////////////////// INDEX ///////////////////////////////////
            model.addAttribute("currentPage", page);
            int lastPage = (articleService.countArticle() / limit) + (articleService.countArticle() % limit == 0 ? 0 : 1);
            model.addAttribute("lastPage", lastPage);

            //basic validation for pagination
            if (page < 1 || page > lastPage)
                page = 1;
            if(limit != 10)
                limit = 10;

            model.addAttribute("articles", articleService.paginate(page, limit));
            //////////////////////// END INDEX /////////////////////////////////
        }
        model.addAttribute("articleSearch", new Article());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("totalRecord", articleService.countArticle());
        return "/articles/index";
    }

    /////////////////// SEARCH FUNCTION DEPRECATED //////////////////////////
//    @GetMapping("/article/search")
////    public String search(@ModelAttribute Article article, Model model) {
////        Integer categoryId = null;
////        if (article.getCategory() != null) {
////            categoryId = article.getCategory().getId();
////            System.out.println("Category id : " + categoryId);
////        }
////        List<Article> articleList = articleService.filter(article);
////        model.addAttribute("articles", articleList);
////        model.addAttribute("categories", categoryService.findAll());
////        model.addAttribute("articleSearch", new Article());
////        model.addAttribute("categoryId", categoryId);
////        model.addAttribute("currentPage", -1);
////        model.addAttribute("totalRecord", articleService.countArticle());
//////        int lastPage = (articleList.size() / 10) + (articleList.size() % 10 == 0 ? 0 : 1);
////        model.addAttribute("lastPage", 0);
////        return "/articles/index";
////    }
    ////////////////////////////////////////////////////////////////////////////////

    @GetMapping("/create-test")
    public String createTest(ModelMap model) {
        model.addAttribute("article", new Article());
        model.addAttribute("categories", categoryService.findAll());
        return "/articles/create_test";
    }

    @GetMapping("/article/add")
    public String create(ModelMap model) {
        model.addAttribute("article", article);
        model.addAttribute("categories", categoryService.findAll());
        return "/articles/create";
    }

    @PostMapping("/article/add")
    public String add(@Valid @ModelAttribute Article article,
                      BindingResult bindingResult,
                      MultipartFile file, Model model) {

        if (bindingResult.hasErrors()) {
            System.out.println(bindingResult.toString());
            model.addAttribute("categories", categoryService.findAll());
            this.article = article; // to store temporary error data
            model.addAttribute("article", article);
            return "/articles/create";
        }

        UploadImage.upload(article, file);
        articleService.add(article);

        // reset article;
        this.article = new Article();
        return "redirect:/article/add";
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


    @GetMapping("navbar")
    public String getNabar() {
        return "fragment/navbar :: navbar";
    }

    @GetMapping("navbar-dropdown")
    public String getDropDownNavbar() {
        return "fragment/navbar :: navbar-dropdown";
    }
}
