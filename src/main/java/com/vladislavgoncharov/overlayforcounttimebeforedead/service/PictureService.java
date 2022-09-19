package com.vladislavgoncharov.overlayforcounttimebeforedead.service;

import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.MapPictureDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    List<MapPictureDTO> findAllMapPicture();

    void mapSelect(Long idMap, Long idUser);

    List<Boolean> getAllIsSelected();

    void resetSelectedMap();

    void saveMapPicture(MapPictureDTO mapPictureDTO, MultipartFile filePicture) throws IOException;

    void deleteMapPicture(Long id);

    String getNameOfPlayerByMapId(Long idMap);

    boolean isResetMap();

    List<String> getOthersPictures();

    void saveOthersPictures(MultipartFile othersPictures) throws IOException;

    void saveSelectMapPicture(MultipartFile selectMapPicture) throws IOException;

}

