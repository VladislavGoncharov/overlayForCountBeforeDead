package com.vladislavgoncharov.overlayforcounttimebeforedead.service.impl;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Player;
import com.vladislavgoncharov.overlayforcounttimebeforedead.repository.PlayerRepository;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PlayerService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

    private final PlayerRepository playerRepository;

    public PlayerServiceImpl(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public void updateTimePlayer(Long id, String time) {
        String second, millisecond;
        int secondInt, millisecondInt;
        Player player = playerRepository.getById(id);


        String[] oldTimeArray = player.getTime().split(":");
        String[] currentTimeArray = time.split(":");

        secondInt = Integer.parseInt(oldTimeArray[0]) + Integer.parseInt(currentTimeArray[0]);
        millisecondInt = Integer.parseInt(oldTimeArray[1]) + Integer.parseInt(currentTimeArray[1]);

        if (millisecondInt > 99) {
            millisecondInt = millisecondInt - 100;
            secondInt++;
        }
        if (secondInt < 10) second = "0" + secondInt;
        else second = String.valueOf(secondInt);

        if (millisecondInt < 10) millisecond = "0" + millisecondInt;
        else millisecond = String.valueOf(millisecondInt);

        player.setTime(second + ":" + millisecond);
        playerRepository.save(player);
    }

    @Override
    public List<Player> findAll() {
        return playerRepository.findAll();
    }

    @Override
    public List<String> getPlayerById(Long id) {
        Player player = playerRepository.getById(id);
        return List.of(player.getNickname(), player.getTime());
    }

    @Override
    public void updateNickname(Long id, String nickname) {
        playerRepository.updateNickname(id,nickname);
    }

    @Override
    public void resetTime(Long id) {
        playerRepository.resetTime(id);
    }
}
