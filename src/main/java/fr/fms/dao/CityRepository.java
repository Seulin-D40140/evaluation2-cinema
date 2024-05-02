package fr.fms.dao;

import fr.fms.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CityRepository extends JpaRepository <City , Long> {
    public List<City> findByNameContains(String description);
}
