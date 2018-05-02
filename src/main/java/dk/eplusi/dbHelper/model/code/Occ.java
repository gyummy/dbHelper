package dk.eplusi.dbHelper.model.code;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_OCC)
@Table(name = Const.TABLE_NAME_OCC, catalog = Const.CATALOG_NAME_CODE)
public class Occ {

    @Id
    @Column(name = "occ_code")
    @GeneratedValue
    private Integer occCode;
    @Column(name = "occ_name")
    private String occName;
    @Column(name = "parent_code")
    private int parentCode;
    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    private Date updateTime;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public Occ() {
    }

    public Integer getOccCode() {
        return occCode;
    }

    public void setOccCode(Integer occCode) {
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

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
