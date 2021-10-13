package br.com.mycoin.adapters.outbound.persistence;

import br.com.mycoin.adapters.outbound.persistence.entity.EntryEntity;
import br.com.mycoin.application.domain.enums.EventEnum;
import br.com.mycoin.application.domain.enums.StatusEnum;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMongoEntryRepository extends CrudRepository<EntryEntity, String> {
    EntryEntity save(EntryEntity entryEntity);
    List<EntryEntity> findByStatusAndEvent(StatusEnum status, EventEnum event);
}
