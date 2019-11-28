package com.example.somoim.model.member;

import com.example.somoim.model.CommonAudit;
import com.example.somoim.model.admin.AdminUser;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "PASSWORD_RESET_TOKEN")
@SequenceGenerator(
        name="RESET_TOKEN_GEN", //시퀀스 제너레이터 이름
        sequenceName="RESET_TOKEN_GEN", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Where(clause="delete=false")

public class PasswordResetToken implements Serializable {
    private static final long serialVersionUID = 3134567267164913179L;


    @Id
    @GeneratedValue(
            strategy=GenerationType.SEQUENCE,
            generator="RESET_TOKEN_GEN"
    )
    @Column(name = "PWD_RESET_TOKEN_SEQ")
    private Long pwdResetTokenSeq;

    @Column(name = "TOKEN")
    private String token;

    @OneToOne(targetEntity = AdminUser.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "ADMIN_ID")
    private AdminUser adminUser;

    @Column(name = "EXPIRY_DATE_TIME")
    private LocalDateTime expiryDateTime;

    @Column(name = "DELETE")
    private boolean delete;

    @CreationTimestamp
    @Column(name = "REG_DATE",updatable = false)
    private LocalDateTime regDate;


}
