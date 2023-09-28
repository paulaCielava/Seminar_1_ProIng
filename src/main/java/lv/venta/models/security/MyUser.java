package lv.venta.models.security;

import java.util.ArrayList;
import java.util.Collection;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Table(name = "MyUser")
@Entity
public class MyUser {

	@Column(name = "MyUserId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int myUserId;
	
	@Column(name = "Name") 
	@NotNull
	@Pattern(regexp = "[A-ZĀŠĒĢŪĪĶĻŅŽ]{1}[a-zēīļķšāžņūģ\\ ]+", message = "Pirmajam burtam ir jābūt lielajam!")
	private String name;
	
	@Column(name = "Surname") 
	@NotNull
	@Pattern(regexp = "[A-ZĀŠĒĢŪĪĶĻŅŽ]{1}[a-zēīļķšāžņūģ\\ ]+", message = "Pirmajam burtam ir jābūt lielajam!")
	private String surname;
	
	@Column(name = "Username")
	@NotNull
	private String username;
	
	@Column(name = "Password") 
	@NotNull
	private String password;
	
	@ManyToMany(mappedBy = "users", fetch = FetchType.EAGER)
	private Collection<MyAuthority> authorities = new ArrayList<>();
	
	
	
	
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMyUserId() {
		return myUserId;
	}

	public String getUsername() {
		return username;
	}
	
	public MyUser() {
		
	}
	
	public MyUser(String name, String surname, String password) {
		setName(name);
		setSurname(surname);
		setPassword(password);
		username = name.toLowerCase() + "." + surname.toLowerCase();  //pats izveido lietotājvārdu 
	}
	
	public void addAuthority(MyAuthority authority) {
		if (!authorities.contains(authority)) {
			authorities.add(authority);
		}
	}
	
	public void removeAuthority(MyAuthority authority) {
		if (authorities.contains(authority))
			authorities.remove(authority);
	}
	
}
