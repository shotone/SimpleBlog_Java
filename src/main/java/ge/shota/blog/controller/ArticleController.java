package ge.shota.blog.controller;

import ge.shota.blog.repository.ArticleRepository;
import ge.shota.blog.storage.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {

    private ArticleRepository articleRepository;


    @GetMapping("/{blogId}")
    public List<Article> getArticlesByBlogId(@PathVariable Long blogId) {
        return articleRepository.findByBlogId(blogId);
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleRepository.save(article);
    }


    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }
}
