package com.vladislavgoncharov.overlayforcounttimebeforedead.controller.admin;

import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.MapPictureDTO;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.FontService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PictureService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PlayerService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.UserService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.AudioUpdate;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.TimeCutoffOnAudio;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import java.io.IOException;

@Controller
@MultipartConfig
@RequestMapping("/admin/settings")
public class AdminSettingsController {

    private final PlayerService playerService;
    private final UserService userService;
    private final PictureService pictureService;
    private final FontService fontService;
    private final AudioUpdate audioUpdate;

    public AdminSettingsController(PlayerService playerService, UserService userService, PictureService pictureService, FontService fontService, AudioUpdate audioUpdate) {
        this.playerService = playerService;
        this.userService = userService;
        this.pictureService = pictureService;
        this.fontService = fontService;
        this.audioUpdate = audioUpdate;
    }

    @RequestMapping()
    public String adminPage(Model model) {
        model.addAttribute("font", fontService.getMainFont());
        model.addAttribute("players", playerService.findAll());
        model.addAttribute("users", userService.findAll());
        model.addAttribute("timeCutoff", TimeCutoffOnAudio.getTimeCutoff());
        return "admin-page";
    }

    @RequestMapping("/map-picture")
    public String mapPictureSetting(Model model) {
        model.addAttribute("font", fontService.getMainFont());
        model.addAttribute("mapPictures", pictureService.findAllMapPicture());
        model.addAttribute("newMap", new MapPictureDTO());
        return "admin-settings-map-picture";
    }

    @PostMapping("/map-picture/save")
    public String saveMapPicture(@RequestParam("saveMapPicture") MultipartFile filePicture,
                                 @ModelAttribute("newMap") MapPictureDTO mapPictureDTO, Model model) {
        try {
            pictureService.saveMapPicture(mapPictureDTO, filePicture);
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("font", fontService.getMainFont());
            model.addAttribute("mapPictures", pictureService.findAllMapPicture());
            model.addAttribute("newMap", new MapPictureDTO());
        }

        return "redirect:/admin/settings/map-picture";
    }

    @RequestMapping("/map-picture/delete-map-picture-{id}")
    public String deleteMapPicture(@PathVariable("id") Long id) {
        pictureService.deleteMapPicture(id);
        return "redirect:/admin/settings/map-picture";
    }

    @RequestMapping("/others-pictures")
    public String othersPicturesSetting(Model model) {
        model.addAttribute("font", fontService.getMainFont());
        model.addAttribute("othersPictures", pictureService.getOthersPictures());
        model.addAttribute("newPicture", new MapPictureDTO());
        return "admin-settings-others-pictures";
    }

    @PostMapping("/others-pictures/save")
    public String saveOthersPictures(@RequestParam("saveOthersPictures") MultipartFile othersPictures,
                                     @RequestParam("saveSelectMapPicture") MultipartFile selectMapPicture, Model model) {
        try {
            if (othersPictures != null) pictureService.saveOthersPictures(othersPictures);
            else pictureService.saveSelectMapPicture(selectMapPicture);
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("font", fontService.getMainFont());
            model.addAttribute("othersPictures", pictureService.getOthersPictures());
            model.addAttribute("newPicture", new MapPictureDTO());
        }

        return "redirect:/admin/settings/others-pictures";
    }

    @RequestMapping("/font")
    public String fontSetting(Model model) {
        model.addAttribute("font", fontService.getMainFont());
        model.addAttribute("allFont", fontService.findAll());
        return "admin-settings-font";
    }

    @RequestMapping("/audio")
    public String audioSetting(Model model) {
        model.addAttribute("font", fontService.getMainFont());
        model.addAttribute("audioPath", AudioUpdate.audioName);
        model.addAttribute("newAudio", new Object());
        return "admin-settings-audio";
    }

    @PostMapping("/audio/save")
    public String saveAudio(@RequestParam("saveAudio") MultipartFile audio, Model model) {
        try {
            audioUpdate.saveAudio(audio);
        } catch (IOException e) {
            model.addAttribute("error", e.getMessage());
            model.addAttribute("font", fontService.getMainFont());
            model.addAttribute("audioPath", AudioUpdate.audioName);
            model.addAttribute("newAudio", new Object());
        }
        return "redirect:/admin/settings/audio";
    }

}
