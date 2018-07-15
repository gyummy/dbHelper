package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_ROLE_TYPE)
@Table(name = Const.TABLE_NAME_ROLE_TYPE, catalog = Const.CATALOG_NAME_EPLUSI)
public class RoleType {

    @Id
    @Column(name = "role_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer roleCode;
    @Column(name = "role_name")
    private String roleName;
    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public RoleType() {
    }

    public Integer getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(Integer roleCode) {
        this.roleCode = roleCode;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

        RoleType roleType = (RoleType) o;

        return getRoleCode() != null ? getRoleCode().equals(roleType.getRoleCode()) : roleType.getRoleCode() == null;
    }

    @Override
    public int hashCode() {
        return getRoleCode() != null ? getRoleCode().hashCode() : 0;
    }
}
