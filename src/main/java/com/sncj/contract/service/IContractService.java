package com.sncj.contract.service;

import com.sncj.contract.entity.ContractEntity;
import org.springframework.data.domain.Page;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IContractService {
    Page<ContractEntity> page(Integer page,Integer limit);
    ContractEntity save(String content,String companyName,Integer contactId,String phone,String fax,Integer salemanId,Double amount);
}
