package com.example.backend.service.impl;

import com.example.backend.common.enums.impl.ECommStatus;
import com.example.backend.common.enums.others.EResCode;
import com.example.backend.common.exception.BusinessException;
import com.example.backend.dao.Tag;
import com.example.backend.pojo.Tag.Dto.TagDto;
import com.example.backend.pojo.Tag.Vo.TagVo;
import com.example.backend.repository.TagRep;
import com.example.backend.service.ITagService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TagServiceImpl implements ITagService {

    private final TagRep tagRep;

    /// 查询条件条件-未被删除
    private final Specification<Tag> spec_active = (root, query, cb) -> cb.equal(root.get("status"), ECommStatus.ACTIVE);


    @Override
    public void create(TagDto tagDto) {
        if (isExist(tagDto.getName())) {
            throw new BusinessException(EResCode.NAME_REPEAT);
        }
        Tag tag = new Tag();
        BeanUtils.copyProperties(tagDto, tag);
        tagRep.save(tag);
    }

    public void update(TagDto tagDto, int id) {
        if (isExist(tagDto.getName(), id)) {
            throw new BusinessException(EResCode.NAME_REPEAT);
        }
        Tag tag = tagRep.findById(id).orElse(null);
        if (tag == null || tag.getStatus() == ECommStatus.DELETED) {
            throw new BusinessException(EResCode.NOT_FOUND);
        } else {
            BeanUtils.copyProperties(tagDto, tag);
            tagRep.save(tag);
        }
    }

    public void delete(int id) {
        Tag tag = tagRep.findById(id).orElse(null);
        if (tag == null || tag.getStatus() == ECommStatus.DELETED) {
            throw new BusinessException(EResCode.NOT_FOUND);
        } else {
            tag.setStatus(ECommStatus.DELETED);
            tagRep.save(tag);
        }
    }

    public List<Tag> get() {
        return tagRep.findAll(spec_active);
    }

    public Tag get(int id) {
        Tag tag = tagRep.findById(id).orElse(null);
        if (tag == null || tag.getStatus() == ECommStatus.DELETED) {
            throw new BusinessException(EResCode.NOT_FOUND);
        } else {
            return tag;
        }
    }

    public int count() {
        return Math.toIntExact(tagRep.count());
    }

    public boolean isExist(String name) {
        Specification<Tag> spec_same_name = (root, query, cb) -> cb.equal(root.get("name"), name);
        return !tagRep.findAll(spec_same_name).isEmpty();
    }

    /// 排除id
    public boolean isExist(String name, int excludeId) {
        Specification<Tag> spec_different_id = (root, query, cb) -> cb.equal(root.get("tagId"), excludeId).not();
        Specification<Tag> spec_same_name = (root, query, cb) -> cb.equal(root.get("name"), name);
        return !tagRep.findAll(spec_same_name.and(spec_different_id)).isEmpty();
    }

    public TagVo CovertToVo(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag, tagVo);
        return tagVo;
    }
}
