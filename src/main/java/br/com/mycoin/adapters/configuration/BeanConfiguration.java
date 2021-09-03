package br.com.mycoin.adapters.configuration;

import br.com.mycoin.MyCoinApplication;
import br.com.mycoin.application.ports.outbound.AccountRepositoryPort;
import br.com.mycoin.application.ports.outbound.EntryRepositoryPort;
import br.com.mycoin.application.services.EntryServiceImpl;
import br.com.mycoin.application.services.LoginServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = MyCoinApplication.class)
public class BeanConfiguration {

    @Bean
    LoginServiceImpl loginServiceImpl(AccountRepositoryPort repository) {
        return new LoginServiceImpl(repository);
    }

    @Bean
    EntryServiceImpl entryServiceImpl(AccountRepositoryPort accountRepository, EntryRepositoryPort entryRepository) {
        return new EntryServiceImpl(accountRepository,entryRepository);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
