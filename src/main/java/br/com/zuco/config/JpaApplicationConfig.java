package br.com.zuco.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "br.com.zuco.repository", enableDefaultTransactions = false)
public class JpaApplicationConfig {}
