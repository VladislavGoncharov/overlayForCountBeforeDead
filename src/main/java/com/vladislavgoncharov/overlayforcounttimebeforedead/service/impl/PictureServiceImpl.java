package com.vladislavgoncharov.overlayforcounttimebeforedead.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.MapPictureDTO;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.MapPicture;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Role;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.User;
import com.vladislavgoncharov.overlayforcounttimebeforedead.mapper.MapPictureMapper;
import com.vladislavgoncharov.overlayforcounttimebeforedead.repository.MapPictureRepository;
import com.vladislavgoncharov.overlayforcounttimebeforedead.repository.UserRepository;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.PictureService;
import com.vladislavgoncharov.overlayforcounttimebeforedead.util.ImgUnderText;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PictureServiceImpl implements PictureService {

    private final MapPictureMapper MAPPER = MapPictureMapper.MAPPER;

    private final MapPictureRepository mapPictureRepository;
    private final UserRepository userRepository;
    private final Cloudinary cloudinary;

    public PictureServiceImpl(MapPictureRepository mapPictureRepository, UserRepository userRepository, Cloudinary cloudinary) {
        this.mapPictureRepository = mapPictureRepository;
        this.userRepository = userRepository;
        this.cloudinary = cloudinary;
    }

    @Override
    public List<MapPictureDTO> findAllMapPicture() {
        return MAPPER.fromMapPictureList(mapPictureRepository.findAll());

    }

    @Override
    public void mapSelect(Long idMap, Long idUser) {
        User user = userRepository.getById(idUser);

        if (user.getRole().equals(Role.PLAYER) &&
                user.isChosenMap()) {
            mapPictureRepository.mapSelect(idMap,user.getUsername());

            User enemy;

            if (idUser == 2)
                enemy = userRepository.getById(3L);
            else enemy = userRepository.getById(2L);

            enemy.setChosenMap(true);
            user.setChosenMap(false);
            userRepository.saveAll(List.of(user, enemy));
        }
    }

    @Override
    public List<Boolean> getAllIsSelected() {
        return mapPictureRepository.getAllIsSelected();
    }

    @Override
    public void resetSelectedMap() {
        mapPictureRepository.resetSelectedMap();
    }

    @Override
    public void saveMapPicture(MapPictureDTO mapPictureDTO, MultipartFile filePicture) throws IOException {
        mapPictureRepository.save(MapPicture.builder()
                .addressMapPicture(savePictureInCloudinary(filePicture))
                .nameMap(mapPictureDTO.getNameMap())
                .isSelected(false)
                .build());

    }

    @Override
    public void deleteMapPicture(Long id) {
        mapPictureRepository.deleteById(id);
    }

    @Override
    public String getNameOfPlayerByMapId(Long idMap) {
        return mapPictureRepository.getNameOfPlayerByMapId(idMap);
    }

    @Override
    public boolean isResetMap() {
        return mapPictureRepository.isResetMap();
    }

    @Override
    public void saveOthersPictures(Integer idPicture, MultipartFile picture) throws IOException {
        String addressImg = savePictureInCloudinary(picture);
        switch (idPicture){
            case 1:{
                ImgUnderText.setAddressImgUnderNumber(addressImg);
                break;
            }
            case 2: {
                ImgUnderText.setAddressImgUnderStopwatch(addressImg);
                break;
            }
            case 3: {
                ImgUnderText.setAddressImgUnderSelectMap(addressImg);
                break;
            }
            default: throw new IOException("Вы ошиблись, попробуйте еще раз");
        }
    }

    private String savePictureInCloudinary(MultipartFile multipartFile) throws IOException {

        File picture = new File("picture_" + multipartFile.getOriginalFilename());
        try (InputStream inputStreamReader = multipartFile.getInputStream();
             OutputStream outputStream = new FileOutputStream(picture)) {
            IOUtils.copy(inputStreamReader, outputStream);
        }

        Map uploadResult = cloudinary.uploader()
                .upload(picture, ObjectUtils.asMap("folder", "owerlay/map_picture"));

        picture.delete();

        return String.valueOf(uploadResult.get("url"));
    }

}
