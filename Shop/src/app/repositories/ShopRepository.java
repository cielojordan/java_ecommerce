package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Shop;

@Repository
public interface ShopRepository extends JpaRepository<Shop, Long> {

	public Shop findByArticleName(String name);
}
