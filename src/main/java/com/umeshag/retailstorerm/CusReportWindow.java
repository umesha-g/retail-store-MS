import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.StandardChartTheme;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import java.sql.*;
import java.io.Serializable;
import java.text.DecimalFormat;
import javax.swing.*;
import java.awt.*;

public class CusReportWindow extends JFrame implements Serializable {
    private static final long serialVersionUID = 5;

//============================= OBJECTS DICLARATION ============================
    JTextArea cusInfoArea = new JTextArea();
    JPanel topMainPanel = new JPanel();
    JPanel topLeftPanel = new JPanel();

    String sqlUrl = "jdbc:mysql://localhost:3306/retail_store_database";
    String sqlUsername = "maths";
    String sqlPassword = "maths";

// ========================= CONSTRUCTOR =======================================
    public CusReportWindow() {
        components();
        setTitle("Customer Report Window");
    }

// ====================== GUI components =======================================                  
    private void components() {
        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        //properties for top main panel
        topMainPanel.setPreferredSize(new java.awt.Dimension(720, 240));
        topMainPanel.setLayout(new BoxLayout(topMainPanel, BoxLayout.X_AXIS));

        // properties for top left panel
        topLeftPanel.setPreferredSize(new java.awt.Dimension(1100, 240));
        topLeftPanel.setBackground(Color.WHITE);
        topLeftPanel.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 51, 51)));
        topLeftPanel.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 20, 30));

        //properties for text customer info
        cusInfoArea.setForeground(new java.awt.Color(0, 51, 51));
        cusInfoArea.setBackground(Color.WHITE);
        cusInfoArea.setFont(new java.awt.Font("Noto Sans", 1, 16));
        cusInfoArea.setEditable(false);
        cusInfoArea.setPreferredSize(new java.awt.Dimension(600, 200));
        cusInfoArea.setBorder(javax.swing.BorderFactory.createLineBorder(Color.WHITE));
        cusInfoTextArea();
        topLeftPanel.add(cusInfoArea);

        topMainPanel.add(topLeftPanel);

        // Create  pie dataset using data from the database
        DefaultPieDataset<String> pieChartDataset = createPieChartDataset();

        // Create  pie chart using dataset
        JFreeChart pieChart = createPieChart(pieChartDataset);

        // adding pie chart as top right panel
        ChartPanel topRightPanel = new ChartPanel(pieChart);
        topMainPanel.add(topRightPanel);
        getContentPane().add(topMainPanel);

         // Create bar dataset using data from the database
        DefaultCategoryDataset barChartDataSet = createBarChartDataSet();

        // Create  bar chart using dataset
        JFreeChart barChart = createBarChart(barChartDataSet);

        // add bar chart to bottom chart panel 
        ChartPanel bottomChartPanel = new ChartPanel(barChart);
        bottomChartPanel.setPreferredSize(new Dimension(800, 600));
        getContentPane().add(bottomChartPanel);

        pack();                 
    }

