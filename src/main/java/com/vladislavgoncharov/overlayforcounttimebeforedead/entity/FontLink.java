package com.vladislavgoncharov.overlayforcounttimebeforedead.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "font_link")
public class FontLink {

    @Id
    private Long id;
    private String fontAddress;
    private String fontName;
    private String color;
}
