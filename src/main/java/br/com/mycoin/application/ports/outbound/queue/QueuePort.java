package br.com.mycoin.application.ports.outbound.queue;

import br.com.mycoin.application.domain.Entry;

public interface QueuePort {
    void sendEntryQueue(Entry entry);
}
