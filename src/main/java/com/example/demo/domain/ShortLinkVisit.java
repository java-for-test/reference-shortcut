package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "visit_history")
public class ShortLinkVisit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

//    @Column(name = "short_link_id")
    @ManyToOne(targetEntity = ShortLink.class)
    private ShortLink shortLink;

    @Column(name = "user_agent")
    private String userAgent;
}
