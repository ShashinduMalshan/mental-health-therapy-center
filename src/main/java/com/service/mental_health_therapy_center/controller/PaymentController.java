package com.service.mental_health_therapy_center.controller;

import com.service.mental_health_therapy_center.Bo.custom.Impl.PaymentBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.Impl.TherapySessionBoImpl;
import com.service.mental_health_therapy_center.Bo.custom.PaymentBo;
import com.service.mental_health_therapy_center.Bo.custom.TherapySessionBo;
import com.service.mental_health_therapy_center.Exceptions.PaymentProcessingException;
import com.service.mental_health_therapy_center.Exceptions.SchedulingConflictException;
import com.service.mental_health_therapy_center.dto.*;
import com.service.mental_health_therapy_center.entity.Patient;
import com.service.mental_health_therapy_center.entity.Therapist;
import com.service.mental_health_therapy_center.entity.TherapyProgram;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class PaymentController implements Initializable {

    public AnchorPane therapyBookingPane;
    public ComboBox <String> patientComboBox;
    public TextField patientIdField;
    public DatePicker sessionDatePicker;
    public Spinner hourSpinner;
    public Spinner minuteSpinner;
    public Button addSessionButton;
    public Label totalCostLabel;
    public TableView <CartTM> sessionsTableView;
    public TableColumn <CartTM , String>dateColumn;
    public TableColumn <CartTM , String> programColumn;
    public TableColumn <CartTM , String> timeColumn;
    public TableColumn <CartTM , String> durationColumn;
    public TableColumn <CartTM , String> therapistColumn;
    public TableColumn  <CartTM , String>costColumn;
    public TableColumn <CartTM , String> actionsColumn;
    public Button clearAllButton;
    public Button bookPayButton;
    public Button pendingButton;
    public ComboBox <String>  planComboBox;
    public TextField planIdField;
    public Label therapist;
    public TextField therapistNameField;
    public TextField PlanCostField;
    public TextField AmountField;
    public TextField dueAmountFeild;
    public TextField balnceField;
    public Button checkButton;

    Map<String, String> patientMap = new HashMap<>();
    Map<String, String> programMap = new HashMap<>();
    Map<String, String> therapistMap = new HashMap<>();
    Map<String, Double> programCostMap = new HashMap<>();
    PaymentBo paymentBo = new PaymentBoImpl();
    TherapySessionBo therapySessionBo = new TherapySessionBoImpl();
    private final ObservableList<CartTM> cartTMS = FXCollections.observableArrayList();
    double enteredAmount = 0;
    double payment = 0;


    
    private void configureTable() {
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        programColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapyProgram().getProGramName()));
        timeColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getStartTime() + " - " + cellData.getValue().getEndTime()));
        durationColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapyProgram().getDuration()));
        therapistColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getTherapist().getName()));
        costColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getTherapyProgram().getCost()).asObject().asString());
        actionsColumn.setCellValueFactory(new PropertyValueFactory<>("removeBtn"));

        sessionsTableView.setItems(cartTMS);

    }

    public void addSessionButtonAction(ActionEvent actionEvent) {
        String patientId = patientIdField.getText();
        String patientName = patientComboBox.getValue();
        String program = planComboBox.getValue();
        String programId =  planIdField.getText();
        LocalDate date = sessionDatePicker.getValue();
        int hour = (int) hourSpinner.getValue();
        int minute = (int) minuteSpinner.getValue();

        LocalTime startTime = LocalTime.of(hour, minute);
        LocalTime endTime = startTime.plusHours(1);

        if (!cartTMS.isEmpty()) {
            if (cartTMS.stream().noneMatch(cart ->
                    cart.getPatient().getName().equals(patientName) &&
                            cart.getTherapyProgram().getProGramName().equals(program))) {

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                        "Do you want to change therapy plan? Changing plan will clear all sessions.",
                        ButtonType.OK, ButtonType.CANCEL);
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    cartTMS.clear();
                    return;
                } else {
                    return;
                }
            }
        }
        patientComboBox.setStyle(patientComboBox.getStyle() + ";-fx-border-color: #7367F0;");
        sessionDatePicker.setStyle(sessionDatePicker.getStyle() + ";-fx-border-color: #7367F0;");
        planComboBox.setStyle(planComboBox.getStyle() + ";-fx-border-color: #7367F0;");


            if (patientName == null) {
                patientComboBox.setStyle("-fx-border-color: red;");
                new Alert(Alert.AlertType.ERROR, "Please fill all the fields!").show();
            return;
            }
            if (program == null) {
                planComboBox.setStyle("-fx-border-color: red;");
                new Alert(Alert.AlertType.ERROR, "Please fill all the fields!").show();
            return;
            }
            if (date == null) {
                sessionDatePicker.setStyle("-fx-border-color: red;");
                new Alert(Alert.AlertType.ERROR, "Please fill all the fields!").show();
            return;
            }


            boolean isValidDateAndTime = true;

            ArrayList<TherapySessionDto> therapySessionDtos = therapySessionBo.loadTable();

            try {
                for (TherapySessionDto session : therapySessionDtos) {
                    if (session.getDate().isEqual(date)) {
                        boolean overlaps = !(endTime.isBefore(session.getStartTime()) ||
                                startTime.isAfter(session.getEndTime()));

                        if (overlaps) {
                            isValidDateAndTime = false;
                            throw new SchedulingConflictException("This time slot is already booked. Please select a different time.");
                        }
                    }
                }
            } catch (SchedulingConflictException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                return;
            }


        try {
                for (CartTM session : cartTMS) {
                    if (session.getDate().isEqual(date)) {
                        boolean overlaps = !(endTime.isBefore(session.getStartTime()) ||
                                startTime.isAfter(session.getEndTime()));

                        if (overlaps) {
                            isValidDateAndTime = false;
                            throw new SchedulingConflictException("This time slot is already added. Please select a different time.");
                        }
                    }
                }
            } catch (SchedulingConflictException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
                return;
            }









        List<TherapyProgram> valueByName = paymentBo.getValueByName(program);
            if (valueByName.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Therapy program not found!").show();
                return;
            }
            TherapyProgram therapyProgram = valueByName.get(0);

            List<Patient> valueById = paymentBo.getValueById(patientId);
            if (valueById.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "Patient not found!").show();
                return;
            }
            Patient patient = valueById.get(0);


            List<Therapist> TherapistValue = paymentBo.getValueByTherapyProgram(therapyProgram);

            if (TherapistValue.isEmpty()) {
                new Alert(Alert.AlertType.ERROR, "No therapists available for the selected therapy program!").show();
                return;
            }
            Therapist therapist = TherapistValue.get(0);


