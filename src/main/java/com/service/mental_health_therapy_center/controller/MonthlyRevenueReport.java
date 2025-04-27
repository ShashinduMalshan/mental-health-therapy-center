package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.PatientBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.PaymentBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.PatientBo;
import com.service.mental_health_therapy_center.Bo.custom.PaymentBo;
import com.service.mental_health_therapy_center.dto.PaymentDto;
import com.service.mental_health_therapy_center.dto.PaymentHistory;
import com.service.mental_health_therapy_center.dto.PaymentTm;
import com.service.mental_health_therapy_center.entity.Payment;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class MonthlyRevenueReport implements Initializable {
    public AnchorPane revenueReportPane;
    public Label businessNameLabel;
    public Label businessAddressLabel;
    public Label businessContactLabel;
    public Label reportDateLabel;
    public Label totalRevenueLabel;
    public Label patientCountLabel;
    public Label avgRevenueLabel;
    public BarChart revenueChart;
    public TableView  <PaymentTm> revenueTable;
    public TableColumn <PaymentTm , String> programColumn;
    public TableColumn  <PaymentTm , String> patientCountColumn;
    public TableColumn  <PaymentTm , String> revenueColumn;
    public TableColumn  <PaymentTm , String> percentageColumn;
    public Label ytdRevenueLabel;
    public Label growthLabel;
    public Button printButton;
    public Button emailButton;
    public Button exportButton;
    public Button financialHistoryReport;
    public Button closeButton;
    public Label yearRevenue;
    PaymentBo paymentBo = new PaymentBoImpl();
    PatientBo patientBo = new PatientBoImpl();

 private void configureTable() {

     programColumn.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
     patientCountColumn.setCellValueFactory(new PropertyValueFactory<>("patientCount"));
     revenueColumn.setCellValueFactory(new PropertyValueFactory<>("totalAmount"));
//     percentageColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
 }

   private void LineChart() {
    XYChart.Series<String, Integer> seriesTwo = new XYChart.Series<>();
    seriesTwo.setName("Payment History by Month");

        List<PaymentHistory> paymentHistories = paymentBo.financialHistoryByMonth();

        for (PaymentHistory paymentHistory : paymentHistories) {
            String paymentDate = paymentHistory.getDate();
            int amount = paymentHistory.getAmount();
            System.out.println(paymentDate + " " + amount);
            seriesTwo.getData().add(new XYChart.Data<>(paymentDate, amount));
        }

        revenueChart.getData().add(seriesTwo);

}
    public void setLable() {

      patientCountLabel.setText(String.valueOf(patientBo.patentCount()));
        List<PaymentHistory> paymentHistories = paymentBo.financialHistoryByMonth();
        if (!paymentHistories.isEmpty()) {
            PaymentHistory lastPayment = paymentHistories.get(paymentHistories.size() - 1);
            int lastId = lastPayment.getAmount();
            totalRevenueLabel.setText(String.valueOf(lastId));
        }
        avgRevenueLabel.setText(String.valueOf(33.25));

    }

    public void loadTable() {
     int yearToDateRevenue = 0;
     List<PaymentTm> allPayment = paymentBo.getAllPayment();
        ObservableList<PaymentTm> paymentDtoObservableList = FXCollections.observableArrayList();

        for (PaymentTm payment : allPayment) {
            yearToDateRevenue=payment.getTotalAmount();
            paymentDtoObservableList.add(payment); // add one by one
        }

        yearRevenue.setText(String.valueOf(yearToDateRevenue));
        revenueTable.setItems(paymentDtoObservableList);


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

       LineChart();
       configureTable();
       loadTable();
       setLable();
    }

    public void printButtonAction(ActionEvent actionEvent) {
    }

    public void exportButtonAction(ActionEvent actionEvent) {
    }

    public void emailButtonAction(ActionEvent actionEvent) {
    }

    public void closeButtonAction(ActionEvent actionEvent) {
    }


}
