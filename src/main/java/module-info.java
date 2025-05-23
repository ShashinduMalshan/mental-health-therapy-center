module com.service.mental_health_therapy_center {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming; // Ensure this is included for Hibernate
    requires static lombok;
    requires jbcrypt;
    requires net.sf.jasperreports.core;

    opens com.service.mental_health_therapy_center to javafx.fxml;
    opens com.service.mental_health_therapy_center.entity to org.hibernate.orm.core;
    opens com.service.mental_health_therapy_center.dto to javafx.base;



    exports com.service.mental_health_therapy_center;
    exports com.service.mental_health_therapy_center.controller to javafx.fxml;
    opens com.service.mental_health_therapy_center.controller to javafx.fxml;

}
