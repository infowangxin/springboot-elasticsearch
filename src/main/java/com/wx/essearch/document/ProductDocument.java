package com.wx.essearch.document;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Mapping;

import java.io.Serializable;
import java.util.Date;

/**
 * 产品实体
 *
 * @author vincent
 */
@Document(indexName = "orders", type = "product")
@Mapping(mappingPath = "productIndex.json") // 解决IK分词不能使用问题
public class ProductDocument implements Serializable {

    private static final long serialVersionUID = -4819387659362663519L;
    @Id
    private Long id;
    //@Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String productName;
    //@Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String productDesc;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public ProductDocument() {

    }

    public ProductDocument(String productName, String productDesc, Date createTime, Date updateTime) {
        this.productName = productName;
        this.productDesc = productDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public ProductDocument(Long id, String productName, String productDesc, Date createTime, Date updateTime) {
        this.id = id;
        this.productName = productName;
        this.productDesc = productDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
