package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "short_link")
public class ShortLink {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "code", unique = true)
    private String code;

    @Column(name = "full_link", length = 1000)
    private String fullLink;

    @OneToMany(mappedBy = "shortLink")
    private List<ShortLinkVisit> visits;

    public ShortLink(String code, String fullLink) {
        this.code = code;
        this.fullLink = fullLink;
    }

    public Long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getFullLink() {
        return fullLink;
    }

    public List<ShortLinkVisit> getVisits() {
        return visits;
    }

    @Override
    public String toString() {
        return "ShortLink{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", fullLink='" + fullLink + '\'' +
                '}';
    }
}
