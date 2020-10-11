package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.StoreHouse;

@Repository
public interface StoreHouseRepository extends JpaRepository<StoreHouse, Long> {

	public StoreHouse findByArticleName(String articleName);
}
