package com.umeshag.retailstorerm;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JFrame;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.time.Second;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class EmployeeReportWindow extends JFrame implements Serializable {
    private static final long serialVersionUID = 8;

    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "maths";
    String sqlPassword = "maths";

// ========================= CONSTRUCTOR =======================================
    public EmployeeReportWindow() {
        TimeSeriesCollection dataset = createDataset();
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        JScrollPane scrollPane = new JScrollPane(chartPanel);
        setContentPane(scrollPane);
        setTitle("Employee Report Window");
    }

// ============================== Methods ======================================
    // data set creating from created series method ----------------------------
    private TimeSeriesCollection createDataset() {
        TimeSeriesCollection dataset = new TimeSeriesCollection();
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                String query = "SELECT emp_id, emp_name FROM employees WHERE emp_job = \"Cashier\"";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    try(ResultSet resultSet = statement.executeQuery(query)){
                        while (resultSet.next()) {
                            String id = resultSet.getString("emp_id");
                            String name = resultSet.getString("emp_name");
                            TimeSeries series = new TimeSeries(name);
                            fetchEmployeePerformanceData(id, series);
                            dataset.addSeries(series);
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return dataset;
    }

    // method for get employee data ,calculate and series making ---------------
    private void fetchEmployeePerformanceData(String cashierId, TimeSeries series) {
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String query = "SELECT date, start_time, end_time, num_of_tra FROM staff_work_hours WHERE cashier_id = ?";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setString(1, cashierId);
                    try(ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                            Date date = resultSet.getDate("date");
                            Date startTime = resultSet.getTime("start_time");
                            Date endTime = resultSet.getTime("end_time");
                            int numTransactions = resultSet.getInt("num_of_tra");

                            // Calculate transactions per hour
                            long elapsedTime = endTime.getTime() - startTime.getTime();
                            double transactionsPerHour = numTransactions / (elapsedTime / (60.0 * 60.0))*100;
                            series.addOrUpdate(new Second(date), transactionsPerHour);
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // chart generating from data set method -----------------------------------
    private JFreeChart createChart(TimeSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createTimeSeriesChart("","Date", "Transactions Per Hour",dataset, true,true, false);
        XYPlot plot = chart.getXYPlot();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        ValueAxis valueAxis =  plot.getRangeAxis();
        axis.setDateFormatOverride(new SimpleDateFormat("yyyy-MM-dd"));
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer(true, true);
        chart.removeLegend();
        
        renderer.setSeriesPaint(0, new Color(0, 102, 102));
        renderer.setSeriesPaint(1, new Color(0, 54, 54));
        renderer.setSeriesPaint(2, new Color(0, 150, 150));
        renderer.setSeriesPaint(3, new Color(0, 200, 200));

        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);

        plot.setRenderer(renderer);

        axis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        axis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        valueAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        valueAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));

        LegendTitle legend = new LegendTitle(renderer);
        legend.setPosition(RectangleEdge.TOP); // You can use RectangleEdge.LEFT, RIGHT, or BOTTOM as well
        legend.setItemFont(new Font("SansSerif", Font.PLAIN, 14));  // Adjust font size for legend items
        chart.addLegend(legend);

        return chart;
    }
}