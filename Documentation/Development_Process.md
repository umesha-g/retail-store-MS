# Development Process

<br>

## 1. Being aware of the problem

Observations of the economic landscape in the Matara Area revealed a pronounced inefficiency prevalent among retail shops relying on manual operations. Noteworthy shortcomings included sluggish transaction processes, minimal inventory management, and challenges in maintaining accurate financial records. The implications of such inefficiencies extend beyond operational hindrances, impacting the overall competitiveness of these retail establishments in a rapidly evolving market. In order to address this prevalent issue effectively, a comprehensive analysis became imperative.

## 2. Formulating a Robust Resolution

Many of the aforementioned challenges can be mitigated through the implementation of a Store Management Software, designed to function efficiently on a computer with modest performance capabilities and a printer. Notably, the adaptability of such software allows for its operation on compact devices, such as a small laptop, strategically positioned alongside the existing cashier’s table in these retail stores. This approach ensures a seamless integration of technology into the existing operational setup, addressing the identified issues while optimizing space and resource utilization.Henceforth, it was determined that the Java software development process, adhering to internationally recognized development Techniques, would serve as the foundational framework for the progression of this project.

## 3. Retail Store Management System Development Process

The development of the Retail Store Management System was executed employing a pragmatic software engineering approach, focusing on fundamental Java programming constructs such as loops and conditional statements and MySQL interactions rather than intricate algorithms or advanced Java features. MySQL commands were employed for efficient data management, providing a reliable back-end for the system.

The development of the Retail Store Management System – followed a structured Software Development Life Cycle (SDLC). The journey commenced with a meticulous analysis phase, on-site visits were conducted to comprehensively understand and document the system’s requirements. Subsequently, the design phase transformed these requirements into a technical blueprint, encompassing system architecture, user interfaces, and data flow. Transitioning to the implementation phase, the focus was on translating the design into code, utilizing basic Java programming constructs to instantiate essential functionalities. The testing phase, a critical aspect of the SDLC, rigorously validated the system against predefined test cases, ensuring a robust and defect-free solution.

## 3.1 Phase 1 : Requirements Gathering and Analysis 

Main Objective in this phase was Understanding and documenting the needs and requirements of the software system. on-site visits were conducted to various retail stores conducting manual operations and supermarkets equipped with existing management systems. This hands-on exploration paved way to analyze the current landscape, understand the challenges faced by these establishments, and evaluate the functionalities of their existing systems. By immersing ourselves in real-world scenarios, gained valuable insights into the practical nuances of retail management. This empirical approach informed the decision making process and contributed to the development of a Retail Store Management System tailored to address industry-specific needs and challenges.

During the field visits to various retail stores and supermarkets, several common challenges and deficiencies in their operational processes and systems were identified

##### Manual Processing Practices

  The majority of the establishments were found to rely on manual processing since their inception. Respondents expressed a notable interest in transitioning to an automated system for monetary calculations and billing, coupled with inventory management, to enhance overall operational efficiency.

##### Lack of Automated Inventory Management

  A prevalent practice among the visited stores was the absence of an automated inventory management mechanism. Current procedures involved recording stock levels during renewal and checking remaining stocks on subsequent renewal dates. This manual approach was acknowledged as labor-intensive, prompting the recognition of the need for an automated stock management system.

##### Absence of Employee Data and Performance Tracking

  Notably, there was a dearth of comprehensive employment data. Limited to basic personal information, this insufficient data-set hindered effective performance evaluation and attendance tracking. Store managers expressed the necessity of implementing a system that could collect and analyze performance data to inform decision-making regarding employee retention.

##### Lack of Customer Relationship Management

  The absence of a formal customer relationship management method was a recurrent observation. Stores primarily relied on personal connections, lacking structured programs for customer loyalty or transaction pattern recognition. Managers acknowledged the importance of a database-driven approach, including analytics for customer information and transaction patterns.

##### Inadequacies in Transaction Recording

  Despite the presence of ledger practices, it became evident that these were insufficient in recording all transaction and performance data. Respondents recognized the need for an automated transaction recording method to comprehensively capture all relevant data.

##### Limited Analytical Reporting Capabilities

  Notably, the establishments lacked the ability to generate analytical reports or graphs about their business progress.

  This absence of analytical tools underscored the demand for a system that could provide insights through comprehensive analytics, enabling informed decisions about business performance and growth.

The insights gained from the analytics guided the design of the management software’s graphical user interface (GUI). Subsequently, a deliberation resulted in the inclusion of two distinct modules: the Cashier module and the Administrator module. In an endeavor to maintain comprehensive organization, all requisite requirements and functionalities were meticulously structured and represented within the following diagram.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.001.jpeg)

Figure 3.1: Diagram of Requirements And Functions Upon the successful culmination of the initial phase, Phase 2 was initiated.

## 3.2 Phase 2 : System Design

Main Objective in this phase was Creating a blueprint or design of the software based on the requirements. The System Design phase in the development of the Retail Store Management Software involves creating a detailed plan for how the software will be structured and how it will function.

### 3.2.1 Architectural Design

The architectural design phase is a critical element in the software development process, encompassing the systematic structuring of the system’s components, their interactions, and the overall layout, all of which serve as the foundation for the software’s functionality and performance.

#### 3.2.1.1. Administrator Window

In accordance with the analysis conducted in the preceding phase, it was established that the Administrator module would necessitate the capability to interact with Stocks’ data, customers’ data, and employees’ data through operations involving viewing, addition, removal, and updates. Additionally, the Administrator module would encompass the functionality to View transaction records. To effectively address these requisites, it was determined that the construction of four distinct tabbed panels within the primary Administrator interface window would serve as the appropriate and organized approach to accommodate these diverse functions.

