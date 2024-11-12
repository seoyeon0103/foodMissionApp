package umc.study;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import umc.study.domain.Region;
import umc.study.domain.enums.MissionStatus;
import umc.study.repository.MemberRepository.MemberRepository;
import umc.study.service.MemberService.MemberQueryService;
import umc.study.service.MissionService.MissionQueryService;
import umc.study.service.ReviewService.ReviewQueryService;
import umc.study.service.StoreService.StoreQueryService;

import java.awt.*;

@EnableJpaAuditing
//@EntityScan("umc.study.domain")
@SpringBootApplication
public class StudyApplication {
    public static void main(String[] args) {
        SpringApplication.run(StudyApplication.class, args);
    }

    @Bean
    public CommandLineRunner run(ApplicationContext context, MemberRepository memberRepository) {
        return args -> {
            StoreQueryService storeService = context.getBean(StoreQueryService.class);
            MissionQueryService missionService = context.getBean(MissionQueryService.class);
            ReviewQueryService reviewService = context.getBean(ReviewQueryService.class);
            MemberQueryService memberQueryService = context.getBean(MemberQueryService.class);

            // 파라미터 값 설정
            String name = "요아정";
            Float score = 4.0f;

            // 쿼리 메서드 호출 및 쿼리 문자열과 파라미터 출력
            System.out.println("Executing findStoresByNameAndScore with parameters:");
            System.out.println("Name: " + name);
            System.out.println("Score: " + score);

            storeService.findStoresByNameAndScore(name, score)
                    .forEach(System.out::println);

            Long memberId = 1L;
            MissionStatus status = MissionStatus.COMPLETE;
            System.out.println("\nExecuting findMissionByMemberIdAndStatus with parameters:");
            System.out.println("MemberId: " + memberId);
            System.out.println("Status: " + status);

            missionService.findMissionByMemberIdAndStatus(memberId, status)
                    .forEach(System.out::println);


            System.out.println("Review 관련 ");
            Long storeId = 1L;
            System.out.println("\nExecuting showReview with parameters:");
            System.out.println("StoreId: " + storeId);

            reviewService.findReviewByStoreId(storeId)
                    .forEach(System.out::println);


            String region = "연남동";
            System.out.println("\nExecuting findMissionByRegionAndStatus with parameters:");
            System.out.println("memberId: " + memberId);
            System.out.println("Region: " + region);

            missionService.findMyHomeInfo(memberId,region)
                    .forEach(System.out::println);

            memberQueryService.showMyPage(memberId);
        };
    }
}
