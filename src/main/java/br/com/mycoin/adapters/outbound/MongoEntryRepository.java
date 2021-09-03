package br.com.mycoin.adapters.outbound;

import br.com.mycoin.adapters.outbound.persistence.entity.EntryEntity;
import br.com.mycoin.application.domain.Entry;
import br.com.mycoin.application.ports.outbound.EntryRepositoryPort;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

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
}
