package com.tech.global.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@MappedSuperclass
@Getter @Setter
@NoArgsConstructor
public abstract class BaseEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    protected Long id;

    @Column(name = "registration_date", nullable = false, updatable = false)
    private LocalDateTime registration_date;

    @Column(name = "modify_date")
    private LocalDateTime modifty_date;

    public BaseEntity(Long id, LocalDateTime registration_date, LocalDateTime modifty_date) {
        this.id = id;
        this.registration_date = registration_date;
        this.modifty_date = modifty_date;
    }

    @PrePersist
    protected void onCreate() {
        this.registration_date = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.registration_date = LocalDateTime.now();
    }

}
