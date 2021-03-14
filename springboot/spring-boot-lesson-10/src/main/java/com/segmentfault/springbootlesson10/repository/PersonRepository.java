package com.segmentfault.springbootlesson10.repository;

import com.segmentfault.springbootlesson10.entity.Person;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;

/**
 * 人员 仓库
 *
 * @author <a href="mailto:mercyblitz@gmail.com">Mercy</a>
 * @see
 * @since 2017.08.04
 */
@NoRepositoryBean
public interface PersonRepository extends Repository {


    @Cacheable(cacheNames = "persons")
    Person findPerson(String id);


    //    @CachePut(cacheNames = "persons")
    boolean savePerson(Person person);


}
