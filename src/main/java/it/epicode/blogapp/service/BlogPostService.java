package it.epicode.blogapp.service;

import it.epicode.blogapp.exeption.BlogNotFoundExeption;
import it.epicode.blogapp.model.Autore;
import it.epicode.blogapp.model.BlogPost;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class BlogPostService {

    private List<BlogPost> blogPostLista = new ArrayList<>();



    public BlogPost saveBlogPost (BlogPost blogPost){
        blogPost.setId(new Random().nextInt(1,200));
        blogPostLista.add(blogPost);
        return blogPost;
    }

    public BlogPost getBlogPost(int id) throws BlogNotFoundExeption {
        return blogPostLista.stream().filter(blogPost -> blogPost.getId()==id).
                findFirst().orElseThrow(() -> new BlogNotFoundExeption("Non esiste un post con id " + id));
    }

    public List<BlogPost> getAllPosts(){
        return blogPostLista;
    }

    public BlogPost updatePost(int id, BlogPost blogPost) throws BlogNotFoundExeption {
        BlogPost blogPostToUpdate = getBlogPost(id);

        blogPostToUpdate.setContenuto(blogPost.getContenuto());

        blogPostToUpdate.setCategoria(blogPost.getCategoria());

        blogPostToUpdate.setTitolo(blogPost.getTitolo());

        blogPostToUpdate.setTempoDiLettura(blogPost.getTempoDiLettura());

        return blogPostToUpdate;
    }

    public void deletePost(int id) throws BlogNotFoundExeption {
        BlogPost deletedPost = getBlogPost(id);
        blogPostLista.remove(deletedPost);
    }

}
