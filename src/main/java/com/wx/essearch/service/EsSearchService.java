package com.wx.essearch.service;

import com.wx.essearch.document.ProductDocument;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * 搜索引擎相关接口
 *
 * @author vincent
 */
public interface EsSearchService {

    /**
     * 搜 索
     *
     * @param keyword
     * @return
     */
    List<ProductDocument> query(String paramName, String keyword);

    /**
     * 搜索高亮显示，返回分页
     *
     * @param pageNo      当前页
     * @param pageSize    每页显示的总条数
     * @param productName 关键字
     * @return
     */
    Page<ProductDocument> queryByPage(int pageNo, int pageSize, String productName);

    /**
     * 删除索引库
     *
     * @param indexName
     * @return
     */
    void deleteIndex(String indexName);

    /**
     * 保存
     */
    void save(List<ProductDocument> productDocuments);

    /**
     * 删除
     *
     * @param id
     */
    void delete(Long id);

    /**
     * 清空索引
     */
    void deleteAll();

    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    ProductDocument getById(Long id);

    /**
     * 查询全部
     *
     * @return
     */
    List<ProductDocument> getAll();
}
