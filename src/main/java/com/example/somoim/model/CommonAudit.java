package com.example.somoim.model;

import com.example.somoim.model.admin.AdminUser;
import lombok.Getter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class CommonAudit {

    @LastModifiedBy
    @ManyToOne
    @JoinColumn(name="MOD_ADMIN", referencedColumnName = "ADMIN_ID")
    private AdminUser modifyBy;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name="REG_ADMIN",referencedColumnName = "ADMIN_ID", updatable = false)
    private AdminUser createdBy;

    @LastModifiedDate
    @Column(name="MOD_DATE")
    private LocalDateTime modifiedDate;

    @CreatedDate
    @Column(name="REG_DATE", updatable = false)
    private LocalDateTime createdDate;

    @Column(name = "DELETE")
    private boolean delete;
}