#### The First tab was allocated for operations related to stocks management. This tab was further subdivided into four discrete sections.

##### 1. First One of these sections is designated for the input and addition of stock data into the database.

   To facilitate the addition or updating of stock information, specific data fields were incorporated, consisting of Stock Code, Stock Name, Quantity, and Unit Price. In conjunction with these fields, an ’Add/Update’ button was introduced. When activated, this button triggers the execution of a database update operation, thereby effecting the addition or modification of relevant stock details.

##### 2. The second section was designated for the purpose of searching and removing stocks.

   For the execution of search and removal functions, a text field was incorporated for user input, enabling dynamic search operations upon each keystroke, thus obviating the necessity for a distinct search button. Additionally, a ’Remove’ and ’Reset’ buttons were integrated into this section. Upon selection of a specific row in the table, the corresponding entry is removed from the database, and an updated table is subsequently displayed to reflect the modification.The ’Reset’ button serves to reset all text fields and the table to their default states.

##### 3. The third section was allocated for two distinct functions: the generation of analytical reports and the inclusion of a stock reset button.

   This section facilitates the initiation of separate mechanisms for stock resetting and report display. Notably, the report is generated and displayed in a distinct window.

##### 4. The fourth section serves the purpose of presenting stock information in tabular form.

   Within this section, the Administrator is empowered to comprehensively view all pertinent stock information. Moreover, a search operation will yield relevant data displayed within this table, thereby offering a tailored and refined view of the stocks data.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.002.jpeg)

Figure 3.2: Diagram of Stock Panel Architecture

#### The second tabbed panel was devoted to Customer Management, mirroring the organization of the Second panel with a division into four distinct sections.

##### 1. The First section is allocated for the entry and inclusion of customer data into the database.

   To streamline the process of adding or modifying customer information, dedicated data fields were integrated, encompassing Customer ID, Customer Name, Phone Number, and Loyalty Points. Complementing these data fields, an ’Add/Update’ button was introduced. Upon activation, this button initiates a database update operation, facilitating the incorporation or alteration of pertinent customer details.

##### 2. The second section was earmarked for the functions of searching and deleting customers.

   To perform search and deletion tasks, a text field was integrated for user input, allowing real-time search operations as each keystroke is entered, thus eliminating the need for a separate search button. Furthermore, a ’Remove’ and ’Reset’ buttons were embedded in this section. When a specific row in the table is chosen, the corresponding entry is deleted from the database, and an updated table is instantly presented to depict the alteration.The ’Reset’ button serves to reset all text fields and the table to their default states.

##### 3. The third section is designated for report generation. 
It encompasses the inclusion of a report button, which, upon activation, initiates the generation of a report. Notably, a dedicated separate window is employed for the presentation of the generated report.

##### 4. The fourth segment is dedicated to displaying customer information in a tabular format.

   In this section, the Administrator has the ability to access a comprehensive view of all relevant customer data. Additionally, conducting a search operation will retrieve and display pertinent information within this table, providing a customized and refined perspective on customer data.

   ![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.003.jpeg)

Figure 3.3: Diagram of Customers Panel Architecture

#### The third tabbed panel was allocated for Employee Management, following a structure similar to the previous panels, partitioned into four discrete sections.

##### 1. The initial section is designated for inputting employee data into the database.

   To simplify the procedure of adding or revising employee information, specific data fields were incorporated, comprising Employee ID, Employee Name, Job Role, and Password. In conjunction with these data fields, an ’Add/Update’ button was introduced. When this button is activated, it triggers a database update operation, thereby enabling the inclusion or modification of relevant employee details.

##### 2. The second section was designated for the tasks of searching and removing employees.

   To carry out search and deletion functions, a text field was incorporated for user input, enabling real-time search operations as each keystroke is entered, thus obviating the requirement for a separate search button. Additionally, a ’Remove’ and ’Reset’ buttons were integrated into this section. Upon selecting a specific row in the table, the corresponding entry is removed from the database, and an updated table is promptly displayed to reflect the modification.The ’Reset’ button serves to reset all text fields and the table to their default states.

##### 3. The third segment is allocated for report generation and Logout.

   In this section, there is an incorporation of both a report button and a logout button. When the report button is activated, it initiates the report generation process, and the result is presented in a dedicated separate window. Additionally, by clicking the logout button, the user can effectively log out from the system.Also, this will reopen the Login window.

##### 4. The fourth segment is dedicated to displaying employee information in a tabular format.

   In this section, the Administrator is empowered to gain comprehensive access to all pertinent employee data. Furthermore, initiating a search operation retrieves and displays relevant information within the table.

   ![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.004.jpeg)

Figure 3.4: Diagram of Employee Panel Architecture

#### The fourth tabbed panel was exclusively designated for presenting transaction data in a tabular format. 
This panel comprises two distinct components: a search text field and a transaction display table. Similar to the previous text fields, the search text field operates in real-time, with each keystroke activating the search functionality.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.005.jpeg)

Figure 3.5: Diagram of Transactions Panel Architecture

#### 3.3.1.2. Cashier Window

As was analyzed in the preceding phase, the need for the capability to manage a comprehensive purchasing process was identified for the cashier panel. This process is depicted in the diagram below.

Hence, the architectural framework of the cashier window was meticulously structured to meet the aforementioned requirement.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.006.jpeg)

Figure 3.6: Diagram of Transaction Process

The upper-left corner of the window was allocated for displaying a welcoming greeting to the cashier, featuring the cashier’s name (”Welcome, [Cashier’s Name]”). This inclusion not only contributes to a user-friendly interface but also provides an assurance to the user, confirming their successful login into the correct account. 

