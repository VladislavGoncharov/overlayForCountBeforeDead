package com.vladislavgoncharov.overlayforcounttimebeforedead.controller.rest;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.MapPicture;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PictureService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/switch-map")
public class MapRestController {

    private final PictureService pictureService;
    private final UserService userService;

    public MapRestController(PictureService pictureService, UserService userService) {
        this.pictureService = pictureService;
        this.userService = userService;
    }

    @PostMapping("/map-select-{idMap}-user-{idUser}")
    public void mapBooking(@PathVariable("idMap") Long idMap,
                           @PathVariable("idUser") Long idUser) {
        pictureService.mapSelect(idMap,idUser);
    }

    @PostMapping("/get-name-of-player-by-map-id-{sequenceNumber}")
    public ResponseEntity<String> mapBooking(@PathVariable("sequenceNumber") Integer sequenceNumber) {
        return new ResponseEntity<>(pictureService.getNameOfPlayerBySequenceNumber(sequenceNumber),HttpStatus.OK);
    }

    @PostMapping("/get-all-selected-and-name-player")
    public ResponseEntity<List<MapPicture>> getAllMapSelectedAndNamePlayer() {
        return new ResponseEntity<>(pictureService.getAllSelectedAndNamePlayer(),HttpStatus.OK);
    }

    @PostMapping("/is-reset-map")
    public ResponseEntity<Boolean> resetMap() {
        return new ResponseEntity<>(pictureService.isResetMap(),HttpStatus.OK);
    }

}
