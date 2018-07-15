package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_YOUTH_ORG)
@Table(name = Const.TABLE_NAME_YOUTH_ORG, catalog = Const.CATALOG_NAME_EPLUSI)
public class YouthOrg {

    @Id
    @Column(name = "youth_org_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer youthOrgId;
    @ManyToOne(targetEntity = Youth.class)
    @JoinColumn(name = "youth_id")
    private Youth youth;
    @ManyToOne(targetEntity = Organization.class)
    @JoinColumn(name = "org_code")
    private Organization organization;
    @ManyToOne(targetEntity = RoleType.class)
    @JoinColumn(name = "role_code")
    private RoleType roleType;
    @Temporal(TemporalType.DATE)
    @Column(name = "start_date")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @Column(name = "end_date")
    private Date endDate;
    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public YouthOrg() {
    }

    public Integer getYouthOrgId() {
        return youthOrgId;
    }

    public void setYouthOrgId(Integer youthOrgId) {
        this.youthOrgId = youthOrgId;
    }

    public Youth getYouth() {
        return youth;
    }

    public void setYouth(Youth youth) {
        this.youth = youth;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public static String getIdColumnName() {
        return "youth_org_id";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        YouthOrg youthOrg = (YouthOrg) o;

        return getYouthOrgId().equals(youthOrg.getYouthOrgId());
    }

    @Override
    public int hashCode() {
        return getYouthOrgId().hashCode();
    }
}
