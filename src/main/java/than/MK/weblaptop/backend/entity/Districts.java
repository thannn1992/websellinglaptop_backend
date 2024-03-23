package than.MK.weblaptop.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
// Generated Mar 19, 2024, 3:29:20 PM by Hibernate Tools 4.3.6.Final

/**
 * Districts generated by hbm2java
 */
@Data
@Entity
@Table(name = "districts", catalog = "web_selling_laptop")
public class Districts implements java.io.Serializable {

    private String code;
    private String name;
    private String nameEn;
    private String fullName;
    private String fullNameEn;
    private String codeName;
    private String provinceCode;
    private Integer administrativeUnitId;

    public Districts() {
    }

    public Districts(String code, String name) {
        this.code = code;
        this.name = name;
    }

    public Districts(String code, String name, String nameEn, String fullName, String fullNameEn, String codeName,
                     String provinceCode, Integer administrativeUnitId) {
        this.code = code;
        this.name = name;
        this.nameEn = nameEn;
        this.fullName = fullName;
        this.fullNameEn = fullNameEn;
        this.codeName = codeName;
        this.provinceCode = provinceCode;
        this.administrativeUnitId = administrativeUnitId;
    }

    @Id

    @Column(name = "code", unique = true, nullable = false, length = 20)
    public String getCode() {
        return this.code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "name_en")
    public String getNameEn() {
        return this.nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    @Column(name = "full_name")
    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    @Column(name = "full_name_en")
    public String getFullNameEn() {
        return this.fullNameEn;
    }

    public void setFullNameEn(String fullNameEn) {
        this.fullNameEn = fullNameEn;
    }

    @Column(name = "code_name")
    public String getCodeName() {
        return this.codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }

    @Column(name = "province_code", length = 20)
    public String getProvinceCode() {
        return this.provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    @Column(name = "administrative_unit_id")
    public Integer getAdministrativeUnitId() {
        return this.administrativeUnitId;
    }

    public void setAdministrativeUnitId(Integer administrativeUnitId) {
        this.administrativeUnitId = administrativeUnitId;
    }

}

