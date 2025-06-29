package com.example.backend.controller;

import com.example.backend.common.exception.BusinessException;
import com.example.backend.common.res.ApiResponse;
import com.example.backend.common.res.ListData;
import com.example.backend.dao.Tag;
import com.example.backend.pojo.Tag.Dto.TagDto;
import com.example.backend.pojo.Tag.Vo.TagVo;
import com.example.backend.repository.TagRep;
import com.example.backend.service.impl.TagServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tag")
public class TagController {

    private final TagServiceImpl tagService;

    @PostMapping
    public ApiResponse<Tag> create(@RequestBody TagDto tagDto) {
        tagService.create(tagDto);
        return ApiResponse.success();
    }

    @PutMapping("/{id}")
    public ApiResponse<Tag> update(@RequestBody TagDto tagDto, @PathVariable int id) {
        tagService.update(tagDto, id);
        return ApiResponse.success();
    }


    @GetMapping("/{id}")
    public ApiResponse<TagVo> findById(@PathVariable int id) {
        return ApiResponse.success(tagService.CovertToVo(tagService.get(id)));
    }

    @GetMapping
    public ApiResponse<ListData<TagVo>> getAll() {
        List<Tag> tagList = tagService.get();
        List<TagVo> tagVoList = new ArrayList<>();
        tagList.forEach(tag -> tagVoList.add(tagService.CovertToVo(tag)));
        ListData<TagVo> res = ListData.<TagVo>builder()
                .list(tagVoList)
                .total(tagService.count())
                .build();
        return ApiResponse.success(res);
    }


    @DeleteMapping("/{id}")
    public ApiResponse<Tag> delete(@PathVariable int id) {
        tagService.delete(id);
        return ApiResponse.success();
    }

    @ExceptionHandler(BusinessException.class)
    public ApiResponse<Tag> handleBusinessException(BusinessException e) {
        return ApiResponse.error(e.getEResCode());
    }

}
