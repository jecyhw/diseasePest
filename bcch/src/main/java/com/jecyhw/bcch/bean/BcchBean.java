package com.jecyhw.bcch.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by jecyhw on 16-8-22.
 */
@Document(collection = "bcch")
final public class BcchBean {
    @Id
    private String id;
    @Field("中文名称")
    private String chineseName;
    @Field("英文名")
    private String englishName;
    @Field("异名")
    private String synonym;
    @Field("简介")
    private String introduction;
    @Field("图片")
    private List<String> imageNames;
    @Field("为害症状")
    private String damageSymptom;
    @Field("病原物")
    private String pathogen;
    @Field("侵染循环")
    private String cycle;
    @Field("发生因素")
    private String occurrenceFactor;
    @Field("形态特征")
    private String morphology;
    @Field("生活习性")
    private String lifeHabit;
    @Field("防治方法")
    private String cureMethod;
    @Field("分类")
    private String classification;

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

    public String getSynonym() {
        return synonym;
    }

    public void setSynonym(String synonym) {
        this.synonym = synonym;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public List<String> getImageNames() {
        return imageNames;
    }

    public void setImageNames(List<String> imageNames) {
        this.imageNames = imageNames;
    }

    public String getDamageSymptom() {
        return damageSymptom;
    }

    public void setDamageSymptom(String damageSymptom) {
        this.damageSymptom = damageSymptom;
    }

    public String getPathogen() {
        return pathogen;
    }

    public void setPathogen(String pathogen) {
        this.pathogen = pathogen;
    }

    public String getCycle() {
        return cycle;
    }

    public void setCycle(String cycle) {
        this.cycle = cycle;
    }

    public String getOccurrenceFactor() {
        return occurrenceFactor;
    }

    public void setOccurrenceFactor(String occurrenceFactor) {
        this.occurrenceFactor = occurrenceFactor;
    }

    public String getMorphology() {
        return morphology;
    }

    public void setMorphology(String morphology) {
        this.morphology = morphology;
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

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }
}
