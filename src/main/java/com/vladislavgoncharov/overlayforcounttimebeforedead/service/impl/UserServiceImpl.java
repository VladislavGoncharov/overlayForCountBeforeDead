package com.vladislavgoncharov.overlayforcounttimebeforedead.service.impl;

import com.vladislavgoncharov.overlayforcounttimebeforedead.dto.UserDTO;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Role;
import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.User;
import com.vladislavgoncharov.overlayforcounttimebeforedead.mapper.UserMapper;
import com.vladislavgoncharov.overlayforcounttimebeforedead.repository.UserRepository;
import com.vladislavgoncharov.overlayforcounttimebeforedead.service.UserService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private static final UserMapper MAPPER = UserMapper.MAPPER;

    private final UserRepository userRepository;


    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Пользователь " + username + " не найден");
        }

        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(user.getRole().name()));

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );

    }

    @Override
    public UserDTO findUserByUsername(String name) {
        return MAPPER.fromUser(userRepository.findUserByUsername(name));
    }

    @Override
    public List<UserDTO> findAll() {
        return MAPPER.fromUserList(userRepository.findAll());
    }

    @Override
    public void updateUsername(Long id, String username) {
        userRepository.updateUsername(id,username);
    }

    @Override
    public void updatePassword(Long id, String password) {
        userRepository.updatePassword(id,password);
    }

    @Override
    public String getUserDataById(Long id) {
        User user = userRepository.getById(id);
        return user.getUsername() + "&&" + user.getPassword();
    }

    @Override
    public void randomPassword(Long id) {
        userRepository.updatePassword(id,RandomStringUtils.random(20, true, true));
    }

}
