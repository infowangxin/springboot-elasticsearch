package com.wx.essearch.repository;

import com.wx.entity.document.HouseDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author vincent
 * @date 2018/12/13 17:35
 */
@Component
public interface HouseRepository extends ElasticsearchRepository<HouseDocument,Long> {

}
