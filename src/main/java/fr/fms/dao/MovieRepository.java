package fr.fms.dao;

import fr.fms.entities.Cinema;
import fr.fms.entities.Movie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long>
{
    public Page<Movie> findByCinemaId(Long idCity, Pageable pageable);
}

