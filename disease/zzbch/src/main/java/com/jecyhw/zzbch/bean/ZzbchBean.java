package com.jecyhw.zzbch.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by jecyhw on 16-8-19.
 */
@Document(collection = "zzbchDisease")
public class ZzbchBean {
    @Id
    private String id;
    @Field("中文名")
    private String chineseName;
    @Field("英文名")
    private String englishName;
    @Field("病原菌")
    private String pathogen;
    @Field("特征")
    private String feature;
    @Field("为害部位")
    private String damagePosition;
    @Field("为害症状")
    private String damageSymptom;
    @Field("传播途径")
    private String transmission;
    @Field("防治措施")
    private String cureMethod;
    @Field("图片")
    private String imageName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getPathogen() {
        return pathogen;
    }

    public void setPathogen(String pathogen) {
        this.pathogen = pathogen;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getDamagePosition() {
        return damagePosition;
    }

    public void setDamagePosition(String damagePosition) {
        this.damagePosition = damagePosition;
    }

    public String getDamageSymptom() {
        return damageSymptom;
    }

    public void setDamageSymptom(String damageSymptom) {
        this.damageSymptom = damageSymptom;
    }

    public String getTransmission() {
        return transmission;
    }

    public void setTransmission(String transmission) {
        this.transmission = transmission;
    }

    public String getCureMethod() {
        return cureMethod;
    }

    public void setCureMethod(String cureMethod) {
        this.cureMethod = cureMethod;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
