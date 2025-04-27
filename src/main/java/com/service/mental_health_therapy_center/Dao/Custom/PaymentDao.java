package com.service.mental_health_therapy_center.Dao.Custom;

import com.service.mental_health_therapy_center.Dao.CrudDao;
import com.service.mental_health_therapy_center.dto.PaymentHistory;
import com.service.mental_health_therapy_center.dto.PaymentTm;
import com.service.mental_health_therapy_center.entity.Payment;
import org.hibernate.Session;

import java.util.List;

public interface PaymentDao extends CrudDao<Payment> {

    List<Payment> getAll(String therapyProgramId, String name);
    List<PaymentHistory> financialHistoryByMonth();
    boolean savePayment(Session session, Payment payment);
    List<PaymentTm> getAllPatentCount();

}