Following this, the remainder of the window was partitioned into five distinct sections. 



##### 1. The initial section was designated as the ”Order Control Panel.”

   Subsequently, a ”New Order” button was introduced, accompanied by an associated mechanism for generating unique bill numbers. Upon clicking the ”New Order” button, the system automatically generated a new bill number, incorporating the corresponding time and date into the bill details.

   Additionally, a ”Log Out” button was incorporated into this section. This button facilitated user logout, and upon activation, it also recorded the user’s performance data in the database. Also, this will reopen the Login window.

   To enhance the search functionality, a text field was introduced for querying stock items in the database. Additionally, a ”Reset” button was provided, serving the dual purpose of resetting both the search text field and the table.

   A ”Print” button was included in this section. Its functionality encompassed both the printing of the bill (if a printer was available) and the recording of transaction details in the database.

##### 2. Second Section Was ”Customer Panel”.

   To facilitate customer search and retrieval, a text field was integrated for entering either the customer’s ID or phone number. Additionally, a ”Select” button was introduced to initiate the search process. Another text field, placed above, was designated for displaying customer details. This field was non-editable. In the event that the customer was found in the database, the relevant details were displayed in this non-editable field. However, if the customer was not found, a ”Customer not Found” message was presented in this field.

##### 3. Third section was ”payment Control Panel”.

   Three distinct buttons were incorporated to allow the customer to select their preferred payment method. These buttons included ”Cash,” ”Card,” and ”Voucher.” Upon clicking any of these buttons, the payment mode was adjusted accordingly. Additionally, a ”Paid” button was introduced to receive payment and calculate the balance. When the ”Paid” button was clicked, a separate dialog box was opened, enabling the entry of the paid amount.

##### 4. The fourth section was dedicated to displaying an item table that received real-time updates directly from the database.

   A table mirroring the structure of the Stock table in the database was integrated, designed to automatically update as items were either reduced or increased through orders. The intended procedure was to directly update the database from each order and subsequently refresh the table on the user interface. To enhance user visibility and awareness, items with a stock level of 0 were highlighted in red.

   A text field was introduced to input the quantity of items to be purchased, accompanied by an ”Add to Bill” button. Upon clicking this button, the item corresponding to the selected row in the table was multiplied by the quantity specified in the text field and subsequently added to the bill. In the event that this quantity exceeded the available stock in the database, an ”Insufficient Stocks” message was presented to the user.

##### 5. The fifth section was designated for the Bill Preview panel and the functionality to remove items from the bill.

   In this panel, the top section featured a decorated text area that displayed essential information such as the ”Name of Shop”, ”Address”, ”Phone Number”, ”Cashier Name”, ”Date”, ”Time”, and ”Bill Number”. The middle part contained a table with fields including ”Number”, ”Item Code”, ”Product Name”, ”Unit Price”, ”Quantity” and ”Amount”. The bottom section was reserved for presenting details such as ”Sub Total”, ”Payment Method”, ”Paid Amount”, ”Balance”, ”Customer Details” and ”Loyalty Points”. The entire Bill Preview area was rendered non-editable to ensure data reliability.

   Below the Bill Preview, a separate bold and non-editable area was dedicated to displaying the ”Total Amount” enhancing its visibility to the customer.

   Furthermore, an ”Item Remove” button was incorporated to facilitate the removal of selected items from the Bill Table. This action triggered updates in both the database and the Item Display table to reflect the changes.

The architectural diagram of the Cashier Windows is represented in the following illustration

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.007.jpeg)

Figure 3.7: Diagram of Cashier window Architecture

#### 3.3.1.3. Login Window

The Login window was thoughtfully divided into two sections. The left side featured a welcoming image, creating an inviting ambiance. On the right side, dedicated to login operations.

The login section included, A ”Sign In” header. A combo box for selecting usernames loaded directly from the database, enabling access for both administrators and cashiers. A password field for authentication. Login and close buttons. If the entered credentials were correct, access was granted based on the user’s role; otherwise, an ”Incorrect credentials” message appeared for immediate feedback.This window operates within the Main program, and upon a successful login, it triggers the opening of either the Admin or Cashier windows, both of which are also integrated into the Main program.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.008.jpeg)

Figure 3.8: Diagram of Login window Architecture

#### 3.3.1.4. Main Program

The Main Program, equipped with the Java Main method, serves as the starting point for the Java application. Upon program initiation, the Main Class runs in the background and presents the Login Window to the user.

The Main window instantiates the Login window and injects an external mechanism for selecting the relevant window. This mechanism includes instances of the Admin and Cashier windows, both created within the Main program. Consequently, any window triggered through the Login window will also run within the Main program.

As described earlier, closing either an Admin or Cashier Window will trigger the reopening of the Login Window, ensuring that one of the three windows is always visible. In the event that the program is not closed through the Login Window, the Main Program will continue to run in the background. Conversely, if the Login Window was closed, the Main Program itself would be terminated.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.009.jpeg)

Figure 3.9: Diagram of Main program Architecture

#### 3.3.1.5. Stock Report window

This feature provides a graphical representation of the stock difference between the stock refill date and the current date. It presents the data in the form of a bar chart, where each item is represented by two dedicated bars. One bar indicates the initial stock quantity, while the front bar displays the current stock quantity. This visual representation allows users to discern the movement of each item in their stocks and identify items that are approaching depletion. Data will be directly imported from the Database (Initial Stocks table and Stocks Table).

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.010.jpeg)

Figure 3.10: Diagram of Stock Report Window Architecture

