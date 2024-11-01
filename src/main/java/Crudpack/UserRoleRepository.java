package Crudpack;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import jakarta.transaction.Transactional;


public interface UserRoleRepository extends JpaRepository<UserRole, UserRoleID> {

	@Transactional
    @Modifying
    @Query("DELETE FROM UserRole ur WHERE ur.id.userId = :userId AND ur.id.roleId = :roleId")
    void deleteUserRole(int userId, int roleId);
	
	@Query("SELECT ur FROM UserRole ur WHERE ur.id.userId = :userId AND ur.id.roleId = :roleId")
    UserRole findUserRole(int userId, int roleId);

    @Modifying
    @Query("UPDATE UserRole ur SET ur.role = :newRole WHERE ur.id.userId = :userId AND ur.id.roleId = :roleId")
    void updateUserRole(int userId, int roleId, Role newRole);

}
