package com.example.somoim.model.member;

import com.example.somoim.model.CommonAudit;
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
@SequenceGenerator(
        name="MEMBER_GEN", //시퀀스 제너레이터 이름
        sequenceName="MEMBER_SEQUENCE", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Table(name = "MEMBER")
@Where(clause="delete=false")
public class Member extends CommonAudit implements Serializable {
    private static final long serialVersionUID = -1899422181748243449L;

    @Id  @GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="MEMBER_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
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
    private int attendCountMonth;

    @Column(name = "LAST_ATTEND")
    private LocalDate lastAttend;

    //insert시 데이터 count 데이터 초기화
    @PrePersist
    public void prePersist(){
        this.attendCount = this.attendCount == null?0L:this.attendCount;
        this.attendCountMonth=this.memberSeq==null?0:this.attendCountMonth;
    }


}