#### 3.3.1.6. Customer Report window.

This window was structured into three distinct sections:

1. The left top section displayed various customer information in textual form, drawn directly from the database and Calculated.

2. The top right section featured a pie chart depicting the count of transactions conducted through different payment methods. The data for this chart was retrieved directly from the database.

3. The bottom section displayed a bar chart that showcased the distribution of customer counts, categorized into 11 distinct bill total ranges, ranging from the maximum bill amount to the minimum bill amount.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.011.jpeg)

Figure 3.11: Diagram of Customer Report Window Architecture

##### 3.3.1.7. Employee Performance Report Window

This window is exclusively dedicated to a time series graph illustrating customer performances. It displays the number of transactions completed per hour for each day, with a separate graph provided for each cashier.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.012.jpeg)

Figure 3.12: Diagram of Employee Report Window Architecture

#####  3.3.1.8. Bill Number Generator

This bill number generator is exclusive to the Cashier window, generating a unique bill number each time a new order is initiated.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.013.png)

Figure 3.13: Diagram of Bill Number Generator Architecture

##### 3.3.1.9. Payment Accepting Dialog Box

This popup dialog box opens when the Cashier Window’s ”Paid” button is clicked and offers three modes: Cash, Card, and Voucher. Cashiers input the paid amount or last four digits of a card or voucher value, followed by clicking the ”Submit” button. This action triggers the internal calculation process within the Cashier Window, ultimately displaying the paid amount and balance.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.014.jpeg)

Figure 3.14: Diagram of Payment Pop-up Dialog Box Architecture

##### 3.3.1.10. Table refreshing Mechanism

This mechanism was designed as a standalone component within the program, ensuring its versatility for use in various contexts where table refreshing is required. It has the capability to refresh tables belonging to different categories, such as customers, stocks, employees, and transactions, upon a valid request for a specific category. This process involves clearing the current data from the table and repopulating it directly from the database.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.015.png)

Figure 3.15: Diagram of Table refreshing Mechanism Architecture

##### 3.3.1.11. Stock Reset Confirming Dialog Box

This feature is triggered when the Admin Window’s ”Stock Reset” button is clicked, opening as a new instance within the Cashier Window. It first requests the admin password, and if the password is correct, a second confirmation dialog box appears, asking, ”Do You Want To Proceed?” Upon selecting ”Yes” or ”No,” the process proceeds to reset the stocks, essentially creating an identical copy of the current stock table known as the ”Initial Stocks Table.”

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.016.jpeg)

Figure 3.16: Diagram of Stock Reset Confirming Dialog Architecture

### 3.3.2 Database Design

The primary database schema, developed within the MySQL framework, interfaces with Java classes utilizing the JDBC driver for data reception and storage. Main database schema was labeled as ”Retail\_Store\_Database”, within which six distinct tables were established. These tables are:

##### 1. Stocks Table

   This specific Table was allocated for the storage of stock-related information (Stock Code, Stock Name, Quantity, and Unit Price).

##### 2. Customers Table

   This Table was designated for the storage of customer details (Customer ID, Customer Name, Customer Phone Number, and Loyalty Points).

##### 3. Employee Table

   This particular Table was intended for the storage of employee information (Employee ID, Employee Name, Employee Job Role, and Employee System Password).

##### 4. Work Hours Table

   This specific Table was dedicated to the storage of employee performance data (Nth Use Time, Cashier ID, Cashier Name, Date, System Login Time, System Logout Time, and the number of transactions completed during that period).

##### 5. Initial Stocks Table

   This table was designed to serve as a repository for creating an identical copy of the stocks table when the restoration process was initiated. This restoration operation should be Manually triggered on the date when the retail store renewed its stock inventory.

##### 6. Transactions Table

   This table was employed for the storage of transaction details (Transaction Number, Invoice Number, Transaction Date, Transaction Time, Cashier ID, Cashier Name, Total Quantity Purchased, Customer ID, Customer Name, Total Bill Amount, Payment Method, Paid Amount, and Balance). Every time a transaction was successfully completed at the cashier window, a corresponding record was generated and added to this table.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.017.jpeg)

Figure 3.17: Diagram of Database Design

### 3.3.3 User Interface (UI) Design

All the interfaces within the system are graphical, and there are no command-line interfaces (CLI). During the architecture design phase, graphical user interfaces (GUIs) were created using the NetBeans IDE for the sake of convenience in the design process. Creating complex GUIs manually would have been time-consuming and challenging. Given the short time frame, the NetBeans IDE was employed primarily for establishing the fundamental layout of GUI components.

Subsequently, the GUI class files were exported from the IDE, and final adjustments were made to the windows. These adjustments encompassed fine-tuning component sizes, colors, and fonts. Additionally, HTML text components were created for Java JEditorPane elements, which are utilized for decorating text areas at the top and bottom of bill preview panels. JEditorPanes enable the use of HTML as their input language, facilitating the creation of aesthetically appealing text areas.

The main font used in the interfaces was kept as the default font provided by the operating system on which the software runs. The only font alteration was applied to the bill preview panel, ensuring a genuine invoice-like appearance.

The component sizes were meticulously adjusted to maximize the utilization of available space. In all windows, tables were granted more space, enhancing the display of tabular data. Text typing areas were designed with a modern aesthetic in mind, featuring a single bottom line for input. The corners of each sub-panel were intentionally squared to maintain a contemporary appearance, while the buttons had slightly curved edges for a more polished look. Horizontal lines were employed in select areas to provide visual separation where necessary.

