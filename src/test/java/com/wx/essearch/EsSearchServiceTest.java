package com.wx.essearch;

import com.alibaba.fastjson.JSON;
import com.wx.essearch.document.ProductDocument;
import com.wx.essearch.service.EsSearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsSearchServiceTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private EsSearchService esSearchService;

    @Test
    public void save() {
        Date time = Calendar.getInstance().getTime();
        ProductDocument productDocument = new ProductDocument(System.currentTimeMillis() + 1, "临汾名城(汾西路88弄)", "工业 住宅 吉房 毛坯", time, time);
        ProductDocument productDocument1 = new ProductDocument(System.currentTimeMillis() + 2, "泗塘四村(虎林路435弄)", "商业 业主住", time, time);
        ProductDocument productDocument2 = new ProductDocument(System.currentTimeMillis() + 3, "中华新路904号", "办公 吉房 毛坯", time, time);

        List<ProductDocument> productDocuments = new ArrayList<>();
        productDocuments.add(productDocument);
        productDocuments.add(productDocument1);
        productDocuments.add(productDocument2);
        esSearchService.save(productDocuments);

    }

    @Test
    public void getAll() {
        esSearchService.getAll().parallelStream()
                .map(JSON::toJSONString)
                .forEach(System.out::println);
    }

    @Test
    public void deleteAll() {
        esSearchService.deleteAll();
        log.info(" deleteAll end");
    }

    @Test
    public void getById() {
        log.info("{}", JSON.toJSONString(esSearchService.getById(1598419945319L)));
    }

    @Test
    public void query() {
        log.info("{}", JSON.toJSONString(esSearchService.query("productDesc", "毛坯")));
    }

    @Test
    public void queryHit() {

        String keyword = "联通尿素";
        String indexName = "orders";

        // List<Map<String, Object>> searchHits = esSearchService.queryHit(keyword, indexName, "productName", "productDesc");
        // log.info("【根据关键字搜索内容，命中部分高亮，返回内容】：{}", JSON.toJSONString(searchHits));
        //[{"highlight":{"productDesc":"<span style='color:red'>无印良品</span> MUJI 基础润肤化妆水 高保湿型 200ml","productName":"<span style='color:red'>无印良品</span> MUJI 基础润肤化妆水"},"source":{"productDesc":"无印良品 MUJI 基础润肤化妆水 高保湿型 200ml","createTime":1544755966204,"updateTime":1544755966204,"id":"154475596620401","productName":"无印良品 MUJI 基础润肤化妆水"}},{"highlight":{"productDesc":"<span style='color:red'>荣耀</span> V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待","productName":"<span style='color:red'>荣耀</span> V10 尊享版"},"source":{"productDesc":"荣耀 V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待","createTime":1544755966204,"updateTime":1544755966204,"id":"154475596620402","productName":"荣耀 V10 尊享版"}}]
    }

    @Test
    public void queryHitByPage() {
        String keyword = "联通尿素";
        String indexName = "orders";
        // Page<Map<String, Object>> searchHits = esSearchService.queryHitByPage(1, 1, keyword, indexName, "productName", "productDesc");
        // log.info("【分页查询，根据关键字搜索内容，命中部分高亮，返回内容】：{}", JSON.toJSONString(searchHits));
        //[{"highlight":{"productDesc":"<span style='color:red'>无印良品</span> MUJI 基础润肤化妆水 高保湿型 200ml","productName":"<span style='color:red'>无印良品</span> MUJI 基础润肤化妆水"},"source":{"productDesc":"无印良品 MUJI 基础润肤化妆水 高保湿型 200ml","createTime":1544755966204,"updateTime":1544755966204,"id":"154475596620401","productName":"无印良品 MUJI 基础润肤化妆水"}},{"highlight":{"productDesc":"<span style='color:red'>荣耀</span> V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待","productName":"<span style='color:red'>荣耀</span> V10 尊享版"},"source":{"productDesc":"荣耀 V10 尊享版 6GB+128GB 幻夜黑 移动联通电信4G全面屏游戏手机 双卡双待","createTime":1544755966204,"updateTime":1544755966204,"id":"154475596620402","productName":"荣耀 V10 尊享版"}}]
    }

    @Test
    public void deleteIndex() {
        esSearchService.deleteIndex("orders");
        log.info(" deleteIndex ");
    }

}

