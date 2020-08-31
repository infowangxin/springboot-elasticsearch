package com.wx.entity.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * 查询参数VO
 *
 * @author wangxin65
 * @date 2020-08-28 17:52
 */
public class HouseVo implements Serializable {

    private static final long serialVersionUID = -4858294435730754792L;
    private Long id;
    private String houseName;
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
}
