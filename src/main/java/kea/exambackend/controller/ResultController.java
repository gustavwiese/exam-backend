package kea.exambackend.controller;

import kea.exambackend.dto.ResultDTO;
import kea.exambackend.service.ResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultService resultService;

    @PostMapping
    public ResponseEntity<ResultDTO> createResult(@RequestBody ResultDTO resultDTO) {
        return new ResponseEntity<>(resultService.createResult(resultDTO), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<ResultDTO>> createResultsBulk(@RequestBody List<ResultDTO> resultDTOs) {
        List<ResultDTO> results = resultDTOs.stream()
                .map(resultService::createResult)
                .collect(Collectors.toList());
        return new ResponseEntity<>(results, HttpStatus.CREATED);
    }


    @GetMapping
    public List<ResultDTO> getAllResults() {
        return resultService.getAllResults();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResultDTO> getResultById(@PathVariable Long id) {
        return new ResponseEntity<>(resultService.getResultById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ResultDTO> updateResult(@PathVariable Long id, @RequestBody ResultDTO resultDTO) {
        return new ResponseEntity<>(resultService.updateResult(id, resultDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        resultService.deleteResult(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/discipline/{disciplineId}")
    public List<ResultDTO> getResultsByDiscipline(@PathVariable Long disciplineId) {
        return resultService.getResultsByDiscipline(disciplineId);
    }

    @GetMapping("/participant/{participantId}")
    public List<ResultDTO> getResultsByParticipant(@PathVariable Long participantId) {
        return resultService.getResultsByParticipant(participantId);
    }
}
