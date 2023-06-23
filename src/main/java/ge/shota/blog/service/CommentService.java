package ge.shota.blog.service;

import ge.shota.blog.exception.GeneralException;
import ge.shota.blog.model.CommentModel;
import ge.shota.blog.repository.ArticleRepository;
import ge.shota.blog.repository.CommentRepository;
import ge.shota.blog.service.impl.CommentServiceImpl;
import ge.shota.blog.storage.Article;
import ge.shota.blog.storage.Comment;
import ge.shota.blog.storage.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentService implements CommentServiceImpl {

    private CommentRepository commentRepository;

    private ArticleRepository articleRepository;

    private AuthenticationService authenticationService;

    @Override
    public void saveComment(CommentModel comment) {

        try {
            User user = authenticationService.getUser();

            Article article = articleRepository.findByIdAndDeleteTimeIsNull(comment.getArticleId()).orElseThrow(() -> new GeneralException.ArticleNotFound(comment.getArticleId()));

            commentRepository.save(new Comment(comment, article, user));

        }catch (Exception e) {
            log.error("Unknown Error while save comment: {}", e.getMessage());
            e.getStackTrace();
        }


    }


    @Autowired
    public void setArticleRepository(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @Autowired
    public void setAuthenticationService(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
}
