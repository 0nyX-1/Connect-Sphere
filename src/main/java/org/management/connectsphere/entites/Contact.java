package org.management.connectsphere.entites;

import jakarta.persistence.*;
import lombok.*;


@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "contactTable")
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactId;
    private String name;
    private String email;
    private String phoneAddress;
    private String city;
    private String picture;
    private String description;
    private boolean favourite = false;
    private String linkedInList;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId")
    private User user;
}
