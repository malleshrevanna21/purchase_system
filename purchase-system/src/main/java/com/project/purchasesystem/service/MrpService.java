package com.project.purchasesystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.purchasesystem.entity.Mrp;
import com.project.purchasesystem.entity.User;
import com.project.purchasesystem.repository.MrpRepository;

@Service
public class MrpService {

    @Autowired
    private MrpRepository mrpRepository;

    // CREATE MRP
    public Mrp createMrp(Mrp mrp) {
        mrp.setStatus("CREATED");
        return mrpRepository.save(mrp);
    }

    // GET ALL MRP
    public List<Mrp> getAllMrp() {
        return mrpRepository.findAll();
    }
    
    public Mrp updateStock(Long id, int availableStock, User user) {

        Mrp mrp = mrpRepository.findById(id).orElse(null);

        if (mrp != null) {

            mrp.setAvailableStock(availableStock);
            mrp.setCheckedBy(user);

            int shortage = mrp.getRequiredQuantity() - availableStock;

            if (shortage <= 0) {
                mrp.setShortage(0);
                mrp.setStatus("COMPLETED");
            } else {
                mrp.setShortage(shortage);
                mrp.setStatus("STOCK_CHECKED");
            }

            return mrpRepository.save(mrp);
        }

        return null;
    }
    
    public Mrp generatePurchaseRequest(Long id, User user) {
        Mrp mrp = mrpRepository.findById(id).orElse(null);

        if (mrp != null) {
            mrp.setStatus("REQUEST_GENERATED");

            mrp.setRequestedBy(user);  // 🔥 ADD THIS

            return mrpRepository.save(mrp);
        }

        return null;
    }
    
    public Mrp approveRequest(Long id, boolean approved, User user) {
        Mrp mrp = mrpRepository.findById(id).orElse(null);

        if (mrp != null) {
            if (approved) {
                mrp.setStatus("APPROVED");
            } else {
                mrp.setStatus("REJECTED");
            }

            mrp.setApprovedBy(user);  // 🔥 ADD THIS

            return mrpRepository.save(mrp);
        }

        return null;
    }
}