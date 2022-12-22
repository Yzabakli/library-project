package com.abakli.entity;

import com.abakli.entity.common.UserPrincipal;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import java.time.LocalDateTime;

public class BaseEntityListener extends AuditingEntityListener {

    @PrePersist
    private void onPrePersist(BaseEntity baseEntity) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.setInsertDateTime(LocalDateTime.now());
        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            UserPrincipal principal = (UserPrincipal) authentication.getPrincipal();
            baseEntity.setInsertUserId(principal.getId());
            baseEntity.setLastUpdateUserId(principal.getId());
        } else {

            baseEntity.setInsertUserId(1L);
            baseEntity.setLastUpdateUserId(1L);
        }
    }

    @PreUpdate
    private void onPreUpdate(BaseEntity baseEntity) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        baseEntity.setLastUpdateDateTime(LocalDateTime.now());

        if (authentication != null && !authentication.getName().equals("anonymousUser")) {

            baseEntity.setLastUpdateUserId(((UserPrincipal) authentication.getPrincipal()).getId());
        }
    }
}
