package dev.patika.schoolmanagementsystem.entities;

import dev.patika.schoolmanagementsystem.entities.Instructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "instructor_salary_logs")
@EntityListeners(AuditingEntityListener.class)
public class InstructorSalaryLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id")
    private Instructor instructor;

    @Column(name = "client_ip_address")
    private String clientIpAddress;

    @Column(name = "client_url")
    private String clientUrl;

    @Column(name = "session_activity_id")
    private String sessionActivityId;

    @CreatedDate
    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "previous_salary", scale = 2)
    private BigDecimal previousSalary;

    @Column(name = "current_salary", scale = 2)
    private BigDecimal currentSalary;

    @Column(name = "percent")
    private double percent;

}
