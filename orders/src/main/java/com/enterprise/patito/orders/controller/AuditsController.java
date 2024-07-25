package com.enterprise.patito.orders.controller;

import com.enterprise.patito.orders.entity.Audits;
import com.enterprise.patito.orders.services.AuditsServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/audits")
public class AuditsController {
    @Autowired
    private AuditsServices auditService;

    @GetMapping
    public ResponseEntity<List<Audits>> getAllAudits() {
        List<Audits> audits = auditService.getAllAudits();
        return ResponseEntity.ok(audits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Audits> getAuditById(@PathVariable Long id) {
        Audits audit = auditService.getAuditById(id);
        return audit != null ? ResponseEntity.ok(audit) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Audits> createAudit(@RequestBody Audits audit) {
        Audits createdAudit = auditService.createAudit(audit);
        return ResponseEntity.ok(createdAudit);
    }
}
