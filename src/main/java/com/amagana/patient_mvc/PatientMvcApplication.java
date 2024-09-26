package com.amagana.patient_mvc;

import com.amagana.patient_mvc.entities.Patient;
import com.amagana.patient_mvc.entities.Roles;
import com.amagana.patient_mvc.entities.Users;
import com.amagana.patient_mvc.repository.PatientRepository;
import com.amagana.patient_mvc.repository.RoleRepository;
import com.amagana.patient_mvc.repository.UsersRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@SpringBootApplication
public class PatientMvcApplication {

	public static void main(String[] args) {
		SpringApplication.run(PatientMvcApplication.class, args);
	}

	@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository, UsersRepository usersRepository,
										PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
	  return args -> {
		  /*Roles roles = new Roles(1L, "ADMIN");
		  roleRepository.save(roles);
		  Roles roles1 = new Roles(2L, "USER");
		  roleRepository.save(roles1);
		  Users users = new Users(1L, "donald", passwordEncoder.encode("donald"), true,
				  Set.of(roles1, roles) );
		  usersRepository.save(users);
		  Users users2 = new Users(1L, "evrard", passwordEncoder.encode("evrard"), true,
				  Set.of(roles1));
		  usersRepository.save(users2);*/
		  /*Roles roles2 = roleRepository.findById(2L).orElse(null);
		  Users users = new Users(2L, "evrard", passwordEncoder.encode("evrard"), true,
				  Set.of(roles2) );
		  usersRepository.save(users);
		  usersRepository.findAll().forEach(user -> System.out.println("user: "+ user + " roles: " + user.getRoles()));*/
		  /**patientRepository.save(
				  Patient.builder()
						  .bornDate(LocalDate.now())
						  .name("Donald")
						  .score(23)
						  .isSick(false)
						  .build()
		  );
		  patientRepository.save(
				  Patient.builder()
						  .bornDate(LocalDate.now())
						  .name("Evrard")
						  .score(40)
						  .isSick(false)
						  .build()
		  );
		  patientRepository.save(
				  Patient.builder()
						  .bornDate(LocalDate.now())
						  .name("Laurice")
						  .score(90)
						  .isSick(false)
						  .build()
		  );**/
		 // patientRepository.findAll().forEach(System.out::println);
	  };
	}

}
