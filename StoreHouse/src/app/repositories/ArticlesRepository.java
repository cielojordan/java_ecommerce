package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Articles;


@Repository
public interface ArticlesRepository extends JpaRepository<Articles, Long> {

	public Articles findByName(String name);
}
