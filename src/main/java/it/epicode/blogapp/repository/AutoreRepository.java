package it.epicode.blogapp.repository;

import it.epicode.blogapp.model.Autore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface AutoreRepository extends JpaRepository<Autore,Integer>, PagingAndSortingRepository<Autore, Integer> {
}
