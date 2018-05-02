package dk.eplusi.dbHelper.model.code;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_RELIGION_TYPE)
@Table(name = Const.TABLE_NAME_RELIGION_TYPE, catalog = Const.CATALOG_NAME_CODE)
public class ReligionType {

    @Id
    @Column(name = "religion_type_code")
    @GeneratedValue
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
}