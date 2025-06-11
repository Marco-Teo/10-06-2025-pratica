package it.epicode.blogapp.controller;

import it.epicode.blogapp.dto.AutoreDto;
import it.epicode.blogapp.exeption.AutoreNotFoundExpetion;
import it.epicode.blogapp.model.Autore;
import it.epicode.blogapp.service.AutoreService;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/authors")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;

    @GetMapping("")
    public Page<Autore> getAllAutori(@RequestParam(defaultValue = "0") int page,
                                         @RequestParam(defaultValue = "10") int size,
                                         @RequestParam(defaultValue = "id") String sortBy){
        return autoreService.getAllAutors(page, size, sortBy);
    }

    @GetMapping("/{id}")
    public Autore getAutore(@PathVariable int id) throws AutoreNotFoundExpetion {
        return autoreService.getAutore(id);
    }

    @PostMapping("")
    public Autore addAutore(@RequestBody AutoreDto autoreDto){
       return autoreService.saveAutore(autoreDto);
    }

    @PutMapping("/{id}")
    public Autore updateAutore(@PathVariable int id, @RequestBody Autore autore) throws AutoreNotFoundExpetion {
        return autoreService.updateAutore(id, autore);
    }

    @DeleteMapping("/{id}")
    public void deleteAutore(@PathVariable int id) throws AutoreNotFoundExpetion {
        autoreService.deleteAutore(id);
    }
}
