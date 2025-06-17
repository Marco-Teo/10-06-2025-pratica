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
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@RestController
@RequestMapping(path = "/blogPosts")
public class BlogPostController {

    @Autowired
    private BlogPostService blogPostService;

    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public Page<BlogPost> getAllPosts(@RequestParam(defaultValue = "0") int page,
                                       @RequestParam(defaultValue = "10") int size,
                                       @RequestParam(defaultValue = "id") String sortBy){
        return blogPostService.getAllPosts(page, size, sortBy);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public BlogPost getPost(@PathVariable int id) throws BlogNotFoundExeption {
        return blogPostService.getBlogPost(id);
    }

    @PostMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @ResponseStatus(HttpStatus.CREATED)
    public BlogPost creatBlogPost(@RequestBody BlogPostDto blogPostDto) throws AutoreNotFoundExpetion {
        return blogPostService.saveBlogPost(blogPostDto);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    public BlogPost updateBlogPost(@PathVariable int id,@RequestBody BlogPostDto blogPostDto) throws BlogNotFoundExeption, AutoreNotFoundExpetion {
        return blogPostService.updatePost(id, blogPostDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable int id) throws BlogNotFoundExeption {
        blogPostService.deletePost(id);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @PatchMapping("/{id}")
    public String patchBlogPost(@PathVariable int id,@RequestBody MultipartFile file) throws BlogNotFoundExeption, IOException {
       return blogPostService.patchPost(id,file);
    }
}
