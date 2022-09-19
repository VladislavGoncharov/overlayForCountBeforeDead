package com.vladislavgoncharov.overlayforcounttimebeforedead.repository;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.FontLink;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FontRepository extends JpaRepository<FontLink,Long> {
}
