package com.vladislavgoncharov.overlayforcounttimebeforedead.repository;


import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUsername(String username);

    @Modifying
    @Query(nativeQuery = true,value = "update users set username = ?2 where id = ?1")
    void updateUsername(Long id, String username);

    @Modifying
    @Query(nativeQuery = true,value = "update users set password = ?2 where id = ?1")
    void updatePassword(Long id, String password);
}
