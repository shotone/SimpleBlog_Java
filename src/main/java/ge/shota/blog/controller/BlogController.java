package ge.shota.blog.controller;

import ge.shota.blog.repository.BlogRepository;
import ge.shota.blog.storage.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/blogs")
public class BlogController {

    private BlogRepository blogRepository;

    @GetMapping("/{userId}")
    public List<Blog> getBlogsByUserId(@PathVariable Long userId) {
        return blogRepository.findByUserId(userId);
    }

    @PostMapping
    public Blog createBlog(@RequestBody Blog blog) {
        return blogRepository.save(blog);
    }


    @Autowired
    public void setBlogRepository(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }
}
