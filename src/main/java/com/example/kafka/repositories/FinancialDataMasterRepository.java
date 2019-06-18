package com.example.kafka.repositories;

import com.example.kafka.models.FinancialDataMaster;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDataMasterRepository extends MongoRepository<FinancialDataMaster, Object> {


    FinancialDataMaster findFirstByNameOrderByAsOfDesc(final String name);
}
