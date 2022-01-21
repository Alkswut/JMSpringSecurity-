package web.controller;//package web.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import web.model.User;
import web.service.UserService;

import java.util.List;
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    UserService userService;

    @GetMapping(value = "/admin")
    public ModelAndView allUsers() {
        ModelAndView modelAndView = new ModelAndView();
        List<User> userList = userService.listUsers();
        modelAndView.setViewName("users");
        modelAndView.addObject("userList", userList);
        return modelAndView;
    }
//
//    @GetMapping(value = "/add")
//    public ModelAndView addPage() {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.addObject("user", new User());
//        modelAndView.setViewName("addUser");
//        return modelAndView;
//    }
//
//    @PostMapping(value = "/users")
//    public ModelAndView addUser(@ModelAttribute("user") User user) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        modelAndView.addObject("user");
//        userService.save(user);
//        return modelAndView;
//    }
//
//    @GetMapping(value = "/edit/{id}")
//    public ModelAndView editPage(@PathVariable("id") int id) {
//        User user = userService.getById(id);
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("editUser");
//        modelAndView.addObject("user", user);
//        return modelAndView;
//    }
//
//    @PostMapping(value = "/edit")
//    public ModelAndView editUser(@ModelAttribute("user") User user) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        userService.editUser(user);
//        return modelAndView;
//    }
//
//    @GetMapping(value = "/delete/{id}")
//    public ModelAndView deleteUser(@PathVariable("id") int id) {
//        ModelAndView modelAndView = new ModelAndView();
//        modelAndView.setViewName("redirect:/");
//        User user = userService.getById(id);
//        userService.delete(user);
//        return modelAndView;
//    }
}
