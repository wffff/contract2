package com.sncj.contract.repository;

import com.sncj.contract.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface IContractRepositoryCustom extends JpaRepository<ContractEntity, Integer>, JpaSpecificationExecutor<ContractEntity> {
}
