package com.example.somoim.model.admin;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@SequenceGenerator(
        name="ADMIN_USER_AUTHORITY_GEN", //시퀀스 제너레이터 이름
        sequenceName="ADMIN_USER_AUTHORITY_SEQUENCE", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Table(name="ADMIN_ROLE")
public class AdminUserAuthority {

    @Id  @GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="ADMIN_USER_AUTHORITY_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
    @Column(name = "ADMIN_AUTHORITY_SEQ")
    private Long authoritySeq;

    @Column(name = "AUTHORITY_ID")
    private String authorityId;

    @Column(name = "AUTHORITY_NAME")
    private String authorityName;

    @Column(name = "AUTHORITY_LEVEL")
    private String authorityLevel;

    @Column(name = "REG_DATE",updatable = false)
    @CreationTimestamp
    private LocalDateTime regDate;
}
