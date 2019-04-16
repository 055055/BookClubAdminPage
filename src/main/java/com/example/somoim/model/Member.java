package com.example.somoim.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Member implements Serializable {
    private static final long serialVersionUID = -1899422181748243449L;

    @Id @GeneratedValue
    private Long memberSeq;

    private String memberName;

    private String memberNickName;

    private LocalDate somoinJoinDate;

    private Long attendCount;

    private Long attendCountMonth;

    private LocalDateTime lastAttend;

    @UpdateTimestamp
    @Column(updatable = true)
    private LocalDateTime modDate;

    @CreationTimestamp
    @Column(updatable = false)
    private LocalDateTime regDate;

    //insert시 데이터 count 데이터 초기화
    @PrePersist
    public void prePersist(){
        this.attendCount = this.attendCount == null?0L:this.attendCount;
        this.attendCountMonth=this.memberSeq==null?0L:this.attendCountMonth;
    }
}
