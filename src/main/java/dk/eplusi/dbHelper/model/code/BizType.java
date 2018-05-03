package dk.eplusi.dbHelper.model.code;

import dk.eplusi.dbHelper.common.Const;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_BIZ_TYPE)
@Table(name = Const.TABLE_NAME_BIZ_TYPE, catalog = Const.CATALOG_NAME_CODE)
public class BizType {

    @Id
    @Column(name = "biz_type_code")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bizTypeCode;
    @Column(name = "biz_type")
    private String bizType;
    @Column(name = "update_time")
    @Temporal(TemporalType.DATE)
    private Date updateTime;
    @Column(name = "create_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createTime;

    public BizType() {
    }

    public Integer getBizTypeCode() {
        return bizTypeCode;
    }

    public void setBizTypeCode(Integer bizTypeCode) {
        this.bizTypeCode = bizTypeCode;
    }

    public String getBizType() {
        return bizType;
    }

    public void setBizType(String bizType) {
        this.bizType = bizType;
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
