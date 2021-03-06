package com.will.redpacket.service;

import com.will.redpacket.entity.RedPacket;

public interface RedPacketService {

    /**
     * 获取红包
     * @param id ——编号
     * @return 红包信息
     */
    RedPacket getRedPacket(Long id);

    /**
     * 扣减红包
     * @param id——编号
     * @return 影响条数.
     */
    int decreaseRedPacket(Long id);


    /**
     * 补充红包的库存
     * @param stock 想要补充的库存数
     * @param redPacketId
     * @return
     */
    int refillStock(int stock,Long redPacketId);
}
