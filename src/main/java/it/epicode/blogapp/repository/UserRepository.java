package it.epicode.blogapp.repository;
import it.epicode.blogapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Integer>, PagingAndSortingRepository<User,Integer> {

    public Optional<User> findByUsername(String username);
}