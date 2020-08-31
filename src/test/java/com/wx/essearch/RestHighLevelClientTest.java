package com.wx.essearch;

import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.ParseException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RestHighLevelClientTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final static Long default_id = 1L;

    @Resource
    private RestHighLevelClient restHighLevelClient;

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Test
    public void save() throws ParseException {

    }

    @Test
    public void search() {
        SearchRequest searchRequest = new SearchRequest().indices("house");
        try {
            SearchResponse sr = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            String result = sr.toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

