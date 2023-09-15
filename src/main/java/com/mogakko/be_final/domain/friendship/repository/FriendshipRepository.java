package com.mogakko.be_final.domain.friendship.repository;

import com.mogakko.be_final.domain.friendship.entity.Friendship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendshipRepository extends JpaRepository<Friendship, Long>, FriendshipCustomRepository {

}
