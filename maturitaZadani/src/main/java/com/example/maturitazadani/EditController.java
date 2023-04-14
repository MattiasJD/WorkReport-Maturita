package com.example.maturitazadani;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

public class EditController {
    @FXML
    public DatePicker datePicker;
    @FXML
    public TextField toText;
    @FXML
    public TextField fromText;

    public void submitChanges(ActionEvent actionEvent) throws SQLException {
        LocalDate date = datePicker.getValue();
        LocalTime from = LocalTime.parse(fromText.getText());
        LocalTime to = LocalTime.parse(toText.getText());
        WorkReportDAO.addWorkReport(from, to, date);
    }
}
