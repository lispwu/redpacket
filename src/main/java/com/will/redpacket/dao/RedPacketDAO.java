package com.will.redpacket.dao;


import com.will.redpacket.entity.RedPacket;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedPacketDAO extends CrudRepository<RedPacket,Long> {

    /**
     * 获取红包信息
     * @param id 红包id
     * @return
     */
    RedPacket getRedPacketById(Long id);

    //这里面的语句居然要求不是写表名，而是实体类的名字，服了。。。
    @Modifying
    @Query("UPDATE RedPacket u set u.stock = u.stock - 1 where u.id = ?1")
    int decreaseRedPacketById(Long id);

//    /**
//     * 使用forupdate来锁住查询的数据（悲观锁）
//     * @param id
//     * @return
//     */
//    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
//    RedPacket getRedPacketById(Long id);

    /**
     * 乐观锁实现方式：在SQL中添加版本号校验
     */
    @Modifying
    @Query("update RedPacket u set u.stock = u.stock - 1, u.version = u.version + 1 where u.id = ?1 and u.version = ?2")
    int decreaseRedPacketForVersion(Long id,int version);


    /**
     * 重新补充库存
     * @param stock
     * @param redPacketId
     * @return
     */
    @Modifying
    @Query("update RedPacket u set u.stock = ?1 where u.id = ?2")
    int refillStock(int stock,Long redPacketId);

}
