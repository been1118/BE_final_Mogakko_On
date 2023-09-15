package com.mogakko.be_final.domain.mogakkoRoom.repository;

import com.mogakko.be_final.domain.mogakkoRoom.dto.response.NeighborhoodResponseDto;
import com.mogakko.be_final.domain.mogakkoRoom.entity.LanguageEnum;
import com.mogakko.be_final.domain.mogakkoRoom.entity.MogakkoRoom;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.mogakko.be_final.domain.mogakkoRoom.entity.QMogakkoRoom.mogakkoRoom;

@Repository
public class MogakkoRoomCustomRepositoryImpl implements MogakkoRoomCustomRepository {
    private final JPAQueryFactory jpaQueryFactory;

    public MogakkoRoomCustomRepositoryImpl(JPAQueryFactory jpaQueryFactory) {
        this.jpaQueryFactory = jpaQueryFactory;
    }

    @Override
    public List<MogakkoRoom> findAllByLatAndLon(double lat, double lon) {
        return jpaQueryFactory
                .selectFrom(mogakkoRoom)
                .where(
                        Expressions.numberTemplate(Double.class,
                                        "6371 * acos(cos(radians({0})) * cos(radians({2})) * cos(radians({3}) - radians({1})) + sin(radians({0})) * sin(radians({2})))",
                                        lat, lon, mogakkoRoom.lat, mogakkoRoom.lon)
                                .loe(12.0)
                )
                .fetch();
    }

    @Override
    public List<MogakkoRoom> findAllByLatAndLonAndLanguage(double lat, double lon, LanguageEnum language) {
        return jpaQueryFactory
                .selectFrom(mogakkoRoom)
                .where(
                        Expressions.numberTemplate(Double.class,
                                        "6371 * acos(cos(radians({0})) * cos(radians({2})) * cos(radians({3}) - radians({1})) + sin(radians({0})) * sin(radians({2})))",
                                        lat, lon, mogakkoRoom.lat, mogakkoRoom.lon)
                                .loe(12.0),
                        mogakkoRoom.language.eq(language)
                )
                .fetch();
    }

    @Override
    public List<MogakkoRoom> findAllBySearchKeywordAndLatAndLon(String searchKeyword, double lat, double lon) {
        return jpaQueryFactory
                .selectFrom(mogakkoRoom)
                .where(
                        mogakkoRoom.title.eq(searchKeyword),
                        Expressions.numberTemplate(Double.class,
                                        "6371 * acos(cos(radians({0})) * cos(radians({2})) * cos(radians({3}) - radians({1})) + sin(radians({0})) * sin(radians({2})))",
                                        lat, lon, mogakkoRoom.lat, mogakkoRoom.lon)
                                .loe(12.0)
                )
                .fetch();
    }

    @Override
    public List<MogakkoRoom> findAllBySearchKeywordAndLanguageAndLatAndLon(String searchKeyword, LanguageEnum language, double lat, double lon) {
        return jpaQueryFactory
                .selectFrom(mogakkoRoom)
                .where(
                        mogakkoRoom.title.eq(searchKeyword),
                        Expressions.numberTemplate(Double.class,
                                        "6371 * acos(cos(radians({0})) * cos(radians({2})) * cos(radians({3}) - radians({1})) + sin(radians({0})) * sin(radians({2})))",
                                        lat, lon, mogakkoRoom.lat, mogakkoRoom.lon)
                                .loe(12.0),
                        mogakkoRoom.language.eq(language)
                )
                .fetch();
    }

    @Override
    public List<NeighborhoodResponseDto> findTop4NeighborhoodsOrderByCountDesc() {
        return jpaQueryFactory
                .select(Projections.constructor(
                        NeighborhoodResponseDto.class,
                        mogakkoRoom.neighborhood.count(),
                        mogakkoRoom.neighborhood))
                .from(mogakkoRoom)
                .groupBy(mogakkoRoom.neighborhood)
                .orderBy(mogakkoRoom.neighborhood.count().desc())
                .limit(4)
                .fetch();
    }

    @Override
    public List<MogakkoRoom> findAllByIsDeleted(boolean isDeleted) {
        return jpaQueryFactory.selectFrom(mogakkoRoom)
                .where(mogakkoRoom.isDeleted.eq(isDeleted))
                .fetch();
    }

    @Override
    public Optional<MogakkoRoom> findBySessionId(String chatRoomId) {
        return Optional.ofNullable(
                jpaQueryFactory.selectFrom(mogakkoRoom)
                        .where(mogakkoRoom.sessionId.eq(chatRoomId))
                        .fetchOne());
    }
}
