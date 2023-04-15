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
    public static void addWorkReport(WorkReport workReport) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `vykazy` (`timeFrom`, `timeTo`, `date`) VALUES (? , ? , ?);");
        preparedStatement.setTime(1, Time.valueOf(workReport.getFrom()));
        preparedStatement.setTime(2, Time.valueOf(workReport.getTo()));
        preparedStatement.setDate(3, Date.valueOf(workReport.getDate()));
        preparedStatement.execute();
        System.out.println("successfully added");
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
    public static void editWorkReport(WorkReport workReport) throws SQLException{
        System.out.println(workReport.getDate()+"  "+workReport.getTo());
        PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `vykazy` SET `timeFrom` = ?, `timeTo` = ?, `date` = ? WHERE `vykazy`.`id` = ?;");
        preparedStatement.setTime(1, Time.valueOf(workReport.getFrom()));
        preparedStatement.setTime(2, Time.valueOf(workReport.getTo()));
        preparedStatement.setDate(3, Date.valueOf(workReport.getDate()));
        preparedStatement.setInt(4, workReport.getId());
        preparedStatement.execute();
        System.out.println("successfully edited");
    }
    public static void deleteWorkReport(WorkReport workReport) throws SQLException{
        PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM vykazy WHERE `vykazy`.`id` = ?");
        preparedStatement.setInt(1,workReport.getId());
        preparedStatement.execute();
        System.out.println("successfully deleted");
    }


}
