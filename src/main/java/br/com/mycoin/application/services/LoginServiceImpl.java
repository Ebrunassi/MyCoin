package br.com.mycoin.application.services;

import br.com.mycoin.application.domain.Account;
import br.com.mycoin.application.domain.utils.Criptografia;
import br.com.mycoin.application.ports.outbound.AccountRepositoryPort;
import br.com.mycoin.application.ports.inbound.LoginServicePort;

public class LoginServiceImpl implements LoginServicePort {

    private AccountRepositoryPort accountRepository;

    public LoginServiceImpl(AccountRepositoryPort accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account registerAccount(Account account) {
        try{
            account.setPassword(Criptografia.getInstance().criptografa(account.getPassword()));
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            return accountRepository.save(account);
        }
    }
}
