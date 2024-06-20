package kea.exambackend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String resultType;

    @ManyToMany(mappedBy = "disciplines")
    private Set<Participant> participants = new HashSet<>();

    public Discipline(String name, String resultType) {
        this.name = name;
        this.resultType = resultType;
    }

}
