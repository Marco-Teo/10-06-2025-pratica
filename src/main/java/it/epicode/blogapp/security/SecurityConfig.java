package it.epicode.blogapp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity // abilita la classe ad essere responsabile della sicurezza dei servizzi.
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        // form login serve pre creare in automatico una pagina di lgo in a noi non serve perchè non usiamo pagine
//        httpSecurity.formLogin(http->http.disable());
        // csrf serve per evitare la possibilita di utilizzi di sessioni aperte i rest non usano sessioni e quindi disbale
        httpSecurity.csrf(http->http.disable());

        // session menagment non ci interessa perchè i servizi rest non hanno una sessione e quindi statless
        httpSecurity.sessionManagement(http->http.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        // il cors serve per bloccare richieste che provegono da domini esterni a quelli del servizio dove
        // i domini sono indirizzi ip e porta
        httpSecurity.cors(Customizer.withDefaults());

        // questo serve per abilitare tutto il path abilitando l'utente all'utilizzo
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers("/auth/**").permitAll());
        // httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.GET,"/auth/**").permitAll());

        // questo serve per abilitare i metodi solo i metodi indifferentemento dal path in questo caso sto gia abilitando tutti i get
        // e tutti i post per specificare un endpoint devorei mettere una "," dopo il get per abilitare i meotodi uer quello specifico
        // path
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.GET).permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.POST).permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.PATCH).permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.PUT).permitAll());
        httpSecurity.authorizeHttpRequests(http->http.requestMatchers(HttpMethod.DELETE).permitAll());

        // questo invce serve alla fine dopo aver permesso tutto quello che c'è sopra riportato questa riga serve a dire di negare tutte
        // le richieste che non sono elecate sopra
        httpSecurity.authorizeHttpRequests(http->http.anyRequest().denyAll());

        return httpSecurity.build();
    }

}
