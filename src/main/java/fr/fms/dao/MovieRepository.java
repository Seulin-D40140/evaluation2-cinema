package fr.fms.dao;

import fr.fms.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie , Long>
{

}
