package ge.shota.blog.repository;

import ge.shota.blog.storage.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByBlogId(Long blogId);

    Optional<Article> findByIdAndDeleteTimeIsNull(Long articleId);
}
