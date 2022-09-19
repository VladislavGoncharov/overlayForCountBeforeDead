package com.vladislavgoncharov.overlayforcounttimebeforedead.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "overlay")
public class Overlay {

    @Id
    private Long id;
    private boolean switcher;

}
