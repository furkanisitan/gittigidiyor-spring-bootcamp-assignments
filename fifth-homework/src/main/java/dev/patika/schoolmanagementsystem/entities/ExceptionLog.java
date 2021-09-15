package dev.patika.schoolmanagementsystem.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "exception_logs")
@EntityListeners(AuditingEntityListener.class)
public class ExceptionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "exception_log_id")
    private Long id;

    @Column(name = "exception_type")
    private String exceptionType;

    @CreatedDate
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "message")
    private String message;
}
