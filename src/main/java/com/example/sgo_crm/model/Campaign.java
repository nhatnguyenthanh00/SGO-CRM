package com.example.sgo_crm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "campaigns")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Campaign {

    @Id
    @Column(name = "campaign_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long campaignId;

    @Column(name = "campaign_name")
    private String campaignName;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "startDate")
    private Date startDate;

    @JsonFormat(pattern = "dd-MM-yyyy")
    @Column(name = "endDate")
    private Date endDate;

    @Column(name = "status")
    private int status = -1;

    @OneToMany(mappedBy = "campaign", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private Set<FbAdPage> fbAdPages;

    @ManyToMany(mappedBy = "campaigns", fetch = FetchType.LAZY)
    @JsonBackReference
    private Set<User> users;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Campaign campaign = (Campaign) o;
        return Objects.equals(campaignId, campaign.campaignId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(campaignId);
    }
}