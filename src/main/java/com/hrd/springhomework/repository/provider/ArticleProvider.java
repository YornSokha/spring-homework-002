package com.hrd.springhomework.repository.provider;

import com.hrd.springhomework.repository.model.Article;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class ArticleProvider {

    public String paginate(@Param("page") int page, @Param("limit") int limit) {
        int offset = limit * (page - 1);
        System.out.println("Page : " + page + " , Limit : " + limit);
        System.out.println("Offset : " + offset);
        return new SQL() {{
            SELECT("tba.*,tbc.name");
            FROM("TB_ARTICLES AS tba");
            INNER_JOIN("TB_CATEGORIES AS tbc ON tba.category_id = tbc.id LIMIT #{limit} OFFSET " + offset);
        }}.toString();
    }

    public String filter(Article article) {
        System.out.println("Hello : " + article.toString());
        String sql = new SQL() {{
            SELECT("tba.*,tbc.name");
            FROM("TB_ARTICLES AS tba");
            INNER_JOIN("TB_CATEGORIES AS tbc ON tba.category_id = tbc.id");

            if (article.getCategory() != null && article.getCategory().getId() != 0) {
                if (article.getTitle().isEmpty()) {
                    WHERE("tbc.id = #{category.id}");
                } else {
                    WHERE("tba.title ilike '%' || #{title} || '%' AND tbc.id = #{category.id}");
                }
            } else {
                if (!article.getTitle().isEmpty()) {
                    WHERE("tba.title ilike '%' || #{title} || '%'");
                }
            }
        }}.toString();
        System.out.println(sql);
        return sql;
    }

}
