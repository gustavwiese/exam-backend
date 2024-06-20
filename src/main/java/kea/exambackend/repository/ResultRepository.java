package kea.exambackend.repository;

import kea.exambackend.entity.Result;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultRepository extends JpaRepository<Result, Long> {
    List<Result> findByDisciplineIdOrderByResultValue(Long disciplineId);
    List<Result> findByDisciplineIdAndParticipantGenderAndParticipantAgeBetweenOrderByResultValue(Long disciplineId, String gender, int minAge, int maxAge);
}

