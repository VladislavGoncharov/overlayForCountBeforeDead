package com.vladislavgoncharov.overlayforcounttimebeforedead.controller.admin;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.FontLink;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.FontService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PictureService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PlayerService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.UserService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.TimeCutoffOnAudio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/settings")
public class AdminSettingsRestController {

    private final PlayerService playerService;
    private final UserService userService;
    private final PictureService pictureService;
    private final FontService fontService;

    public AdminSettingsRestController(PlayerService playerService, UserService userService, PictureService pictureService, FontService fontService) {
        this.playerService = playerService;
        this.userService = userService;
        this.pictureService = pictureService;
        this.fontService = fontService;
    }

    @PostMapping("/update-player-{id}-nickname-{nickname}")
    public void updateNicknamePlayer(@PathVariable("id") Long id, @PathVariable("nickname") String nickname) {
        playerService.updateNickname(id, nickname);
        userService.updateUsername(++id, nickname);
    }

    @PostMapping("/reset-time-player-{id}")
    public void resetTimePlayer(@PathVariable("id") Long id) {
        playerService.resetTime(id);
    }

    @PostMapping("/reset-selected-map")
    public void resetSelectedMap() {
        pictureService.resetSelectedMap();
    }

    @PostMapping("/update-user-{id}-username-{username}")
    public void updateUserUsername(@PathVariable("id") Long id, @PathVariable("username") String username) {
        userService.updateUsername(id, username);
    }

    @PostMapping("/update-user-{id}-password-{password}")
    public void updateUserPassword(@PathVariable("id") Long id, @PathVariable("password") String password) {
        userService.updatePassword(id, password);
    }

    @PostMapping("/random-password-{id}")
    public void updateUserPassword(@PathVariable Long id) {
        userService.randomPassword(id);
    }

    @PostMapping("/get-user-data-by-id-{id}")
    public ResponseEntity<String> getUserData(@PathVariable("id") Long id) {
        return new ResponseEntity<>(userService.getUserDataById(id),HttpStatus.OK);
    }

    @PostMapping(value = "/font/save-font")
    public void updateFont(@RequestBody FontLink fontLink) {
        fontService.updateFont(fontLink);
    }

    @PostMapping(value = "/font/get-font-numbers")
    public ResponseEntity<String[]> getFontNumbers() {
        return new ResponseEntity<>(fontService.getNumbersFont(), HttpStatus.OK);
    }
    @PostMapping(value = "/timeCutoff={time}")
    public void setTimeCutoff(@PathVariable int time) {
        TimeCutoffOnAudio.setTimeCutoff(time);
    }


}
