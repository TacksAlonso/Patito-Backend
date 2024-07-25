package com.enterprise.patito.orders.services;

import com.enterprise.patito.orders.entity.Audits;
import com.enterprise.patito.orders.repository.AuditsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditsServices {
    @Autowired
    private AuditsRepository auditRepository;

    public List<Audits> getAllAudits() {
        return auditRepository.findAll();
    }

    public Audits getAuditById(Long id) {
        return auditRepository.findById(id).orElse(null);
    }

    public Audits createAudit(Audits audit) {
        return auditRepository.save(audit);
    }

}
