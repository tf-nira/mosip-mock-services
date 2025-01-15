package io.mosip.proxy.abis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jms.annotation.EnableJms;

import java.security.Security;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

@ComponentScan(basePackages = { "io.mosip.proxy.abis", "${mosip.auth.adapter.impl.basepackage}" })
@SpringBootApplication
@EnableAutoConfiguration
@EnableJpaRepositories(basePackages = { "io.mosip.proxy.abis.dao" })
@EnableJms
public class ProxyAbisApplication {
	public static void main(String[] args) {
		Security.addProvider(new BouncyCastleProvider());
		ConfigurableApplicationContext configurableApplicationContext = SpringApplication
				.run(ProxyAbisApplication.class, args);
		configurableApplicationContext.getBean(Listener.class).runAbisQueue();
	}
}