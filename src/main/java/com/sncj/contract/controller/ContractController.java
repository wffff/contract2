package com.sncj.contract.controller;

import com.sncj.contract.baseconfig.ReturnMessage;
import com.sncj.contract.entity.ContractEntity;
import com.sncj.contract.service.IContractService;
import org.springframework.data.domain.Page;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
@RestController
@RequestMapping("contract")
public class ContractController {
    @Resource
    private IContractService iContractService;

    @RequestMapping("page")
    @ResponseBody
    public ReturnMessage<List<ContractEntity>> page(@RequestParam(value = "page", defaultValue = "1") Integer page, @RequestParam(value = "limit", defaultValue = "20") Integer limit) {
        Page<ContractEntity> p = iContractService.page(page, limit);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }

    @RequestMapping("save")
    @ResponseBody
    public ReturnMessage<ContractEntity> save(String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, String payMethod, String title, String remarks) {
        ContractEntity c = iContractService.save(content, companyName, contactMan, phone, fax, saleman, amount, time, payMethod, title, remarks);
        if (c != null) {
            return ReturnMessage.success(0, c);
        } else {
            return ReturnMessage.failed("保存失败");
        }
    }

    @RequestMapping("update")
    @ResponseBody
    public ReturnMessage<ContractEntity> update(Integer id,String content, String companyName, String contactMan, String phone, String fax, String saleman, Double amount, @DateTimeFormat(pattern = "yyyy-MM-dd") Date time, String payMethod, String title, String remarks) {
        ContractEntity c = iContractService.update(id,content, companyName, contactMan, phone, fax, saleman, amount, time, payMethod, title, remarks);
        if (c != null) {
            return ReturnMessage.success(0, c);
        } else {
            return ReturnMessage.failed("修改失败");
        }
    }

    @RequestMapping("detail")
    @ResponseBody
    public ReturnMessage<ContractEntity> detail(Integer id) {
        ContractEntity c = iContractService.findById(id);
        return ReturnMessage.success(1, c);
    }

    @RequestMapping("delete")
    @ResponseBody
    public ReturnMessage delete(@RequestParam("id") List<Integer> ids) {
        Iterator it = ids.iterator();
        while (it.hasNext()) {
            Integer id = (Integer) it.next();
            iContractService.delete(id);
        }
        return ReturnMessage.success();
    }


   // @RequestMapping("edit")
   // @ResponseBody
    //public ReturnMessage<ContractEntity> edit(Integer id) {
     //   ContractEntity c = iContractService.findById(id);
     //   return ReturnMessage.success(1, c);
    //}

}
