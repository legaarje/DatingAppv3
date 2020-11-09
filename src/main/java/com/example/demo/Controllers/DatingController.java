package com.example.demo.Controllers;

import com.example.demo.Models.Profile;
import com.example.demo.Repositories.ProfileRepository;
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

    ProfileRepository rp = new ProfileRepository();
    List<Profile> allProfiles = new ArrayList<>();
    // Profile profile = new Profile(0,null,null,null,null,null, 0, null);


    @GetMapping("/")
    public String index(Model profileModel){
        profileModel.addAttribute("profile", rp.listAllProfiles());
        return "index";
    }

    @PostMapping("/createprofile")
    public String createProfile(WebRequest createProfileData) throws SQLException {
        String gender = null;
        int admin = 0;
        String name = createProfileData.getParameter("pName");
        if (createProfileData.getParameter("pGender") == createProfileData.getParameter("pGenderMand")) {
            gender = "Mand";
        } else {
            gender = "Kvinde";
        }
       // String gender = createProfileData.getParameter("pGender");
        String email = createProfileData.getParameter("pEmail");
        String description = createProfileData.getParameter("pDescription");
        String kodeord = createProfileData.getParameter("pKodeord");
        rp.createProfile(name, kodeord, gender ,email, description, admin);
        return "profilelist";
    }

    @PostMapping("/correctlogin")
    public String login(WebRequest loginData) throws SQLException {
    String name = loginData.getParameter("pName");
    String kodeord = loginData.getParameter("pKodeord");

        //rp.searchLogin(name, kodeord).getId();

       // rp.searchLogin(name, kodeord).getAdmin();

        return "main";
    }
    @PostMapping("/main")
    public String searchGender(Model searchModel, WebRequest searchProfile){
            String gender = null;

        if (searchProfile.getParameter("pGender") == searchProfile.getParameter("pGenderMand")) {
            gender = "Mand";
        } else {
            gender = "Kvinde";
        }

        try {
            allProfiles = rp.searchProfile(gender);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        searchModel.addAttribute("profile", allProfiles);
    return "main";
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
            String gender = null;
            int id = Integer.parseInt(editProfile.getParameter("eId"));
            String name = editProfile.getParameter("eName");
            //String gender = editProfile.getParameter("eGender");
            if (editProfile.getParameter("pGender") == editProfile.getParameter("pGenderMand")) {
                gender = "Mand";
            } else {
                gender = "Kvinde";
            }
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
        return "main";
    }

    //Login
    @GetMapping("/login")
    public String login(Model model) {
        //model.addAttribute("pLogin", new login());
        //model.addAttribute("pName", "pKodeord");
        return "login";
    }

    @GetMapping("/profile")
    public String profile(Model profileModel){
        profileModel.addAttribute("profile", rp.listAllProfiles());
        return "profile";
    }

    /*
   @PostMapping("/login")
    public String login(WebRequest login) throws SQLException {
        String
        model.addAttribute("contactForm", contactForm);
        // Tilføj ArrayList og / eller FileWriter her?
        return "contactReceipt";
    } */

   //         String name = createProfileData.getParameter("pName");

    }




