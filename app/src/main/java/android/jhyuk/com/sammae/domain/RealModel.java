package android.jhyuk.com.sammae.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by maxx on 2017. 8. 17..
 */

public class RealModel implements Serializable {

    private int order;                  //순번

    private String width;                  //가로 사이즈

    private String height;                 //세로 사이즈

    private double area;                //넓이

    private String surface;             //부착면

    private String howProcess;          //시공방법

    private String material;            //출력소재

    private int unitPrice;              //단가(소재별 단가 있음)

    private double totalAmount;         //총액 (넓이 * 단가)

    private Date surveyDate;            //실측일

    private Date constructionDate;      //시공일

    private String remarks;             //비고

    private String done;                //프로세스

    private String surveyImage1;        //실측사진1

    private String surveyImage2;        //실측사진2

    private String constructionImage1;  //시공사진1

    private String constructionImage2;  //시공사진2

    private List<Boolean> mearsureCpl;  //실측완료

    private List<Boolean> constructionCpl;    //시공완료



    public List<Boolean> getMearsureCpl() {
        return mearsureCpl;
    }

    public void setMearsureCpl(List<Boolean> mearsureCpl) {
        this.mearsureCpl = mearsureCpl;
    }

    public List<Boolean> getConstructionCpl() {
        return constructionCpl;
    }

    public void setConstructionCpl(List<Boolean> constructionCpl) {
        this.constructionCpl = constructionCpl;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public String getSurface() {
        return surface;
    }

    public void setSurface(String surface) {
        this.surface = surface;
    }

    public String getHowProcess() {
        return howProcess;
    }

    public void setHowProcess(String howProcess) {
        this.howProcess = howProcess;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(int unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double area, int unitPrice) {
        totalAmount = area * unitPrice;
    }

    public Date getSurveyDate() {
        return surveyDate;
    }

    public void setSurveyDate(Date surveyDate) {
        this.surveyDate = surveyDate;
    }

    public Date getConstructionDate() {
        return constructionDate;
    }

    public void setConstructionDate(Date constructionDate) {
        this.constructionDate = constructionDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getDone() {
        return done;
    }

    public void setDone(String done) {
        this.done = done;
    }

    public String getSurveyImage1() {
        return surveyImage1;
    }

    public void setSurveyImage1(String surveyImage1) {
        this.surveyImage1 = surveyImage1;
    }

    public String getSurveyImage2() {
        return surveyImage2;
    }

    public void setSurveyImage2(String surveyImage2) {
        this.surveyImage2 = surveyImage2;
    }

    public String getConstructionImage1() {
        return constructionImage1;
    }

    public void setConstructionImage1(String constructionImage1) {
        this.constructionImage1 = constructionImage1;
    }

    public String getConstructionImage2() {
        return constructionImage2;
    }

    public void setConstructionImage2(String constructionImage2) {
        this.constructionImage2 = constructionImage2;
    }
}

