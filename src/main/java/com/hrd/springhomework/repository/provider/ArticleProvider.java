package com.hrd.springhomework.repository.provider;

import org.apache.ibatis.jdbc.SQL;

public class ArticleProvider {
    public String findAll(){
        return new SQL(){
            {
                SELECT("*");
                FROM("tb_articles");
//                INNER_JOIN("tb_categories on tb_articles.category = tb_categories.id");
            }
        }.toString();
    }

//    public String findPagination(int limit){
//        return new SQL(){{
//            SELECT("*");
//            FROM("tb_articles LIMIT #{limit}");
//        }}.toString();
//    }
}
