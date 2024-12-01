package org.example.service;


import org.example.pojo.Category;

import java.util.List;

/**
 * 文章分类接口
 */
public interface CategoryService {

    /**
     * 添加文章分类
     * @param category
     * @return
     */
    int addCategory(Category category);

    /**
     * 根据用户id获取文章分类列表
     * @return
     */
    List<Category> getCategoryByCreateId(Integer createId);

    /**
     * 根据文章分类id获取详情
     * @param id
     * @return
     */
    List<Category> getCategoryById(Integer id);

    /**
     * 根据id更新文章分类
     * @param category
     * @return
     */
    int updateCategory(Category category);

    /**
     * 根据id删除文章分类
     * @param id
     * @return
     */
    int deleteCategory(Integer id);
}
