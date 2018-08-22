package com.sncj.contract.service.impl;

import com.sncj.contract.entity.ContractEntity;
import com.sncj.contract.repository.IContractRepository;
import com.sncj.contract.service.IContractService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by Danny on 2018/8/21.
 */
@Service
public class ContractService implements IContractService {

    @Resource
    private IContractRepository iContractRepository;

    @Override
    public Page<ContractEntity> page(Integer page, Integer limit) {
        return iContractRepository.findAll(PageRequest.of(page-1,limit));
    }

    @Override
    public ContractEntity save(String content, String companyName, Integer contactId, String phone, String fax, Integer salemanId, Double amount) {
        ContractEntity c=new ContractEntity();
        c.setContent(content);
        c.setCompanyName(companyName);
        c.setContactId(contactId);
        c.setPhone(phone);
        c.setFax(fax);
        c.setSalemanId(salemanId);
        c.setAmount(amount);
        c.setTime(new Date());
        return iContractRepository.save(c);
    }
}
