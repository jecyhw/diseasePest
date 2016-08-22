package com.jecyhw.zzbch.repository;

import com.jecyhw.zzbch.bean.ZzbchBean;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by jecyhw on 16-8-19.
 */
public interface ZzbchRepository  extends MongoRepository<ZzbchBean, String>{

}
