package com.hrd.springhomework.repository.provider;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;

public class CategoryProvider {

    public String paginate(@Param("page") int page, @Param("limit") int limit) {
        int offset = limit * (page - 1);
        System.out.println("FUNCTION IS CALLED");
        System.out.println("Page : " + page + " , Limit : " + limit);
        System.out.println("Offset : " + offset);
        return new SQL() {{
            SELECT("*");
            FROM("TB_CATEGORIES LIMIT #{limit} OFFSET " + offset);
        }}.toString();
    }
}
