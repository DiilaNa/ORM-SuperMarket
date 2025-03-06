module lk.ijse.gdse.supermarket {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires java.sql;
    requires com.jfoenix;
    requires net.sf.jasperreports.core;
    requires java.mail;
    requires mysql.connector.j;
    requires org.apache.commons.logging;

    requires org.hibernate.orm.core;
    requires jakarta.persistence;
    requires java.naming;
    requires modelmapper;

    opens lk.ijse.gdse.supermarket.entity to org.hibernate.orm.core, modelmapper;
    opens lk.ijse.gdse.supermarket.config to jakarta.persistence;
    opens lk.ijse.gdse.supermarket.dto to modelmapper;
    opens lk.ijse.gdse.supermarket.dto.tm to javafx.base;
    opens lk.ijse.gdse.supermarket.controller to javafx.fxml;
    exports lk.ijse.gdse.supermarket;
}