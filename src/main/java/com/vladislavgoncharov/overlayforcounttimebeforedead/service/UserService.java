package com.vladislavgoncharov.overlayforcounttimebeforedead.service;

import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    UserDTO findUserByUsername(String name);

    List<UserDTO> findAll();

    void updateUsername(Long id, String username);

    void updatePassword(Long id, String password);

    String getUserDataById(Long id);

    void randomPassword(Long id);
}
