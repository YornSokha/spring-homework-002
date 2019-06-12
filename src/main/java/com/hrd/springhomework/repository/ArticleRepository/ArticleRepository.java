package com.hrd.springhomework.repository.ArticleRepository;

import com.hrd.springhomework.repository.model.Article;
import com.hrd.springhomework.repository.provider.ArticleProvider;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository {
    @Insert("insert into tb_articles(title, author, description, image, category_id) values(#{title}, #{author}, #{description}, #{image}, #{category.id})")
    boolean add(Article article);

    @Delete("delete from tb_articles where id = #{id}")
    boolean remove(Article article);

    @Select("SELECT tba.*,tbc.name FROM TB_ARTICLES as tba INNER JOIN TB_CATEGORIES AS tbc ON tba.category_id = tbc.id")
//    @SelectProvider(method = "findAll", type = ArticleProvider.class)
    @Results({
            @Result(property = "category.id", column = "category_id", jdbcType = JdbcType.INTEGER),
            @Result(property = "category.name", column = "name", jdbcType = JdbcType.VARCHAR)
    })
    List<Article> findAll();

    @Select("SELECT tba.*,tbc.name FROM TB_ARTICLES as tba INNER JOIN TB_CATEGORIES AS tbc ON tba.category_id = tbc.id where tba.id = #{id}")
    @Results({
            @Result(property = "category.name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "category.id", column = "category_id", jdbcType = JdbcType.INTEGER)
    })
    Article find(int id);

    @SelectProvider(method = "filter", type = ArticleProvider.class)
    @Results({
            @Result(property = "category.name", column = "name", jdbcType = JdbcType.VARCHAR),
            @Result(property = "category.id", column = "category_id", jdbcType = JdbcType.INTEGER)
    })
    List<Article> filter(Article article);


    List<Article> paginate(int page, int limit);

    @Update("update tb_articles set title=#{title}, author=#{author}, description=#{description}, image=#{image}, category_id=#{category.id} where id=#{id}")
    void update(Article article);

    int getLastId();
}
