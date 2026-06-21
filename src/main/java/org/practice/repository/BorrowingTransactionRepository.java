package org.practice.repository;

import org.practice.entity.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction,Long> {
}
