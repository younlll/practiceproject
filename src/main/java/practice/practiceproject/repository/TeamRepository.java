package practice.practiceproject.repository;

import java.util.List;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import practice.practiceproject.domain.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Long> {
    @Query("select DISTINCT a from Team a join fetch a.members")
    List<Team> findAllJoinFetch();

    @EntityGraph(attributePaths = "members")
    @Query("select a from Team a")
    List<Team> findAllEntityGraph();
}
