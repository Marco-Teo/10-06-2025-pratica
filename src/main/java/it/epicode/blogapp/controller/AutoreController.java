package it.epicode.blogapp.controller;

import it.epicode.blogapp.exeption.AutoreNotFoundExpetion;
import it.epicode.blogapp.model.Autore;
import it.epicode.blogapp.service.AutoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("/authors")
    public List<Autore> getAutori(){
        return autoreService.getAllAutors();
    }

    @GetMapping("/authors/{id}")
    public Autore getAutore(@PathVariable int id) throws AutoreNotFoundExpetion {
        return autoreService.getAutore(id);
    }

    @PostMapping("/authors")
    public Autore addAutore(@RequestBody Autore autore){
       return autoreService.saveAutore(autore);
    }

    @PutMapping("/authors/{id}")
    public Autore updateAutore(@PathVariable int id, @RequestBody Autore autore) throws AutoreNotFoundExpetion {
        return autoreService.updateAutore(id, autore);
    }

    @DeleteMapping("/authors/{id}")
    public void deleteAutore(@PathVariable int id) throws AutoreNotFoundExpetion {
        autoreService.deleteAutore(id);
    }
}
