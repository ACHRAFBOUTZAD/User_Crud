package Crudpack;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import jakarta.transaction.Transactional;


@Controller
public class ControllerApp {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private ServiceRepository serviceRepository;
    
    @Autowired 
	UserRoleRepository userroleRepository;
	
	@Autowired 
	RoleRepository roleRepository  ;
	
	
	
 //add User page form
    @GetMapping("/addUser")
    public String showAddUserForm(Model model) {
    	List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        List<Service> availableServices = serviceRepository.findAll(); // Fetch all services
        model.addAttribute("user", new User());
        model.addAttribute("availableServices", availableServices);
        return "addUser";
    }
    
  
    @GetMapping("/")
    public ModelAndView index(Model model) {
        List<User> users = userRepository.findAll(); 
        model.addAttribute("users", users);
        return new ModelAndView("index");
    }
    
    @PostMapping("/saveUser")
    public ModelAndView saveUser(User user) {
        userRepository.save(user);
        return new ModelAndView("userAdded");
    }

    @GetMapping("/addTask")
    public ModelAndView showAddTaskForm(Model model) {
        List<Service> allServices = serviceRepository.findAll();
        model.addAttribute("services", allServices);
        model.addAttribute("task", new Task());
        return new ModelAndView("addTask");
    }

    @PostMapping("/saveTask")
    public ModelAndView saveTask(@ModelAttribute Task task, 
                                 @RequestParam List<Integer> serviceIds, 
                                 @RequestParam("userId") int userId,Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        task.setUser(user);
        List<Service> selectedServices = serviceRepository.findAllById(serviceIds);
        task.setServices(selectedServices);
        taskRepository.save(task);
        model.addAttribute("userId", userId);
        return new ModelAndView("taskAdded");
    }
 
    @GetMapping("/addUserRole")
    public ModelAndView showAddRoleForm(@RequestParam("userId") Integer userId, Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        model.addAttribute("userId", userId);
        return new ModelAndView("addRole");
    }
    @GetMapping("/Listroles")
    public ModelAndView showRoles(Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return new ModelAndView("ListRoles");
    }
   

	@PostMapping("/addUserRoles")
    public ModelAndView addUserRole(@RequestParam("userId") int userId, @RequestParam("roleIds") List<Integer> roleIds,Model model) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        for (Integer roleId : roleIds) {
            Role role = roleRepository.findById(roleId).orElseThrow(() -> new RuntimeException("Role not found"));
            UserRoleID userRoleId = new UserRoleID(userId, roleId);
            UserRole userRole = new UserRole(userRoleId, "10-10-2024", user, role);
            userroleRepository.save(userRole);
        }

        model.addAttribute("userId", userId);
        return new ModelAndView("roleAdded");    
    }
    @PostMapping("/addNewRole")
    public String addRole(@RequestParam("roleName") String roleName) {
        Role role = new Role();
        role.setIntituleRole(roleName);
        roleRepository.save(role);
        return "redirect:/"; 
    }

 // Modify user form
    @GetMapping("/modifyUser")
    public ModelAndView modifyUserForm(@RequestParam("userId") Integer userId, Model model) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new RuntimeException("User not found with id " + userId));
        model.addAttribute("user", user);
        return new ModelAndView("modifyUser"); 
    }


    
 // Update user
    @PostMapping("/saveModifyUser")
    public ModelAndView updateUser(@RequestParam("userId") int userId, 
    							   @RequestParam("username") String username,
    							   @RequestParam("password") String password,
    							   @RequestParam(value = "student", defaultValue = "false") boolean student,
    							   @RequestParam("country") String country) {
        User user = userRepository.findById(userId)
                                  .orElseThrow(() -> new RuntimeException("User not found: " + userId));
        user.setUsername(username);
        user.setPassword(password); 
        user.setStudent(student);
        user.setCountry(country);
        
        userRepository.save(user);
        return new ModelAndView("redirect:/");
    }
    @GetMapping("/modifyTaskService")
    public ModelAndView editTask(@RequestParam("taskId") int taskId, Model model) {
        Task task = taskRepository.findById(taskId)
                                  .orElseThrow(() -> new RuntimeException("Task not found"));
        model.addAttribute("task", task);
        model.addAttribute("services", task.getServices());
        return new ModelAndView("modifyTaskService");
    }
    
    @PostMapping("/updateTaskAndServices")
    public String updateTaskAndServices(@RequestParam("taskId") int taskId,
                                        @RequestParam("taskTitle") String taskTitle,
                                        @RequestParam("serviceIds") List<Integer> serviceIds,
                                        @RequestParam("serviceTitles") List<String> serviceTitles,
                                        RedirectAttributes redirectAttributes) {
        // Find and update the task
        Task task = taskRepository.findById(taskId)
                                  .orElseThrow(() -> new RuntimeException("Task not found"));

        task.setIntituleTask(taskTitle);
        taskRepository.save(task);

        // Update services
        for (int i = 0; i < serviceIds.size(); i++) {
            Service service = serviceRepository.findById(serviceIds.get(i))
                                               .orElseThrow(() -> new RuntimeException("Service not found"));
            service.setIntituleService(serviceTitles.get(i));
            serviceRepository.save(service);
        }

        redirectAttributes.addFlashAttribute("message", "Task and Services updated successfully!");
        return "redirect:/"; // Redirect to the index page
    }
    @GetMapping("/modifyRole")
    public String showModifyRolePage(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId ,Model model) {
        List<Role> roles = roleRepository.findAll();
        model.addAttribute("roles", roles);
        return "modifyRole";
    }
    
    

    @PostMapping("/modifyRole")
    public String modifyUserRole(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId) {
    	User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Role newRole = roleRepository.findById(roleId)
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Find the UserRole using the composite key
        Optional<UserRole> optionalUserRole = userroleRepository.findById(new UserRoleID(userId, roleId));
        UserRole userRole = optionalUserRole.orElseThrow(() -> new RuntimeException("User Role not found"));

        userRole.setRole(newRole);
        userroleRepository.save(userRole);

        return "redirect:/"; 
    }


 // Method to delete a user
    @PostMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int userId) {
        userRepository.deleteById(userId);
        return "redirect:/"; 
    }

    // Method to delete a task
    @PostMapping("/deleteTask")
    public String deleteTask(@RequestParam("taskId") int taskId) {
        taskRepository.deleteById(taskId);
        return "redirect:/"; 
    }
    @PostMapping("/deleteUserRole")
    public String deleteUserRole(@RequestParam("userId") int userId, @RequestParam("roleId") int roleId) {
        userroleRepository.deleteUserRole(userId, roleId);
        return "redirect:/"; 
    }



}