//            boolean isAlreadyAdded = cartTMS.stream()
//                    .anyMatch(cart -> cart.getSessionId().equals(sessionID));
//
//            if (isAlreadyAdded) {
//                new Alert(Alert.AlertType.WARNING, "This session already exists.").show();
//                return;
//            }
    
            double dueAmount =therapyProgram.getCost() - paymentBo.getalreadyPaid(programId, patientId);
            dueAmountFeild.setText(String.valueOf(dueAmount));

            boolean isValidAmount = AmountField.getText().matches("^\\d*\\.?\\d+$");

            if (!isValidAmount) {
                new Alert(Alert.AlertType.ERROR, "Amount entered is invalid.").show();
                AmountField.setStyle("-fx-border-color: red;");
                return;
            }
        AmountField.setStyle(AmountField.getStyle() + ";-fx-border-color: #7367F0;");
        enteredAmount = Double.parseDouble(AmountField.getText());

            if (enteredAmount > dueAmount) {
                payment = dueAmount;
                balnceField.setText(String.valueOf(enteredAmount-dueAmount));
            }else {
                payment = enteredAmount;
                balnceField.setText(String.valueOf(0));
            }


        Button btn = new Button("Remove");

                    CartTM newCartTM = new CartTM(
                        "sessionID",
                        patient,
                        therapist,
                        therapyProgram,
                        date,
                        startTime,
                        endTime,
                        btn
                    );

                    btn.setOnAction(event -> {
                        cartTMS.remove(newCartTM);
                        sessionsTableView.refresh();
                    });
                    cartTMS.add(newCartTM);




        }

    public void clearAllButtonAction(ActionEvent actionEvent) {
        sessionsTableView.getItems().clear();
    }

    public void pendingButtonAction(ActionEvent actionEvent) {

        String paymentId = paymentBo.getNextId();
        String patientId = patientIdField.getText();
        String program = planComboBox.getValue();
        LocalDate date = sessionDatePicker.getValue();
        int hour = (int) hourSpinner.getValue();
        int minute = (int) minuteSpinner.getValue();

        LocalTime startTime = LocalTime.of(hour, minute);
        LocalTime endTime = startTime.plusHours(1);


        if (cartTMS.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add at least one session!").show();
            return;
        }
        List<TherapyProgram> valueByName = paymentBo.getValueByName(program);

        if (valueByName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Therapy program not found!").show();
            return;
        }
        TherapyProgram therapyProgram = valueByName.get(0);

        List<Patient> valueById = paymentBo.getValueById(patientId);
        if (valueById.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Patient not found!").show();
            return;
        }
        Patient patient = valueById.get(0);


        List<Therapist> TherapistValue = paymentBo.getValueByTherapyProgram(therapyProgram);

        if (TherapistValue.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "No therapists available for the selected therapy program!").show();
            return;
        }
        Therapist therapist = TherapistValue.get(0);



        PaymentDto paymentDto = new PaymentDto(
                paymentId,
                therapyProgram.getCost(),
                date,
                "Paid",
                patient,
                therapyProgram
        );


            ArrayList<SessionPaymentDto> therapySessionDtos = new ArrayList<>();

            for (CartTM cartTM : cartTMS) {
                SessionPaymentDto sessionPaymentDto = new SessionPaymentDto(
                        "S001",
                        cartTM.getDate(),
                        cartTM.getStartTime(),
                        cartTM.getEndTime(),
                        cartTM.getPatient(),
                        cartTM.getTherapyProgram(),
                        cartTM.getTherapist(),
                        "Pending",
                        null
                );
                therapySessionDtos.add(sessionPaymentDto);
            }


            cartTMS.clear();
            sessionsTableView.refresh();


        boolean isSave = paymentBo.save(paymentDto, therapySessionDtos);
        if (isSave) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION,
                    "Payment marked as pending. You can complete the payment at your next session.",
                    ButtonType.OK);
            alert.setHeaderText("Session Booked Successfully");
            alert.showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Booking failed! Please try again.").show();
        }
        clearField();

    }

    public void bookPayButtonAction(ActionEvent actionEvent) {

        String paymentId = paymentBo.getNextId();
        String patientId = patientIdField.getText();
        String program = planComboBox.getValue();
        LocalDate date = sessionDatePicker.getValue();
        int hour = (int) hourSpinner.getValue();
        int minute = (int) minuteSpinner.getValue();

        LocalTime startTime = LocalTime.of(hour, minute);
        LocalTime endTime = startTime.plusHours(1);


        if (cartTMS.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Please add at least one session!").show();
            return;
        }
        List<TherapyProgram> valueByName = paymentBo.getValueByName(program);
        if (valueByName.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Therapy program not found!").show();
            return;
        }
        TherapyProgram therapyProgram = valueByName.get(0);

        List<Patient> valueById = paymentBo.getValueById(patientId);
        if (valueById.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "Patient not found!").show();
            return;
        }
        Patient patient = valueById.get(0);


        List<Therapist> TherapistValue = paymentBo.getValueByTherapyProgram(therapyProgram);

        if (TherapistValue.isEmpty()) {
            new Alert(Alert.AlertType.ERROR, "No therapists available for the selected therapy program!").show();
            return;
        }
        Therapist therapist = TherapistValue.get(0);



        PaymentDto paymentDto = new PaymentDto(
                paymentId,
                payment,
                date,
                "Paid",
                patient,
                therapyProgram
        );


            ArrayList<SessionPaymentDto> therapySessionDtos = new ArrayList<>();

            for (CartTM cartTM : cartTMS) {
                SessionPaymentDto sessionPaymentDto = new SessionPaymentDto(
                        "S001",
                        cartTM.getDate(),
                        cartTM.getStartTime(),
                        cartTM.getEndTime(),
                        cartTM.getPatient(),
                        cartTM.getTherapyProgram(),
                        cartTM.getTherapist(),
                        "Paid",
                        null
                );
                therapySessionDtos.add(sessionPaymentDto);
            }


            cartTMS.clear();
            sessionsTableView.refresh();


        boolean isSave = paymentBo.save(paymentDto, therapySessionDtos);
        if (isSave) {
            new Alert(Alert.AlertType.INFORMATION, "Payment completed successfully!").show();
        } else {
            throw new PaymentProcessingException("Payment failed!");
            }
            clearField();


    }

    public void loadPrograms() {

        ObservableList<TherapistDto> programs = paymentBo.loadTherapist();
        ObservableList<String> programDisplayStrings = FXCollections.observableArrayList();

        for (TherapistDto program : programs) {

            String id = program.getTherapyProgram().getId();
            String therapistName = program.getName();
            String name = program.getTherapyProgram().getProGramName();
            double cost = program.getTherapyProgram().getCost();
            programDisplayStrings.add(name);

            programMap.put(name, id);
            therapistMap.put(name, therapistName);
            programCostMap.put(name, cost);

        }
        planComboBox.setItems(programDisplayStrings);

    }


    public void loadPatient() {

        ObservableList<PatientTm> patient = paymentBo.loadTherapyPatient();

        ObservableList<String> displayList = FXCollections.observableArrayList();

        for (PatientTm patientTm : patient) {
            String id = patientTm.getId();
            String name = patientTm.getName();

            displayList.add(name);
            patientMap.put(name, id);
        }

        patientComboBox.setItems(displayList);
    }

    public void clearField() {
        sessionDatePicker.setValue(null);
        patientComboBox.setValue(null);
        planComboBox.setValue(null);
        therapistNameField.clear();
        patientIdField.clear();
        planIdField.clear();
        AmountField.clear();
        dueAmountFeild.clear();
        balnceField.clear();
     }

    public void refresh() {

        SpinnerValueFactory<Integer> hourFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(9, 22, 9);
        hourSpinner.setValueFactory(hourFactory);

        SpinnerValueFactory<Integer> minuteFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0, 5);
        minuteSpinner.setValueFactory(minuteFactory);

        sessionDatePicker.setDayCellFactory(picker -> new DateCell() {
            @Override
            public void updateItem(LocalDate date, boolean empty) {
                super.updateItem(date, empty);
                setDisable(empty || date.isBefore(LocalDate.now()));
            }
        });

        patientComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                patientIdField.setText(patientMap.get(newValue));
            }
        });

        planComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                planIdField.setText(programMap.get(newValue));
            }
        });

        planComboBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                therapistNameField.setText(therapistMap.get(newValue));
                PlanCostField.setText(String.valueOf(programCostMap.get(newValue)));
            }else {therapistNameField.clear();}
        });



        loadPrograms();
        loadPatient();

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        refresh();
        configureTable();


    }

    public void checkButtonAction(ActionEvent actionEvent) {
    }
}
