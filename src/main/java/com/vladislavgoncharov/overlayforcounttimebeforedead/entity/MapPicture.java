package com.vladislavgoncharov.overlayforcounttimebeforedead.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "map_pictures")
public class MapPicture {
    private static final String SEQ_NAME = "seq_map_pictures";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME, sequenceName = SEQ_NAME, allocationSize = 1)
    private Long id;

    private String addressMapPicture;
    private String nameMap;
    private boolean isSelected;
    private String nameOfPlayerWhoChose;

}
