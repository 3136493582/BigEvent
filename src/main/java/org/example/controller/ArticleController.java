package org.example.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import jakarta.annotation.Resource;
import org.example.pojo.Article;
import org.example.pojo.Result;
import org.example.pojo.User;
import org.example.service.ArticleService;
import org.example.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/article")
public class ArticleController {
    @Resource
    private ArticleService articleService;

    @Autowired
    private ThreadLocalUtil threadLocalUtil;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @PostMapping
    public Result addArticle(@RequestBody @Validated Article article) {
        User user = threadLocalUtil.get();
        article.setCreateUser(user.getId());
        articleService.addArticle(article);
        return Result.success();
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    @PutMapping
    public Result updateArticle(@RequestBody @Validated Article article) {
        articleService.updateArticle(article);
        return Result.success();
    }

    /**
     * 根据id获取文章
     * @param id
     * @return
     */
    @GetMapping("/detail")
    public Result getArticleById(@RequestParam Integer id) {
        return Result.success(articleService.getArticleById(id));
    }

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    @DeleteMapping
    public Result deleteArticle(@RequestParam Integer id) {
        articleService.deleteArticleById(id);
        return Result.success();
    }

    @GetMapping
    public Result getAllArticles(@RequestParam Integer pageNum,
                                 @RequestParam Integer pageSize,
                                 @RequestParam(required = false) Integer categoryId,
                                 @RequestParam(required = false) String state) {
        User user= threadLocalUtil.get();
        Integer userId = user.getId();
        IPage<Article> articles=articleService.getAllArticles(pageNum, pageSize, categoryId, state,userId);
        return Result.success(articles);
    }
}
