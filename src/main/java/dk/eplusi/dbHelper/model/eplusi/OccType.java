package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_OCC_TYPE)
@Table(name = Const.TABLE_NAME_OCC_TYPE, catalog = Const.CATALOG_NAME_EPLUSI)
public class OccType {

    @Id
    @Column(name = "occ_type_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer occTypeCode;
    @Column(name = "occ_type")
    private String occType;
    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public OccType() {
    }

    public Integer getOccTypeCode() {
        return occTypeCode;
    }

    public void setOccTypeCode(Integer occTypeCode) {
        this.occTypeCode = occTypeCode;
    }

    public String getOccType() {
        return occType;
    }

    public void setOccType(String occType) {
        this.occType = occType;
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

        OccType occType = (OccType) o;

        return getOccTypeCode() != null ? getOccTypeCode().equals(occType.getOccTypeCode()) : occType.getOccTypeCode() == null;
    }

    @Override
    public int hashCode() {
        return getOccTypeCode() != null ? getOccTypeCode().hashCode() : 0;
    }
}
