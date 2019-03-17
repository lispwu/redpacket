package com.will.redpacket.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter
@ToString
@Builder
@Entity
@Table(name = "T_USER_RED_PACKET")
public class UserRedPacket {
    // 用户红包id
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // 红包id
    @Column
    private Long redPacketId;
    // 抢红包的用户的id
    @Column
    private Long userId;
    // 抢红包金额
    @Column
    private Double amount;
    // 抢红包时间
    @Column
    private Timestamp grabTime;
    // 备注
    @Column
    private String remark;
}
