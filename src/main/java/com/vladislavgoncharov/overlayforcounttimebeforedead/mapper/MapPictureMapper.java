package com.vladislavgoncharov.overlayforcounttimebeforedead.mapper;

import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.MapPictureDTO;
import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.UserDTO;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.MapPicture;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface MapPictureMapper {

    MapPictureMapper MAPPER = Mappers.getMapper(MapPictureMapper.class);

    default MapPictureDTO fromMapPicture(MapPicture mapPicture) {
        return MapPictureDTO.builder()
                .id(mapPicture.getId())
                .addressMapPicture(mapPicture.getAddressMapPicture())
                .nameMap(mapPicture.getNameMap())
                .isSelected(mapPicture.isSelected())
                .nameOfPlayerWhoChose(mapPicture.getNameOfPlayerWhoChose())
                .build();

    }

    default List<MapPictureDTO> fromMapPictureList(List<MapPicture> mapPictures) {
        return mapPictures.stream()
                .map(this::fromMapPicture)
                .collect(Collectors.toList());
    }

}
