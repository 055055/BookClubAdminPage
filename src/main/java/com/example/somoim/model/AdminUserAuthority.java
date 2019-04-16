package com.example.somoim.model;

import lombok.Getter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="ADMIN_ROLE")
public class AdminUserAuthority {

    @Id @GeneratedValue
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