// ============================== Methods ======================================

    // bar chart dataset creating method ---------------------------------------
    private DefaultCategoryDataset createBarChartDataSet() {
    DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    double minSubTotal = 0;
    double maxSubTotal = 0;

        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
                // Calculate the minimum and maximum sub_total values
                String minMaxQuery = "SELECT MIN(sub_total) AS min_sub_total, MAX(sub_total) AS max_sub_total FROM transactions";
                try(PreparedStatement minMaxStatmnt = connection.prepareStatement(minMaxQuery)){
                    try(ResultSet minMaxResult = minMaxStatmnt.executeQuery()){
                        if (minMaxResult.next()) {
                            minSubTotal = minMaxResult.getDouble("min_sub_total");
                            maxSubTotal = minMaxResult.getDouble("max_sub_total");
                            cusInfoArea.append("Highest Bill Amount Recorded : " + maxSubTotal +"\n\n");
                            cusInfoArea.append("Lowest Bill Amount Recorded : " + minSubTotal +"\n\n");
                        }
                    }
                }

            // Calculate the range and interval size
            double range = maxSubTotal - minSubTotal;
            double intervalSize = range / 10; 

                String query = "SELECT sub_total_group, COUNT(*) AS customer_count FROM (SELECT FLOOR((sub_total - ?) / ?) AS sub_total_group FROM transactions) AS sub_total_groups GROUP BY sub_total_group ORDER BY sub_total_group";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    statement.setDouble(1, minSubTotal);
                    statement.setDouble(2, intervalSize);
                    try(ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                            double subTotalGroup = resultSet.getDouble("sub_total_group");
                            double lowerBound = minSubTotal + (subTotalGroup * intervalSize);
                            double upperBound = lowerBound + intervalSize;
                            int customerCount = resultSet.getInt("customer_count");
                            dataset.addValue(customerCount, "Customer Count", format(lowerBound) + " - " + format(upperBound));
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
        return dataset;
    }

    // bar chart creating from bar data set method -----------------------------
    private JFreeChart createBarChart(CategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart("","Total Bill Amount","Customer Count",dataset,PlotOrientation.VERTICAL,false,true,false);
        CategoryPlot plot = chart.getCategoryPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        CategoryAxis axis = plot.getDomainAxis();
        ValueAxis valueAxis =  plot.getRangeAxis();
        
        renderer.setBarPainter(new StandardBarPainter()); //remove gradient effect
        
        Paint customColor = new Color(0, 102, 102); //add custom color
        renderer.setSeriesPaint(0, customColor);
        
        plot.setBackgroundPaint(Color.WHITE); //set background and foreground colors
        plot.setRangeGridlinePaint(Color.GRAY);
       
        axis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));  //set fonts for axlis and lables
        axis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));
        valueAxis.setTickLabelFont(new Font("SansSerif", Font.PLAIN, 12));
        valueAxis.setLabelFont(new Font("SansSerif", Font.BOLD, 14));

        return chart;
    }

    // string formatting method ------------------------------------------------
    private String format(double value) {
        DecimalFormat df = new DecimalFormat("#");
        return df.format(value);
    }

    //pie chart data set creating method ---------------------------------------
    private DefaultPieDataset<String> createPieChartDataset() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
            // Fetch payment method counts from the database
                String query = "SELECT pay_method, COUNT(*) AS count FROM transactions GROUP BY pay_method";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    try(ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                            String paymentMethod = resultSet.getString("pay_method");
                            int count = resultSet.getInt("count");
                            dataset.setValue(paymentMethod, count);
                        }
                    }
                }
            }
            connection.close();
        }catch (SQLException e) {
            e.printStackTrace();
        }
        return dataset;
    }

    //pie chart create from pie dataset method ---------------------------------
    public JFreeChart createPieChart(DefaultPieDataset<String> dataset) {
        JFreeChart chart = ChartFactory.createPieChart("",dataset,false, true,false);
        PiePlot plot = (PiePlot) chart.getPlot();

        // Set the custom color for the pie chart sections
        plot.setSectionPaint("Card", new Color(0, 153, 153));
        plot.setSectionPaint("Cash", new Color(0, 102, 102));
        plot.setSectionPaint("Voucher", new Color(0, 51, 51));
        plot.setShadowPaint(null);
        plot.setBackgroundPaint(Color.WHITE);
        chart.setBackgroundPaint(new Color(255, 255, 255));  
        return chart;
    }

    // text info area data adding method ---------------------------------------
    public void cusInfoTextArea(){

        try {
            Connection connection = DriverManager.getConnection(sqlUrl, sqlUsername, sqlPassword);{
            // Fetch transaction counts from the database for non registerd customers
                String query = "Select COUNT(cus_name) AS count FROM transactions WHERE cus_name LIKE \"Non-Reg Customer\"";
                try (PreparedStatement statement = connection.prepareStatement(query)){
                    try (ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                            int nonreg = resultSet.getInt("count");
                            cusInfoArea.append("Transaction Count From Non Registerd Customers : " + nonreg + "\n");
                        }
                    }
                }
                // Fetch transacton counts from the database for registerd customers
                query = "Select COUNT(cus_name) AS count FROM transactions WHERE cus_name NOT LIKE \"Non-Reg Customer\"";
                try(PreparedStatement statement = connection.prepareStatement(query)){
                    try(ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                            int reg = resultSet.getInt("count");
                            cusInfoArea.append("Transaction Count From Registerd Customers : " + reg +"\n");
                        }
                    }
                }
                // Fetch registered customer count
                query = "Select COUNT(cus_id) AS count FROM customers";
                try (PreparedStatement statement = connection.prepareStatement(query)){
                    try (ResultSet resultSet = statement.executeQuery()){
                        while (resultSet.next()) {
                            int regcus = resultSet.getInt("count");
                            cusInfoArea.append("Number of Registered Customers : " + regcus +"\n");
                        }
                    }
                }
            }
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}