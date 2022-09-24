package com.vladislavgoncharov.overlayforcounttimebeforedead.service;

import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.MapPictureDTO;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.MapPicture;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface PictureService {
    List<MapPictureDTO> findAllMapPicture();

    void mapSelect(Long idMap, Long idUser);

    List<MapPicture> getAllSelectedAndNamePlayer();

    void resetSelectedMap();

    void saveMapPicture(MapPictureDTO mapPictureDTO, MultipartFile filePicture) throws IOException;

    void deleteMapPicture(Long id);

    String getNameOfPlayerBySequenceNumber(Integer sequenceNumber);

    boolean isResetMap();

    void saveOthersPictures(Integer idPicture, MultipartFile othersPictures) throws IOException;

}

