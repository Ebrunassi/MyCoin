package br.com.mycoin.application.ports.inbound;

import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;

public interface EntryServicePort {

    Entry registerEntry(Entry entry);
    void receiveEntryQueue(Entry entry);
    void processEntry(StatusEnum status, EventEnum event);

}
