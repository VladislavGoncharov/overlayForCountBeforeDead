package com.vladislavgoncharov.overlayforcounttimebeforedead.service;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Player;

import java.util.List;


public interface PlayerService {

    void updateTimePlayer(Long id, String time);

    List<Player> findAll();

    List<String> getPlayerById(Long id);

    void updateNickname(Long id, String nickname);

    void resetTime(Long id);
}
