package com.lntu.digitalhuman.repository;

import com.lntu.digitalhuman.entity.HumanData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface HumanRepository extends MongoRepository<HumanData, String> { }