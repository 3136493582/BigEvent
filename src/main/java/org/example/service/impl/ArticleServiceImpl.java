package org.example.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.ArticleMapper;
import org.example.pojo.Article;
import org.example.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * 文章接口实现
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    /**
     * 添加文章
     * @param article
     * @return
     */
    @Override
    public int addArticle(Article article) {
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());
        return articleMapper.insert(article);
    }

    /**
     * 更新文章
     * @param article
     * @return
     */
    @Override
    public int updateArticle(Article article) {
        return articleMapper.updateById(article);
    }

    @Override
    public Article getArticleById(Integer id) {
        return articleMapper.selectById(id);
    }

    @Override
    public int deleteArticleById(Integer id) {
        return articleMapper.deleteById(id);
    }

    @Override
    public IPage<Article> getAllArticles(Integer pageNum, Integer pageSize, Integer categoryId, String state, Integer userId) {
        IPage<Article> page = new Page<>();
        page.setCurrent(pageNum);
        page.setSize(pageSize);
        IPage<Article> articles = articleMapper.selectArticlePage(page, categoryId, state, userId);
        return articles;
    }


}
