package umc.study.repository.MissionRepository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.study.domain.Mission;

public interface MissionRepository extends JpaRepository<Mission, Long>, MissionRepositoryCustom {
}
