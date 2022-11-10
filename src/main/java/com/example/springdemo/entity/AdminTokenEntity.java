package com.example.springdemo.entity;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "admin_token")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AdminTokenEntity {
    @Id
    String token;
    @CreationTimestamp
    LocalDateTime date;
    @ManyToOne
    AdminEntity admin;
}
