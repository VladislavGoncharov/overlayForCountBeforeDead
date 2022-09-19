package com.vladislavgoncharov.overlayforcounttimebeforedead.repository;


import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.MapPicture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MapPictureRepository extends JpaRepository<MapPicture, Long> {

    @Modifying
    @Query(nativeQuery = true,
            value = "update map_pictures set is_selected = true, name_of_player_who_chose = ?2 where id = ?1")
    void mapSelect(Long id, String username);

    @Modifying
    @Query(nativeQuery = true,value = "select is_selected from map_pictures")
    List<Boolean> getAllIsSelected();

    @Modifying
    @Query(nativeQuery = true,value = "update map_pictures set is_selected = false, name_of_player_who_chose = ''")
    void resetSelectedMap();

    @Query(nativeQuery = true,value = "select name_of_player_who_chose from map_pictures  where id = ?1")
    String getNameOfPlayerByMapId(Long idMap);

    @Query(nativeQuery = true,value = "select (7 =  (select count(*) from map_pictures  where is_selected = false))")
    boolean isResetMap();
}
