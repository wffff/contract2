package com.sncj.contract.service.impl;

import com.sncj.contract.entity.ContractEntity;
import com.sncj.contract.repository.IContractRepository;
import com.sncj.contract.repository.IContractRepositoryCustom;
import com.sncj.contract.service.IContractService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * Created by Danny on 2018/8/21.
 */
@Service
public class ContractService implements IContractService {

    @Resource
    private IContractRepository iContractRepository;
    @Resource
    private IContractRepositoryCustom iContractRepositoryCustom;

    @Override
    public Page<ContractEntity> page(Integer page, Integer limit,String companyName ,String title) {
        Specification<ContractEntity> specification = (Specification<ContractEntity>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();//List<Predicate> 条件集合；
            if (companyName!=null&&companyName!=""){
                predicates.add(cb.like(root.get("companyName"), "%"+companyName.trim()+"%"));//往条件集合中添加条件，companyName.trim()是去掉输入companyName时带有的空格
            }
            if (title!=null&&title!=""){
                predicates.add(cb.like(root.get("title"), "%"+title.trim()+"%"));//往条件集合中添加条件，companyName.trim()是去掉输入companyName时带有的空格
            }
            predicates.add(cb.equal(root.get("del"), false));
            return cb.and(predicates.toArray(new Predicate[predicates.size()]));
        };
       return iContractRepositoryCustom.findAll(specification, PageRequest.of(page - 1, limit));
    }

    @Override
    public ContractEntity save(String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, Date time, String payMethod, String title, String remarks) {
        ContractEntity c = new ContractEntity();
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
        if (id == null) {
            c = new ContractEntity();
        } else {
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

    public void delete(Integer id) {
        ContractEntity c;
        c = iContractRepository.findById(id).get();
        c.setDel(true);
        iContractRepository.save(c);
    }

   /* @Override
    public List<ContractEntity> findAllByNameLike(String key) {
        return iContractRepository.findAllByCompanyNameLike("%" + key + "%");
    }
*/
}
