package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_BIZ_TYPE)
@Table(name = Const.TABLE_NAME_BIZ_TYPE, catalog = Const.CATALOG_NAME_EPLUSI)
public class BizType {

    @Id
    @Column(name = "biz_type_code")
    private String bizTypeCode;
    @Column(name = "biz_type")
    private String bizType;
    @Column(name = "parent_code")
    private String parentCode;
    @Column(name = "updated_date")
    @Temporal(TemporalType.DATE)
    private Date updatedDate;
    @Column(name = "created_date")
    @Temporal(TemporalType.DATE)
    private Date createdDate;

    public BizType() {
    }

    public String getBizTypeCode() {
        return bizTypeCode;
    }

    public void setBizTypeCode(String bizTypeCode) {
        this.bizTypeCode = bizTypeCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
    }

    public String getParentCode() {
        return parentCode;
    }

    public void setParentCode(String parentCode) {
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

        BizType bizType = (BizType) o;

        return getBizTypeCode() != null ? getBizTypeCode().equals(bizType.getBizTypeCode()) : bizType.getBizTypeCode() == null;
    }

    @Override
    public int hashCode() {
        return getBizTypeCode() != null ? getBizTypeCode().hashCode() : 0;
    }
}
