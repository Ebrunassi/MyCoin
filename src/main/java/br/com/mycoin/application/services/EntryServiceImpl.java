package br.com.mycoin.application.services;

import br.com.mycoin.application.domain.Account;
import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.domain.Situation;
import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;
import br.com.mycoin.application.ports.inbound.EntryServicePort;
import br.com.mycoin.application.ports.outbound.AccountRepositoryPort;
import br.com.mycoin.application.ports.outbound.EntryRepositoryPort;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EntryServiceImpl implements EntryServicePort {

    private AccountRepositoryPort accountRepository;
    private EntryRepositoryPort entryRepository;

    public EntryServiceImpl(AccountRepositoryPort accountRepository, EntryRepositoryPort entryRepository) {
        this.accountRepository = accountRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public Entry registerEntry(Entry entry) {
        List<Account> account = accountRepository.findByUsername(entry.getUserId());
        if(account != null && account.size() > 0){
            System.out.println("Account size : " + account.size());
            System.out.println("User id: " + account.get(0).getId() + " - Usename '"+account.get(0).getUsername()+"', password '"+account.get(0).getPassword()+"'");
        }
        // Create message and send to queue
        entry.setCreationDate(new Date());
        entry.setSituationHistory(new ArrayList<Situation>());
        entry.getSituationHistory().add(Situation.builder()
                .status(StatusEnum.SENDING_QUEUE)
                .event(EventEnum.PROCESSING)
                .processDate(new Date())
                .build());

        return entryRepository.save(entry);
    }
}
