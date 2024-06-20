package kea.exambackend.service;

import kea.exambackend.dto.DisciplineDTO;
import kea.exambackend.dto.ParticipantDTO;
import kea.exambackend.dto.ResultDTO;
import kea.exambackend.entity.Discipline;
import kea.exambackend.entity.Participant;
import kea.exambackend.entity.Result;
import kea.exambackend.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Override
    public ResultDTO createResult(ResultDTO resultDTO) {
        Result result = convertToEntity(resultDTO);
        return convertToDTO(resultRepository.save(result));
    }

    @Override
    public List<ResultDTO> getAllResults() {
        return resultRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResultDTO getResultById(Long id) {
        Result result = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
        return convertToDTO(result);
    }

    @Override
    public ResultDTO updateResult(Long id, ResultDTO resultDTO) {
        Result existingResult = resultRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Result not found with id: " + id));
        existingResult.setDate(resultDTO.getDate());
        existingResult.setResultValue(resultDTO.getResultValue());
        return convertToDTO(resultRepository.save(existingResult));
    }

    @Override
    public void deleteResult(Long id) {
        resultRepository.deleteById(id);
    }

    @Override
    public List<ResultDTO> getResultsByDiscipline(Long disciplineId) {
        return resultRepository.findByDisciplineIdOrderByResultValue(disciplineId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<ResultDTO> getResultsByParticipant(Long participantId) {
        return resultRepository.findByParticipantIdOrderByResultValue(participantId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ResultDTO convertToDTO(Result result) {
        ResultDTO resultDTO = new ResultDTO();
        resultDTO.setId(result.getId());
        resultDTO.setDate(result.getDate());
        resultDTO.setResultValue(result.getResultValue());
        // Convert Participant and Discipline entities to DTOs
        resultDTO.setParticipant(convertToDTO(result.getParticipant()));
        resultDTO.setDiscipline(convertToDTO(result.getDiscipline()));
        return resultDTO;
    }

    private Result convertToEntity(ResultDTO resultDTO) {
        Result result = new Result();
        result.setId(resultDTO.getId());
        result.setDate(resultDTO.getDate());
        result.setResultValue(resultDTO.getResultValue());
        // Convert ParticipantDTO and DisciplineDTO to entities if necessary
        return result;
    }

    private ParticipantDTO convertToDTO(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participant.getId());
        participantDTO.setName(participant.getName());
        participantDTO.setGender(participant.getGender());
        participantDTO.setAge(participant.getAge());
        participantDTO.setClub(participant.getClub());
        return participantDTO;
    }

    private DisciplineDTO convertToDTO(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(discipline.getId());
        disciplineDTO.setName(discipline.getName());
        disciplineDTO.setResultType(discipline.getResultType());
        return disciplineDTO;
    }
}
