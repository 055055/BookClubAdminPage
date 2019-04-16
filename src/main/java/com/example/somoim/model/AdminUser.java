package com.example.somoim.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Entity
public class AdminUser {

    @Id @GeneratedValue
    private Long adminUserSeq;

    private String adminId;

    private String adminUserName;

    private String email;

    private String password;

    @CreationTimestamp
    private LocalDateTime modDate;

    @UpdateTimestamp
    private LocalDateTime regDate;

}
