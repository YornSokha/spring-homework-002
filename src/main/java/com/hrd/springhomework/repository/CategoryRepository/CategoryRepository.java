package com.hrd.springhomework.repository.CategoryRepository;

import com.hrd.springhomework.repository.model.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @Select("select * from category_tb")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    public List<Category> findAll();

    @Insert("insert into category_tb(name) values(#{name})")
    public void add(Category category);

    @Delete("delete * from category_tb where id = #{id}")
    public void remove(Integer id);

    @Select("select * from category_tb where id = #{id}")
    public Category find(Integer id);

    @Update("update category_tb set name = #{name} where id = #{id}")
    public void update(Category category);
}
