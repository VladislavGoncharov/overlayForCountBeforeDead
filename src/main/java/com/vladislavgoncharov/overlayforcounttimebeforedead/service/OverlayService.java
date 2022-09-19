package com.vladislavgoncharov.overlayforcounttimebeforedead.service;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Overlay;
import com.vladislavgoncharov.overlayforcounttimebeforedead.repository.OverlayRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class OverlayService {

    private final OverlayRepository overlayRepository;

    public OverlayService(OverlayRepository showOverlayRepository) {
        this.overlayRepository = showOverlayRepository;
    }

    public void overlaySwitch(){
        Overlay overlay = overlayRepository.getById(1L);
        overlay.setSwitcher(!overlay.isSwitcher());
        overlayRepository.save(overlay);
    }

    public String getValueSwitcher(){
        if (overlayRepository.getById(1L).isSwitcher()) return "block";
        else return "none";
    }

}
