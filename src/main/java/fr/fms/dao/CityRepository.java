package fr.fms.dao;

import fr.fms.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository <City , Long> {
}
