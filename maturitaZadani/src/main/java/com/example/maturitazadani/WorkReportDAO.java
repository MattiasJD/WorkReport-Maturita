package com.example.maturitazadani;
import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkReportDAO {
    private static final Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://localhost/maturita", "root", "");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public WorkReportDAO() throws SQLException {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM vykazy");
        while(resultSet.next()){
            System.out.println(resultSet.getString("timeFrom"));
        }
    }
    public static void addWorkReport(LocalTime from, LocalTime to, LocalDate date) throws SQLException {
        WorkReport workReport = new WorkReport();
        workReport.setDate(date);
        workReport.setFrom(from);
        workReport.setTo(to);
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `vykazy` (`timeFrom`, `timeTo`, `date`) VALUES (? , ? , ?);");
        preparedStatement.setTime(1, Time.valueOf(workReport.getFrom()));
        preparedStatement.setTime(2, Time.valueOf(workReport.getTo()));
        preparedStatement.setDate(3, Date.valueOf(workReport.getDate()));
        preparedStatement.execute();
    }
    public static List <WorkReport> getWorkReport() throws SQLException{
        List<WorkReport> workReports = new ArrayList<>();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM vykazy");
        while (resultSet.next()){
            WorkReport workReport = new WorkReport();
            workReport.setId(resultSet.getInt("id"));
            workReport.setFrom(resultSet.getTime("timeFrom").toLocalTime());
            workReport.setTo(resultSet.getTime("timeTo").toLocalTime());
            workReport.setDate(resultSet.getDate("date").toLocalDate());
            workReports.add(workReport);
        }
        return workReports;
    }


}
