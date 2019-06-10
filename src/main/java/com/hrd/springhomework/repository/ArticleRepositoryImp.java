package com.hrd.springhomework.repository;

import com.hrd.springhomework.repository.ArticleRepository.ArticleRepository;
import com.hrd.springhomework.repository.model.Article;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class ArticleRepositoryImp implements ArticleRepository {
    private List<Article> articleList = new ArrayList<>();
    public static int count;
    public static int currentPage;

    @Override
    public boolean add(Article article) {
        articleList.add(article);
        count = articleList.size();
        return true;
    }

    @Override
    public int getLastId() {
        int size = articleList.size();
        return (size != 0) ? articleList.get(size - 1).getId() : 1;
    }

    private int getIndex(Article article) {
        return articleList.indexOf(articleList.stream()
                .filter(x -> article.getId() == x.getId())
                .findFirst()
                .orElse(null));
    }

    @Override
    public void update(Article article) {
        articleList.set(getIndex(article), article);
    }

    @Override
    public Article find(int id) {
        return articleList.stream().filter(x -> (id == x.getId())).findFirst().orElse(null);
    }

    @Override
    public List<Article> findAll() {
        return articleList;
    }

    @Override
    public List<Article> paginate(int page, int limit) {
        currentPage = page;
        return articleList.stream().skip(page * limit).limit(limit).collect(Collectors.toCollection(ArrayList::new));
    }

    @Override
    public boolean remove(Article article) {
        boolean result = articleList.remove(article);
        count = articleList.size();
        return result;
    }

}
