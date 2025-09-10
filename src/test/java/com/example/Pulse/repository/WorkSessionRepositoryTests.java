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




}
