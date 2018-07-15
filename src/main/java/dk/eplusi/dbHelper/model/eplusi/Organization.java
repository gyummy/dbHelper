package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity
@Table(name = Const.TABLE_NAME_ORGANIZATION, catalog = Const.CATALOG_NAME_EPLUSI)
public class Organization {

    @Id
    @Column(name = "org_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orgCode;
    @Column(name = "parent_code")
    private Integer parentCode;
    @Column(name = "org_name")
    private String orgName;
    @Column(name = "applied_year")
    private String appliedYear;
    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public Organization() {
    }

    public Integer getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(Integer orgCode) {
        this.orgCode = orgCode;
    }

    public Integer getParentCode() {
        return parentCode;
    }

    public void setParentCode(Integer parentCode) {
        this.parentCode = parentCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getAppliedYear() {
        return appliedYear;
    }

    public void setAppliedYear(String appliedYear) {
        this.appliedYear = appliedYear;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Organization that = (Organization) o;

        return getOrgCode() != null ? getOrgCode().equals(that.getOrgCode()) : that.getOrgCode() == null;
    }

    @Override
    public int hashCode() {
        return getOrgCode() != null ? getOrgCode().hashCode() : 0;
    }
}
