package com.hrd.springhomework.repository.CategoryRepository;

import com.hrd.springhomework.repository.model.Category;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository {

    @Select("select * from tb_categories")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name")
    })
    public List<Category> findAll();

    @Insert("insert into tb_categories(name) values(#{name})")
    public void add(Category category);

    @Delete("delete from tb_categories where id = #{id}")
    public int remove(Integer id);

    @Select("select * from tb_categories where id = #{id}")
    public Category find(Integer id);

    @Update("update tb_categories set name = #{name} where id = #{id}")
    public void update(Category category);
}
