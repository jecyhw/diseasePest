package com.jecyhw.fjsnw.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by jecyhw on 16-8-19.
 */
@Document(collection = "fjsnwDisease")
public class FjsnwBean {
    @Id
    private String id;
    @Field("病害名称")
    private String diseaseName;
    @Field("危害植物")
    private String harmPlant;
    @Field("病因")
    private String diseaseReason;
    @Field("症状描述")
    private String diseaseDescription;
    @Field("防治方法")
    private String cureMethod;
    @Field("参考文献")
    private String reference;
    @Field("病害图片名字")
    private String diseaseImageName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public String getHarmPlant() {
        return harmPlant;
    }

    public void setHarmPlant(String harmPlant) {
        this.harmPlant = harmPlant;
    }

    public String getDiseaseReason() {
        return diseaseReason;
    }

    public void setDiseaseReason(String diseaseReason) {
        this.diseaseReason = diseaseReason;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public String getCureMethod() {
        return cureMethod;
    }

    public void setCureMethod(String cureMethod) {
        this.cureMethod = cureMethod;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getDiseaseImageName() {
        return diseaseImageName;
    }

    public void setDiseaseImageName(String diseaseImageName) {
        this.diseaseImageName = diseaseImageName;
    }
}
