package com.hrd.springhomework.repository.ArticleRepository;

import com.hrd.springhomework.repository.model.Article;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {
    @Insert("insert into articles_tb(title, author, description, image, category) values(#{title}, #{author}, #{description}, #{image}, #{category}")
    boolean add(Article article);

    @Delete("delete from articles_tb where id = #{id}")
    boolean remove(Article article);
    @Select("select * from articles_tb")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "title", column = "title"),
            @Result(property = "author" , column = "author"),
            @Result(property = "image", column = "image"),
            @Result(property = "category", column = "category")
    })
    List<Article> findAll();

    @Select("select * from articles_tb where id = #{id}")
    Article find(int id);


    List<Article> paginate(int page, int limit);

    @Update("update articles_tb set title=#{title}, author=#{author}, description=#{description}, image=#{image}, category=#{category} where id=#{id}")
    void update(Article article);

    int getLastId();
}
