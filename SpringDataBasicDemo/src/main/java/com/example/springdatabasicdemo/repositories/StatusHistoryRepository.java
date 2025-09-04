package com.example.springdatabasicdemo.repositories;

import com.example.springdatabasicdemo.models.StatusHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StatusHistoryRepository extends JpaRepository<StatusHistory, Integer> {
	List<StatusHistory> findAllStatusHistoriesByParcelId(int parcelId);
}
