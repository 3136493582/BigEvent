package org.example.controller;

import jakarta.annotation.Resource;
import org.example.pojo.Category;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.CategoryService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Resource
    private CategoryService categoryService;

    @Resource
    private ThreadLocalUtil threadLocalUtil;

    /**
     * 添加文章分类
     * @param category
     * @return
     */
    @PostMapping
    public Result addCategory(@RequestBody @Validated Category category) {
        User user=threadLocalUtil.get();
        category.setCreateUser(user.getId());
        categoryService.addCategory(category);
        return Result.success();
    }

    /**
     * 根据用户id获取文章分类
     * @return
     */
    @GetMapping
    public  Result getCategoryByCreateId() {
        User user=threadLocalUtil.get();
        Integer createId=user.getId();
        List<Category> category = categoryService.getCategoryByCreateId(createId);
        return Result.success(category);
    }

    /**
     * 根据文章分类id获取详情
     * @param id
     * @return
     */
    @GetMapping("detail")
    public Result getCategoryDetail(@RequestParam Integer id) {
        return Result.success(categoryService.getCategoryById(id));
    }

    /**
     * 根据id更新文章分类
     * @param category
     * @return
     */
    @PutMapping
    public Result updateCategory(@RequestBody @Validated Category category) {
        categoryService.updateCategory(category);
        return Result.success();
    }
    @DeleteMapping
    public Result deleteCategory(@RequestParam Integer id) {
        categoryService.deleteCategory(id);
        return Result.success();
    }
}
