package com.example.maturitazadani;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    @FXML
    public DatePicker datePicker;
    @FXML
    public TextField toText;
    @FXML
    public TextField fromText;
    @FXML
    public ChoiceBox<WorkReport> choiceBox;
    @FXML
    public TextField toTextEdit;
    @FXML
    public TextField fromTextEdit;
    @FXML
    public DatePicker pickedDate;

    public void submitChanges(ActionEvent actionEvent) throws SQLException {
        WorkReport workReport = new WorkReport();
        workReport.setDate(datePicker.getValue());
        workReport.setFrom(LocalTime.parse(fromText.getText()));
        workReport.setTo(LocalTime.parse(toText.getText()));
        WorkReportDAO.addWorkReport(workReport);
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void submitEdit(ActionEvent actionEvent) throws SQLException {
        WorkReport workReport = new WorkReport();
        workReport.setId(choiceBox.getValue().getId());
        workReport.setFrom(LocalTime.parse(fromTextEdit.getText()));
        workReport.setTo(LocalTime.parse(toTextEdit.getText()));
        System.out.println(toTextEdit.getText());
        workReport.setDate(pickedDate.getValue());
        WorkReportDAO.editWorkReport(workReport);
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    public void submitDelete(ActionEvent actionEvent) {
        WorkReport workReport = new WorkReport();
        workReport.setId(choiceBox.getValue().getId());
        try {
            WorkReportDAO.deleteWorkReport(workReport);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            choiceBox.getItems().addAll(WorkReportDAO.getWorkReport());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
