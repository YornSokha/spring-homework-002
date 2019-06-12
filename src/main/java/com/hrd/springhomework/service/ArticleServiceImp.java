package com.hrd.springhomework.service;

import com.hrd.springhomework.repository.ArticleRepository.ArticleRepository;
import com.hrd.springhomework.repository.model.Article;
import com.hrd.springhomework.service.ArticleService.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {
    private ArticleRepository articleRepository;

    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Override
    public List<Article> filter(Article article) {
        return articleRepository.filter(article);
    }

    @Override
    public int getLastId() {
        return articleRepository.getLastId();
    }

    @Override
    public List<Article> paginate(int page, int limit) {
        return articleRepository.paginate(page, limit);
    }

    @Override
    public void update(Article article) {
        articleRepository.update(article);
    }

    @Override
    public Article find(int id) {
        return articleRepository.find(id);
    }

    @Override
    public void add(Article article) {
        articleRepository.add(article);
    }

    @Override
    public List<Article> findAll() {
        return articleRepository.findAll();
    }

    @Override
    public boolean remove(int id) {
        Article article = articleRepository.findAll().stream().filter(x -> (id == x.getId())).findFirst().orElse(null);
        return articleRepository.remove(article);
    }

}
