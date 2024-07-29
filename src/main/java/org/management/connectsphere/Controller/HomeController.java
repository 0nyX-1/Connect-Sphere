package org.management.connectsphere.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.management.connectsphere.Mapper.UserMapper;
import org.management.connectsphere.Service.UserService;
import org.management.connectsphere.entites.User;
import org.management.connectsphere.forms.UserForm;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;


@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserService userService;
    private final UserMapper userMapper;
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("isAdmin", "true");
        return "redirect:/home";
    }

    @GetMapping("/about")
    public String about(){
        return "about";
    }

    @GetMapping("/home")
    public String homePage(){
        return "home";
    }

    @GetMapping("/services")
    public String services(){
        return "services";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/login")
    public String login(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "login";
    }
    private final AuthenticationManager authenticationManager;

    @GetMapping("/login-process")
    public String loginProcess( @ModelAttribute("userForm") UserForm userForm, @ModelAttribute("email")String email,
                               @ModelAttribute("password")String password,
                               Model model, HttpServletRequest request,
                               HttpSession httpSession) throws IOException {
        System.out.println("THe userForm is " +userForm);
        UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(email, password);
        authenticationToken.setDetails(new WebAuthenticationDetails(request));
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        System.out.println("Email and Password " + email+" "+ password);

        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            httpSession.setAttribute("username", email);
            System.out.println(SecurityContextHolder.getContext().getAuthentication());
            return "user/dashboard";
        } else {
            return "redirect:/login?error=true";
        }
    }
    @GetMapping("/register")
    public String register(Model model){
        UserForm userForm = new UserForm();
        model.addAttribute("userForm",userForm);
        return "register";
    }
//    Processing Register Process
    @PostMapping("/doRegister")
    public String processRegister(@Valid @ModelAttribute("userForm") UserForm userForm,BindingResult bindingResult, HttpSession httpSession){
        /*
        * fetch form data
        * validate form data
        * save to database
        * message="Registration successful"
        *redirect to login page
        *  */
        System.out.println("Received UserForm: " + userForm);
        System.out.println("Binding Result: " + bindingResult.getAllErrors());

        if(bindingResult.hasErrors()){
            return "register"; // return to register page with validation errors"
        }else{
            User user = userMapper.toUser(userForm);
            userService.storeRegistration(user);
            return "redirect:/register";
        }
    }
}
