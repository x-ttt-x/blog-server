package com.example.backend.service.impl;

import com.example.backend.dao.Category;
import com.example.backend.pojo.Common.vo.EnumsAllVo;
import com.example.backend.repository.CategoryRep;
import com.example.backend.service.ICommonService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommonServiceImpl implements ICommonService {
    private final CategoryRep categoryRep;

    public EnumsAllVo getCategoryList() {
        List<Category> categoryDbList = categoryRep.findAll();
        EnumsAllVo vo = new EnumsAllVo();
        vo.setCategoryList(categoryDbList);
        return vo;
    }
}
