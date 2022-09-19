package com.vladislavgoncharov.overlayforcounttimebeforedead.service;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.FontLink;
import com.vladislavgoncharov.overlayforcounttimebeforedead.repository.FontRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FontService {

    private final FontRepository fontRepository;


    public FontService(FontRepository fontRepository) {
        this.fontRepository = fontRepository;
    }

    public FontLink getMainFont(){
        return fontRepository.getById(1L);
    }

    public List<FontLink> findAll() {
        return fontRepository.findAll();
    }

    public void updateFont(FontLink fontLink) {
        fontRepository.save(fontLink);
    }

    public String[] getNumbersFont() {
        FontLink fontLink = fontRepository.getById(2L);
        return new String[]{fontLink.getFontAddress(),fontLink.getFontName(),fontLink.getColor()};
    }
}
