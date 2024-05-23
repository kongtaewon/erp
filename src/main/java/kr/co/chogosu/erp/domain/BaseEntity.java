package kr.co.chogosu.erp.domain;


import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(value = { AuditingEntityListener.class })
@Getter
abstract class BaseEntity { 
    // 등록 처음 수정 불가
    @CreatedDate
    @Column(name = "regdate")
    private LocalDateTime regDate;

    // 등록 이후 수정 가능
    @LastModifiedDate
    @Column(name ="moddate")
    private LocalDateTime modDate;

}
