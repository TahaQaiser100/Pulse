package com.example.Pulse.repository;

import com.example.Pulse.model.WorkProjectModel;
import com.example.Pulse.model.WorkSessionModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@DataJpaTest
public class WorkSessionRepositoryTests {

    @Autowired
    private WorkSessionRepository workSessionRepository;

    @Autowired
    private TestEntityManager entityManager;
    // C-Create, R-Retrieve, U-Update, D-Delete


    @BeforeEach
    void setUp(){
        WorkProjectModel project = new WorkProjectModel("Project1", LocalDateTime.now(), "Manager1");
        entityManager.persistAndFlush(project);

        WorkSessionModel completedSession = new WorkSessionModel("user1", project, "task1");
        completedSession.setEndTime(LocalDateTime.now());
        entityManager.persistAndFlush(completedSession);

        WorkSessionModel activeSession = new WorkSessionModel("user2", project, "task2");
        entityManager.persistAndFlush(activeSession);
    }

    @Test
    public void findByUserId(){
        List<WorkSessionModel> result = workSessionRepository.findByUserId("user1");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserId()).isEqualTo("user1");
    }

    @Test
    public void findByUserIdAndEndTimeIsNull(){
        List<WorkSessionModel> result = workSessionRepository.findByUserIdAndEndTimeIsNull("user2");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getUserId()).isEqualTo("user2");
        assertThat(result.get(0).getEndTime()).isNull();
    }

    @Test
    public void findByUserIdAndEndTimeIsNotNull(){
        List<WorkSessionModel> result = workSessionRepository.findByUserIdAndEndTimeIsNotNull("user1");

        assertThat(result).hasSize(1);
        assertThat(result.get(0).getEndTime()).isNotNull();
    }



    @Test
    public void updateWorkSession(){
        List<WorkSessionModel> sessions = workSessionRepository.findByUserId("user1");
        WorkSessionModel existingSession = sessions.get(0);

        existingSession.setFocusScore(8);
        existingSession.setCompletedPercentage(88.0F);
        existingSession.setNotes("Completed JWT auth integration");

        workSessionRepository.save(existingSession);

        assertThat(existingSession.getFocusScore()).isEqualTo(8);
        assertThat(existingSession.getCompletedPercentage()).isEqualTo(88.0F);
        assertThat(existingSession.getNotes()).isEqualTo("Completed JWT auth integration");

    }

    @Test void deleteWorkSession(){
        List<WorkSessionModel> sessions = workSessionRepository.findByUserId("user1");
        WorkSessionModel existingSession = sessions.get(0);

        workSessionRepository.delete(existingSession);

        assertThat(workSessionRepository.findByUserId("user1")).isEmpty();

    }

    @Test
    public void createWorkSession() {
        List<WorkSessionModel> existingSessions = workSessionRepository.findByUserId("user1");
        WorkProjectModel project = existingSessions.get(0).getProject();

        WorkSessionModel newSession = new WorkSessionModel("user3", project, "task3");
        newSession.setStartTime(LocalDateTime.now());

        WorkSessionModel savedSession = workSessionRepository.save(newSession);

        assertThat(savedSession.getId()).isNotNull();
        assertThat(savedSession.getUserId()).isEqualTo("user3");
        assertThat(workSessionRepository.findAll()).hasSize(3);
    }
}
