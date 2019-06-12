package org.jresearch.tekies.docker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({ "conf" })
public class TekiesApplication {

	public static void main(final String[] args) {
		SpringApplication.run(TekiesApplication.class, args);
	}

}
