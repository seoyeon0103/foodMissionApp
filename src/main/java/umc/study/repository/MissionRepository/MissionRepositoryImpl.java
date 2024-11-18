package umc.study.repository.MissionRepository;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import umc.study.domain.*;
import umc.study.domain.enums.MissionStatus;
import umc.study.domain.mapping.MemberMission;
import umc.study.domain.mapping.QMemberMission;

import java.util.List;

@Repository
@RequiredArgsConstructor
@Transactional
public class MissionRepositoryImpl implements MissionRepositoryCustom {
    private final JPAQueryFactory queryFactory;
    private final QMember member = QMember.member;
    private final QMemberMission memberMission = QMemberMission.memberMission;
    private final QMission mission = QMission.mission;
    private final QStore store = QStore.store;
    private final QRegion qRegion = QRegion.region;

    @Override
    public List<MemberMission> dynamicQueryWithMemberIdAndStatus(Long memberId, MissionStatus status) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(memberId != null) {
            predicate.and(memberMission.member.id.eq(memberId));
        }

        if(status != null){
            predicate.and(memberMission.missionStatus.eq(status));
        }

        return queryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission, mission)
                .where(predicate)
                .fetch();
    }

    @Override
    public List<MemberMission> dynamicQueryWithMemberIdAndRegion(Long memberId,String region) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(memberId != null) {
            predicate.and(memberMission.member.id.eq(memberId));
        }

        if(region != null){
            predicate.and(memberMission.mission.store.address.contains(region));
        }

        return queryFactory
                .selectFrom(memberMission)
                .join(memberMission.mission,mission)
                .join(memberMission.member, member)
                .join(memberMission.mission.store,store)
                .where(predicate.and(memberMission.missionStatus.eq(MissionStatus.CHALLENGING)))
                .fetch();
    }

    @Override
    public Long dynamicQueryWithtotalCounting(Long memberId, String region){
        BooleanBuilder predicate = new BooleanBuilder();

        if(memberId != null) {
            predicate.and(memberMission.member.id.eq(memberId));
        }
        if(region != null){
            predicate.and(memberMission.mission.store.address.contains(region));
        }

        return queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .join(memberMission.member,member)
                .join(memberMission.mission.store,store)
                .where(predicate)
                .fetchOne();
    }

    @Override
    public Long dynamicQueryWithCompleteCounting(Long memberId, String region) {
        BooleanBuilder predicate = new BooleanBuilder();

        if(memberId != null) {
            predicate.and(memberMission.member.id.eq(memberId));
        }
        if(region != null){
            predicate.and(memberMission.mission.store.address.contains(region));
        }

        return queryFactory
                .select(memberMission.count())
                .from(memberMission)
                .join(memberMission.member,member)
                .join(memberMission.mission.store,store)
                .where(memberMission.missionStatus.eq(MissionStatus.COMPLETE).and(predicate))
                .fetchOne();
    }
}
