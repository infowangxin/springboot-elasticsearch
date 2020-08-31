package com.wx.essearch;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wx.constant.Constants;
import com.wx.entity.document.HouseDocument;
import com.wx.entity.vo.HouseVo;
import com.wx.essearch.service.EsHouseSearchService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EsHouseSearchServiceTest {

    private Logger log = LoggerFactory.getLogger(getClass());

    private final static Long default_id = 1L;

    @Autowired
    private EsHouseSearchService esHouseSearchService;

    @Test
    public void save() throws ParseException {
        long i = 1;
        List<HouseDocument> productDocuments = new ArrayList<>();
        productDocuments.add(new HouseDocument(i++, "凯德都会新峰", "中区/34F 其他/其他 昆山市花桥镇兆丰路11号", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", DateUtils.parseDate("2020-02-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-02-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "阿里巴巴电子商务大厦", "中区/26F 其他/其他 花安路171号", DateUtils.parseDate("2020-03-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-03-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "凯德都会新峰", "高区/34F 其他/其他 昆山市花桥镇兆丰路11号", DateUtils.parseDate("2020-04-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-04-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "亚太广场", "高区/9F 其他/其他 昆山市花桥镇亚太广场", DateUtils.parseDate("2020-05-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-05-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "THE SHORE", "中区/25F 其他/其他 Jalan Tun Faud Stephens,Kota Kinabalu, Sabah", DateUtils.parseDate("2020-06-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-06-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", DateUtils.parseDate("2020-07-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-07-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", DateUtils.parseDate("2020-08-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-08-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", DateUtils.parseDate("2020-09-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-09-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "阜兴世纪公馆", "低区/33F 浦东/源深 向城路29号", DateUtils.parseDate("2020-01-11", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-11", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "芳草路商铺", "低区/1F 浦东/北蔡 芳草路", DateUtils.parseDate("2020-5-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-05-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "莲安西路125弄32支弄", "低区/6F 浦东/北蔡 莲安西路125弄32支弄", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "鹏海东苑", "低区/6F 浦东/北蔡 五星路239弄", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "车位", "低区/1F 其他/其他 车位", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "金杨七街坊(枣庄路998弄)", "低区/6F 浦东/金杨 枣庄路998弄", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "金杨七街坊(枣庄路998弄)", "低区/6F 浦东/金杨 枣庄路998弄", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "鸿禧会", "中区/4F 浦东/潍坊 南泉北路1054号(旧址崂山西路1054号)", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "康沈路1880号", "低区/1F 浦东/周浦 康沈路1880号", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "康沈路1880号", "低区/1F 浦东/周浦 康沈路1880号", DateUtils.parseDate("2020-08-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-08-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "康桥半岛密苏里假日公寓(秀沿路1177弄10支弄)", "高区/4F 浦东/康桥 秀沿路1177弄10支弄", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "康桥半岛密苏里假日公寓(秀沿路1177弄10支弄)", "高区/4F 浦东/康桥 秀沿路1177弄10支弄", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "泰州市义乌大市场", "中区/51F 其他/其他 泰州市高港区金港北路与春港路交汇处", DateUtils.parseDate("2020-08-05", "yyyy-MM-dd"), DateUtils.parseDate("2020-08-04", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "奥园印象高迪花园(佑林泛太)", "高区/9F 其他/其他 昆山市千灯镇沿沪产业带机场南路5A号", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "阿里巴巴电子商务大厦", "中区/26F 其他/其他 花安路171号", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "朗悦湾", "中区/11F 其他/其他 苏州相城区澄阳路2999号", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "朗悦湾", "中区/11F 其他/其他 苏州相城区澄阳路2999号", DateUtils.parseDate("2020-07-05", "yyyy-MM-dd"), DateUtils.parseDate("2020-07-05", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "鑫苑国际城市花园", "高区/29F 其他/其他 昆山市花桥镇绿地大道绿地大道189号", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "绿地青青家园", "低区/22F 其他/其他 绿地大道261号", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "金都安亭家园", "低区/9F 其他/其他 昆山市花桥镇金都安亭家园", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));
        productDocuments.add(new HouseDocument(i++, "金融家", "低区/8F 浦东/碧云 上丰路1483弄", DateUtils.parseDate("2020-01-01", "yyyy-MM-dd"), DateUtils.parseDate("2020-01-01", "yyyy-MM-dd")));

        // esSearchService.deleteIndex(Constants.INDEX_NAME);
        esHouseSearchService.save(productDocuments);

        // log.info("{}", JSON.toJSONString(esSearchService.getById(default_id), true));
        log.info("\n{}", JSON.toJSONString(esHouseSearchService.getById(default_id), true));

    }

    @Test
    public void getAll() {
        esHouseSearchService.getAll().parallelStream()
                .map(JSON::toJSONString)
                .forEach(System.out::println);
    }

    @Test
    public void deleteAll() {
        esHouseSearchService.deleteAll();
        log.info(" deleteAll end");
    }

    @Test
    public void getById() {
        log.info("{}", JSON.toJSONString(esHouseSearchService.getById(default_id), true));
    }

    @Test
    public void query() throws ParseException {
        HouseVo vo = new HouseVo();
        // vo.setId(1L);
        vo.setHouseName("阜兴世纪公馆");
        vo.setHouseTags("浦东");
        vo.setCreateTime(DateUtils.parseDate("2021-08-01", "yyyy-MM-dd"));
        vo.setUpdateTime(DateUtils.parseDate("2021-08-01", "yyyy-MM-dd"));
        List<HouseDocument> list = esHouseSearchService.query(vo);
        String json = JSON.toJSONStringWithDateFormat(list, "yyyy-MM-dd HH:mm:ss:SSS", SerializerFeature.WriteDateUseDateFormat);
        log.info("\n{}", json);
    }

    @Test
    public void queryByPage() throws ParseException {
        HouseVo vo = new HouseVo();
        // vo.setId(1L);
        // vo.setHouseName("阜兴世纪公馆");
        // vo.setHouseTags("浦东");
        // vo.setCreateTime(DateUtils.parseDate("2021-08-01", "yyyy-MM-dd"));
        // vo.setUpdateTime(DateUtils.parseDate("2021-08-01", "yyyy-MM-dd"));
        Page<HouseVo> page = esHouseSearchService.queryPage(0, 5, vo);
        log.info("\ntotalElements={},totalPages={},number={},numberOfElements={}", page.getTotalElements(), page.getTotalPages(), page.getNumber(), page.getNumberOfElements());
        for (HouseVo houseVo : page.getContent()) {
            log.info("\n{}", JSON.toJSONStringWithDateFormat(houseVo, "yyyy-MM-dd HH:mm:ss:SSS", SerializerFeature.WriteDateUseDateFormat));
        }
    }


    @Test
    public void deleteIndex() {
        esHouseSearchService.deleteIndex(Constants.INDEX_NAME);
        log.info(" deleteIndex ");
    }

}

