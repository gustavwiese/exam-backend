package kea.exambackend.dto;

import kea.exambackend.entity.Discipline;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DisciplineDTO {
    private Long id;
    private String name;
    private String resultType;
    private List<ParticipantDTO> participants;
}
