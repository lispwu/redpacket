package com.will.redpacket.dao;

import com.will.redpacket.entity.UserRedPacket;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRedPacketDAO extends CrudRepository<UserRedPacket,Long>{
}
