package br.com.mycoin.application.services;

import br.com.mycoin.application.domain.Account;
import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.domain.Situation;
import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;
import br.com.mycoin.application.ports.inbound.EntryServicePort;
import br.com.mycoin.application.ports.outbound.AccountRepositoryPort;
import br.com.mycoin.application.ports.outbound.EntryRepositoryPort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

public class EntryServiceImpl implements EntryServicePort {

    private AccountRepositoryPort accountRepository;
    private EntryRepositoryPort entryRepository;
    @Autowired private ObjectMapper mapper;
    @Autowired private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.entry}") private String exchange;
    @Value("${rabbitmq.routing-key.entry}") private String routingKey;

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

        Entry entryReturn = entryRepository.save(entry);
        sendEntryQueue(entryReturn);
        return entryReturn;
    }

    @Override
    public void processEntry(Entry entry) {
        try {
            System.out.println("Recebido da fila : " + mapper.writeValueAsString(entry));
            entry.addSituation(StatusEnum.SENDING_QUEUE, EventEnum.PROCESSED);
            entryRepository.save(entry);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    private void sendEntryQueue(Entry entry){

        try {
            System.out.println("Sending to queue : " + mapper.writeValueAsString(entry));
            rabbitTemplate.convertAndSend(exchange,routingKey, mapper.writeValueAsString(entry), m -> {
                m.getMessageProperties().setContentType(APPLICATION_JSON_VALUE);
                return m;
            });
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
