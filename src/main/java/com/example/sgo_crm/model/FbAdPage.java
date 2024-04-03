package com.example.sgo_crm.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "fbAdPage")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FbAdPage {

    @Id
    @Column(name = "page_Id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long pageId;

    @Column(name = "page_name")
    private String pageName;

    @Column(name = "fbPage_Id")
    private String fbPageId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_Id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "campaign_Id")
    private Campaign campaign;

    @OneToMany(mappedBy = "fbAdPage", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<FbAdAccount> fbAdAccounts;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FbAdPage fbAdPage = (FbAdPage) o;
        return Objects.equals(pageId, fbAdPage.pageId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pageId);
    }
}
