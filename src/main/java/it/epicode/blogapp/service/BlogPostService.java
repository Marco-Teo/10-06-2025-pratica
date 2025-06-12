package it.epicode.blogapp.service;

import com.cloudinary.Cloudinary;
import it.epicode.blogapp.dto.BlogPostDto;
import it.epicode.blogapp.exeption.AutoreNotFoundExpetion;
import it.epicode.blogapp.exeption.BlogNotFoundExeption;
import it.epicode.blogapp.model.Autore;
import it.epicode.blogapp.model.BlogPost;
import it.epicode.blogapp.repository.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@Service
public class BlogPostService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private AutoreService autoreService;

    @Autowired
    private Cloudinary cloudinary;

    public BlogPost saveBlogPost (BlogPostDto blogPostDto) throws AutoreNotFoundExpetion {
        Autore autore = autoreService.getAutore(blogPostDto.getAutoreId());

        BlogPost blogPost = new BlogPost();
        blogPost.setAutore(autore);
        blogPost.setContenuto(blogPostDto.getContenuto());
        blogPost.setTitolo(blogPostDto.getTitolo());
        blogPost.setCategoria(blogPostDto.getCategoria());
        blogPost.setTempoDiLettura(blogPostDto.getTempoDiLettura());
        blogRepository.save(blogPost);
        return blogPost;
    }

    public String patchPost (int id, MultipartFile file) throws BlogNotFoundExeption, IOException {
        BlogPost postToPatch = getBlogPost(id);
        String url = (String) cloudinary.uploader().upload(file.getBytes(), Collections.emptyMap()).get("url");
        postToPatch.setCover(url);
        blogRepository.save(postToPatch);

        return url;
    }

    public BlogPost getBlogPost(int id) throws BlogNotFoundExeption {
        return blogRepository.findById(id).orElseThrow(() -> new BlogNotFoundExeption("Non esiste un post con id " + id));
    }

    public Page<BlogPost> getAllPosts(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return blogRepository.findAll(pageable);
    }


    public BlogPost updatePost(int id, BlogPostDto blogPostDto) throws BlogNotFoundExeption, AutoreNotFoundExpetion {
        BlogPost blogPostToUpdate = getBlogPost(id);

        blogPostToUpdate.setContenuto(blogPostDto.getContenuto());

        blogPostToUpdate.setCategoria(blogPostDto.getCategoria());

        blogPostToUpdate.setTitolo(blogPostDto.getTitolo());

        blogPostToUpdate.setTempoDiLettura(blogPostDto.getTempoDiLettura());

        if (blogPostToUpdate.getAutore().getId() != blogPostDto.getAutoreId()){
            Autore autore = autoreService.getAutore(blogPostDto.getAutoreId());
            blogPostToUpdate.setAutore(autore);
        }

        return blogRepository.save(blogPostToUpdate);
    }

    public void deletePost(int id) throws BlogNotFoundExeption {
        BlogPost deletedPost = getBlogPost(id);
        blogRepository.delete(deletedPost);
    }

}
