package com.jediap.infrastructure.elastic;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.mapping.IndexCoordinates;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.elasticsearch.index.query.QueryBuilders.termQuery;

@Service
public class ElasticsearchRepository {

  private final ElasticsearchRestTemplate elasticsearchRestTemplate;

  public ElasticsearchRepository(ElasticsearchRestTemplate elasticsearchRestTemplate) {
    this.elasticsearchRestTemplate = elasticsearchRestTemplate;
  }

  public <E> void save(E entityClass, String index) {
    IndexQuery indexQuery = new IndexQueryBuilder().withObject(entityClass).build();
    elasticsearchRestTemplate.index(indexQuery, IndexCoordinates.of(index));
  }

  public <E> List<E> find(final PageRequest pageRequest, final Class<E> targetVOClass) {
    return find(new HashMap<>(), pageRequest,targetVOClass);
  }

  public <E> List<E> find(final Map<String, Object> filters, final PageRequest pageRequest, final Class<E> targetVOClass) {
    BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
    filters
        .entrySet()
        .forEach(
            filter -> {
              boolQueryBuilder.must(termQuery(filter.getKey(), filter.getValue()));
            });

    Query searchQuery =
        new NativeSearchQueryBuilder()
            .withSourceFilter(new FetchSourceFilter(new String[0], new String[] {"metadata.*"}))
            .withFilter(boolQueryBuilder)
            .withQuery(boolQueryBuilder)
            // .addAggregation(aggregation)
            .withPageable(pageRequest)
            .build();

    SearchHits<E> result = elasticsearchRestTemplate.search(searchQuery, targetVOClass);

    List<E> list = new ArrayList<>();
    result.getSearchHits().stream().forEach(source -> {
      list.add(source.getContent());
    });
    return list;
  }
}
