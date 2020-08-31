package com.wx.entity.document;

import com.wx.constant.Constants;
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
@Document(indexName = Constants.INDEX_NAME)
@Mapping(mappingPath = "house.json") // 解决IK分词不能使用问题
public class HouseDocument implements Serializable {

    private static final long serialVersionUID = -4819387659362663519L;
    @Id
    private Long id;
    //@Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String houseName;
    //@Field(analyzer = "ik_max_word",searchAnalyzer = "ik_max_word")
    private String houseTags;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHouseName() {
        return houseName;
    }

    public void setHouseName(String houseName) {
        this.houseName = houseName;
    }

    public String getHouseTags() {
        return houseTags;
    }

    public void setHouseTags(String houseTags) {
        this.houseTags = houseTags;
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

    public HouseDocument() {

    }

    public HouseDocument(Long id, String houseName, String houseTags, Date createTime, Date updateTime) {
        this.id = id;
        this.houseName = houseName;
        this.houseTags = houseTags;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }
}
