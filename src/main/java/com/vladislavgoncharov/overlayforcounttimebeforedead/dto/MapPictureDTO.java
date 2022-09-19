package com.vladislavgoncharov.overlayforcounttimebeforedead.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class MapPictureDTO {

    private Long id;
    private String addressMapPicture;
    private String nameMap;
    private boolean isSelected;
    private String nameOfPlayerWhoChose;

    public String getSelectedStyle(){
        if (isSelected) return "opacity: 1";
        return "opacity: 0";
    }

}
