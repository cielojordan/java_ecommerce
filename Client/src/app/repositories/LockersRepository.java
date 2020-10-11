package app.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Lockers;

@Repository
public interface LockersRepository extends JpaRepository<Lockers, Long> {
	
	public List<Lockers> findByIsFree(Boolean bool);

}
