package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;

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
