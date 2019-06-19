package com.example.kafka.repositories;

import com.example.kafka.models.FinancialData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FinancialDataRepository extends MongoRepository<FinancialData, Object> {

    void deleteAllByAsOfGreaterThanEqual(String asOf);

    void deleteAllByAsOfBetween(String asOf,String asOf2);

}
