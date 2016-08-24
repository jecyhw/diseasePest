package com.jecyhw.kech.repository;

import com.jecyhw.kech.bean.KechBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jecyhw on 16-8-22.
 */
public interface KechRepository extends MongoRepository<KechBean, String> {
}
