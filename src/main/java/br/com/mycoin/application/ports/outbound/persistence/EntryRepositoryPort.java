package br.com.mycoin.application.ports.outbound.persistence;

import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;

import java.util.List;

public interface EntryRepositoryPort {
    Entry save(Entry entry);
    List<Entry> findByStatusAndEvent(StatusEnum status, EventEnum event);
}
