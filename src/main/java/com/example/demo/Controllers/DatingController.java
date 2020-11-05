package com.example.demo.Controllers;

import com.example.demo.Models.Profile;
import com.example.demo.repositories.ProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DatingController {
    //hej test

    ProfileRepository rp = new ProfileRepository();
    List<Profile> allProfiles = new ArrayList<>();

    @GetMapping("/")
    public String index(Model profileModel){
        profileModel.addAttribute("profile", rp.listAllProfiles());
        return "index";
    }

    @PostMapping("/createprofile")
    public String createProfile(WebRequest createProfileData) throws SQLException {
        String name = createProfileData.getParameter("pName");
        String gender = createProfileData.getParameter("pGender");
        String email = createProfileData.getParameter("pEmail");
        String description = createProfileData.getParameter("pDescription");
        rp.createProfile(name,gender,email,description);
        return "redirect:/";
    }

    @PostMapping("/deleteprofile")
    public String deleteProfile(WebRequest deleteProfile) {
        try {
            int id = Integer.parseInt(deleteProfile.getParameter("delete"));
            rp.deleteProfile(id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/";
    }

    @PostMapping("/editprofile")
    public String editProfile(WebRequest editProfile) {
        try {
            int id = Integer.parseInt(editProfile.getParameter("eId"));
            String name = editProfile.getParameter("eName");
            String gender = editProfile.getParameter("eGender");
            String email = editProfile.getParameter("eEmail");
            String description = editProfile.getParameter("eDescription");
            rp.editProfile(id,name,gender,email,description);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("searchprofiles")
    public String searchProfiles(Model searchModel, WebRequest searchProfile){
        String gender = searchProfile.getParameter("sGender");
        try {
            allProfiles = rp.searchProfile(gender);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        searchModel.addAttribute("profile",allProfiles);
        return "profileList";
    }
}
