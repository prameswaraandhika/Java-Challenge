package org.example.challenge3.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import org.example.challenge3.controller.ControllerOrder;
import org.example.challenge3.controller.ControllerProduct;
import org.example.challenge3.model.Product;
import org.example.challenge3.service.ServiceProduct;
import org.example.challenge3.service.ServiceProductImpl;

@Getter
public class BinarFudView {
	private JFrame frame;
	private JTable table;
	private DefaultTableModel tableModel;
	private Map<JButton, Product> menuItemToProduct;
	private ServiceProduct serviceProduct;
	private Product clickedProduct;
	private ControllerOrder controllerOrder;
	private ControllerProduct controllerProduct;



	public BinarFudView() {
		controllerOrder = new ControllerOrder(this);
		controllerProduct = new ControllerProduct(this);
		frame = new JFrame("BinarFud");
		menuItemToProduct = new HashMap();
		serviceProduct = new ServiceProductImpl();

		// Create a table with column names
		String[] columnNames = {"Name", "Qty", "Price"};
		tableModel = new DefaultTableModel(columnNames, 0);
		table = new JTable(tableModel);

		// Create a JScrollPane to hold the table and set its preferred size
		JScrollPane tableScrollPane = new JScrollPane(table);
		tableScrollPane.setPreferredSize(new Dimension(300, 200)); // Adjust the dimensions as needed

		// Create a panel for menu buttons
		JPanel menuPanel = new JPanel(new GridLayout(0, 2));

		// Simulate loading products from a service
		List<Product> products = controllerProduct.getAllProducts();

		// Create a JButton for each product and associate with products
		for (Product product : products) {
			JButton menuItemButton = new JButton(product.getProductName());
			menuPanel.add(menuItemButton);
			menuItemToProduct.put(menuItemButton, product);

			menuItemButton.addActionListener(e -> {
				// Retrieve the associated product for the clicked menu item
				Product clickedProduct = menuItemToProduct.get(menuItemButton);

				controllerOrder.createOrder(clickedProduct);
			});

		}

		// Create a button for payment
		JButton bayarButton = new JButton("Bayar");
		bayarButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JButton totalPesanan = new JButton("Total: 1,000,000"); // Modify the text as needed
		totalPesanan.setEnabled(false);
		totalPesanan.setFont(new Font("Times New Roman", Font.BOLD, 20));
		JPanel bayarAndTotalPanel = new JPanel(new GridLayout(2, 1));
		bayarAndTotalPanel.add(totalPesanan);
		bayarAndTotalPanel.add(bayarButton);

		// Create a panel to hold the table and Bayar/Total buttons
		JPanel tableAndButtonPanel = new JPanel(new BorderLayout());
		tableAndButtonPanel.add(tableScrollPane, BorderLayout.CENTER);
		tableAndButtonPanel.add(bayarAndTotalPanel, BorderLayout.SOUTH);

		// Add components to the frame using BorderLayout
		frame.add(menuPanel, BorderLayout.WEST);
		frame.add(tableAndButtonPanel, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 400); // Adjust the frame size as needed
		frame.setVisible(true);
	}


}
