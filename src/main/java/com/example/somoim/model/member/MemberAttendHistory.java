package com.example.somoim.model.member;

import com.example.somoim.model.CommonAudit;
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
@Setter
@SequenceGenerator(
        name="MEMBER_ATTEND_HIS_GEN", //시퀀스 제너레이터 이름
        sequenceName="MEMBER_ATTEND_HIS_SEQUENCE", //시퀀스 이름
        initialValue=1, //시작값
        allocationSize=1 //메모리를 통해 할당할 범위 사이즈
)
@Table(name = "MEMBER_ATTEND_HISTORY")
@Where(clause="delete=false")
public class MemberAttendHistory extends CommonAudit implements Serializable {
    private static final long serialVersionUID = -7280512720483564483L;

    @Id  @GeneratedValue(
            strategy=GenerationType.SEQUENCE, //사용할 전략을 시퀀스로  선택
            generator="MEMBER_ATTEND_HIS_GEN" //식별자 생성기를 설정해놓은  USER_SEQ_GEN으로 설정
    )
    @Column(name = "MEMBER_ATTEND_HIS_SEQ",updatable = false)
    private Long memberAttendHisSeq;

    @ManyToOne(targetEntity = Member.class)
    @JoinColumn(name = "MEMBER_SEQ", referencedColumnName = "MEMBER_SEQ", nullable = false)
    private Member member;

    @Column(name = "MEMBER_NAME")
    private String memberName;

    @Column(name = "MEMBER_ATTEND_DAY")
    private LocalDate memberAttendDay;

    @Column(name = "MEMBER_ATTEND_PLACE")
    private String memberAttendPlace;

    @Builder
    public MemberAttendHistory(Member member, String memberName, LocalDate memberAttendDay, String memberAttendPlae) {
        this.member = member;
        this.memberName = memberName;
        this.memberAttendDay = memberAttendDay;
        this.memberAttendPlace = memberAttendPlae;
    }
}
