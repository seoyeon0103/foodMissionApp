package umc.study.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.mapping.MemberMission;

public interface MemberOfMissionRepository extends JpaRepository<MemberMission, Long>,MemberOfMIssionRepositoryCustom{
}
