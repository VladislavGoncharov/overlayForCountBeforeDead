package com.vladislavgoncharov.overlayforcounttimebeforedead.controller;

import com.vladislavgoncharov.overlayforcounttimebeforedead.service.FontService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PictureService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PlayerService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.UserService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.AudioUpdate;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.ImgUnderNickname;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class MainController {

    private final PlayerService playerService;
    private final PictureService pictureService;
    private final UserService userService;
    private final FontService fontService;

    public MainController(PlayerService playerService, PictureService pictureService, UserService userService, FontService fontService) {
        this.playerService = playerService;
        this.pictureService = pictureService;
        this.userService = userService;
        this.fontService = fontService;
    }

    @RequestMapping("/")
    public String stopwatchPage(Model model) {
        model.addAttribute("players", playerService.findAll());
        model.addAttribute("audioPath", AudioUpdate.audioName);
        model.addAttribute("font", fontService.getMainFont());
        model.addAttribute("imgUnderNickname", ImgUnderNickname.getAddressPicture());
        return "stopwatch";
    }

    @RequestMapping("/switch-map")
    public String switchMapPage(Model model, Principal principal) {
        model.addAttribute("font", fontService.getMainFont());
        model.addAttribute("maps", pictureService.findAllMapPicture());
        if (principal != null)
            model.addAttribute("user", userService.findUserByUsername(principal.getName()));

        return "switch-map";
    }
}
