package com.mogakko.be_final.domain.friendship.repository;

import com.mogakko.be_final.domain.friendship.entity.Friendship;
import com.mogakko.be_final.domain.friendship.entity.FriendshipStatus;
import com.mogakko.be_final.domain.members.entity.Members;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

import static com.mogakko.be_final.domain.friendship.entity.QFriendship.friendship;

@Repository
public class FriendshipCustomRepositoryImpl implements FriendshipCustomRepository{
    private final JPAQueryFactory queryFactory;

    public FriendshipCustomRepositoryImpl(EntityManager entityManager) {
        this.queryFactory = new JPAQueryFactory(entityManager);
    }

    @Override
    public Optional<Friendship> findBySenderAndReceiver(Members sender, Members receiver) {
        return Optional.ofNullable(queryFactory.selectFrom(friendship)
                .where(friendship.sender.eq(sender).and(friendship.receiver.eq(receiver)))
                .fetchOne());
    }

    @Override
    public Optional<Friendship> findBySenderAndReceiverAndStatus(Members sender, Members receiver, FriendshipStatus status) {
        return Optional.ofNullable(queryFactory.selectFrom(friendship)
                .where(friendship.sender.eq(sender).and(friendship.receiver.eq(receiver)).and(friendship.status.eq(status)))
                .fetchOne());
    }

    @Override
    public List<Friendship> findAllByReceiverAndStatusOrSenderAndStatus(Members receiver, FriendshipStatus status1, Members sender, FriendshipStatus status2) {
        return queryFactory.selectFrom(friendship)
                .where(friendship.receiver.eq(receiver).and(friendship.status.eq(status1))
                        .or(friendship.sender.eq(sender).and(friendship.status.eq(status2))))
                .fetch();
    }

    @Override
    public List<Friendship> findAllByReceiverAndStatus(Members receiver, FriendshipStatus status) {
        return queryFactory.selectFrom(friendship)
                .where(friendship.receiver.eq(receiver).and(friendship.status.eq(status)))
                .fetch();
    }

    @Override
    public void deleteAllBySenderAndReceiver(Members sender, Members receiver) {
        queryFactory.delete(friendship)
                .where(friendship.sender.eq(sender).and(friendship.receiver.eq(receiver)))
                .execute();
    }
}
