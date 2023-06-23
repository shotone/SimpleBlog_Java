package ge.shota.blog.controller;

import ge.shota.blog.model.CommentModel;
import ge.shota.blog.repository.CommentRepository;
import ge.shota.blog.service.CommentService;
import ge.shota.blog.storage.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {

    private CommentRepository commentRepository;

    private CommentService commentService;


    @GetMapping("/{articleId}")
    public List<Comment> getCommentsByArticleId(@PathVariable Long articleId) {
        return commentRepository.findByArticleId(articleId);
    }

    @PostMapping("/add")
    public void createComment(@RequestBody CommentModel comment) {
        commentService.saveComment(comment);
    }


    @Autowired
    public void setCommentRepository(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    @Autowired
    public void setCommentService(CommentService commentService) {
        this.commentService = commentService;
    }
}
