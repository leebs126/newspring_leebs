package com.springboot.ch05;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Member")  // 실제 DB 테이블 이름과 맞추세요
public class Member {
	@Id
    @Column(name = "memId")
    private String memId;

//    @Column(nullable = false)
    private String pwd;

//    @Column(nullable = false)
    private String name;

    private String email;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "joinDate", columnDefinition = "DATE DEFAULT SYSDATE")
    private Date  joinDate;

    @PrePersist
    public void prePersist() {
        if (this.joinDate == null) {
            this.joinDate = new Date();
        }
    }
}
