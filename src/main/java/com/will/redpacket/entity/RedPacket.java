package com.will.redpacket.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.sql.Timestamp;


@Getter
@Setter
@ToString
@Entity
@Table(name = "T_RED_PACKET")
public class RedPacket {
    // 红包编号
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    // 发红包的用户id
    @Column
    private Long userId;
    // 红包金额
    @Column
    private Double amount;
    // 发红包日期
    @Column
    private Timestamp sendDate;
    // 红包总数
    @Column
    private Integer total;
    // 单个红包的金额
    @Column
    private Double unitAmount;
    // 红包剩余个数
    @Column
    private Integer stock;
    // 版本（为后续扩展用）
    @Column
    private Integer version;
    // 备注
    @Column
    private String remark;

}
