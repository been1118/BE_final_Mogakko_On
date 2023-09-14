package com.mogakko.be_final.domain.declare.repository;

import com.mogakko.be_final.domain.declare.entity.DeclaredMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeclaredMembersRepository extends JpaRepository<DeclaredMembers, Long> {

    @Query("select d from DeclaredMembers d join fetch d.declaredMember")
    List<DeclaredMembers> findAllFetchJoin();
}
