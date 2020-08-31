package com.wx.essearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.wx.entity.document.HouseDocument;
import com.wx.entity.vo.HouseVo;
import com.wx.essearch.repository.HouseRepository;
import com.wx.essearch.service.EsHouseSearchService;
import com.wx.util.IteratorUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public class EsHouseSearchServiceImpl implements EsHouseSearchService {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private HouseRepository houseRepository;

    @Override
    public void save(List<HouseDocument> productDocuments) {
        if (CollectionUtils.isNotEmpty(productDocuments)) {
            log.info("{}", JSON.toJSONString(productDocuments));
        }
        houseRepository.saveAll(productDocuments);
    }

    @Override
    public void delete(Long id) {
        houseRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        houseRepository.deleteAll();
    }

    @Override
    public HouseDocument getById(Long id) {
        Optional<HouseDocument> optional = houseRepository.findById(id);
        return optional.get();
    }

    @Override
    public List<HouseDocument> getAll() {
        Iterable<HouseDocument> iterable = houseRepository.findAll();
        return IteratorUtil.toList(iterable);
    }

    @Override
    public List<HouseDocument> query(String paramName, String keyword) {
        QueryBuilder queryBuilder = QueryBuilders.matchPhrasePrefixQuery(paramName, keyword);
        Iterable<HouseDocument> iterable = houseRepository.search(queryBuilder);
        return IteratorUtil.toList(iterable);
    }

    @Override
    public Page<HouseDocument> queryByPage(int pageNo, int pageSize, HouseVo vo) {

        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (null == vo) {
            builder.must(QueryBuilders.matchAllQuery());
        } else {

            if (null != vo.getId() && vo.getId() > 0) {
                builder.must(QueryBuilders.matchQuery("id", vo.getId()));
            }

            if(StringUtils.isNotBlank(vo.getHouseName())){
                builder.must(QueryBuilders.fuzzyQuery("houseName", vo.getHouseName()));
            }

            if(StringUtils.isNotBlank(vo.getHouseTags())){
                builder.must(QueryBuilders.fuzzyQuery("houseTags", vo.getHouseTags()));
            }

            if(null!=vo.getCreateTime()){
                builder.must(QueryBuilders.rangeQuery("createTime").to(vo.getCreateTime()));
            }

            if(null!=vo.getUpdateTime()){
                builder.must(QueryBuilders.rangeQuery("updateTime").to(vo.getUpdateTime()));
            }

        }
        log.info("queryName={}", builder.queryName());
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, "id");
        Page<HouseDocument> page = houseRepository.search(builder, pageable);
        return page;
    }

    @Override
    public void deleteIndex(String indexName) {
        elasticsearchRestTemplate.deleteIndex(indexName);
    }
}
