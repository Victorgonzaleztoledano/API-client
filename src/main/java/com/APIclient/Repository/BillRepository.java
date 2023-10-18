package com.APIclient.Repository;

import com.APIclient.Domain.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByDni(String dni);
    List<Bill> findByMesAndAnyo(int mes, int anyo);
}