package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.dto.InvoiceDto;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class InvoiceController implements Initializable {

    public Label businessNameLabel;
    public Label businessAddressLabel;
    public Label businessContactLabel;
    public Label invoiceNumberLabel;
    public Label referenceLabel;
    public Label issueDateLabel;
    public Label dueDateLabel;
    public Label paymentIdLabel;
    public Label patientNameLabel;
    public Label therapyProgramLabel;
    public Label programIdLabel;
    public Label totalDueAmountLabel;
    public Label subtotalLabel;
    public Label amountPaidLabel;
    public Label balanceDueLabel;
    public Label totalLabel;
    public Button printButton;
    public Button emailButton;
    public Button saveButton;
    public Button closeButton;
    public Label therapistIdLable;

    public void setValue(InvoiceDto invoiceDto) {
    String amountPaid = String.valueOf(invoiceDto.getAmount());
    String balanceDue = String.valueOf(invoiceDto.getBalanceDue());
    String invoiceNumber = "INV-2024-001";
    String reference = "Payment for Order #456";
    String issueDate = LocalDate.now().toString();
    String dueDate = "2026-05-10";
    String paymentId = "PAY-789654";

    amountPaidLabel.setText("Rs. "+amountPaid);
    balanceDueLabel.setText("Rs. "+balanceDue);
    invoiceNumberLabel.setText(invoiceNumber);
    referenceLabel.setText(reference);
    issueDateLabel.setText(issueDate);
    dueDateLabel.setText(dueDate);
    paymentIdLabel.setText(paymentId);
    patientNameLabel.setText(invoiceDto.getPatientName());
    programIdLabel.setText(invoiceDto.getProgramId());
    therapyProgramLabel.setText(invoiceDto.getProgramName());
    therapistIdLable.setText(invoiceDto.getTherapistName());
    totalDueAmountLabel.setText("Rs. "+String.valueOf(invoiceDto.getDueAmount()));

    
}


    public void printButtonAction(ActionEvent actionEvent) {
    }

    public void saveButtonAction(ActionEvent actionEvent) {
    }

    public void emailButtonAction(ActionEvent actionEvent) {
    }

    public void closeButtonAction(ActionEvent actionEvent) {

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
