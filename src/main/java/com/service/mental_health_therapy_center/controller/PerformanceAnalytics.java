package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.PatientBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.PaymentBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapistBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapySessionBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.PatientBo;
import com.service.mental_health_therapy_center.Bo.custom.PaymentBo;
import com.service.mental_health_therapy_center.Bo.custom.TherapistBo;
import com.service.mental_health_therapy_center.Bo.custom.TherapySessionBo;
import com.service.mental_health_therapy_center.dto.DateDto;
import com.service.mental_health_therapy_center.dto.PaymentHistory;
import com.service.mental_health_therapy_center.dto.TopRateTherapistDto;
import javafx.fxml.Initializable;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class PerformanceAnalytics implements Initializable {
    public Label TotalPatient;
    public LineChart lineChart;
    public Label totalTheraoist;
    public AreaChart AreaChart;
    public BarChart barchart;
    public Button financialHistoryReport;

    PatientBo patientBo = new PatientBoImpl();
    TherapistBo therapistBo = new TherapistBoImpl();
    TherapySessionBo therapySessionBo = new TherapySessionBoImpl();
    PaymentBo paymentBo = new PaymentBoImpl();

    public void setLabel(){
        TotalPatient.setText(String.valueOf(patientBo.patentCount()));
        totalTheraoist.setText(String.valueOf(therapistBo.therapistCount()));
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

        lineChart.getData().add(seriesTwo);

}

   private void addDataAreaChart() throws SQLException {



          XYChart.Series<String, Integer> seriesTwo = new XYChart.Series<>();


        List<TopRateTherapistDto> topRateTherapist = therapySessionBo.topRateTherapist();


        for (TopRateTherapistDto therapistDto : topRateTherapist) {

            String therapistId = therapistDto.getSessionId();
            int count = therapistDto.getCount();

            seriesTwo.getData().add(new XYChart.Data<>(therapistId, count));
        }
        AreaChart.getData().add(seriesTwo);

    }
    
    private void BarChart() throws SQLException {

        XYChart.Series<String, Number> series = new XYChart.Series<>();

        List<DateDto> allMostChosenTherapyProgram = therapySessionBo.MostChosenTherapyProgram();
        for (DateDto dateDto : allMostChosenTherapyProgram) {
            String month = dateDto.getMonth();
            int count = dateDto.getCount();

            series.getData().add(new XYChart.Data<>(month, count));
        }
        barchart.getData().add(series);



    }
    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setLabel();
        try {
            addDataAreaChart();
            BarChart();
            LineChart();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void financialHistoryReportOnAction(MouseEvent mouseEvent) {


    }
}
