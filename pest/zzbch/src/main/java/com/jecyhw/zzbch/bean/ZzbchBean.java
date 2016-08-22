package com.jecyhw.zzbch.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by jecyhw on 16-8-19.
 */
@Document(collection = "zzbchPest")
public class ZzbchBean {
    @Id
    private String id;
    @Field("中文名")
    private String chineseName;
    @Field("中文俗名")
    private String chineseCommonName;
    @Field("英文名")
    private String englishName;
    @Field("学名")
    private String scientificName;
    @Field("所属科目")
    private String subject;
    @Field("寄主")
    private String host;
    @Field("为害特点")
    private String damageFeature;
    @Field("形态特征")
    private String morphological;
    @Field("生活习性")
    private String lifeHabit;
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

    public String getChineseCommonName() {
        return chineseCommonName;
    }

    public void setChineseCommonName(String chineseCommonName) {
        this.chineseCommonName = chineseCommonName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    public String getScientificName() {
        return scientificName;
    }

    public void setScientificName(String scientificName) {
        this.scientificName = scientificName;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getDamageFeature() {
        return damageFeature;
    }

    public void setDamageFeature(String damageFeature) {
        this.damageFeature = damageFeature;
    }

    public String getMorphological() {
        return morphological;
    }

    public void setMorphological(String morphological) {
        this.morphological = morphological;
    }

    public String getLifeHabit() {
        return lifeHabit;
    }

    public void setLifeHabit(String lifeHabit) {
        this.lifeHabit = lifeHabit;
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
