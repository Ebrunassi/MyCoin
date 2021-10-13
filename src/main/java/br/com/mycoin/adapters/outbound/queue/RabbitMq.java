package br.com.mycoin.adapters.outbound.queue;

import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.ports.outbound.queue.QueuePort;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Component
@Primary
public class RabbitMq implements QueuePort {
    @Autowired
    private ObjectMapper mapper;
    @Autowired private RabbitTemplate rabbitTemplate;
    @Value("${rabbitmq.exchange.entry}") private String exchange;
    @Value("${rabbitmq.routing-key.entry}") private String routingKey;

    @Override
    public void sendEntryQueue(Entry entry) {
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
