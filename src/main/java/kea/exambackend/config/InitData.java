package kea.exambackend.config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import kea.exambackend.entity.Discipline;
import kea.exambackend.entity.Participant;
import kea.exambackend.repository.DisciplineRepository;
import kea.exambackend.repository.ParticipantRepository;

import java.util.Arrays;

@Configuration
public class InitData implements CommandLineRunner {

    @Autowired
    private DisciplineRepository disciplineRepository;

    @Autowired
    private ParticipantRepository participantRepository;

    @Override
    public void run(String... args) {
        participantRepository.deleteAll();
        disciplineRepository.deleteAll();
        // Create some disciplines
        Discipline d1 = new Discipline("100m Sprint", "time");
        Discipline d2 = new Discipline("Long Jump", "distance");
        Discipline d3 = new Discipline("High Jump", "distance");
        Discipline d4 = new Discipline("Shot Put", "distance");
        Discipline d5 = new Discipline("Decathlon", "points");
        Discipline d6 = new Discipline("Marathon", "time");
        Discipline d7 = new Discipline("Pole Vault", "distance");
        Discipline d8 = new Discipline("Discus Throw", "distance");
        Discipline d9 = new Discipline("Javelin Throw", "distance");

        disciplineRepository.saveAll(Arrays.asList(d1, d2, d3, d4, d5, d6, d7, d8, d9));

        // Create some participants
        Participant p1 = new Participant("John Doe", "Male", 25, "Athletics Club");
        p1.getDisciplines().add(d1);
        p1.getDisciplines().add(d2);

        Participant p2 = new Participant("Jane Smith", "Female", 22, "Town Athletics Club");
        p2.getDisciplines().add(d3);
        p2.getDisciplines().add(d4);

        Participant p3 = new Participant("Emily Davis", "Female", 27, "City Athletics Club");
        p3.getDisciplines().add(d5);
        p3.getDisciplines().add(d6);

        Participant p4 = new Participant("Michael Brown", "Male", 30, "Village Athletics Club");
        p4.getDisciplines().add(d7);
        p4.getDisciplines().add(d8);

        Participant p5 = new Participant("Emma Wilson", "Female", 24, "Regional Athletics Club");
        p5.getDisciplines().add(d9);
        p5.getDisciplines().add(d1);

        Participant p6 = new Participant("James Johnson", "Male", 28, "State Athletics Club");
        p6.getDisciplines().add(d2);
        p6.getDisciplines().add(d3);

        Participant p7 = new Participant("Olivia Martinez", "Female", 23, "National Athletics Club");
        p7.getDisciplines().add(d4);
        p7.getDisciplines().add(d5);

        Participant p8 = new Participant("William Garcia", "Male", 29, "City Athletics Club");
        p8.getDisciplines().add(d6);
        p8.getDisciplines().add(d7);

        Participant p9 = new Participant("Sophia Rodriguez", "Female", 26, "Town Athletics Club");
        p9.getDisciplines().add(d8);
        p9.getDisciplines().add(d9);

        Participant p10 = new Participant("Liam Anderson", "Male", 27, "Village Athletics Club");
        p10.getDisciplines().add(d1);
        p10.getDisciplines().add(d2);

        Participant p11 = new Participant("Isabella Hernandez", "Female", 21, "Regional Athletics Club");
        p11.getDisciplines().add(d3);
        p11.getDisciplines().add(d4);

        Participant p12 = new Participant("Mason Lee", "Male", 24, "State Athletics Club");
        p12.getDisciplines().add(d5);
        p12.getDisciplines().add(d6);

        Participant p13 = new Participant("Mia Thompson", "Female", 22, "National Athletics Club");
        p13.getDisciplines().add(d7);
        p13.getDisciplines().add(d8);

        Participant p14 = new Participant("Elijah White", "Male", 26, "City Athletics Club");
        p14.getDisciplines().add(d9);
        p14.getDisciplines().add(d1);

        Participant p15 = new Participant("Ava Clark", "Female", 25, "Town Athletics Club");
        p15.getDisciplines().add(d2);
        p15.getDisciplines().add(d3);

        Participant p16 = new Participant("Ethan Lewis", "Male", 23, "Village Athletics Club");
        p16.getDisciplines().add(d4);
        p16.getDisciplines().add(d5);

        Participant p17 = new Participant("Charlotte Walker", "Female", 28, "Regional Athletics Club");
        p17.getDisciplines().add(d6);
        p17.getDisciplines().add(d7);

        Participant p18 = new Participant("Alexander Hall", "Male", 30, "State Athletics Club");
        p18.getDisciplines().add(d8);
        p18.getDisciplines().add(d9);

        Participant p19 = new Participant("Amelia Young", "Female", 27, "National Athletics Club");
        p19.getDisciplines().add(d1);
        p19.getDisciplines().add(d2);

        Participant p20 = new Participant("Benjamin King", "Male", 29, "City Athletics Club");
        p20.getDisciplines().add(d3);
        p20.getDisciplines().add(d4);

        participantRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11, p12, p13, p14, p15, p16, p17, p18, p19, p20));

    }
}
