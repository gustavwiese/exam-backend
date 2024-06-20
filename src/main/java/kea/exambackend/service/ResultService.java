package kea.exambackend.service;

import kea.exambackend.dto.ResultDTO;
import java.util.List;

public interface ResultService {
    ResultDTO createResult(ResultDTO resultDTO);
    List<ResultDTO> getAllResults();
    ResultDTO getResultById(Long id);
    ResultDTO updateResult(Long id, ResultDTO resultDTO);
    void deleteResult(Long id);
    List<ResultDTO> getResultsByDiscipline(Long disciplineId);
    List<ResultDTO> getResultsByParticipant(Long participantId);
}
