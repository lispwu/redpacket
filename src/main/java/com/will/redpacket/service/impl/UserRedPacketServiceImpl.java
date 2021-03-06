package com.will.redpacket.service.impl;

import com.will.redpacket.dao.RedPacketDAO;
import com.will.redpacket.dao.UserRedPacketDAO;
import com.will.redpacket.entity.RedPacket;
import com.will.redpacket.entity.UserRedPacket;
import com.will.redpacket.service.UserRedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserRedPacketServiceImpl implements UserRedPacketService{

    @Autowired
    private RedPacketDAO redPacketDAO;

    @Autowired
    private UserRedPacketDAO userRedPacketDAO;


    @Override
    @Transactional
    public int grapRedPacket(Long redPacketId, Long userId) {

        //1.查询红包库存
        RedPacket redPacket = redPacketDAO.getRedPacketById(redPacketId);
        int leftRedPacketNum = redPacket.getStock();

        //2.如果库存还有值，就开抢
        if(leftRedPacketNum > 0){
            int update = redPacketDAO.decreaseRedPacketForVersion(redPacketId,redPacket.getVersion());
            //如果数据没有修改，则说明其他线程已经修改过数据，则重新抢夺
            if(update == 0){
                return 0;
            }

            UserRedPacket userRedPacket = UserRedPacket.builder().redPacketId(redPacketId).userId(userId).amount(redPacket.getUnitAmount()).remark("redPacket-" + redPacketId).build();
            userRedPacketDAO.save(userRedPacket);
            return 1;
        }
        return 0;
    }
}
