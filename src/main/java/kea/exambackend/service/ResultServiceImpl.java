package kea.exambackend.service;

import kea.exambackend.dto.DisciplineDTO;
import kea.exambackend.dto.ParticipantDTO;
import kea.exambackend.dto.ResultDTO;
import kea.exambackend.entity.Discipline;
import kea.exambackend.entity.Participant;
import kea.exambackend.entity.Result;
import kea.exambackend.repository.ResultRepository;
import kea.exambackend.repository.ParticipantRepository; // Importer ParticipantRepository
import kea.exambackend.repository.DisciplineRepository; // Importer DisciplineRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ResultServiceImpl implements ResultService {

    @Autowired
    private ResultRepository resultRepository;

    @Autowired
    private ParticipantRepository participantRepository; // Tilføj denne linje

    @Autowired
    private DisciplineRepository disciplineRepository; // Tilføj denne linje

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
        updateEntityFromDTO(existingResult, resultDTO);
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
        resultDTO.setParticipant(convertToDTO(result.getParticipant()));
        resultDTO.setDiscipline(convertToDTO(result.getDiscipline()));
        return resultDTO;
    }

    private Result convertToEntity(ResultDTO resultDTO) {
        Result result = new Result();
        updateEntityFromDTO(result, resultDTO);
        return result;
    }

    private void updateEntityFromDTO(Result result, ResultDTO resultDTO) {
        result.setDate(resultDTO.getDate());
        result.setResultValue(resultDTO.getResultValue());
        if (resultDTO.getParticipant() != null && resultDTO.getParticipant().getId() != null) {
            Participant participant = participantRepository.findById(resultDTO.getParticipant().getId())
                    .orElseThrow(() -> new RuntimeException("Participant not found with id: " + resultDTO.getParticipant().getId()));
            result.setParticipant(participant);
        }
        if (resultDTO.getDiscipline() != null && resultDTO.getDiscipline().getId() != null) {
            Discipline discipline = disciplineRepository.findById(resultDTO.getDiscipline().getId())
                    .orElseThrow(() -> new RuntimeException("Discipline not found with id: " + resultDTO.getDiscipline().getId()));
            result.setDiscipline(discipline);
        }
    }

    private ParticipantDTO convertToDTO(Participant participant) {
        ParticipantDTO participantDTO = new ParticipantDTO();
        participantDTO.setId(participant.getId());
        participantDTO.setName(participant.getName());
        participantDTO.setGender(participant.getGender());
        participantDTO.setAge(participant.getAge());
        participantDTO.setClub(participant.getClub());
        // You may need to set disciplines here if needed
        return participantDTO;
    }

    private DisciplineDTO convertToDTO(Discipline discipline) {
        DisciplineDTO disciplineDTO = new DisciplineDTO();
        disciplineDTO.setId(discipline.getId());
        disciplineDTO.setName(discipline.getName());
        disciplineDTO.setResultType(discipline.getResultType());
        // You may need to set participants here if needed
        return disciplineDTO;
    }
}
