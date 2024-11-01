package Crudpack;


import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class UserRoleID implements Serializable {
    
	private static final long serialVersionUID = 1L;

	@Column(name = "user_id")
    private int userId;

    @Column(name = "role_id")
    private int roleId;
    

	public UserRoleID(int userId, int roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public UserRoleID() {
		
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

    
}

