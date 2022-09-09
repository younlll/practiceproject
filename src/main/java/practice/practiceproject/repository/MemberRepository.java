package practice.practiceproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import practice.practiceproject.domain.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
}
