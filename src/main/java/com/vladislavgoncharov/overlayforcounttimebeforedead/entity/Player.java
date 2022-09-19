package com.vladislavgoncharov.overlayforcounttimebeforedead.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

@Entity
@Table(name = "players")
public class Player {

    private static final String SEQ_NAME = "seq_players";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = SEQ_NAME)
    @SequenceGenerator(name = SEQ_NAME,sequenceName = SEQ_NAME,allocationSize = 1)
    private Long id;

    private String nickname;
    private String time;



}
