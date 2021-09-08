package br.com.mycoin.adapters.inbound.consumers;

import br.com.mycoin.adapters.dtos.EntryDto;
import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.ports.inbound.EntryServicePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class EntryConsumer {

    @Autowired
    private EntryServicePort entryServicePort;
    @Autowired private ObjectMapper mapper;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void listen(@Payload String message) {
        try {
            Entry entry = mapper.readValue(message, Entry.class);
            System.out.println("--------> " + entry.toString());
            entryServicePort.processEntry(entry);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
