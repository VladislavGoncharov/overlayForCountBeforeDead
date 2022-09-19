package com.vladislavgoncharov.overlayforcounttimebeforedead.controller.rest;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.FontLink;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.FontService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.OverlayService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PlayerService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.StatusStopwatch;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.StopStartStopwatch;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.TimeCutoffOnAudio;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StopwatchRestController {

    private final PlayerService playerService;
    private final FontService fontService;
    private final OverlayService overlayService;

    public StopwatchRestController(PlayerService playerService, FontService fontService, OverlayService overlayService) {
        this.playerService = playerService;
        this.fontService = fontService;
        this.overlayService = overlayService;
    }

    @GetMapping("/{id}/time={time}")
    public void updateTimePlayers(@PathVariable("id") Long id,
                                  @PathVariable("time") String time) {
        playerService.updateTimePlayer(id, time);
    }

    @PostMapping("/get-player/{id}")
    public ResponseEntity<List<String>> getTimePlayer(@PathVariable Long id) {
        return new ResponseEntity<>(playerService.getPlayerById(id), HttpStatus.OK);
    }

    @PostMapping(value = "/font/get-font-numbers")
    public ResponseEntity<String[] > getFontNumbers() {
        return new ResponseEntity<>(fontService.getNumbersFont(), HttpStatus.OK);
    }

    @PostMapping("/switcher-overlay")
    public void switcherShowOverlay() {
        overlayService.overlaySwitch();
    }

    @PostMapping("/get-switcher-overlay")
    public ResponseEntity<String> getSwitcherOverlay() {
        return new ResponseEntity<>(overlayService.getValueSwitcher(), HttpStatus.OK);
    }

    @PostMapping("/stopwatch-start")
    public void stopwatchStart() {
        StopStartStopwatch.setWork(StatusStopwatch.START);
    }

    @PostMapping("/stopwatch-stop")
    public void stopwatchStop(@RequestBody String time) {
        StopStartStopwatch.setWork(StatusStopwatch.STOP);
        StopStartStopwatch.setResult(time);
    }

    @PostMapping("/stopwatch-reset")
    public void stopwatchReset() {
        StopStartStopwatch.setWork(StatusStopwatch.RESET);
    }

    @PostMapping("/does-stopwatch-work")
    public ResponseEntity<String> doesStopwatchWork() {
        return new ResponseEntity<>(StopStartStopwatch.getWork(), HttpStatus.OK);
    }

    @PostMapping("/get-result-stopwatch")
    public ResponseEntity<String> getResultStopwatch() {
        return new ResponseEntity<>(StopStartStopwatch.getResult(), HttpStatus.OK);
    }

    @PostMapping("/get-time-cutoff-on-audio")
    public ResponseEntity<Integer> getTimeCutoffOnAudio() {
        return new ResponseEntity<>(TimeCutoffOnAudio.getTimeCutoff(), HttpStatus.OK);
    }


}
