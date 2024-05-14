package com.example.qlbvmb.controller;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.qlbvmb.model.Customer;
import com.example.qlbvmb.repository.CustomerRepo;
import java.util.List;
import org.springframework.security.core.context.SecurityContextHolder;
import com.example.qlbvmb.model.CustomerUserDetails;
import com.example.qlbvmb.service.UserService;
import com.example.qlbvmb.model.HanhKhach;


@Controller 
public class CustomerAuth {
    
    @Autowired
    UserService userService;

    @GetMapping("/login")
    public String showLoginPage() {
        return "index_signin";
    }


    @GetMapping("/staff")
    public String showStaffPage(Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        return "staff";
    }

    @GetMapping("/user")
    public String showUserPage(Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = userService.getUser(user.getUsername());
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        model.addAttribute("user", user);
        return "user";
    }
    
    @GetMapping("/admin")
    public String showAdminPage(Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Iterable<Customer> customers = userService.getAllUser();
        model.addAttribute("customers", customers);
        return "admin";
    }
    
    @GetMapping("/admin/edit/{id}")
    public String showEditCustomerPage(@PathVariable("id") int id, Model model) {
        Customer customer = userService.getUserById(id);
        model.addAttribute("customer", customer);
        return "Edit";
    }

    @PostMapping("/admin/edit/{id}")
    public String editCustomer(@PathVariable("id") int id, HttpServletRequest request) {
        Customer customer = userService.getUserById(id);
        String role = request.getParameter("Role");
        customer.setRole(role);
        userService.createUser(customer);
        return "redirect:/admin";
    }

    @GetMapping("/admin/delete/{id}")
    public String deleteCustomer(@PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/signup")
    public String showSignupPage() {
        return "index_signup";
    }

    @PostMapping("/signup")
    public String signup(HttpServletRequest request,
                        @ModelAttribute Customer customer,
                        @ModelAttribute HanhKhach hanhKhach,
                        Model model){

        String response;
        try {
            String username = request.getParameter("userName");
            if(userService.isUserExist(username)){
                response = "User is already existed for username " + username;
            }
            else {
                String password1 = request.getParameter("passWord1");
                String password2 = request.getParameter("passWord2");
                if(!password1.equals(password2)){
                    response = "Password does not match";
                }
                else {
                    String dinhDanh = request.getParameter("dinhDanh");
                    String Email = request.getParameter("email");
                    if(!userService.isHanhKhachExist(dinhDanh)){
                        userService.createNewHanhKhach(hanhKhach);
                    }
                    customer.setPassWord(password1);
                    customer.setEmail(Email);
                    customer.setDinhDanh(dinhDanh);
                    customer.setRole("USER");
                    userService.createNewUser(customer);
                    return "redirect:/login";
                }
            }
        }
        catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        model.addAttribute("response", response);
        return "index_signup";
    }

    @GetMapping("/password")
    public String showPasswordPage() {
        return "password";
    }

    @PostMapping("/password")
    public String changePassword(HttpServletRequest request, Model model,
                                  @ModelAttribute Customer customer) {
        String response;
        try {
            String username = request.getParameter("userName");
            if(!userService.isUserExist(username)){
                response = "User is not existed for username " + username;
            }
            else {
                String password1 = request.getParameter("passWord1");
                String password2 = request.getParameter("passWord2");
                if(!password1.equals(password2)){
                    response = "Password does not match";
                }
                else {
                    customer = userService.getUser(username);
                    customer.setPassWord(password1);
                    userService.createNewUser(customer);
                    return "redirect:/login";
                }
            }
        }
        catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        model.addAttribute("response", response);
        return "password";
    }
}
