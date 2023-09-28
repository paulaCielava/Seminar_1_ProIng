package lv.venta.models.security;

import java.util.ArrayList;
import java.util.Collection;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Table(name = "MyAuthority")
@Entity
public class MyAuthority {

	@Column(name = "MyAuthorityId")
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int myAuthorityId;
	
	@Column(name = "Title")
	@Pattern(regexp = "[A-Z]{3,8}")
	@NotNull
	private String title;
	
	@ManyToMany
	@JoinTable(name = "Users_Authorities", joinColumns = @JoinColumn(name = "MyUserId"), inverseJoinColumns = @JoinColumn(name = "MyAuthorityId"))
	private Collection<MyUser> users = new ArrayList<>();
	
	
	
	
	
	
	

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getMyAuthorityId() {
		return myAuthorityId;
	}
	
	public MyAuthority() {
		
	}
	
	public MyAuthority (String title) {
		setTitle(title);
	}
	
	
	
	
	
	
}
