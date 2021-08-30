package br.com.mycoin.application.ports.inbound;

import br.com.mycoin.application.domain.Entry;

public interface EntryServicePort {

    Entry registerEntry(Entry entry);

}
