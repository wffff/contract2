package com.sncj.contract.controller;

import com.sncj.contract.baseconfig.ReturnMessage;
import com.sncj.contract.entity.ContractEntity;
import com.sncj.contract.service.IContractService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
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
    public ReturnMessage<List<ContractEntity>> page(@RequestParam(value="page", defaultValue="1") Integer page,@RequestParam(value="limit", defaultValue="20")  Integer limit) {
        Page<ContractEntity> p = iContractService.page(page, limit);
        return ReturnMessage.success((int) p.getTotalElements(), p.getContent());
    }
}
