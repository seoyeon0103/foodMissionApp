package umc.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import umc.study.domain.QMember;
import umc.study.domain.QMission;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.QMemberMission;

@Repository
@RequiredArgsConstructor
@Transactional
public class MemberOfMissionRepositoryImpl implements MemberOfMIssionRepositoryCustom{
    private final JPAQueryFactory queryFactory;
    private final QMember member = QMember.member;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    @Override
    public boolean existsByMemberIdAndMissionIdAndMissionStatus(Long missionId, MissionStatus status){
        BooleanBuilder predicate = new BooleanBuilder();

        if(missionId != null){
            predicate.and(memberMission.mission.id.eq(missionId));
        }

        if(status != null){
            predicate.and(memberMission.missionStatus.eq(MissionStatus.CHALLENGING));
        }

        return queryFactory
                .selectFrom(memberMission)
                .where(predicate)
                .fetchOne() != null;
    }

}
