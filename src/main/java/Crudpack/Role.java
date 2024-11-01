package Crudpack;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Role {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idRole;
	private String intituleRole;
	
	@OneToMany(mappedBy = "role", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<UserRole> users = new HashSet<>();
	
	
	
	
	

	public Set<UserRole> getUsers() {
		return users;
	}

	public void setUsers(Set<UserRole> users) {
		this.users = users;
	}

	public Role() {
		super();
	}
	
	public Role(int idRole,String intituleRole) {
		this.idRole=idRole;
		this.intituleRole=intituleRole;
	}
	
	public int getIdRole() {
		return idRole;
	}
	public void setIdRole(int idRole) {
		this.idRole = idRole;
	}
	public String getIntituleRole() {
		return intituleRole;
	}
	public void setIntituleRole(String intituleRole) {
		this.intituleRole = intituleRole;
	}
	

}
