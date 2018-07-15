package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_OCC)
@Table(name = Const.TABLE_NAME_OCC, catalog = Const.CATALOG_NAME_EPLUSI)
public class Occ {

    @Id
    @Column(name = "occ_code")
    private String occCode;
    @Column(name = "occ_name")
    private String occName;
    @Column(name = "parent_code")
    private int parentCode;
    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public Occ() {
    }

    public String getOccCode() {
        return occCode;
    }

    public void setOccCode(String occCode) {
        this.occCode = occCode;
    }

    public String getOccName() {
        return occName;
    }

    public void setOccName(String occName) {
        this.occName = occName;
    }

    public int getParentCode() {
        return parentCode;
    }

    public void setParentCode(int parentCode) {
        this.parentCode = parentCode;
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

        Occ occ = (Occ) o;

        return getOccCode() != null ? getOccCode().equals(occ.getOccCode()) : occ.getOccCode() == null;
    }

    @Override
    public int hashCode() {
        return getOccCode() != null ? getOccCode().hashCode() : 0;
    }
}
