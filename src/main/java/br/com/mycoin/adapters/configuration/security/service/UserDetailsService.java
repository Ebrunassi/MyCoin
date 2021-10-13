package br.com.mycoin.adapters.configuration.security.service;


import br.com.mycoin.application.domain.Account;
import br.com.mycoin.application.ports.outbound.persistence.AccountRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserDetailsService implements  org.springframework.security.core.userdetails.UserDetailsService{

    @Autowired
    private AccountRepositoryPort accountRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        try {

            List<Account> usuario = accountRepository.findByUsername(login);
            if (usuario == null || usuario.size() == 0)
                throw new UsernameNotFoundException("User not found");

            List<SimpleGrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ADMIN"));

            return new User(usuario.get(0).getUsername(),
                    usuario.get(0).getPassword(),
                    authorities);
        }catch(Exception e){
            System.out.println(e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

}