To ensure responsive interfaces across various screen resolutions, JPanels in Java were assigned different layouts. The primary layouts employed included BOX Layout, FLOW Layout, and CARD Layout, which were chosen for their adaptability. However, for dialog boxes and the Login Window, Absolute Layout was utilized since these windows had fixed dimensions and were consistently centered on the screen. Both the Cashier and Admin Windows were designed as border-less, full-screen interfaces, with the only means of closing the window being through the logout functionality.

The development of the system utilized various Java components from the ***Swing*** package, each serving a specific purpose:

JTextField: Employed for text inputs where user data was required.
JPasswordField: Utilized for password inputs to ensure secure data entry.
JButton: Used for all interactive buttons within the user interface.
JLabel: Employed as non-editable labels to display information.
JTable: Utilized to present detailed tabular data.
JScrollPane: Incorporated to enable scrollable tables by containing JTables.
JSeparator: Applied where visual separation or distinction was needed.
Image panels were implemented using JLabel components for displaying images within the interface.

The selection of colors aimed to maintain a pleasant and dark-colored interface, considering that users might have the application open for extended periods. The chosen colors include the following:

* Dark Blue, rgb(0, 51, 51) , hsl(180, 100%, 10%)
* Light Blue, rgb(0, 102, 102), hsl(180, 100%, 20%)
* White, rgb(255, 255, 255), hsl(0, 0%, 100%)
* Black, rgb(0, 0, 0), hsl(0, 0%, 0%)

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.018.png)

Figure 3.18: Diagram of Login Window GUI

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.019.jpeg)

Figure 3.19: Diagram of Cashier Window GUI

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.020.jpeg)

Figure 3.20: Diagram of Stock Panel GUI

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.021.jpeg)

Figure 3.21: Diagram of Customer Panel GUI

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.022.jpeg)

Figure 3.22: Diagram of Employee Panel GUI

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.023.jpeg)

Figure 3.23: Diagram of Transaction Panel GUI

### 3.3.4 Functional Design

#### 3.3.4.1 Login Screen

Any registered user can access the system, with two primary user types: Administrator and Cashier. In the login screen, users can select their ID from the combo box, input their password, and click the ’Continue’ button. The system validates the inputs with the database and grants access if the credentials are correct. In the case of an incorrect password, an ’Invalid Credentials’ message is displayed in red. Upon validation, the system automatically launches the relevant user side based on the user’s job role. Users can also close the system by clicking the ’Close’ button.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.024.jpeg)

Figure 3.24: Functions of Login Screen as a Flow chart

#### 3.3.4.2 Cashier Window

The Cashier window is purposefully designed to facilitate the entire billing process, with both functions and interface components tailored to this specific objective.

##### Step 1 : Starting a New Order

To initiate a new order, the user must click on the ”Start New Order” button. This action triggers several important functions: it resets all input text fields, clears customer information, resets variables involved in calculation processes, and clears the list of billed items. Additionally, it generates and displays a new invoice number, as well as the corresponding date and time. This comprehensive action ensures a clean slate for each new transaction, streamlining the billing process.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.025.png)

Figure 3.25: Function of New Order Button as a Flow chart 

##### Step 2 : Selecting the Customer

In the cashier module, there exists a dynamic and versatile customer selection process. When a customer claims to be a registered user, the cashier is provided with the option to input either the customer’s ID number or phone number. by clicking the ”Select” button, the search process is initiated. If the entered number happens to correspond with any of the phone numbers or ID numbers stored in the database, the customer is seamlessly and accurately selected. Their relevant information, stored in specific variables, is promptly displayed. These variables play a pivotal role in the calculation and display of loyalty points. Importantly, this customer selection process can be performed at any point in between starting a new order and printing the bill. If neither the provided ID nor phone number is found in the database, the system will display ”Non-Registered Customer”. Also Users can Skip this step, in which case the system will automatically categorize the customer as non-registered by default.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.026.png)

Figure 3.26: Function of Customer selection Button as a Flow chart 

##### Step 3 : Adding Items to the Bill

Users have the flexibility to add items to the bill in two ways. The first method involves selecting the item from the table, entering the desired quantity, and then clicking the ”Add

to Bill” button. The system will verify whether the item is available in stock at the required quantity. If the stock quantity surpasses the requested amount, an ’Insufficient stocks’ message will be shown. In case the stock level is at ”0”, an ’Out Of Stocks’ message will be displayed. However, if there are adequate stocks to fulfill the required quantity, the system will perform automatic calculations by multiplying the quantity by the unit price. It will also verify if the item is already in the bill; if so, it will update the quantity and amount, otherwise, it will add the item as a new entry. The details of the item, including entry number, item code, product name, unit price, quantity, and amount, will be displayed. Subsequently, the quantity will be decremented in the database. The subtotal and Total Item count will

be continuously recalculated and presented for each item added. An alternative method allows users to simply double-click on the item in the Item Display table, triggering the same mechanism described earlier. After each addition, the quantity field will automatically reset to ”1”, allowing users to add multiple items with the same simple double-click action.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.027.jpeg)

Figure 3.27: Function of Adding Items to Bill as a Flow Chart 

##### Step 4 : Remove Items From Bill (Optional)

This mechanism also functions similarly to the previous item addition method. Users can select an item by clicking on it in the bill table and then click on the ’Remove Item’ button. The system will remove the item from the bill and add the relevant quantity back to the database. Additionally, the item display table will be refreshed with updated stock levels. This can also be triggered by double-clicking on a bill item, and both the sub-total and the total item count will be recalculated accordingly.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.028.png)

Figure 3.28: Function of Removing Items from Bill as a Flow Chart 

##### Step 5 : Search For Items In Table (Optional)

