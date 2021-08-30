package br.com.mycoin.application.ports.inbound;

import br.com.mycoin.application.domain.Account;
import org.springframework.http.ResponseEntity;

public interface LoginServicePort {

    Account registerAccount(Account account);
}
