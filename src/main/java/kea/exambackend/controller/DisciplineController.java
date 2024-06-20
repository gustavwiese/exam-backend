package kea.exambackend.controller;

import kea.exambackend.dto.DisciplineDTO;
import kea.exambackend.dto.ParticipantDTO;
import kea.exambackend.service.DisciplineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplines")
public class DisciplineController {

    @Autowired
    private DisciplineService disciplineService;

    @PostMapping
    public ResponseEntity<DisciplineDTO> createDiscipline(@RequestBody DisciplineDTO disciplineDTO) {
        return new ResponseEntity<>(disciplineService.createDiscipline(disciplineDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplineDTO> updateDiscipline(@PathVariable Long id, @RequestBody DisciplineDTO disciplineDTO) {
        return new ResponseEntity<>(disciplineService.updateDiscipline(id, disciplineDTO), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<DisciplineDTO>> getAllDisciplines() {
        List<DisciplineDTO> disciplines = disciplineService.getAllDisciplines();
        return new ResponseEntity<>(disciplines, HttpStatus.OK);
    }

    @GetMapping("/{id}/participants")
    public ResponseEntity<List<ParticipantDTO>> getParticipantsOfDiscipline(@PathVariable Long id) {
        return new ResponseEntity<>(disciplineService.getParticipantsOfDiscipline(id), HttpStatus.OK);
    }

    @PostMapping("/{disciplineId}/participants/{participantId}")
    public ResponseEntity<Void> addParticipantToDiscipline(@PathVariable Long disciplineId, @PathVariable Long participantId) {
        disciplineService.addParticipantToDiscipline(disciplineId, participantId);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @DeleteMapping("/{disciplineId}/participants/{participantId}")
    public ResponseEntity<Void> removeParticipantFromDiscipline(@PathVariable Long disciplineId, @PathVariable Long participantId) {
        disciplineService.removeParticipantFromDiscipline(disciplineId, participantId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Andre endpoints som søgning, sletning osv.
}
