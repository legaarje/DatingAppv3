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
    List<Profile> searchLogin = new ArrayList<>();


    @GetMapping("/")
    public String index(Model profileModel){
        profileModel.addAttribute("profile", rp.listAllProfiles());
        return "index";
    }

    @PostMapping("/createprofile")
    public String createProfile(WebRequest createProfileData) throws SQLException {
        String gender = "";
        int admin = 0;
        String name = createProfileData.getParameter("pName");
        if (createProfileData.getParameter("pGender") != createProfileData.getParameter("pGenderMand")) {
            gender = "Mand";
        } else {
            gender = "Kvinde";
        }
        String email = createProfileData.getParameter("pEmail");
        String description = createProfileData.getParameter("pDescription");
        String kodeord = createProfileData.getParameter("pKodeord");
        rp.createProfile(name, kodeord, gender, email, description, admin);
        return "login";
    }

    @PostMapping("/correctlogin")
    public String login(WebRequest loginData) throws SQLException {
        String email = loginData.getParameter("pEmail");
        String kodeord = loginData.getParameter("pKodeord");
        allProfiles = rp.searchLogin(email,kodeord);

        try {
            Profile p = allProfiles.get(0);
        } catch (IndexOutOfBoundsException e) {
            return "errorlogin";
        }
        return "main";
    }
    /*
    @PostMapping("/incorretlogin")
    public String incorretLogin(WebRequest loginData, Model errorModel) throws SQLException {
        String name = loginData.getParameter("pName");
        String kodeord = loginData.getParameter("pKodeord");
        allProfiles = rp.searchLogin(name, kodeord);
        errorModel.addAttribute("errorModel",errorModel);

        try {
            Profile p = allProfiles.get(0);

        } catch (IndexOutOfBoundsException e) {
            return "redirect:/incorrectlogin";
        }
        return "main";
    }
     */

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
            String gender = "";
            int id = Integer.parseInt(editProfile.getParameter("eId"));
            String name = editProfile.getParameter("eName");
            if (editProfile.getParameter("pGender") != editProfile.getParameter("pGenderMand")) {
                gender = "Mand";
            } else {
                gender = "Kvinde";
                System.out.println("fejl");
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
    public String profile(Model profileModel, WebRequest profile){

        String profileId = profile.getParameter("profileId");

        try {
            allProfiles = rp.profile(profileId);
            profileModel.addAttribute("profile",allProfiles);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
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




