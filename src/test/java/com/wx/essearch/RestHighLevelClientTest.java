package com.wx.essearch;

import com.alibaba.fastjson.JSON;
import com.wx.entity.vo.HouseVo;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestHighLevelClientTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Resource
    private RestHighLevelClient restHighLevelClient;

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


    @Test
    public void search() throws ParseException {
        HouseVo vo = new HouseVo();
        // vo.setId(1L);
        // vo.setHouseName("阜兴世纪公馆");
        // vo.setHouseTags("浦东");
        // vo.setCreateTime(DateUtils.parseDate("2021-08-01", "yyyy-MM-dd"));
        // vo.setUpdateTime(DateUtils.parseDate("2021-08-01", "yyyy-MM-dd"));

        // 指定索引
        SearchRequest searchRequest = new SearchRequest().indices("house");
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();

        // 查询条件
        QueryBuilder query = getQuery(vo);
        sourceBuilder.query(query);

        //包含字段
        // String[] includes = new String[]{"id", "houseName", "houseTags", "updateTime"};
        String[] includes = new String[]{"*"};

        // 排除字段
        String[] excludes = new String[]{"createTime"};

        Integer pageNo = 1, pageSize = 5;

        //init
        if (pageSize == null || pageSize < 0) {
            pageSize = 10;
        }
        if (pageNo == null || pageNo < 0) {
            pageNo = 1;
        }
        Integer from = (pageNo - 1) * pageSize;
        if (from <= 0) {
            from = 0;
        }

        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        sourceBuilder.fetchSource(fetchSourceContext);
        sourceBuilder.from(from);
        sourceBuilder.size(pageSize);

        // 指定字段排序
        sourceBuilder.sort(new FieldSortBuilder("id").order(SortOrder.ASC));

        searchRequest.source(sourceBuilder);


        try {
            SearchResponse response = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] searchHits = response.getHits().getHits();
            List<HouseVo> results = Arrays.stream(searchHits)
                    .map(hit -> JSON.parseObject(hit.getSourceAsString(), HouseVo.class))
                    .collect(Collectors.toList());
            Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.Direction.ASC, "id");
            long total = response.getHits().getTotalHits().value;
            log.info("# total={}", total);
            Page<HouseVo> page = new PageImpl<>(results, pageable, total);
            log.info("\ntotalElements={},totalPages={},number={},numberOfElements={}", page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getNumberOfElements());
            log.info("# {}", JSON.toJSONString(page, true));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

