package com.sncj.contract.repository;

import com.sncj.contract.entity.AttachmentEntity;
import com.sncj.contract.entity.ContractEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Danny on 2018/8/21.
 */
public interface IAttachmentRepository extends JpaRepository<AttachmentEntity, Integer> {
    List<AttachmentEntity> findAllByContractIdAndDelFalse(Integer contractId);
}
