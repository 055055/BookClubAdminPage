package com.example.somoim.model.admin;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@SequenceGenerator(
        name="ADMIN_USER_GEN", //시퀀스 제너레이터 이름
        sequenceName="ADMIN_USER_SEQUENCE", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)

@Table(name="ADMIN_USER")
@Where(clause="delete=false")

public class AdminUser implements Serializable {

    private static final long serialVersionUID = 8471401993543971272L;
    @Id  @GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="ADMIN_USER_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )

    @Column(name="ADMIN_USER_SEQ")
    private Long adminUserSeq;

    @Column(name = "ADMIN_ID")
    private String adminId;

    @Column(name = "ADMIN_USER_NAME")
    private String adminUserName;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "ADMIN_ROLE_ID")
    private String authority;

    @Column(name = "DELETE")
    private boolean delete;

    @UpdateTimestamp
    @Column(name = "MOD_DATE",updatable = true)
    private LocalDateTime modDate;

    @CreationTimestamp
    @Column(name = "REG_DATE",updatable = false)
    private LocalDateTime regDate;

}
