package com.sii.java.codingtask.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferHistoryRepository extends JpaRepository<TransferHistoryEntity, Long> {

}
