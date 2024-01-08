//in this Java file, the only functions you need to complete are the following:
	//RegisterNewUser()
	//LoginWithCreds()
	//GetAllProducts()
	//GetSalesTotal()
	//SubmitOrder()
	//SubmitNewProduct()

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.*;

@SuppressWarnings({ "rawtypes", "unchecked", "serial" })
public class FinalProjectGUI extends JFrame {
	
	//global variables used by many functions
	GridBagConstraints gbc;
	CustomActionListener cal;
	ComboBoxActionListener cbal;
	String connection = "jdbc:mysql://localhost:3306/finalguiproject?";
	String user = "guest";
	String pass = "guest";
	
	//constructor
	public FinalProjectGUI() {

		setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.insets = new Insets(10,10,10,10);
		cal = new CustomActionListener();
		cbal = new ComboBoxActionListener();
		DisplaySplashScreen();
	}
	
	//this function removes all of the controls from the content pane;
	//this should not be called except by the pre-defined functions!!
	private void ClearScreen()
	{
		this.getContentPane().removeAll();		
	}
	
	
	
	
	
	
	//this screen is the first screen the user sees!
	JLabel welcomeMessage;
	JButton registerButton;
	JButton loginButton;
	private void DisplaySplashScreen()
	{
		ClearScreen();
		
		welcomeMessage = new JLabel("Welcome to the Shoe Store application!  What function do you want to do?");
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 2;
		this.add(welcomeMessage, gbc);
		
		registerButton = new JButton("Register New User");
		gbc.gridwidth = 1;
		gbc.gridx = 0;
		gbc.gridy = 1;
		registerButton.addActionListener(cal);
		registerButton.setName("register");
		this.add(registerButton, gbc);
		
		loginButton = new JButton("Login with Existing Credentials");
		gbc.gridx = 1;
		loginButton.addActionListener(cal);
		loginButton.setName("login");
		this.add(loginButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}
	
	
	
	
	//this screen lets a user sign up for an account!
	JLabel registerMessage;
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JTextField passwordField;
	JButton submitButton;
	JTextArea errorMessageArea;
	private void DisplayRegisterScreen()
	{
		ClearScreen();
		
		registerMessage = new JLabel("Please enter a new username and password below.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(registerMessage, gbc);
		
		usernameLabel = new JLabel("User Name:");
		gbc.gridy++;
		add(usernameLabel, gbc);
		
		usernameField = new JTextField(20);
		gbc.gridy++;
		add(usernameField, gbc);
		
		passwordLabel = new JLabel("Password:");
		gbc.gridy++;
		add(passwordLabel, gbc);
		
		passwordField = new JTextField(20);
		gbc.gridy++;
		add(passwordField, gbc);
		
		submitButton = new JButton("Submit");
		submitButton.setName("newUserRegister");
		submitButton.addActionListener(cal);
		gbc.gridy++;
		add(submitButton, gbc);
		
		errorMessageArea = new JTextArea(10,50);
		gbc.gridy++;
		add(errorMessageArea, gbc);
		
		//all 3 of these are necessary to display the new form!
		this.validate();
		this.pack();
		this.repaint();
	}
	
	
	
	
	
	
	
	
	
	
	
	//this function re-uses some of the controls from the Register User screen.
	//this lets any user signin to their account
	/*
	JLabel usernameLabel;
	JLabel passwordLabel;
	JTextField usernameField;
	JTextField passwordField;
	JButton submitButton;
	JTextArea errorMessageArea;
	*/
	JLabel loginLabel;
	private void DisplayLoginScreen()
	{
		ClearScreen();
		
		loginLabel = new JLabel("Please enter your credentials below.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(loginLabel, gbc);
		
		usernameLabel = new JLabel("User Name:");
		gbc.gridy++;
		add(usernameLabel, gbc);
		
		usernameField = new JTextField(20);
		gbc.gridy++;
		add(usernameField, gbc);
		
		passwordLabel = new JLabel("Password:");
		gbc.gridy++;
		add(passwordLabel, gbc);
		
		passwordField = new JTextField(20);
		gbc.gridy++;
		add(passwordField, gbc);
		
		submitButton = new JButton("Submit");
		submitButton.setName("loginWithCreds");
		submitButton.addActionListener(cal);
		gbc.gridy++;
		add(submitButton, gbc);
		
		errorMessageArea = new JTextArea(10,50);
		gbc.gridy++;
		add(errorMessageArea, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}
	
	
	
	
	
	
	
	//this screen is shown once a user successfully logs in.
	//if the user has the Role of admin (denoted by the value 1 in the table "Users"),
	//then they can see buttons to manage inventory and view a sales report.
	//otherwise, they can see a button to place an order.
	//finally, all users will be able to see a Logout button.
	JLabel selectFormLabel;
	JButton orderFormButton;
	JButton inventoryFormButton;
	JButton salesReportButton;
	JButton logoutButton;
	private void DisplayFormSelectScreen()
	{
		ClearScreen();
		
		selectFormLabel = new JLabel("Welcome, " + loggedInUserName + ", please select a form below.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(selectFormLabel, gbc);
		
		if(loggedInUserRole == 2)
		{
			orderFormButton = new JButton("Place an Order");
			orderFormButton.setName("placeOrder");
			orderFormButton.addActionListener(cal);
			gbc.gridy++;
			add(orderFormButton, gbc);
		}
		else if(loggedInUserRole == 1)
		{
			inventoryFormButton = new JButton("Manage Inventory");
			inventoryFormButton.setName("inventory");
			inventoryFormButton.addActionListener(cal);
			gbc.gridy++;
			add(inventoryFormButton, gbc);
			
			salesReportButton = new JButton("See Sales Report");
			salesReportButton.setName("salesReport");
			salesReportButton.addActionListener(cal);
			gbc.gridy++;
			add(salesReportButton, gbc);
		}
		
		logoutButton = new JButton("Log Out");
		logoutButton.setName("logout");
		logoutButton.addActionListener(cal);
		gbc.gridy++;
		add(logoutButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}
	

	//this screen allows a non-admin user (i.e. has a Role of "2" in the Users table)
	//to order a new item.
	//This screen's comboBox will be populated with data from the database!
	JLabel orderLabel;
	JLabel priceLabel;
	JComboBox productListComboBox;
	JTextField productQtyField;
	JButton placeOrderButton;
	JButton cancelButton;
	private void DisplayOrderScreen()
	{
		productNames = new ArrayList<String>();
		productPrices = new ArrayList<Double>();
		productIDs = new ArrayList<Integer>();
		
		GetAllProducts();
		
		ClearScreen();
		
		orderLabel = new JLabel("Please select the item you want to order and specify the quantity.");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(orderLabel, gbc);
		
		productListComboBox = new JComboBox(productNames.toArray());
		gbc.gridy++;
		productListComboBox.addActionListener(cbal);
		add(productListComboBox, gbc);
		
		//this might throw an error if there are no items in the arraylist.
		//that means you need to complete the "GetAllProducts()" function first :)
		priceLabel = new JLabel("Price: " + productPrices.get(0).toString());
		gbc.gridy++;
		add(priceLabel, gbc);
		
		productQtyField = new JTextField(5);
		gbc.gridy++;
		add(productQtyField, gbc);
		
		placeOrderButton = new JButton("Place Order");
		placeOrderButton.setName("submitOrder");
		placeOrderButton.addActionListener(cal);
		gbc.gridy++;
		add(placeOrderButton, gbc);
		
		cancelButton = new JButton("Go Back");
		cancelButton.setName("return");
		cancelButton.addActionListener(cal);
		gbc.gridy++;
		add(cancelButton, gbc);
		
		
		this.validate();
		this.pack();
		this.repaint();
	}
	
	
	
	
	//this screen allows an admin user (i.e. with role "1" in the Users table)
	//to add new products to the Product table
	JLabel productNameLabel;
	JTextField productNameField;
	JLabel productPriceLabel;
	JTextField productPriceField;
	//JButton submitButton;
	JButton returnButton;
	private void DisplayInventoryScreen()
	{
		ClearScreen();
		
		productNameLabel = new JLabel("Product Name:");
		gbc.gridx = 0;
		gbc.gridy = 0;
		add(productNameLabel, gbc);
		
		productNameField = new JTextField(25);
		gbc.gridy++;
		add(productNameField, gbc);
		
		productPriceLabel = new JLabel("Price:");
		gbc.gridy++;
		add(productPriceLabel, gbc);
		
		productPriceField = new JTextField(10);
		gbc.gridy++;
		add(productPriceField, gbc);
		
		submitButton = new JButton("Add New Product");
		submitButton.setName("submitNewProduct");
		submitButton.addActionListener(cal);
		gbc.gridy++;
		add(submitButton, gbc);
		
		returnButton = new JButton("Go Back");
		returnButton.setName("return");
		returnButton.addActionListener(cal);
		gbc.gridy++;
		add(returnButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}
	
	
	
	
	//this screen allows an admin user (i.e. with role "1" in the Users table)
	//to see a simple report of all sales logged in the database
	JLabel totalSalesLabel;
	//JButton returnButton;
	private void DisplaySalesReportScreen()
	{
		ClearScreen();
		
		GetSalesTotal();
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		totalSalesLabel = new JLabel("Total Sales: " + salesTotal);
		add(totalSalesLabel, gbc);
		
		returnButton = new JButton("Return");
		returnButton.setName("return");
		gbc.gridy++;
		returnButton.addActionListener(cal);
		add(returnButton, gbc);
		
		this.validate();
		this.pack();
		this.repaint();
	}
	

	
	//custom action listener for when a combobox changes values.
	//useful for displaying the price of the selected item.
	class ComboBoxActionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			JComboBox theBox = (JComboBox)e.getSource();
			int selectedIndex = theBox.getSelectedIndex();
			
			priceLabel.setText("Price: " + productPrices.get(selectedIndex).toString());
		}
		
	}
	
	
	//custom action listener for all buttons in the program.  dang!
	class CustomActionListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			
			JButton button = (JButton)e.getSource();
			String name = button.getName();
			
			//this button is in the splash screen
			if(name.equals("login"))
				DisplayLoginScreen();
			
			//this button is in the splash screen
			else if (name.equals("register"))
				DisplayRegisterScreen();
			
			//this button is in the Register New User screen
			else if (name.equals("newUserRegister"))
				RegisterNewUser();
			
			//this button is in the Login screen
			else if(name.equals("loginWithCreds"))
				LoginWithCreds();
			
			//this button is in the Form Select screen
			else if (name.equals("placeOrder"))
				DisplayOrderScreen();
			
			//this button is in the Form Select screen
			else if(name.equals("inventory"))
				DisplayInventoryScreen();
			
			//this button is in the Form Select screen
			else if(name.equals("salesReport"))
				DisplaySalesReportScreen();
			
			//this button is found on the Form Select screen
			else if(name.equals("logout"))
				DisplaySplashScreen();
			
			//this button is in the Order form for customers
			else if(name.equals("submitOrder"))
				SubmitOrder();
			
			//this button is found on both the Order form and the Inventory form
			else if(name.equals("return"))
				DisplayFormSelectScreen();
			
			//this button is found on the Inventory screen
			else if(name.equals("submitNewProduct"))
				SubmitNewProduct();
		}
		
	}
	

	/////////////////////////////////////////
	/////////////////////////////////////////
	//Begin completing the functions below!//
	//Everything else, above, do not touch!//
	/////////////////////////////////////////
	/////////////////////////////////////////

	//this function calls a stored procedure to insert a new user into the "User" table
	//Any error from the database (e.g. duplicate username) should appear in the "errorMessageArea" JTextArea.
	//On a success, the function "DisplaySplashScreen()" should be called.
	private void RegisterNewUser() {
	    String username = usernameField.getText();
	    String password = passwordField.getText();
	    
	    String conString = connection;

	    if (username.isEmpty() || password.isEmpty()) {
	        errorMessageArea.setText("Username and password cannot be empty.");
	        return;
	    }

	    try (Connection conn = DriverManager.getConnection(conString, user, pass)) {
	        // Prepare the stored procedure call
	        String storedProcedure = "{CALL addNewUser(?, ?, ?)}";
	        try (CallableStatement stmt = conn.prepareCall(storedProcedure)) {

	            stmt.setString(1, username);
	            stmt.setString(2, password);
	            stmt.setInt(3, 2); // assuming default user role is 2
	            stmt.executeUpdate();

	            errorMessageArea.setText("User registration successful!");

	            usernameField.setText("");
	            passwordField.setText("");

	        }
	    } catch (SQLException e) {
	        errorMessageArea.setText("Error: " + e.getMessage());
	    }
	}
	
	//this function calls a stored procedure to determine if there exists
	//a username / value pair in the database that matches the user's input.
	//If there is, the below variables should be populated, then call the DisplayFormSelectScreen() function.
	//If there is not, then the "errorMessageArea" (JTextArea) should display an 'error' message
	int loggedInUserID;	
	int loggedInUserRole;
	String loggedInUserName;
	private void LoginWithCreds() {
	    String username = usernameField.getText();
	    String password = passwordField.getText();

	    String conString = connection;

	    if (username.isEmpty() || password.isEmpty()) {
	        errorMessageArea.setText("Username and password cannot be empty.");
	        return;
	    }

	    try (Connection conn = DriverManager.getConnection(conString, user, pass)) {
	        // Prepare the stored procedure call
	        String storedProcedure = "{CALL LoginWithCreds(?, ?)}";
	        try (CallableStatement stmt = conn.prepareCall(storedProcedure)) {

	            stmt.setString(1, username);
	            stmt.setString(2, password);

	            // Execute the query
	            try (ResultSet resultSet = stmt.executeQuery()) {
	                if (resultSet.next()) {
	                    // User found, capture ID, username, and role
	                    loggedInUserID = resultSet.getInt("id");
	                    loggedInUserName = resultSet.getString("username");
	                    loggedInUserRole = resultSet.getInt("userRole");

	                    // Display the appropriate screen based on user role
	                    DisplayFormSelectScreen();

	                    // Clear the username and password fields
	                    usernameField.setText("");
	                    passwordField.setText("");
	                } else {
	                    errorMessageArea.setText("Invalid username/password.");
	                }
	            }
	        }
	    } catch (SQLException e) {
	        errorMessageArea.setText("Error: " + e.getMessage());
	    }
	}
	
	ArrayList<String> productNames;
	ArrayList<Double> productPrices;
	ArrayList<Integer> productIDs;
	private void GetAllProducts() {
	    productNames = new ArrayList<>();
	    productPrices = new ArrayList<>();
	    productIDs = new ArrayList<>();

	    String conString = connection;

	    try (Connection conn = DriverManager.getConnection(conString, user, pass)) {
	        String storedProcedure = "{CALL getAllProducts()}";
	        try (CallableStatement stmt = conn.prepareCall(storedProcedure)) {
	            ResultSet rs = stmt.executeQuery();

	            while (rs.next()) {
	                int productId = rs.getInt("id");
	                String productName = rs.getString("prodName");
	                double productPrice = rs.getDouble("price");

	                productIDs.add(productId);
	                productNames.add(productName);
	                productPrices.add(productPrice);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}
	
	double salesTotal = -1;
	private void GetSalesTotal() {
	    String conString = connection;

	    try (Connection conn = DriverManager.getConnection(conString, user, pass)) {
	        String storedProcedure = "{CALL sumOfSales(?)}";
	        try (CallableStatement stmt = conn.prepareCall(storedProcedure)) {
	            stmt.registerOutParameter(1, Types.DECIMAL);
	            stmt.execute();
	            salesTotal = stmt.getDouble(1);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	        // Handle the exception (e.g., log the error, show an error message, etc.)
	    }
	}

	private void SubmitOrder()
    {
        String username = user;
        String password = pass;

        int selectedProductIndex = productListComboBox.getSelectedIndex();
        int selectedProductID = productIDs.get(selectedProductIndex);
        int quantity = Integer.parseInt(productQtyField.getText());

       // double total = productPrices.get(selectedProductIndex) * quantity;

        String conString = connection;

        try (Connection conn = DriverManager.getConnection(conString, username, password)) {
            String storedProcedure = "{CALL newSale (?, ?, ?)}";
            try (CallableStatement stmt = conn.prepareCall(storedProcedure)) {
                stmt.setInt(1, loggedInUserID);
                stmt.setInt(2, selectedProductID);
                stmt.setInt(3 , quantity);

                stmt.executeUpdate();

                errorMessageArea.setText("Order submitted successfully!");
                DisplayFormSelectScreen();

                //productQtyField.setText("");
            }
        } catch (SQLException e) {
            // Handle any SQL exceptions
            errorMessageArea.setText("Error submitting order: " + e.getMessage());
        }

    }

	//this function calls a stored procedure to add a new product to the database
	private void SubmitNewProduct() {
	    String productName = productNameField.getText();
	    String productPriceStr = productPriceField.getText();

	    if (productName.isEmpty() || productPriceStr.isEmpty()) {
	        errorMessageArea.setText("Product name and price cannot be empty.");
	        return;
	    }

	    try {
	        double productPrice = Double.parseDouble(productPriceStr);

	        // Call stored procedure to insert a new record into "Product" table
	        String conString = connection;
	        try (Connection conn = DriverManager.getConnection(conString, user, pass)) {
	            String storedProcedure = "{CALL newProduct(?, ?)}";
	            try (CallableStatement stmt = conn.prepareCall(storedProcedure)) {
	                stmt.setString(1, productName);
	                stmt.setDouble(2, productPrice);
	                stmt.executeUpdate();

	                errorMessageArea.setText("Product added successfully!");
	            }
	        }
	    } catch (NumberFormatException e) {
	        errorMessageArea.setText("Invalid product price. Please enter a valid number.");
	    } catch (SQLException e) {
	        errorMessageArea.setText("Error: " + e.getMessage());
	    }
	}

}