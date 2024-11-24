package umc.study.repository.MissionRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import umc.study.domain.mapping.MemberMission;

public interface MemberMissionRepository extends JpaRepository<MemberMission, Long>, MemberMIssionRepositoryCustom {
    @Query("select mm from MemberMission mm join mm.member m where m.id = :memberId and mm.missionStatus = 'CHALLENGING'")
    Page<MemberMission> findById(@Param("memberId") Long memberId, PageRequest pageRequest);
}
