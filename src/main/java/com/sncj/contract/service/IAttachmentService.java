package com.sncj.contract.service;

import com.sncj.contract.entity.AttachmentEntity;

import java.util.List;

/**
 * Created by Danny on 2018-08-29.
 */
public interface IAttachmentService {
    List<AttachmentEntity> findAllByContractId(Integer contractId);
}
