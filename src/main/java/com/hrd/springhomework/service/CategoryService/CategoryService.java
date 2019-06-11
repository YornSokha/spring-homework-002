package com.hrd.springhomework.service.CategoryService;

import com.hrd.springhomework.repository.model.Category;

import java.util.List;

public interface CategoryService {
    void add(Category article);
    int remove(int id);
    List<Category> findAll();

    List<Category> paginate(int page, int limit);

    Category find(int id);

    void update(Category article);

    int getLastId();
}
