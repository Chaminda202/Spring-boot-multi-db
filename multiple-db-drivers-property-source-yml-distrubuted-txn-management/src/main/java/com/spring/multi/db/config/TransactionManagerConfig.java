package com.spring.multi.db.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class TransactionManagerConfig {
    @Bean(name = "chainedTransactionManager")
    public ChainedTransactionManager transactionManager (@Qualifier("baseTransactionManager") PlatformTransactionManager baseTransactionManager,
                                                         @Qualifier("thirdPartyTransactionManager") PlatformTransactionManager thirdPartyTransactionManager) {
        return new ChainedTransactionManager(baseTransactionManager, thirdPartyTransactionManager);
    }
}
