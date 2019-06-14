package com.hrd.springhomework.repository.CategoryRepository;

import com.hrd.springhomework.repository.model.Category;
import com.hrd.springhomework.repository.provider.CategoryProvider;
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
    List<Category> findAll();

    @Insert("insert into tb_categories(name) values(#{name})")
    void add(Category category);

    @Delete("delete from tb_categories where id = #{id}")
    int remove(Integer id);

    @Select("select * from tb_categories where id = #{id}")
    Category find(Integer id);

    @Update("update tb_categories set name = #{name} where id = #{id}")
    void update(Category category);

    @Select("select count(*) from tb_categories")
    int countCategory();

    @SelectProvider(method = "paginate", type = CategoryProvider.class)
    List<Category> paginate(@Param("page") int page, @Param("limit") int limit);
}
