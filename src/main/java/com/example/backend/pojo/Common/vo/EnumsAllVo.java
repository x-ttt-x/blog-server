package com.example.backend.pojo.Common.vo;

import com.example.backend.dao.Category;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class EnumsAllVo {

    @Data
    private static class CategoryCommVo {
        private int categoryId;
        private String name;
    }

    private List<CategoryCommVo> categoryList = new ArrayList<>();


    public void setCategoryList(List<Category> DbCategoryList) {
        DbCategoryList.forEach(category -> {
            CategoryCommVo vo = new CategoryCommVo();
            vo.setCategoryId(category.getCategoryId());
            vo.setName(category.getName());
            categoryList.add(vo);
        });
    }

}
