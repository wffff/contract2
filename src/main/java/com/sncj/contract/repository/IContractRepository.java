package com.sncj.contract.repository;

import com.sncj.contract.entity.ContractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IContractRepository extends JpaRepository<ContractEntity, Integer> {
    //List<ContractEntity> findAllByCompanyNameLike(String s);


    //@Query("select c from ContractEntity c where c.del=false ")
    //Page<ContractEntity> findAll(Pageable pageable);

    //Page<ContractEntity> findAllByDelFalse(Pageable pageable);
}
