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
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsSearchServiceTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final static Long default_id = 1598494995351L;

    @Autowired
    private EsSearchService esSearchService;

    @Test
    public void save() {
        Date time = Calendar.getInstance().getTime();
        int i = 0;
        List<ProductDocument> productDocuments = new ArrayList<>();
        productDocuments.add(new ProductDocument(default_id, "凯德都会新峰", "中区/34F 其他/其他 昆山市花桥镇兆丰路11号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "阿里巴巴电子商务大厦", "中区/26F 其他/其他 花安路171号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "凯德都会新峰", "高区/34F 其他/其他 昆山市花桥镇兆丰路11号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "亚太广场", "高区/9F 其他/其他 昆山市花桥镇亚太广场", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "THE SHORE", "中区/25F 其他/其他 Jalan Tun Faud Stephens,Kota Kinabalu, Sabah", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "芳草路商铺", "低区/1F 浦东/北蔡 芳草路", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "莲安西路125弄32支弄", "低区/6F 浦东/北蔡 莲安西路125弄32支弄", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "鹏海东苑", "低区/6F 浦东/北蔡 五星路239弄", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "车位", "低区/1F 其他/其他 车位", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "金杨七街坊(枣庄路998弄)", "低区/6F 浦东/金杨 枣庄路998弄", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "金杨七街坊(枣庄路998弄)", "低区/6F 浦东/金杨 枣庄路998弄", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "鸿禧会", "中区/4F 浦东/潍坊 南泉北路1054号(旧址崂山西路1054号)", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "康沈路1880号", "低区/1F 浦东/周浦 康沈路1880号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "康沈路1880号", "低区/1F 浦东/周浦 康沈路1880号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "康桥半岛密苏里假日公寓(秀沿路1177弄10支弄)", "高区/4F 浦东/康桥 秀沿路1177弄10支弄", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "康桥半岛密苏里假日公寓(秀沿路1177弄10支弄)", "高区/4F 浦东/康桥 秀沿路1177弄10支弄", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "泰州市义乌大市场", "中区/51F 其他/其他 泰州市高港区金港北路与春港路交汇处", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "奥园印象高迪花园(佑林泛太)", "高区/9F 其他/其他 昆山市千灯镇沿沪产业带机场南路5A号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "阿里巴巴电子商务大厦", "中区/26F 其他/其他 花安路171号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "朗悦湾", "中区/11F 其他/其他 苏州相城区澄阳路2999号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "朗悦湾", "中区/11F 其他/其他 苏州相城区澄阳路2999号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "鑫苑国际城市花园", "高区/29F 其他/其他 昆山市花桥镇绿地大道绿地大道189号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "绿地青青家园", "低区/22F 其他/其他 绿地大道261号", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "金都安亭家园", "低区/9F 其他/其他 昆山市花桥镇金都安亭家园", time, time));
        productDocuments.add(new ProductDocument(System.currentTimeMillis() + (i++), "金融家", "低区/8F 浦东/碧云 上丰路1483弄", time, time));
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
        log.info("{}", JSON.toJSONString(esSearchService.getById(default_id), true));
    }

    @Test
    public void query() {
        log.info("{}", JSON.toJSONString(esSearchService.query("productDesc", "毛坯"), true));
    }

    @Test
    public void queryHit() {

        // Page<ProductDocument> page = esSearchService.queryByPage(1, 5, null);
        Page<ProductDocument> page = esSearchService.queryByPage(0, 5, null);
        log.info("totalElements={},totalPages={},number={},numberOfElements={}", page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getNumberOfElements());
        for (ProductDocument document : page.getContent()) {
            log.info("{}", JSON.toJSONString(document));
        }
    }


    @Test
    public void deleteIndex() {
        esSearchService.deleteIndex("orders");
        log.info(" deleteIndex ");
    }

}

