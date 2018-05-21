package dk.eplusi.dbHelper.model.eplusi;

import dk.eplusi.dbHelper.common.Const;
import dk.eplusi.dbHelper.model.code.BizType;
import dk.eplusi.dbHelper.model.code.Occ;
import dk.eplusi.dbHelper.model.code.OccType;
import dk.eplusi.dbHelper.model.code.ReligionType;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Gyummy on 2018-03-06.
 *
 */
@Entity(name = Const.TABLE_NAME_YOUTH)
@Table(name = Const.TABLE_NAME_YOUTH, catalog = Const.CATALOG_NAME_EPLUSI)
public class Youth {

    @Id
    @Column(name = "youth_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer youthId;
    @Column(name = "youth_name")
    private String youthName;
    @Column(name = "youth_peer")
    private String youthPeer;
    @Column(name = "gender")
    private Character gender;
    @Temporal(TemporalType.DATE)
    @Column(name = "birth_date")
    private Date birthDate;
    @Column(name = "cell_phone")
    private String cellPhone;
    @Column(name = "home_address")
    private String homeAddress;
    @Column(name = "is_born_chr")
    private Integer isBornChr;
    @Column(name = "is_self_in")
    private Integer isSelfIn;
    @Column(name = "guide_name")
    private String guideName;
    @ManyToOne(targetEntity = OccType.class)
    @JoinColumn(name = "occ_type_code")
    private OccType occType;
    @ManyToOne(targetEntity = Occ.class)
    @JoinColumn(name = "occ_code")
    private Occ occ;
    @ManyToOne(targetEntity = BizType.class)
    @JoinColumn(name = "biz_type_code")
    private BizType bizType;
    @ManyToOne(targetEntity = ReligionType.class)
    @JoinColumn(name = "religion_type_code")
    private ReligionType religionType;
    @Temporal(TemporalType.DATE)
    @Column(name = "church_reg_date")
    private Date churchRegDate;
    @Column(name = "is_attending")
    private Integer isAttending;
    @Column(name = "is_registered")
    private Integer isRegistered;
    @Temporal(TemporalType.DATE)
    @Column(name = "update_time")
    private Date updateTime;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "create_time", insertable = false)
    private Date createTime;

    public Youth() {
    }

    public Integer getYouthId() {
        return youthId;
    }

    public void setYouthId(Integer youthId) {
        this.youthId = youthId;
    }

    public String getYouthName() {
        return youthName;
    }

    public void setYouthName(String youthName) {
        this.youthName = youthName;
    }

    public String getYouthPeer() {
        return youthPeer;
    }

    public void setYouthPeer(String youthPeer) {
        this.youthPeer = youthPeer;
    }

//    public Character getGender() {
//        return gender;
//    }

    public String getGender() {
        return gender == 'M' ? "남" : "여";
    }

    public void setGender(Character gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getHomeAddress() {
        return homeAddress;
    }

    public void setHomeAddress(String homeAddress) {
        this.homeAddress = homeAddress;
    }

    public String getIsBornChr() {
        return isBornChr == 1 ? "예" : "아니오";
    }

    public void setIsBornChr(Integer isBornChr) {
        this.isBornChr = isBornChr;
    }

    public String getIsSelfIn() {
        return isSelfIn == 1 ? "예" : "아니오";
    }

    public void setIsSelfIn(Integer isSelfIn) {
        this.isSelfIn = isSelfIn;
    }

    public String getGuideName() {
        return guideName;
    }

    public void setGuideName(String guideName) {
        this.guideName = guideName;
    }

    public OccType getOccType() {
        return occType;
    }

    public void setOccType(OccType occType) {
        this.occType = occType;
    }

    public Occ getOcc() {
        return occ;
    }

    public void setOcc(Occ occ) {
        this.occ = occ;
    }

    public BizType getBizType() {
        return bizType;
    }

    public void setBizType(BizType bizType) {
        this.bizType = bizType;
    }

    public ReligionType getReligionType() {
        return religionType;
    }

    public void setReligionType(ReligionType religionType) {
        this.religionType = religionType;
    }

    public Date getChurchRegDate() {
        return churchRegDate;
    }

    public void setChurchRegDate(Date churchRegDate) {
        this.churchRegDate = churchRegDate;
    }

    public String getIsAttending() {
        return isAttending == 1 ? "예" : "아니오";
    }

    public void setIsAttending(Integer isAttending) {
        this.isAttending = isAttending;
    }

    public String getIsRegistered() {
        return isRegistered == 1 ? "예" : "아니오";
    }

    public void setIsRegistered(Integer isRegistered) {
        this.isRegistered = isRegistered;
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

    public static String getIdColumnName() {
        return "youth_id";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Youth youth = (Youth) o;

        return getYouthId().equals(youth.getYouthId());
    }

    @Override
    public int hashCode() {
        return getYouthId().hashCode();
    }
}
