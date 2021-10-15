package br.com.mycoin.application.services;

import br.com.mycoin.application.domain.Account;
import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.domain.Situation;
import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;
import br.com.mycoin.application.ports.inbound.EntryServicePort;
import br.com.mycoin.application.ports.outbound.persistence.AccountRepositoryPort;
import br.com.mycoin.application.ports.outbound.persistence.EntryRepositoryPort;
import br.com.mycoin.application.ports.outbound.queue.QueuePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Log4j2
public class EntryServiceImpl implements EntryServicePort {

    private QueuePort queuePort;
    private AccountRepositoryPort accountRepository;
    private EntryRepositoryPort entryRepository;
    @Autowired private ObjectMapper mapper;

    public EntryServiceImpl(AccountRepositoryPort accountRepository, EntryRepositoryPort entryRepository, QueuePort queuePort) {
        this.accountRepository = accountRepository;
        this.entryRepository = entryRepository;
        this.queuePort = queuePort;
    }

    @Override
    public Entry registerEntry(Entry entry) {

        List<Account> account = accountRepository.findByUsername(entry.getUserId());
        if(account != null && account.size() > 0){
            log.info("Account size : " + account.size());
            log.info("User id: " + account.get(0).getId() + " - Usename '"+account.get(0).getUsername()+"', password '"+account.get(0).getPassword()+"'");
        }
        // Create message and send to queue
        entry.setCreationDate(new Date());
        entry.setSituationHistory(new ArrayList<Situation>());
        entry.getSituationHistory().add(Situation.builder()
                .status(StatusEnum.SENDING_QUEUE)
                .event(EventEnum.PROCESSING)
                .processDate(new Date())
                .build());

        Entry entryReturn = entryRepository.save(entry);
        queuePort.sendEntryQueue(entryReturn);      // Send to queue
        return entryReturn;
    }

    @Override
    public void receiveEntryQueue(Entry entry) {
        try {
            log.info("Received from queue : " + mapper.writeValueAsString(entry));
            entry.addSituation(StatusEnum.RECEIVE_QUEUE, EventEnum.PROCESSED);
            entry.addSituation(StatusEnum.WAITING_PROCESS, EventEnum.STOPPED);
            entryRepository.save(entry);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void processEntry(StatusEnum status, EventEnum event) {
        List<Entry> entriesToProcess = entryRepository.findByStatusAndEvent(status, event);
        log.info("There are '{}' entries to process", entriesToProcess.size());

        // TODO - Process the entries
    }
}
