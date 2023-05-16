package com.assestmanagement.assetmanagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.assestmanagement.assetmanagement.Model.AssetMutation;
import com.assestmanagement.assetmanagement.Model.User;
import com.assestmanagement.assetmanagement.Repository.AssetMutationRepository;
import com.assestmanagement.assetmanagement.Repository.UserRepository;

@Controller
public class AssetMutionController {
    private static User user;
    private static String message="Welcome to asset management System";
    @Autowired
    private AssetMutationRepository assetrepo;
    @Autowired
    private UserRepository userRepo;
    @GetMapping(value = "/home/{id}")
    public String getPage(Model model,@PathVariable int id)
    {model.addAttribute("assetMutationList", assetrepo.findAll());
    user=userRepo.findById(id);
    model.addAttribute("message",message);
    model.addAttribute("listOfUserAssets", assetrepo.findUserAsset(user));
    model.addAttribute("userDetails", user);
        return "asset/asset";
    }
    @PostMapping(value = "/saveasset")
    public String saveOrUpdatemutation(@RequestParam int id,@RequestParam String name,@RequestParam int quantity
    ,@RequestParam String notes,@RequestParam int userid)
    {AssetMutation asset;
        user=userRepo.findById(userid);
        if(id==-1)
            asset=new AssetMutation(name, quantity, notes,user);
            else{
                asset=new AssetMutation(id, name, quantity, notes,user);
            }
            message=name+" saved Successfully";
            assetrepo.save(asset);
    return "redirect:/home/"+user.getId()+"";
    }
    @GetMapping(value="/deleteasset/{id}")
    public String deleteAsset(@PathVariable int id)
    {
        assetrepo.deleteById(id);
        message="Asset Deleted Successfully";
        return "redirect:/home/"+user.getId()+"";
    }
    
}
