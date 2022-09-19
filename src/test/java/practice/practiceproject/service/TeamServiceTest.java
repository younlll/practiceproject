package practice.practiceproject.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import practice.practiceproject.domain.Member;
import practice.practiceproject.domain.Team;
import practice.practiceproject.repository.TeamRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TeamServiceTest {
    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private TeamService teamService;

    @BeforeEach
    public void setUp() {
        teamRepository.deleteAll();

        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Team team = Team.builder()
                    .name("team" + i)
                    .build();

            team.addMember(Member.builder()
                    .firstName("first" + i)
                    .lastName("last" + i)
                    .build());
            team.addMember(Member.builder()
                    .firstName("2first" + i)
                    .lastName("2last" + i)
                    .build());
            teams.add(team);
        }

        teamRepository.saveAll(teams);
    }

    @Test
    void 팀_조회_N1_발생() {
        List<Team> teams = teamRepository.findAll();
        assertThat(teams.size()).isEqualTo(10);
    }

    @Test
    void 팀_조회_LAZY_연관관계_데이터_호출_N1_발생() {
        List<String> firstNames = teamService.findAllFirstName();
        assertThat(firstNames.size()).isEqualTo(10);
    }

    @Test
    void 팀_조회_JoinFetch_N1_해결() {
        List<Team> teams = teamRepository.findAllJoinFetch();
    }

    @Test
    void 팀_조회_JoinFetch_카데시안곱_확인() {
        List<Team> teams = teamRepository.findAllJoinFetch();
        assertThat(teams.size()).isEqualTo(10);
    }

    @Test
    void 멤버_firstName_조회_JoinFetch_카데시안곱_확인() {
        List<String> names = teamService.findAllFirstNameByJoinFetch();
        assertThat(names.size()).isEqualTo(20);
    }

    @Test
    void 팀_조회_EntityGraph_N1_해결() {
        List<Team> teams = teamRepository.findAllEntityGraph();
        assertThat(teams.size()).isEqualTo(10);
    }

    @Test
    void 멤버_firstName_조회_EntityGraph_카데시안곱_확인() {
        List<String> names = teamService.findAllFirstNameByEntityGraph();
        assertThat(names.size()).isEqualTo(20);
    }
}
