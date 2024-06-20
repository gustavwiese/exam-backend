package kea.exambackend.repository;

import kea.exambackend.entity.Discipline;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DisciplineRepository extends JpaRepository<Discipline, Long> {
    List<Discipline> findByNameContaining(String name);
}
