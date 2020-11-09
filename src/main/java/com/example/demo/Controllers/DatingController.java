package com.example.demo.Controllers;

import com.example.demo.Models.Profile;
import com.example.demo.Repositories.ProfileRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.WebRequest;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class DatingController {

    ProfileRepository rp = new ProfileRepository();
    List<Profile> listAllProfiles = rp.listAllProfiles();
    Profile profile = new Profile(0,null,null,null,null,null);


    @GetMapping("/")
    public String index(Model profileModel){
        profileModel.addAttribute("profiles", listAllProfiles);
        return "index";
    }

    @PostMapping("/createprofile")
    public String createProfile(WebRequest createProfileData) throws SQLException {

        String name = createProfileData.getParameter("pName");
        String gender = null;
        if (createProfileData.getParameter("pGender") == createProfileData.getParameter("pGenderMand")) {
            gender = "Kvinde";
        } else {
            gender = "Mand";
        }
        String password = createProfileData.getParameter("pPassword");
        String email = createProfileData.getParameter("pEmail");
        String description = createProfileData.getParameter("pDescription");
        // Blob picture = (Blob) createProfileData.getAttribute("pPicture"); // FileInputStream ?
        rp.createProfile(name, email, password, gender, description);
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
            String gender = null;
            int id = Integer.parseInt(editProfile.getParameter("eId"));
            String name = editProfile.getParameter("eName");
            //String gender = editProfile.getParameter("eGender");
            if (editProfile.getParameter("pGender") == editProfile.getParameter("pGenderMand")) {
                gender = "Kvinde";
            } else {
                gender = "Mand";
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
            listAllProfiles = rp.searchProfile(gender);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        searchModel.addAttribute("profile",listAllProfiles);
        return "profileList";
    }

    @PostMapping("login")
    public String login(Model searchModel, WebRequest login){
        String name = login.getParameter("pName");
        try {
            listAllProfiles = rp.searchProfile(name);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        searchModel.addAttribute("profile",listAllProfiles);
        return "profileList";
    }

/*    @GetMapping("/contactForm")
    public String contactForm(Model model) {
        model.addAttribute("contactForm", new contactForm());
        return "Login";
    }

    @PostMapping("/contactForm")
    public String contactSubmit(@ModelAttribute contactForm contactForm, Model model) {
        model.addAttribute("contactForm", contactForm);
        // Tilføj ArrayList og / eller FileWriter her?
        return "contactReceipt";
    }

 */

}



