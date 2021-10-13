package br.com.mycoin.adapters.outbound.persistence;

import br.com.mycoin.adapters.outbound.persistence.entity.EntryEntity;
import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;
import br.com.mycoin.application.ports.outbound.persistence.EntryRepositoryPort;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Primary
public class MongoEntryRepository implements EntryRepositoryPort {
    private final SpringDataMongoEntryRepository entryRepository;

    public MongoEntryRepository(SpringDataMongoEntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Entry save(Entry entry) {
        EntryEntity saved = entryRepository.save(modelMapper.map(entry,EntryEntity.class));
        return modelMapper.map(saved, Entry.class);
    }

    @Override
    public List<Entry> findByStatusAndEvent(StatusEnum status, EventEnum event) {

        List<EntryEntity> entriesEntity = entryRepository.findByStatusAndEvent(status, event);
        List<Entry> entries = modelMapper.map(entriesEntity, new TypeToken<List<Entry>>() {}.getType());
        return entries;
    }
}
