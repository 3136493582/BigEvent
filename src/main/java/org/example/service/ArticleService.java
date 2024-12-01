package org.example.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import org.example.pojo.Article;

import java.util.List;

/**
 * 文章分类接口
 */
public interface ArticleService {

    /**
     * 添加文章
     * @param article
     * @return
     */
    int addArticle(Article article);

    /**
     * 更新文章
     * @param article
     * @return
     */
    int updateArticle(Article article);

    /**
     * 根据id获取文章
     * @return
     */
    Article getArticleById(Integer id);

    /**
     * 根据id删除文章
     * @param id
     * @return
     */
    int deleteArticleById(Integer id);

    /**
     * 分页查询文章
     * @return
     */
    IPage<Article> getAllArticles(Integer pageNum, Integer pageSize, Integer categoryId, String state, Integer userId);
}
