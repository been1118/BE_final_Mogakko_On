package com.mogakko.be_final.domain.mogakkoRoom.repository;

import com.mogakko.be_final.domain.mogakkoRoom.dto.response.NeighborhoodResponseDto;
import com.mogakko.be_final.domain.mogakkoRoom.entity.LanguageEnum;
import com.mogakko.be_final.domain.mogakkoRoom.entity.MogakkoRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import java.util.List;
import java.util.Optional;

@Repository
public interface MogakkoRoomRepository extends JpaRepository<MogakkoRoom, String>, MogakkoRoomCustomRepository {

}
