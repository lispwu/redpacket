package com.will.redpacket.controller;

import com.will.redpacket.entity.RedPacket;
import com.will.redpacket.service.RedPacketService;
import com.will.redpacket.service.UserRedPacketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Controller
@Slf4j
@RequestMapping("/userRedPacket")
public class UserRedPacketController {

    private static int corePoolSize = Runtime.getRuntime().availableProcessors();
    //调整队列数 拒绝服务
    private static ThreadPoolExecutor executor  = new ThreadPoolExecutor(corePoolSize, corePoolSize+1, 10l, TimeUnit.SECONDS,
            new LinkedBlockingQueue<Runnable>(1000));

    @Autowired
    private UserRedPacketService userRedPacketService;

    @Autowired
    private RedPacketService redPacketService;

    @RequestMapping("/grabRedPacket")
    @ResponseBody
    public String grabRedPacket(Long redPacketId){

        int stock = 50;
        redPacketService.refillStock(stock,redPacketId);
        log.info("开始抢红包!");
        for(int i = 0;i < 50;i++){
            final long userId = i;
            Runnable task = new Runnable() {
                @Override
                public void run() {
                    int result = userRedPacketService.grapRedPacket(redPacketId,userId);
                    if(result > 0){
                        log.info("抢到红包！");
                    }else {
                        log.info("没抢到红包！");
                    }
                }
            };
            executor.submit(task);
        }

        //等待红包抢完后，计算被抢红包的数量
        try{
            Thread.sleep(10000);
            RedPacket redPacket = redPacketService.getRedPacket(redPacketId);
            log.info("共抢到红包{}!!!",stock-redPacket.getStock());
        }catch (InterruptedException e) {
            e.printStackTrace();
        }


        return "抢红包活动结束！";
    }

    @RequestMapping("/simpleTest")
    @ResponseBody
    public String grabRedPacketSimple(Long redPacketId){
        log.info("开始抢红包!");

        Random rand = new Random();
        long userId = rand.nextInt(100);

        int result = userRedPacketService.grapRedPacket(redPacketId,userId);
        if(result > 0){
            log.info("{}-抢到红包！",userId);
        }else {
            log.info("没抢到红包！");
        }
        return "success";
    }
}
