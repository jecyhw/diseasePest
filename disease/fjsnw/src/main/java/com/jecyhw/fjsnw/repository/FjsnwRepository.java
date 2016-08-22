package com.jecyhw.fjsnw.repository;

import com.jecyhw.fjsnw.bean.FjsnwBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jecyhw on 16-8-19.
 */
public interface FjsnwRepository  extends MongoRepository<FjsnwBean, String>{

}
