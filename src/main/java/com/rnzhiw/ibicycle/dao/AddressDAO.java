package com.rnzhiw.ibicycle.dao;

import com.rnzhiw.ibicycle.model.domain.TAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressDAO extends JpaRepository<TAddress, Long> {
}
