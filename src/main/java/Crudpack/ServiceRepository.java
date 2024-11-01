package Crudpack;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ServiceRepository extends JpaRepository<Service, Integer> {

	// JPQL query to select services associated with a specific task ID
    @Query("SELECT s FROM Service s JOIN s.tasks t WHERE t.codetask = :taskId")
    List<Service> findByTaskId(@Param("taskId") int taskId);
    
}