If a user is unable to locate an item in the display table, they can perform a search by typing the name or code into the search text field. With each key press, the search is initiated using the characters entered in the text field, providing wildcard search results. This allows the user to find products without needing to know the exact product name or code. To revert the table to its initial state, the user can click on the ’Reset’ button, and both the search text field and the table will be reset. If the text field is empty and a backspace key is pressed, the table will be reset.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.029.png)

Figure 3.29: Function of Searching Items as a Flow Chart

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.030.png)

Figure 3.30: Function of Resetting Items as a Flow Chart 

##### Step 6 : Selecting Payment Method

The cashier can select the payment method used by the customer by clicking one of the three buttons: ’Cash’, ’Card’ or ’Voucher.’ If the user does not make a selection, the system will default to ’Cash’. This choice is then stored in a variable for future calculations.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.031.png)

Figure 3.31: Function of Payment method Selection as a Flow Chart

##### Step 7 : Accepting Payment

When the customer pays the cashier using the previously selected payment method, the cashier can click the ’Paid’ button. A small popup window will open, containing a text field and a submit button. If the payment method is ’Cash,’ the cashier will be prompted to enter the paid amount. If it’s ’Card,’ the last four digits of the card should be provided. For ’Voucher,’ the cashier needs to enter the voucher value. The user can input the required information and then click the submit button or press Enter.

Subsequently, if the payment method is ’Cash,’ the system will calculate the balance using the subtotal. For ’Card,’ there’s no balance to calculate. In the case of ’Voucher,’ the remaining voucher balance will be calculated based on the subtotal. If a customer is selected, their loyalty points will also be calculated from the subtotal. The payment method, paid amount, balance, customer name, newly added points, and total points will be displayed on the bill and stored in variables for future transaction record creation. Additionally, the customer’s loyalty points will be updated in the database. A ’Thank you for Shopping’ message will be added to the bottom of the bill preview.

Furthermore, the functions to add and remove items , Customer Selecting will be disabled for Safety until the ’Print the Bill’ button is clicked. The payment acceptance dialog box will also be disabled after a single use until the ’Start New Order’ process is initiated.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.032.png)

Figure 3.32: Function of Paid Button as a Flow Chart

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.033.jpeg)

Figure 3.33: Function of Payment Accepting Dialog Box as a Flow Chart 

##### Step 8 : Printing the Bill

At this stage, the cashier reaches the final step, which involves printing the bill and providing a copy to the customer. Adding and removing items have been disabled for safety reasons. To proceed, the user must click on the ’Print the Bill’ button. The system will then generate a comprehensive transaction record in the database, allowing for bill printing. Following this, the system resets the bill preview panel and all associated variables. The disabled functions are re-enabled, and the system is prepared for another transaction. To initiate a new transaction, the cashier must return to step 1.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.034.jpeg)

Figure 3.34: Function of Print the Bill as a Flow Chart

##### Step 9 : Logout

When a cashier concludes their shift, they can log out of the system by clicking the ’Log out’ button. This action closes the cashier window, creates a comprehensive record of the cashier’s performance during their usage period, and opens the login screen, enabling another user to seamlessly log into the system.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.035.png)

Figure 3.35: Function of Login Out as a Flow Chart

#### 3.3.4.3 Administrator Window 

##### Adding or Updating Items

In the Administrator window, the primary functions involve adding, updating, or removing customers, stocks, and employees. All sections share a common method for adding new data. To execute this method, users must input data into four text fields relevant to each section and click the ’Add/Update’ button. If the provided ID or Code is already present in the database, the new data will override the existing entry; otherwise, a new entry will be added. For the convenience of updating existing entries, users can double-click on items in the table. This action will copy the details of the selected item to the relevant input fields on the adding panel. Users can then modify any details they choose and click the ’Add/Update’ button to execute the update. After executing the update, the table will be refreshed with the new database records.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.036.jpeg)

Figure 3.36: Function of Item add to Database as a Flow Chart

##### Search and Remove Items

The search, remove, and reset functions function similarly across all sections. If a user can’t locate an item in the table, they can simply type the item code or name in the search text field. As with the cashier window, each keystroke triggers the search, directly querying the database with the text entered. This clears all items from the table and adds wildcard search results. To reset the table, a user can click the ’Reset’ button, which empties the search text, removes all data from the table, and refills it with all database entries.

For removal, the user can select any entry from the table and click the ’Remove’ button. This deletes the chosen entry from the database and refreshes the table with the updated dataset.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.037.jpeg)

Figure 3.37: Function of Search and Reset from Database as a Flow Chart

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.038.png)

Figure 3.38: Function of Remove Item from Database as a Flow Chart 

##### Viewing Report About Stocks

To view the stock report, users can click the ’Stock Report’ button on the Stocks Panel. This action triggers the creation of a new dataset. Initially, quantity data from the ’Initial Stocks’ table is added to the dataset. Subsequently, quantity data from the ’Stocks’ table is included in the dataset. This combined dataset is then utilized to generate a stacked bar

chart of Stocks vs Quantity, which is displayed in a separate window on top of the admin window. Users can close this separate window after analyzing the chart.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.039.png)

Figure 3.39: Function of Stock Report Generating as a Flow Chart 

##### Resetting stock Levels at Renewing Date.

The stock resetting mechanism is required when a retail store is renewing its stock. After adding new stock data to the database, the user can click on the ’Stock Reset’ button. This action prompts a dialog box to request the Admin’s password. If the correct password is entered, the process continues; otherwise, an ’Access Denied’ message is displayed, and the process is aborted. With the correct password, the user is then asked, ’Do you want to proceed?’ If the user clicks ’NO’, the process is aborted, but if ’YES’ is chosen, an identical copy of the ’Stocks’ table in the database is created in the ’Initial Stocks’ table in the database, and the message ’Initial Stocks Reset’ is displayed.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.040.jpeg)

