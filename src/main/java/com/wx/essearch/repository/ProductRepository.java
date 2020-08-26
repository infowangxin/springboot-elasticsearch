package com.wx.essearch.repository;

import com.wx.essearch.document.ProductDocument;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

/**
 * @author vincent
 * @date 2018/12/13 17:35
 */
@Component
public interface ProductRepository extends ElasticsearchRepository<ProductDocument,Long> {

}
