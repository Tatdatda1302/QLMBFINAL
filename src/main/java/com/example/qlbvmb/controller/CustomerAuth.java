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
import org.springframework.security.crypto.password.PasswordEncoder;



@Controller 
public class CustomerAuth {
    
    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @GetMapping("/home")
    public String showPage() {
        return "index";
    }

    @GetMapping("/logout")
    public String logout() {
        return "redirect:/login";
    }

    // @GetMapping("/Home/{role}")
    // public String showPage(@PathVariable("role") String role, Model model) {
    //     model.addAttribute("role", role);
    //     return "index";
    // }

    @GetMapping("/login")
    public String showLoginPage() {
        return "index_signin";
    }

    @GetMapping("/{role}")
    public String showPage(@PathVariable("role") String role, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        if(user.getAuthorities().toString().equals("[ADMIN]")) {
            Iterable<Customer> customers = userService.getAllUser();
            model.addAttribute("customers", customers);
            return "redirect:/ADMIN";
        }
        if(user.getAuthorities().toString().equals("[STAFF]")) {
            return "redirect:/STAFF";
        }
        else {
            Customer customer = userService.getUser(user.getUsername());
            HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
            model.addAttribute("hanhKhach", hanhKhach);
            model.addAttribute("customer", customer);
            return "redirect:/USER";
        }
    }

    @GetMapping("/STAFF")
    public String showStaffPage(Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUser(user.getUsername());
        model.addAttribute("customer", customer);
        return "staff";
    }

    @GetMapping("/USER")
    public String showUserPage(Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Customer customer = userService.getUser(user.getUsername());
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        model.addAttribute("user", user);
        model.addAttribute("customer", customer);
        return "user";
    }
    
    @GetMapping("/ADMIN")
    public String showAdminPage(Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Iterable<Customer> customers = userService.getAllUser();
        model.addAttribute("customers", customers);
        return "admin";
    }

    // @GetMapping("/{role}/PhanQuyen")
    // public String showPhanQuyenPage(@PathVariable("role") String role, Model model) {
    //     CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    //     model.addAttribute("user", user);
    //     Iterable<Customer> customers = userService.getAllUser();
    //     model.addAttribute("customers", customers);
    //     model.addAttribute("role", role);
    //     return "PhanQuyen";
    // }
    
    @GetMapping("/{role}/edit/{id}")
    public String showEditCustomerPage(@PathVariable("role") String role, @PathVariable("id") int id, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        Customer customer = userService.getUserById(id);
        model.addAttribute("customer", customer);
        HanhKhach hanhKhach = userService.getHanhKhach(customer.getDinhDanh());
        model.addAttribute("hanhKhach", hanhKhach);
        model.addAttribute("role", role);
        return "Edit";
    }

    @PostMapping("/{role}/edit/{id}")
    public String editCustomer(@PathVariable("role") String role ,@PathVariable("id") int id, HttpServletRequest request, Model model) {
        CustomerUserDetails user = (CustomerUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("user", user);
        model.addAttribute("role", role);
        Customer customer = userService.getUserById(id);
        String username = request.getParameter("userName");
        String password = request.getParameter("passWord");
        String email = request.getParameter("Email");
        String dinhDanh = request.getParameter("dinhDanh");
        String Role = request.getParameter("Role");
        if(username != null) customer.setUserName(username);
        if(password != null) customer.setPassWord(passwordEncoder.encode(password));
        if(email != null) customer.setEmail(email);
        if(dinhDanh != null) customer.setDinhDanh(dinhDanh);
        if(Role != null) customer.setRole(Role);
        userService.createUser(customer);
        if(role.equals("USER")) return "redirect:/{role}/edit/{id}";
        else if(role.equals("STAFF")) return "redirect:/{role}/edit/{id}";
        return "redirect:/ADMIN";
    }

    @GetMapping("/{role}/delete/{id}")
    public String deleteCustomer(@PathVariable("role") String role, @PathVariable("id") int id) {
        userService.deleteUser(id);
        return "redirect:/ADMIN";
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

    // @GetMapping("/{role}/ThemUser")
    // public String showThemUserPage() {
    //     return "ThemUser";
    // }
    @PostMapping("/{role}/ThemUser")
    public String ThemUser(HttpServletRequest request,
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
                String password = request.getParameter("passWord");
                String dinhDanh = request.getParameter("dinhDanh");
                String Email = request.getParameter("email");
                String role = request.getParameter("Role");
                if(!userService.isHanhKhachExist(dinhDanh)){
                    userService.createNewHanhKhach(hanhKhach);
                }
                customer.setPassWord(password);
                customer.setEmail(Email);
                customer.setDinhDanh(dinhDanh);
                customer.setRole(role);
                userService.createNewUser(customer);
                return "redirect:/admin";
            }
        }
        catch (Exception e) {
            response = "Error: " + e.getMessage();
        }
        model.addAttribute("response", response);
        return "ThemUser";
    }
}
