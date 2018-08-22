package com.sncj.contract.repository;

import com.sncj.contract.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IContractRepository extends JpaRepository<ContractEntity, Integer> {
}
