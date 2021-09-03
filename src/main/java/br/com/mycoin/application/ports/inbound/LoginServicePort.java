package br.com.mycoin.application.ports.inbound;

import br.com.mycoin.application.domain.Account;

public interface LoginServicePort {

    Account registerAccount(Account account);
}
