package it.epicode.blogapp.service;

import it.epicode.blogapp.dto.AutoreDto;
import it.epicode.blogapp.exeption.AutoreNotFoundExpetion;
import it.epicode.blogapp.model.Autore;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import it.epicode.blogapp.repository.AutoreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;



@Service
public class AutoreService {

    @Autowired
    private AutoreRepository autoreRepository;

    @Autowired
    private JavaMailSenderImpl javaMailSender;

    public Autore saveAutore (AutoreDto autoreDto){
        Autore autore = new Autore();
        autore.setCognome(autoreDto.getCognome());
        autore.setNome(autoreDto.getNome());
        autore.setEmail(autoreDto.getEmail());
        autore.setDataDiNascita(autoreDto.getDataDiNascita());
        autore.setAvatar("https://ui-avatars.com/api/?name="+autore.getNome()+"+"+autore.getCognome());
        sendMail("marco.teo.mancuso@gmail.com");
        autoreRepository.save(autore);
        return autore;
    }

    public Autore getAutore (int id) throws AutoreNotFoundExpetion {
        return autoreRepository.findById(id).orElseThrow(() -> new AutoreNotFoundExpetion("Non esiste un autore con id  " + id));
    }

    public Page<Autore> getAllAutors(int page, int size, String sortBy){
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));

        return autoreRepository.findAll(pageable);
    }

    public Autore updateAutore(int id, Autore autore) throws AutoreNotFoundExpetion {
        Autore autoreToUpdate = getAutore(id);
        autoreToUpdate.setNome(autore.getNome());
        autoreToUpdate.setEmail(autore.getEmail());
        autoreToUpdate.setCognome(autore.getCognome());
        autoreToUpdate.setDataDiNascita(autore.getDataDiNascita());
        autoreRepository.save(autoreToUpdate);
        return autoreToUpdate;
    }

    public void deleteAutore(int id) throws AutoreNotFoundExpetion {
        Autore deleteAutore = getAutore(id);
        autoreRepository.delete(deleteAutore);
    }

    private void sendMail(String email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("Registrazione Servizio rest");
        message.setText("Registrazione al servizio rest avvenuta con successo");
        javaMailSender.send(message);
    }
}
