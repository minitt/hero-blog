package net.minitt.hero.core.base;

import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface BaseElasticsearchDao<T> extends ElasticsearchCrudRepository<T, Integer> {
}
