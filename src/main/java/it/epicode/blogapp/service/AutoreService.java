package it.epicode.blogapp.service;

import it.epicode.blogapp.exeption.AutoreNotFoundExpetion;
import it.epicode.blogapp.model.Autore;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class AutoreService {

    private List<Autore> autoriList = new ArrayList<>();

    public Autore saveAutore (Autore autore){
        autore.setId(new Random().nextInt(1,200));
        autoriList.add(autore);
        return autore;
    }

    public Autore getAutore (int id) throws AutoreNotFoundExpetion {
        return autoriList.stream().filter(autore -> autore.getId()==id).
                findFirst().orElseThrow(() -> new AutoreNotFoundExpetion("Non esiste un autore con id  " + id));
    }

    public List<Autore> getAllAutors(){
        return autoriList;
    }

    public Autore updateAutore(int id, Autore autore) throws AutoreNotFoundExpetion {
        Autore autoreToUpdate = getAutore(id);

        autoreToUpdate.setNome(autore.getNome());

        autoreToUpdate.setEmail(autore.getEmail());

        autoreToUpdate.setCognome(autore.getCognome());

        autoreToUpdate.setDataDiNascita(autore.getDataDiNascita());

        return autoreToUpdate;
    }

    public void deleteAutore(int id) throws AutoreNotFoundExpetion {

        Autore deleteAutore = getAutore(id);

        autoriList.remove(deleteAutore);
    }

}
