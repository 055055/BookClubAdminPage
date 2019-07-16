package com.example.somoim.model.admin;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
@Table(name="ADMIN_USER")
public class AdminUser implements Serializable {

    private static final long serialVersionUID = 8471401993543971272L;
    @Id @GeneratedValue
    @Column(name = "ADMIN_USER_SEQ")
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
