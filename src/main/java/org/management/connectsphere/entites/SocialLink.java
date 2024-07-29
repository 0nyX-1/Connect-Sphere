package org.management.connectsphere.entites;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SocialLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String link;
    private String title;

    @ManyToOne
    @JoinColumn(referencedColumnName = "userId")
    private User userLink;

}
