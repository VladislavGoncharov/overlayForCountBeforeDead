package com.vladislavgoncharov.overlayforcounttimebeforedead.dto;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Role;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UserDTO {

    private Long id;
    private String username;
    private String password;
    private Role role;
    private boolean isChosenMap;

}


