package br.com.mycoin.adapters.outbound;

import br.com.mycoin.adapters.outbound.persistence.entity.EntryEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataMongoEntryRepository extends CrudRepository<EntryEntity, String> {
    EntryEntity save(EntryEntity entryEntity);
}
