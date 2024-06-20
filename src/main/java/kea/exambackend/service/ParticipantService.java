package kea.exambackend.service;

import kea.exambackend.entity.Participant;
import kea.exambackend.repository.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ParticipantService {
    @Autowired
    private ParticipantRepository participantRepository;

    public Participant createParticipant(Participant participant) {
        return participantRepository.save(participant);
    }

    public List<Participant> getAllParticipants() {
        return participantRepository.findAll();
    }

    public Participant getParticipantById(Long id) {
        return participantRepository.findById(id).orElseThrow(() -> new RuntimeException("Participant not found with id: " + id));
    }

    public Participant updateParticipant(Long id, Participant participantDetails) {
        Participant participant = getParticipantById(id);
        participant.setName(participantDetails.getName());
        participant.setGender(participantDetails.getGender());
        participant.setAge(participantDetails.getAge());
        participant.setClub(participantDetails.getClub());
        return participantRepository.save(participant);
    }

    public void deleteParticipant(Long id) {
        Participant participant = getParticipantById(id);
        participantRepository.delete(participant);
    }

    public List<Participant> searchParticipantsByName(String name) {
        return participantRepository.findByNameContaining(name);
    }
}