Figure 3.40: Function of Initial Stock Rest as a Flow Chart

##### Viewing Report About Customers

To access the customer report, users are required to click on the ’Customer Report’ button. Subsequently, the system generates a new dataset for a pie chart, comprising transaction counts for each payment method. This data is employed to produce a pie chart illustrating the distribution of transactions across different payment methods. Additionally, the system identifies the maximum and minimum subtotals from all transactions and computes a range as follows:

Range = ( Maximum Subtotal ) − (Minimum Subtotal ) Next, the interval size is determined as follows:

(Interval Size ) = ( Range ) ÷ 10

This number, 10, is chosen because the chart is predefined to have 11 bars due to limited space in the chart area. Using 10 intervals results in 11 cutoff points, including 0. Subsequently, a command is issued to the database to count subtotals falling within each range.

To achieve this, the minimum subtotal is subtracted from each subtotal in the transaction table stored in the database. Subsequently, each resulting value is divided by the interval size and rounded down to the nearest lower integer. This process generates a range of integers from 0 to 10, totaling 11 integers. The database will return a table with each integer and its corresponding count. Subsequently, the system will process this data and perform the following calculations for each integer:

(Lower Bound ) = ( Minimum Sub Total ) + ( Group Integer × Interval Size )

(Upper Bound) = (Lower Bound) + (Interval Size)

Next, the Lower Bound, Upper Bound, and Count will be incorporated into the Bar Chart Dataset, which will then be utilized to generate a Bar chart illustrating the Total Bill amount versus Customer Count.

Subsequently, transaction counts from registered and non-registered customer segments, the number of registered customers, maximum bill amount, and minimum bill amount will be fetched from the database. All this data, along with the two generated charts, will be presented in a separate window that can be independently closed after analysis.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.041.jpeg)

Figure 3.41: Function of Customer Report Displaying as a Flow Chart 

##### Viewing Report about Employee Performance

Initially, the system will retrieve employee data from the database and create a new data series for each employee, organized by their names. Subsequently, it will obtain information regarding their working periods and transaction counts for each day from the database, utilizing their unique Employee ID. Then, several calculations will be performed.

(Elapsed Time ) = ( End Time ) − (Start Time )
(Transactions per hour ) = ( Transaction Count ) ÷ (Elapsed Time )

Subsequently, transactions per hour for each day and append this data to the pre-created series as a time series. All these series will then be incorporated into the dataset. The dataset will be utilized to generate and exhibit a timeseries diagram depicting the relationship between the date and the number of transactions per hour. Within the same chart area, data from all employees will be displayed, with each employee’s name represented by a distinct color. This window can also be closed independently.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.042.jpeg)

Figure 3.42: Function of Employee Report Displaying as a Flow Chart

##### Login Out of Administrator Window.

Administrator can log out of the system by clicking the ’Log out’ button. This action closes the Admin window and opens the login screen, enabling another user to seamlessly log into the system.

![Alt text](images/Aspose.Words.df38283e-4b90-45c3-8293-7c4965409766.043.png)

Figure 3.43: Function of Logout of Administrator as a Flow Chart

In the transactions panel, users can efficiently search for transactions using any transaction related data. All searches are conducted with wildcards, allowing users to find transactions without needing to input complete information.

### 3.3.5 Security Design

A system that handles financial Data need a special concern about its security. there for few different security levels been introduced to this system.

##### 3.3.5.1. Login Credentials

   The introduction of a separate login window, as previously described, was implemented to provide access exclusively to authenticated users. Any password that does not match the one stored in the database would deny access to the system, thus enhancing security and safeguarding against unauthorized external access. This additional security layer ensures the protection of sensitive data and system interfaces.

##### 3.3.5.2. Security of Administrators Area.

   Access to the Administrator area is restricted to designated administrators only, and this access control is enforced through the login panel. Cashiers and other employees are prevented from accessing the Administrator area. This measure ensures that only authorized users with administrative privileges can access and modify the most sensitive information within the system. if an employee engaged in any improper or unauthorized activities using the system on the cashier side, they cannot erase or eliminate the record of that activity after it has been executed. The system preserves the records of their actions, ensuring that any misconduct or inappropriate behavior is traceable and remains in the system’s logs.

##### 3.3.5.3. Security of Transactions Records

   Only administrators have the privilege to view transaction records in the form of a table within their window. However, even administrators do not possess the ability to modify or change any transaction record. Their access is strictly read-only, allowing them to view and search through the transaction data. This approach ensures that every legitimate or illegitimate transaction can be thoroughly inspected and remains unaltered in the system’s records.

##### 3.3.5.4. Safety Layers of Stock Reset Mechanism

   The Stock Resetting mechanism incorporates two layers of security. The first layer involves the use of an administrator’s password. This layer ensures that even if the administrator window is open, no one else can initiate the mechanism. After the password is confirmed, the system further enhances security by requesting confirmation through a dialog box. The mechanism will only execute if the user explicitly clicks ”Yes,” ensuring that it cannot be triggered accidentally or without deliberate user consent.

##### 3.3.5.5. Disabling Unwanted Functions

   In the Cashier window, after a payment is completed and the cashier clicks the ’Paid’ button, the functions for customer selection, adding items to the bill, and removing items from the bill will be disabled until the ’Print Bill’ button is clicked. This measure ensures that no data can be altered between the payment process and the creation of the transaction record.

