package com.example.somoim.model;

import lombok.*;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@Getter
@Table(name = "MEMBER_ATTEND_HISTORY")
@Where(clause="delete=false")
public class MemberAttendHistory extends CommonAudit implements Serializable {
    private static final long serialVersionUID = -7280512720483564483L;

    @Id @GeneratedValue
    @Column(name = "MEMBER_ATTEND_HIS_SEQ",updatable = false)
    private Long memberAttendHisSeq;

    @Column(name = "MEMBER_SEQ")
    private Long memberSeq;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "MEMBER_ATTEND_DAY")
    private LocalDate memberAttendDay;

    @Column(name = "MEMBER_ATTEND_PLACE")
    private String memberAttendPlace;

    @Builder
    public MemberAttendHistory(Long memberSeq, String memberName, LocalDate memberAttendDay, String memberAttendPlae) {
        this.memberSeq = memberSeq;
        this.memberName = memberName;
        this.memberAttendDay = memberAttendDay;
        this.memberAttendPlace = memberAttendPlae;
    }
}
