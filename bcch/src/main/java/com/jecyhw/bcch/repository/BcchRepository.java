package com.jecyhw.bcch.repository;

import com.jecyhw.bcch.bean.BcchBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jecyhw on 16-8-22.
 */
public interface BcchRepository extends MongoRepository<BcchBean, String> {
}
