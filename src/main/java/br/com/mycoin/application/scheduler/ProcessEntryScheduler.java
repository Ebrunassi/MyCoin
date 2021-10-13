package br.com.mycoin.application.scheduler;

import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;
import br.com.mycoin.application.ports.inbound.EntryServicePort;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class ProcessEntryScheduler {

    @Autowired
    private EntryServicePort entryServicePort;

    @Scheduled(fixedRate = 2 * 60 * 1000)   // 2 minutes
    public void processNewEntries(){
        entryServicePort.processEntry(StatusEnum.WAITING_PROCESS, EventEnum.STOPPED);
    }
}
