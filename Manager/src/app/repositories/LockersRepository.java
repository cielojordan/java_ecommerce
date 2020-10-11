package app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import app.entities.Lockers;

@Repository
public interface LockersRepository extends JpaRepository<Lockers, Long> {

}
