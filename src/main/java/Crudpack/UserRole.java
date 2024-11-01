package Crudpack;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

@Entity
public class UserRole {
    @EmbeddedId
    private UserRoleID id;
    
    private String dateAffect;

    @ManyToOne
    @MapsId("userId") 
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("roleId") 
    @JoinColumn(name = "role_id")
    private Role role;
    
    
    

	public UserRole(UserRoleID id, String dateAffect, User user, Role role) {
		super();
		this.id = id;
		this.dateAffect = dateAffect;
		this.user = user;
		this.role = role;
	}

	public UserRole() {
		
	}

	public UserRoleID getId() {
		return id;
	}

	public void setId(UserRoleID id) {
		this.id = id;
	}

	public String getDateAffect() {
		return dateAffect;
	}

	public void setDateAffect(String dateAffect) {
		this.dateAffect = dateAffect;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}


    
    

    }

