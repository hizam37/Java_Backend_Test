package org.example.model;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Application {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Long id;

   @Enumerated(EnumType.STRING)

   private Status status;

   private String userText;

   private int phoneNumber;

   private String userName;

   @CreationTimestamp
   private LocalDateTime creationDate;

}
