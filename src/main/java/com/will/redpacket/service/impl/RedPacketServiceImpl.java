package com.will.redpacket.service.impl;

import com.will.redpacket.dao.RedPacketDAO;
import com.will.redpacket.entity.RedPacket;
import com.will.redpacket.service.RedPacketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class RedPacketServiceImpl implements RedPacketService{


    @Autowired
    private RedPacketDAO redPacketDAO;

    @Override
    @Transactional
    public RedPacket getRedPacket(Long id) {
        return redPacketDAO.getRedPacketById(id);
    }

    @Override
    @Transactional
    public int decreaseRedPacket(Long id) {
        return redPacketDAO.decreaseRedPacketById(id);
    }

    @Override
    @Transactional
    public int refillStock(int stock, Long redPacketId) {
        return redPacketDAO.refillStock(stock,redPacketId);
    }
}
