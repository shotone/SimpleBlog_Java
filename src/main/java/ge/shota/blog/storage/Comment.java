package ge.shota.blog.storage;

import ge.shota.blog.model.CommentModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(schema = "blog", name = "comments")
public class Comment extends AbstractEntity {


    @Column(nullable = false)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    public Comment(CommentModel commentModel, Article article, User user) {
        this.content = commentModel.getContent();
        this.article = article;
        this.user = user;
    }

}
