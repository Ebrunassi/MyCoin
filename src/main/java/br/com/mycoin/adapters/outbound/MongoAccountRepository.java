package br.com.mycoin.adapters.outbound;

import br.com.mycoin.adapters.outbound.persistence.entity.AccountEntity;
import br.com.mycoin.application.domain.Account;
import br.com.mycoin.application.ports.outbound.AccountRepositoryPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Primary
public class MongoAccountRepository implements AccountRepositoryPort {
    private final SpringDataMongoAccountRepository accountRepository;

    public MongoAccountRepository(final SpringDataMongoAccountRepository orderRepository) {
        this.accountRepository = orderRepository;
    }

    @Autowired
    ModelMapper modelMapper;

    @Override
    public Account save(Account account) {
        AccountEntity emailEntity = accountRepository.save(modelMapper.map(account, AccountEntity.class));
        return modelMapper.map(emailEntity, Account.class);
    }

    public List<Account> findByUsername(String username){
        List<Account> accountEntity = accountRepository.findByUsername(username);
        if (!accountEntity.isEmpty()) {
            return accountEntity.stream().map(entity -> modelMapper.map(entity, Account.class))
                    .collect(Collectors.toList());
//            return modelMapper.map(accountEntity.get(), Account.class);
        } else {
            return null;
        }
    }
}