### 3.3.6 Performance Designs

The system was intentionally designed to be lightweight, capable of running on low-performance laptops or all-in-one PCs. This design choice enhances its convenience for use alongside existing retail business processes. Additionally, the interfaces were optimized to display perfectly on low-resolution displays, ensuring that it can operate efficiently on a range of devices. During the programming process, a significant effort was made to specify the exact addresses within the package for almost all the code components. This approach minimizes loading times and keeps operating times very low, resulting in a responsive and efficient system.

### 3.3.7 Error Handling and Recovery Design

The system is equipped with robust error and exception handling mechanisms, which were extensively implemented during the coding process. Exception handling is applied wherever MySQL code is executed, as well as for parsing data types. Decision-making mechanisms are strategically employed in various processes to prevent the execution of potentially problematic procedures. Data type checks are systematically performed before proceeding, and reset buttons are provided where possible to address any potential system stalls during resetting. These comprehensive error handling measures ensure that the system remains stable and resilient under any operational load, minimizing the risk of crashes.

## 3.4 Phase 3 : Implementation

Following the Design phase, the coding process commenced. Objective of this phase was Translating the design into actual code. The coding was carried out using Java as the primary language and SQL[5] and HTML[7] as secondary languages. Notably, coding was executed using a basic text editor without the aid of integrated development environments (IDEs) or supportive software tools.

The coding process followed a systematic order. First, the graphical user interfaces (GUIs) were coded, and subsequently, methods were added to the respective buttons. After the non-database-related methods were implemented, the focus shifted to creating and structuring the database using MySQL.

The development process was divided into distinct stages, with the primary components(Main, Login, Administrator, and Cashier) being the initial focus. Following their completion, work began on coding the report windows and the stock resetting mechanism, further advancing the functionality of the system.

To facilitate graph generation within this system, the JFreeChart Package, developed by David Gilbert and available under the LGPL license, was employed. JFreeChart is an open-source framework for Java that enables the creation of a wide range of both interactive and non-interactive charts.

Additionally, for database connectivity to MySQL, the Java MySQL Connector Package was utilized. MySQL Connector/J, which implements the Java Database Connectivity (JDBC) API, allows Java client applications to connect to MySQL databases. The open-source version of MySQL Connector/J is provided under the General Public License (GPL).

To achieve a modern user interface (UI) design, the FlatLaf Package developed by FormDev Software was employed. FlatLaf is a contemporary open-source cross-platform Look and Feel for Java Swing desktop applications and is licensed under the Apache 2.0 License.

## 3.5 Phase 4 : Testing

Subsequently, the testing phase was initiated with the objective of ensuring that the software meets the specified requirements and is free of defects. For testing, a dedicated test database was generated using a finely-tuned Java program to produce realistic data. This test data consisted of 1500 transaction records, 55 cashier performance records, 25 stock items, 4 customer records, and 5 employee records.

The test database was then employed to run the system under various combinations of transaction steps and administrator’s operations, repeated 20 times. The system adeptly handled all operations, providing predefined messages in cases of unnecessary user actions while maintaining stability and avoiding any crashes. Notably, no SQL exceptions were encountered during testing.

Furthermore, the system demonstrated compatibility and seamless operation on both Windows and Linux operating systems.

At the culmination of the testing phase, it was established that the system had achieved a state of readiness for deployment.

## 3.6 Phase 5 : Deployment

Now, the system is prepared for deployment in a retail store environment using the parallel deployment method. Parallel deployment testing is a strategy that involves implementing a new system or software while keeping the old one running concurrently. This approach is often employed to mitigate the risk of failures, errors, or user dissatisfaction that may arise during a transition. The primary goal of this phase is to make the software available for use in retail stores. If the deployment proves successful, the new system has the potential to replace existing methods. However, it’s important to note that as this project does not involve the actual implementation in a real-world store, the Deployment Phase has not been initiated.

## 3.7 Phase 6 : Maintenance and Support

The objective of this phase is to address issues, enhance functionalities, and provide ongoing support. However, it is important to note that there was no implementation, and as a result, maintenance is not expected to be required. In the event that maintenance becomes necessary, the following steps should be taken:

Regularly monitor the system’s performance, ensuring it operates without issues.
Address and fix any software bugs or glitches that may emerge during real-world usage.
Implement software updates to enhance system features or introduce new functionalities based on user feedback.
Regularly back up critical data to prevent data loss in case of unforeseen events.
Keep the system secure by applying security patches and updates, and addressing vulnerabilities promptly.
Provide support to users, addressing their queries or issues related to system usage.
Maintain up-to-date documentation for the system, helping both users and future developers understand the software’s operation.
If necessary, provide training to new users or employees to ensure they can effectively use the system.
Continuously assess and optimize the system’s performance to ensure efficiency.
Encourage and collect user feedback for improvements and feature requests.
Ensure that the system complies with relevant regulations and standards.
Develop and maintain a disaster recovery plan to safeguard the system against potential disasters.
If third-party software components are used, maintain relationships with vendors for support and updates.
Periodically evaluate the system’s performance and its alignment with business objectives.

this Completes the Development Process of this Retail Store Management System. The Source Code of the system is added to the report under the Appendix Chapter.

## 4. Validation of Problem Solution Efficacy

In the event of potential challenges in implementing the system in a real retail store, the system was retested within the parameters of an hypothetical Retail store with pre-defined issues. The system exhibited robust performance, effectively resolving the majority of the problems identified during the analysis phase. It is recognized, however, that real-world deployment may introduce additional challenges, necessitating ongoing adjustments. Nonetheless, the system demonstrates proficiency in addressing previously identified issues.
