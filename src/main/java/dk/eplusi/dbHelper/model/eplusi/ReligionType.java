package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_RELIGION_TYPE)
@Table(name = Const.TABLE_NAME_RELIGION_TYPE, catalog = Const.CATALOG_NAME_EPLUSI)
public class ReligionType {

    @Id
    @Column(name = "religion_type_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer religionTypeCode;
    @Column(name = "religion_type")
    private String religionType;
    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public ReligionType() {
    }

    public Integer getReligionTypeCode() {
        return religionTypeCode;
    }

    public void setReligionTypeCode(Integer religionTypeCode) {
        this.religionTypeCode = religionTypeCode;
    }

    public String getReligionType() {
        return religionType;
    }

    public void setReligionType(String religionType) {
        this.religionType = religionType;
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

        ReligionType that = (ReligionType) o;

        return getReligionTypeCode() != null ? getReligionTypeCode().equals(that.getReligionTypeCode()) : that.getReligionTypeCode() == null;
    }

    @Override
    public int hashCode() {
        return getReligionTypeCode() != null ? getReligionTypeCode().hashCode() : 0;
    }
}
