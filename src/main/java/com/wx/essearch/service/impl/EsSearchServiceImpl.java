package com.wx.essearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.wx.essearch.document.ProductDocument;
import com.wx.essearch.repository.ProductRepository;
import com.wx.essearch.service.EsSearchService;
import com.wx.util.IteratorUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 搜索引擎相关接口
 *
 * @author vincent
 */
@Service
public class EsSearchServiceImpl implements EsSearchService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public void save(List<ProductDocument> productDocuments) {
        if (CollectionUtils.isNotEmpty(productDocuments)) {
            log.info("{}", JSON.toJSONString(productDocuments));
        }
        productRepository.saveAll(productDocuments);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }

    @Override
    public ProductDocument getById(Long id) {
        Optional<ProductDocument> optional = productRepository.findById(id);
        return optional.get();
    }

    @Override
    public List<ProductDocument> getAll() {
        Iterable<ProductDocument> iterable = productRepository.findAll();
        return IteratorUtil.toList(iterable);
    }

    @Override
    public List<ProductDocument> query(String paramName, String keyword) {
        QueryBuilder queryBuilder = QueryBuilders.termQuery(paramName, keyword);
        Iterable<ProductDocument> iterable = productRepository.search(queryBuilder);
        return IteratorUtil.toList(iterable);
    }

    @Override
    public Page<ProductDocument> queryByPage(int pageNo, int pageSize, String keyword, String indexName, String... fieldNames) {
        return null;
    }

    @Override
    public void deleteIndex(String indexName) {
        elasticsearchRestTemplate.deleteIndex(indexName);
    }
}
