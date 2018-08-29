package com.sncj.contract.service.impl;

import com.sncj.contract.entity.AttachmentEntity;
import com.sncj.contract.repository.IAttachmentRepository;
import com.sncj.contract.service.IAttachmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Override
    public AttachmentEntity update(Integer id, String type, String content, String url,Integer contractId) {
        AttachmentEntity attachmentEntity;
        if (null!=id){
            attachmentEntity = iAttachmentRepository.findById(id).get();
        }else {
            attachmentEntity=new AttachmentEntity();
        }
        attachmentEntity.setContent(content);
        attachmentEntity.setType(type);
        attachmentEntity.setUrl(url);
        attachmentEntity.setTime(new Date());
        attachmentEntity.setContractId(contractId);
        return iAttachmentRepository.save(attachmentEntity);
    }
}
