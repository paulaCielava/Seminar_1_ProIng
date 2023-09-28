package lv.venta;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import lv.venta.repo.security.IMyAuthorityRepo;
import lv.venta.repo.security.IMyUserRepo;

@SpringBootApplication
public class Seminar1Application {

	public static void main(String[] args) {
		SpringApplication.run(Seminar1Application.class, args);
	}

	
	@Bean
	public CommandLineRunner testDB(IMyUserRepo userRepo, IMyAuthorityRepo authorityRepo) {
		return new CommandLineRunner() {
			
			@Override
			public void run(String... args) throws Exception {
				// TODO Auto-generated method stub
				
			}
		};
	}
}
