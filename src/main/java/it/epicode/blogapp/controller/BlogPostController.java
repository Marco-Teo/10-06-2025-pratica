package it.epicode.blogapp.controller;

import it.epicode.blogapp.exeption.BlogNotFoundExeption;
import it.epicode.blogapp.model.BlogPost;
import it.epicode.blogapp.service.BlogPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("/blogPosts")
    public List<BlogPost> getPosts(){
        return blogPostService.getAllPosts();
    }

    @GetMapping("/blogPosts/{id}")
    public BlogPost getPost(@PathVariable int id) throws BlogNotFoundExeption {
        return blogPostService.getBlogPost(id);
    }

    @PostMapping("/blogPosts")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost creatBlogPost(@RequestBody BlogPost blogPost){
        return blogPostService.saveBlogPost(blogPost);
    }

    @PutMapping("/blogPosts/{id}")
    public BlogPost updateBlogPost(@PathVariable int id,@RequestBody BlogPost blogPost) throws BlogNotFoundExeption {
        return blogPostService.updatePost(id, blogPost);
    }

    @DeleteMapping("/blogPosts/{id}")
    public void deletePost(@PathVariable int id) throws BlogNotFoundExeption {
        blogPostService.deletePost(id);
    }

}
