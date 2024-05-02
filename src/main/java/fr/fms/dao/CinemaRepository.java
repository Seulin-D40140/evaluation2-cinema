package fr.fms.dao;

import fr.fms.entities.Cinema;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema , Long> {
    public Page<Cinema> findByCityId(Long idCity, Pageable pageable);

    public Page<Cinema> findByNameContains(String description , Pageable pageable);
}
