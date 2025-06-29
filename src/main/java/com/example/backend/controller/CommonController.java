package com.example.backend.controller;

import com.example.backend.common.res.ApiResponse;
import com.example.backend.pojo.Common.vo.EnumsAllVo;
import com.example.backend.service.impl.CommonServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
@RequiredArgsConstructor
public class CommonController {

    private final CommonServiceImpl commonService;

    @GetMapping("/getAllEnums")
    public ApiResponse<EnumsAllVo> getAll() {
        EnumsAllVo result = commonService.getCategoryList();
        return ApiResponse.success(result);
    }
}
