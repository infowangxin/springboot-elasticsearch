package com.wx.essearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.wx.entity.document.HouseDocument;
import com.wx.entity.vo.HouseVo;
import com.wx.essearch.repository.HouseRepository;
import com.wx.essearch.service.EsHouseSearchService;
import com.wx.util.BeanCopyUtil;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    public QueryBuilder getQuery(HouseVo vo) {
        BoolQueryBuilder builder = QueryBuilders.boolQuery();
        if (null == vo) {
            builder.filter(QueryBuilders.matchAllQuery());
        } else {
            if (null != vo.getId() && vo.getId() > 0) {
                // 按主键搜索
                builder.filter(QueryBuilders.idsQuery().addIds(vo.getId().toString()));
            } else {
                // 其它搜索条件
                if (StringUtils.isNotBlank(vo.getHouseName())) {
                    log.info("# houseName={}", vo.getHouseName());
                    // termQuery：待查询的字段会分词，待查询的值不会分词，但如果字段类型设置为"type": "keyword"，不支持索引查询，则等同于精确匹配
                    builder.must(QueryBuilders.termQuery("houseName", vo.getHouseName()));
                }

                if (StringUtils.isNotBlank(vo.getHouseTags())) {
                    log.info("# houseTags={}", vo.getHouseTags());
                    // termQuery：待查询的字段会分词，待查询的值不会分词，但如果字段类型设置为"type": "keyword"，不支持索引查询，则等同于精确匹配
                    builder.must(QueryBuilders.termQuery("houseTags", vo.getHouseTags()));
                }

                try {
                    Date startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").parse("1949-10-01 00:00:00:000");
                    log.info("# startTime={}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(startTime));

                    if (null != vo.getCreateTime()) {
                        // createTime >= startTime and createTime <= vo.getCreateTime(),includeLower(true)包括下界,includeUpper(false)包括上界
                        log.info("# createTime={}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(vo.getCreateTime()));
                        // QueryBuilder query = QueryBuilders.rangeQuery("createTime").from(startTime.getTime()).to(vo.getCreateTime().getTime()).includeLower(true).includeUpper(true);
                        QueryBuilder query = QueryBuilders.rangeQuery("createTime").from(startTime.getTime()).to(vo.getCreateTime().getTime());
                        log.info("# query={}", query.toString());
                        builder.must(query);
                    }
                    if (null != vo.getUpdateTime()) {
                        // updateTime >= startTime and updateTime <= vo.getUpdateTime(),includeLower(true)包括下界,includeUpper(false)包括上界
                        log.info("# updateTime={}", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS").format(vo.getUpdateTime()));
                        QueryBuilder query = QueryBuilders.rangeQuery("updateTime").from(startTime.getTime()).to(vo.getUpdateTime().getTime()).includeLower(true).includeUpper(true);
                        log.info("# query={}", query.toString());
                        builder.must(query);
                    }
                } catch (ParseException e) {
                    log.error("# SimpleDateFormat parse to date error");
                }
            }

        }
        return builder;
    }

    @Override
    public List<HouseDocument> query(HouseVo vo) {
        QueryBuilder queryBuilder = getQuery(vo);
        Iterable<HouseDocument> iterable = houseRepository.search(queryBuilder);
        return IteratorUtil.toList(iterable);
    }

    @Override
    public Page<HouseDocument> queryByPage(int pageNo, int pageSize, HouseVo vo) {
        QueryBuilder builder = getQuery(vo);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, "id");
        Page<HouseDocument> page = houseRepository.search(builder, pageable);
        return page;
    }


    @Override
    public Page<HouseVo> queryPage(int pageNo, int pageSize, HouseVo vo) {
        QueryBuilder builder = getQuery(vo);
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, "id");
        Page<HouseDocument> page = houseRepository.search(builder, pageable);
        long begin = System.currentTimeMillis();
        log.debug("# {}", JSON.toJSONString(page, true));
        List<HouseVo> list = BeanCopyUtil.copyList(page.getContent(), HouseVo.class);
        Page<HouseVo> voPage = new PageImpl<>(list, page.getPageable(), page.getTotalElements());
        long end = System.currentTimeMillis();
        log.info("# execute time={}", (end - begin));
        return voPage;
    }

    @Override
    public void deleteIndex(String indexName) {
        elasticsearchRestTemplate.deleteIndex(indexName);
    }
}
