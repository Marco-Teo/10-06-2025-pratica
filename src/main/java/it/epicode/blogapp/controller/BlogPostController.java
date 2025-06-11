package it.epicode.blogapp.controller;

import it.epicode.blogapp.dto.BlogPostDto;
import it.epicode.blogapp.exeption.AutoreNotFoundExpetion;
import it.epicode.blogapp.exeption.BlogNotFoundExeption;
import it.epicode.blogapp.model.Autore;
import it.epicode.blogapp.model.BlogPost;
import it.epicode.blogapp.service.BlogPostService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping(path = "/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("")
    public Page<BlogPost> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy){
        return blogPostService.getAllPosts(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public BlogPost getPost(@PathVariable int id) throws BlogNotFoundExeption {
        return blogPostService.getBlogPost(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost creatBlogPost(@RequestBody BlogPostDto blogPostDto) throws AutoreNotFoundExpetion {
        return blogPostService.saveBlogPost(blogPostDto);
    }

    @PutMapping("/{id}")
    public BlogPost updateBlogPost(@PathVariable int id,@RequestBody BlogPostDto blogPostDto) throws BlogNotFoundExeption, AutoreNotFoundExpetion {
        return blogPostService.updatePost(id, blogPostDto);
    }

    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) throws BlogNotFoundExeption {
        blogPostService.deletePost(id);
    }

}
