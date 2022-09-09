package practice.practiceproject.service;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import practice.practiceproject.domain.Team;
import practice.practiceproject.repository.TeamRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
public class TeamService {

    private TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Transactional
    public void saveAll(List<Team> teams) {
        teamRepository.saveAll(teams);
    }

    public Team findById(Long id) {
        return teamRepository.findById(id)
                .orElseThrow(RuntimeException::new);
    }

    public List<String> findAllFirstName() {
        return teamRepository.findAll()
                .stream()
                .map(a -> a.getMembers().get(0).getFirstName())
                .collect(Collectors.toList());
    }

    public List<String> findAllFirstNameByJoinFetch() {
        return teamRepository.findAllJoinFetch()
                .stream()
                .map(a -> a.getMembers().get(0).getFirstName())
                .collect(Collectors.toList());
    }

    public List<String> findAllFirstNameByEntityGraph() {
        return teamRepository.findAllEntityGraph()
                .stream()
                .map(a -> a.getMembers().get(0).getFirstName())
                .collect(Collectors.toList());
    }

    public List<Team> findAll() {
        return teamRepository.findAll();
    }
}
