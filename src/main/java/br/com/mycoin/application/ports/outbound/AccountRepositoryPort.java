package br.com.mycoin.application.ports.outbound;

import br.com.mycoin.application.domain.Account;

import java.util.List;

public interface AccountRepositoryPort {
    Account save(Account account);
    List<Account> findByUsername(String username);
}
