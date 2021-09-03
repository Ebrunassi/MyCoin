package br.com.mycoin.application.ports.outbound;

import br.com.mycoin.application.domain.Entry;

public interface EntryRepositoryPort {
    Entry save(Entry entry);
}
