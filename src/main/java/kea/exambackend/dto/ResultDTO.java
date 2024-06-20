package kea.exambackend.dto;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResultDTO {
    private Long id;
    private ParticipantDTO participant;
    private DisciplineDTO discipline;
    private LocalDate date;
    private String resultValue;

}
