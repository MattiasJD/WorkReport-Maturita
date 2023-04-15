package com.example.maturitazadani;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    @FXML
    public DatePicker datePicker;
    @FXML
    public TableView<WorkReport> tableView;
    @FXML
    public Label labelIDK;
    @FXML
    public TableColumn<WorkReport, Integer> idColumn;
    @FXML
    public TableColumn<WorkReport, LocalTime> fromColumn;
    @FXML
    public TableColumn<WorkReport, LocalTime> toColumn;
    @FXML
    public TableColumn<WorkReport, LocalDate> dateColumn;


    @FXML
    protected void onHelloButtonClick(ActionEvent actionEvent) throws SQLException, IOException {
        HelloApplication.edit();
    }

    public static int calculateHours() throws SQLException {
        int hours = 0;
        List<WorkReport> workReports = WorkReportDAO.getWorkReport();
        for (WorkReport workReport : workReports) {
            int from = (workReport.getFrom()).getHour();
            int to = (workReport.getTo()).getHour();
            hours = hours + (to - from);
        }
        return hours;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        List<WorkReport> workReportList;
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        fromColumn.setCellValueFactory(new PropertyValueFactory<>("from"));
        toColumn.setCellValueFactory(new PropertyValueFactory<>("to"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        try {
            workReportList = WorkReportDAO.getWorkReport();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        for (WorkReport workReport : workReportList) {
            tableView.getItems().addAll(workReport);
        }
        try {
            labelIDK.setText("You have worked for "+workReportList.size()+" days. And spent "+calculateHours()+" hours while doing so.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        datePicker.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);

                        if (!empty && item != null) {
                            if (workReportList.contains(item)) {
                                //////////// aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa
                                this.setStyle("-fx-background-color: red");
                            }
                        }
                    }
                };
            }
        })
    ;}
}