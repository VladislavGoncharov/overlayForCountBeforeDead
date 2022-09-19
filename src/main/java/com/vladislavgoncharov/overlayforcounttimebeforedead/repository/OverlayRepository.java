package com.vladislavgoncharov.overlayforcounttimebeforedead.repository;

import com.vladislavgoncharov.overlayforcounttimebeforedead.entity.Overlay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OverlayRepository extends JpaRepository<Overlay,Long> {
}
