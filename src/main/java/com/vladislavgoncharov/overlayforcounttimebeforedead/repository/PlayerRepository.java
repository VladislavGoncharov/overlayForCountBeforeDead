package com.vladislavgoncharov.overlayforcounttimebeforedead.repository;


import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Player;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    @Modifying
    @Query(nativeQuery = true,value = "update players set nickname = ?2 where id = ?1")
    void updateNickname(Long id, String nickname);

    @Modifying
    @Query(nativeQuery = true,value = "update players set time = '00:00' where id = ?1")
    void resetTime(Long id);
}
