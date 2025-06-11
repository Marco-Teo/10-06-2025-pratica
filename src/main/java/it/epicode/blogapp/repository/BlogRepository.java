package it.epicode.blogapp.repository;

import it.epicode.blogapp.model.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BlogRepository extends JpaRepository<BlogPost, Integer>, PagingAndSortingRepository<BlogPost,Integer> {

}
