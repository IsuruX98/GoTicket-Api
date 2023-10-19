package com.csse.eticket.serviceImpl;

import com.csse.eticket.model.topup.FamilyTopUpAcc;
import com.csse.eticket.model.topup.TopUpAcc;
import org.springframework.stereotype.Service;

@Service
public class TopUpFactory {
    public TopUpAcc createTopUp(String type) {
        switch (type.toLowerCase()) {
            case "personal":
                return new TopUpAcc();
            case "family":
                return new FamilyTopUpAcc();
            default:
                throw new IllegalArgumentException("Invalid account type");
        }
    }
}
