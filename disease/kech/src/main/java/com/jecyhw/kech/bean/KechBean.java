package com.jecyhw.kech.bean;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * Created by jecyhw on 16-8-22.
 */
@Document(collection = "kechDisease")
final public class KechBean {
    @Id
    private String id;
    @Field("病害名称")
    private String diseaseName;
    @Field("病害症状")
    private String diseaseSymptom;
    @Field("发病规律")
    private String pathogenesis;
    @Field("防治方法")
    private String cureMethod;
    @Field("病害图片")
    private String diseaseImageName;
    @Field("寄主名称")
    private String host;
    @Field("寄主所在科")
    private String hostFamily;
    @Field("常规分类")
    private String classification;
    @Field("病原描述")
    private String diseaseDescription;
    @Field("病原图片")
    private String diseasePathogenImageName;

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

    public String getDiseaseSymptom() {
        return diseaseSymptom;
    }

    public void setDiseaseSymptom(String diseaseSymptom) {
        this.diseaseSymptom = diseaseSymptom;
    }

    public String getPathogenesis() {
        return pathogenesis;
    }

    public void setPathogenesis(String pathogenesis) {
        this.pathogenesis = pathogenesis;
    }

    public String getCureMethod() {
        return cureMethod;
    }

    public void setCureMethod(String cureMethod) {
        this.cureMethod = cureMethod;
    }

    public String getDiseaseImageName() {
        return diseaseImageName;
    }

    public void setDiseaseImageName(String diseaseImageName) {
        this.diseaseImageName = diseaseImageName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getHostFamily() {
        return hostFamily;
    }

    public void setHostFamily(String hostFamily) {
        this.hostFamily = hostFamily;
    }

    public String getClassification() {
        return classification;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDiseaseDescription() {
        return diseaseDescription;
    }

    public void setDiseaseDescription(String diseaseDescription) {
        this.diseaseDescription = diseaseDescription;
    }

    public String getDiseasePathogenImageName() {
        return diseasePathogenImageName;
    }

    public void setDiseasePathogenImageName(String diseasePathogenImageName) {
        this.diseasePathogenImageName = diseasePathogenImageName;
    }
}
