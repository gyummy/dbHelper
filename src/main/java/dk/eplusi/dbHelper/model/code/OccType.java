package dk.eplusi.dbHelper.model.code;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_OCC_TYPE)
@Table(name = Const.TABLE_NAME_OCC_TYPE, catalog = Const.CATALOG_NAME_CODE)
public class OccType {

    @Id
    @Column(name = "occ_type_code")
    @GeneratedValue
    private Integer occTypeCode;
    @Column(name = "occ_type")
    private String occType;

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
}
