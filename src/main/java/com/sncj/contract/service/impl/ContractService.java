package com.sncj.contract.service.impl;

import com.sncj.contract.entity.ContractEntity;
import com.sncj.contract.repository.IContractRepository;
import com.sncj.contract.service.IContractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

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
    public ContractEntity save(String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, Date time, String payMethod, String title, String remarks) {
        ContractEntity c=new ContractEntity();
        c.setContent(content);
        c.setCompanyName(companyName);
        c.setContactMan(contactMan);
        c.setPhone(phone);
        c.setFax(fax);
        c.setSaleman(saleman);
        c.setAmount(amount);
        //c.setTime(new Date());
        c.setTime(time);
        c.setPayMethod(payMethod);
        c.setTitle(title);
        c.setRemarks(remarks);

        return iContractRepository.save(c);
    }

    @Override
    public ContractEntity update(Integer id, String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, Date time, String payMethod, String title, String remarks) {
        ContractEntity c;
        if (id==null){
            c=new ContractEntity();
        }else {
            c = iContractRepository.findById(id).get();
        }
        c.setContent(content);
        c.setCompanyName(companyName);
        c.setContactMan(contactMan);
        c.setPhone(phone);
        c.setFax(fax);
        c.setSaleman(saleman);
        c.setAmount(amount);
        //c.setTime(new Date());

        c.setTime(time);
        c.setPayMethod(payMethod);
        c.setTitle(title);
        c.setRemarks(remarks);

        return iContractRepository.save(c);
    }

    @Override
    public ContractEntity findById(Integer id) {
        Optional<ContractEntity> c = iContractRepository.findById(id);
        return c.get();
    }

    public void delete(Integer id)
    {
        iContractRepository.deleteById(id);
    }

    @Override
    public Page<ContractEntity> page(Integer page, Integer limit, String name) {
        ContractEntity c=new ContractEntity();
        c.setContactMan(name);
        return iContractRepository.findAll(Example.of(c),PageRequest.of(page-1,limit));
    }
}
