package com.umeshag.retailstorerm;

import java.awt.Color;
import java.awt.Font;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LayeredBarRenderer;
import org.jfree.chart.title.LegendTitle;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class StockReportWindow extends JFrame implements Serializable {
    private static final long serialVersionUID = 6;

    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "user";
    String sqlPassword = "pass";

    // Create dataset
    CategoryDataset combinedDataset = createCombinedDataset();

    // Create chart
    JFreeChart chart = createStackedBarChart(combinedDataset);

//============================= CONSTRUCTOR ====================================
    public StockReportWindow() {
        components();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setTitle("Stocks Report");
        setLocationRelativeTo(null);
        setBackground(Color.WHITE);
    }

//=================== GUI COMPONENTS POSITIONING ===============================
    private void components() {
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new javax.swing.BoxLayout(mainPanel, javax.swing.BoxLayout.LINE_AXIS));

        ChartPanel chartPanel = new ChartPanel(chart);
        JScrollPane scrollPane = new JScrollPane(chartPanel);
        mainPanel.add(scrollPane);
        getContentPane().add(mainPanel);

        pack(); 
    }

// create initial and current data combiation ----------------------------------
    private CategoryDataset createCombinedDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        // Connect to the database and retrieve initial data
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String initialQuery = "SELECT stock_name, stock_qntity FROM initial_stocks";
                try (PreparedStatement statement = connection.prepareStatement(initialQuery)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String stockName = resultSet.getString("stock_name");
                            int quantity = resultSet.getInt("stock_qntity");
                            dataset.addValue(quantity, "Initial Stocks", stockName);
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Connect to the database and retrieve current data
        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword); {
                String currentQuery = "SELECT stock_name, stock_qntity FROM stocks";
                try (PreparedStatement statement = connection.prepareStatement(currentQuery)) {
                    try (ResultSet resultSet = statement.executeQuery()) {
                        while (resultSet.next()) {
                            String stockName = resultSet.getString("stock_name");
                            int quantity = resultSet.getInt("stock_qntity");
                            dataset.addValue(quantity, "Current Stocks", stockName);
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return dataset;
    }

    // method for create 2 layer bar charts ------------------------------------
    private JFreeChart createStackedBarChart(CategoryDataset dataset) {
        // create the chart
        CategoryAxis categoryAxis = new CategoryAxis("Products");
        ValueAxis valueAxis = new NumberAxis("Quantity");
        CategoryPlot plot = new CategoryPlot(dataset,categoryAxis, valueAxis, new LayeredBarRenderer());
        
        plot.setOrientation(PlotOrientation.VERTICAL);
        JFreeChart chart = new JFreeChart("", JFreeChart.DEFAULT_TITLE_FONT,  plot, true);
        chart.removeLegend(); // remove default legend
        
        //background color for the chart
        plot.setBackgroundPaint(Color.WHITE);
        plot.setRangeGridlinePaint(Color.GRAY);

        LayeredBarRenderer renderer = (LayeredBarRenderer) plot.getRenderer();
        //set colors for serieses
        renderer.setSeriesPaint(1, new Color(0, 102, 102)); 
        renderer.setSeriesPaint(0, new Color(0, 51, 51));
        //set bar size to look both bars same size
        renderer.setSeriesBarWidth(0, 1.7);
        renderer.setSeriesBarWidth(1, 1.0);
       
        categoryAxis.setMaximumCategoryLabelLines(2);
        categoryAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        valueAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        valueAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        categoryAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        //new legend style
        LegendTitle legend = new LegendTitle(renderer);
        legend.setPosition(RectangleEdge.TOP); 
        legend.setItemFont(new Font("SansSerif", Font.PLAIN, 14));  
        chart.addLegend(legend);

        return chart;
    }
}