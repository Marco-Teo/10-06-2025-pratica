package it.epicode.blogapp.controller;

import it.epicode.blogapp.dto.AutoreDto;
import it.epicode.blogapp.exeption.AutoreNotFoundExpetion;
import it.epicode.blogapp.model.Autore;
import it.epicode.blogapp.service.AutoreService;
import jakarta.validation.ValidationException;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/authors")
public class AutoreController {

    @Autowired
    private AutoreService autoreService;



    @GetMapping("")
    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    public Autore addAutore(@RequestBody @Validated AutoreDto autoreDto, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            throw new ValidationException(bindingResult.getAllErrors().
                    stream().map(objectError -> objectError.getDefaultMessage()).
                    reduce("", (e,c)->e+c));
        }
       return autoreService.saveAutore(autoreDto);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PutMapping("/{id}")
    public Autore updateAutore(@PathVariable int id, @RequestBody Autore autore) throws AutoreNotFoundExpetion {
        return autoreService.updateAutore(id, autore);
    }


    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("/{id}")
    public void deleteAutore(@PathVariable int id) throws AutoreNotFoundExpetion {
        autoreService.deleteAutore(id);
    }
}
