package android.jhyuk.com.sammae.domain;

import java.io.Serializable;
import java.util.List;

/**
 * Created by maxx on 2017. 8. 17..
 */

public class StoreInfo implements Serializable{

    private String createDate;                    //매장 추가일

    private String localArea;                   //해당 지역

    private String code;                        //매장 코드

    private String name;                        //매장명

    private String telecomCategory;             //통신사 구분

    private String address;                     //주소

    private String address2;                    //상세 주소

    private String tel;                         //매장 전화번호

    private String fax;                         //매장 팩스번호

    private String storeImage;                  //매장사진

    private String nameCardImage;               //명함사진

    private String blueprintImage;              //매장도면 사진

    private String phone;                       //휴대폰 번호

    private String manager;                     //매장 담당자명

    private List<Boolean> mearsureComplete;    //실측완료 (ON/OFF)

    private List<Boolean> constructionComplete; //시공완료 (ON/OFF)

    public List<Boolean> getMearsureComplete() {
        return mearsureComplete;
    }

    public void setMearsureComplete(List<Boolean> mearsureComplete) {
        this.mearsureComplete = mearsureComplete;
    }

    public List<Boolean> getConstructionComplete() {
        return constructionComplete;
    }

    public void setConstructionComplete(List<Boolean> constructionComplete) {
        this.constructionComplete = constructionComplete;
    }


    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getLocalArea() {
        return localArea;
    }

    public void setLocalArea(String localArea) {
        this.localArea = localArea;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelecomCategory() {
        return telecomCategory;
    }

    public void setTelecomCategory(String telecomCategory) {
        this.telecomCategory = telecomCategory;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getStoreImage() {
        return storeImage;
    }

    public void setStoreImage(String storeImage) {
        this.storeImage = storeImage;
    }

    public String getNameCardImage() {
        return nameCardImage;
    }

    public void setNameCardImage(String nameCardImage) {
        this.nameCardImage = nameCardImage;
    }

    public String getBlueprintImage() {
        return blueprintImage;
    }

    public void setBlueprintImage(String blueprintImage) {
        this.blueprintImage = blueprintImage;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }
}

