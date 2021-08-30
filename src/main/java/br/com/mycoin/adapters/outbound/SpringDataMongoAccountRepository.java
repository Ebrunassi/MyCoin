package br.com.mycoin.adapters.outbound;

import br.com.mycoin.adapters.outbound.persistence.entity.AccountEntity;
import br.com.mycoin.application.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SpringDataMongoAccountRepository extends CrudRepository<AccountEntity, String> {
    List<Account> findByUsername(String login);
}
