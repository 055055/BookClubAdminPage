package com.example.somoim.model;

import lombok.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "MEMBER")
@Where(clause="delete=false")
public class Member extends  CommonAudit implements Serializable {
    private static final long serialVersionUID = -1899422181748243449L;

    @Id @GeneratedValue
    @Column(name = "MEMBER_SEQ",updatable = false)
    private Long memberSeq;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "MEMBER_NICK_NAME")
    private String memberNickName;

    @Column(name = "SOMOIM_JOIN_DATE",updatable = false)
    private LocalDate somoimJoinDate;

    @Column(name = "ATTEND_COUNT")
    private Long attendCount;

    @Column(name = "ATTEND_COUNT_MONTH")
    private Long attendCountMonth;

    @Column(name = "LAST_ATTEND")
    private LocalDate lastAttend;

    //insert시 데이터 count 데이터 초기화
    @PrePersist
    public void prePersist(){
        this.attendCount = this.attendCount == null?0L:this.attendCount;
        this.attendCountMonth=this.memberSeq==null?0L:this.attendCountMonth;
    }


}
