package com.rnzhiw.ibicycle.service;

import com.rnzhiw.ibicycle.form.ResourceForm;
import com.rnzhiw.ibicycle.model.dto.ResourceDTO;
import org.springframework.data.domain.Sort;

import java.util.List;

/**
 * 资源业务接口
 *
 * @author fuenhui
 * @date 2018/12/12
 */
public interface ResourceService {

    /**
     * 默认权重排序
     */
    Sort WEIGHT_SORT = new Sort(Sort.Direction.DESC, "weight");

    /**
     * 列出所有资源,并按条件排序
     *
     * @param sort
     * @return
     */
    List<ResourceDTO> findAll(Sort sort);

    /**
     * 添加资源
     *
     * 注意：
     * 调用此方法时应保证form的存在性且form的属性满足Validated判断条件
     * 否则不可调用此方法
     *
     * @param resourceForm
     * @return
     */
    ResourceDTO save(ResourceForm resourceForm);

    /**
     * 编辑资源
     *
     * 注意：
     * 调用此方法时应保证form的存在性且form的属性满足Validated判断条件
     * 否则不可调用此方法
     *
     * @param resourceForm
     * @return
     */
    ResourceDTO update(ResourceForm resourceForm);

    /**
     * 根据roleId查询对应的权限信息
     *
     * @param id
     * @return
     */
    List<ResourceDTO> getResources(Long id);

    /**
     * 根据id获取资源
     *
     * @param id
     * @return
     */
    ResourceDTO findOne(Long id);

    /**
     * 根据id删除资源
     *
     * @param id
     * @return
     */
    boolean delete(Long id);
}
