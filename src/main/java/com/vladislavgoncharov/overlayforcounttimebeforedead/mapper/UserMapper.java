package com.vladislavgoncharov.overlayforcounttimebeforedead.mapper;

import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.UserDTO;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper
public interface UserMapper {

    UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

    default UserDTO fromUser(User user) {
        return UserDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .isChosenMap(user.isChosenMap())
                .build();

    }

    default List<UserDTO> fromUserList(List<User> users) {
        return users.stream()
                .map(this::fromUser)
                .collect(Collectors.toList());
    }

}
