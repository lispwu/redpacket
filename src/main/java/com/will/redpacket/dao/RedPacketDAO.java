package com.will.redpacket.dao;


import com.will.redpacket.entity.RedPacket;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;

@Repository
public interface RedPacketDAO extends CrudRepository<RedPacket,Long> {

//    /**
//     * 获取红包信息
//     * @param id 红包id
//     * @return
//     */
//    RedPacket getRedPacketById(Long id);

    //这里面的语句居然要求不是写表名，而是实体类的名字，服了。。。
    @Modifying
    @Query("UPDATE RedPacket u set u.stock = u.stock - 1 where u.id = ?1")
    int decreaseRedPacketById(Long id);

    /**
     * 使用forupdate来锁住查询的数据（悲观锁）
     * @param id
     * @return
     */
    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    RedPacket getRedPacketById(Long id);

}
