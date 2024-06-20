package kea.exambackend.controller;
import kea.exambackend.repository.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import kea.exambackend.entity.Result;


import java.util.List;

@RestController
@RequestMapping("/api/results")
public class ResultController {

    @Autowired
    private ResultRepository resultRepository;

    @PostMapping
    public ResponseEntity<Result> createResult(@RequestBody Result result) {
        return new ResponseEntity<>(resultRepository.save(result), HttpStatus.CREATED);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<Result>> createResults(@RequestBody List<Result> results) {
        return new ResponseEntity<>(resultRepository.saveAll(results), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Result> updateResult(@PathVariable Long id, @RequestBody Result resultDetails) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        result.setDate(resultDetails.getDate());
        result.setResultValue(resultDetails.getResultValue());
        return new ResponseEntity<>(resultRepository.save(result), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteResult(@PathVariable Long id) {
        Result result = resultRepository.findById(id).orElseThrow(() -> new RuntimeException("Result not found"));
        resultRepository.delete(result);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/discipline/{disciplineId}")
    public List<Result> getResultsByDiscipline(@PathVariable Long disciplineId) {
        return resultRepository.findByDisciplineIdOrderByResultValue(disciplineId);
    }

    @GetMapping("/discipline/{disciplineId}/filter")
    public List<Result> getResultsByDisciplineAndFilter(@PathVariable Long disciplineId, @RequestParam String gender, @RequestParam int minAge, @RequestParam int maxAge) {
        return resultRepository.findByDisciplineIdAndParticipantGenderAndParticipantAgeBetweenOrderByResultValue(disciplineId, gender, minAge, maxAge);
    }
}
