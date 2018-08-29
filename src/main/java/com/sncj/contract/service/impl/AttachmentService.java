package com.sncj.contract.service.impl;

import com.sncj.contract.entity.AttachmentEntity;
import com.sncj.contract.repository.IAttachmentRepository;
import com.sncj.contract.service.IAttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Danny on 2018-08-29.
 */
@Service
public class AttachmentService implements IAttachmentService {
    @Resource
    private IAttachmentRepository iAttachmentRepository;
    @Override
    public List<AttachmentEntity> findAllByContractId(Integer contractId) {
        return iAttachmentRepository.findAllByContractId(contractId);
    }
}