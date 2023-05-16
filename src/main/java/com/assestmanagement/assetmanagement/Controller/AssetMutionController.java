package com.assestmanagement.assetmanagement.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;

import com.assestmanagement.assetmanagement.Model.AssetMutation;
import com.assestmanagement.assetmanagement.Model.User;
import com.assestmanagement.assetmanagement.Repository.AssetMutationRepository;
import com.assestmanagement.assetmanagement.Repository.UserRepository;

import jakarta.servlet.http.Part;

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
    @GetMapping(value = "/getUpdate")
    public String getUserDetail(Model model,@RequestParam() int assetId)
    {model.addAttribute("user", user);
    AssetMutation mutation = assetrepo.findById(assetId);
    model.addAttribute("asset",mutation);
        return "asset/update";
    }
    @PostMapping(value = "/saveasset")
    public String saveOrUpdatemutation(@RequestParam int id,@RequestParam String name,@RequestParam int quantity
    ,@RequestParam String notes,@RequestParam int userid,@RequestPart Part photo) throws IOException
    {AssetMutation asset;
        if(photo==null)
        {
            return "redirect:/";
        }else{
            InputStream in=photo.getInputStream();
            byte[] arrs=new byte[(int)photo.getSize()];
            in.read(arrs);
            String image=Base64.getEncoder().encodeToString(arrs);
        user=userRepo.findById(userid);
        if(id==-1)
            asset=new AssetMutation(name, quantity, notes,user,image);
            else{
                asset=new AssetMutation(id, name, quantity, notes,user,image);
            }
            message=name+" saved Successfully";
            assetrepo.save(asset);
    return "redirect:/home/"+user.getId()+"";
        }
    }
    @GetMapping(value="/deleteasset/{id}")
    public String deleteAsset(@PathVariable int id)
    {
        assetrepo.deleteById(id);
        message="Asset Deleted Successfully";
        return "redirect:/home/"+user.getId()+"";
    }
}
