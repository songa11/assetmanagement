package com.assestmanagement.assetmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.*;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assestmanagement.assetmanagement.Model.User;
import com.assestmanagement.assetmanagement.Repository.UserRepository;

@Controller
public class UserController {
    @Autowired
    private UserRepository userrepo;
    @GetMapping(value="/")
    private String getPage()
    {
        return "index";
    }
    @PostMapping(value="/addOrupdateuser")
    public String saveOrupdate(Model model,@RequestParam int id,@RequestParam String name,
    @RequestParam String email,@RequestParam String username,@RequestParam String password)
    {User user;
        if(id==-1)
        user=new User(email,name, username, password);
        else
         {
            user=new User(id, name,email, username, password);
            userrepo.save(user);
         }
         userrepo.save(user);
        return "redirect:/";
    }
    public String getUserCredentials()
    {
        return "";
    }
    @PostMapping(value="/login")
    public String login(@RequestParam String username,@RequestParam String password)
    {
       User user= userrepo.login(username, password);
       if(user==null)
       {
        return "redirect:/";
       }
       else
      {
        return "redirect:/home/"+user.getId()+"";
      }
    }
}
