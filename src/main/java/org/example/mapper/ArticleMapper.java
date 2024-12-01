package org.example.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import org.example.pojo.Article;
import java.util.List;

@Mapper
public interface ArticleMapper extends BaseMapper<Article> {
    IPage<Article> selectArticlePage(IPage<Article> page, Integer categoryId, String state, Integer userId);
}
