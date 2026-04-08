package com.omartobar.backendTienda;

import jdk.swing.interop.SwingInterOpUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackendTiendaApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(BackendTiendaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        System.out.println("API Funcionando");
    }
}
