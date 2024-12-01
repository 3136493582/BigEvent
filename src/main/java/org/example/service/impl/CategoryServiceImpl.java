package org.example.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.example.mapper.CategoryMapper;
import org.example.pojo.Category;
import org.example.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 文章分类实现
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    /**
     * 添加文章分类
     * @param category
     * @return
     */
    @Override
    public int addCategory(Category category) {
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.insert(category);
    }

    /**
     * 根据用户id获取文章分类
     * @param createId
     * @return
     */
    @Override
    public List<Category> getCategoryByCreateId(Integer createId) {
        QueryWrapper<Category> queryWrapper = new QueryWrapper<Category>();
        queryWrapper.eq("create_user", createId);
        return categoryMapper.selectList(queryWrapper);
    }

    /**
     * 根据id获取文章分类详情
     * @param id
     * @return
     */
    @Override
    public List<Category> getCategoryById(Integer id) {
        return categoryMapper.selectList(new QueryWrapper<Category>().eq("id", id));
    }

    /**
     * 根据id更新文章分类
     * @param category
     * @return
     */
    @Override
    public int updateCategory(Category category) {
        category.setUpdateTime(LocalDateTime.now());
        return categoryMapper.updateById(category);
    }

    /**
     * 根据id删除文章分类
     * @param id
     * @return
     */
    @Override
    public int deleteCategory(Integer id) {
        return categoryMapper.deleteById(id);
    }
}
