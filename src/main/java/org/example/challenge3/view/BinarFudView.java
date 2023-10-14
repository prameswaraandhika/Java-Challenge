package org.example.challenge3.view;

import javax.swing.*;
import java.awt.*;
import javax.swing.table.DefaultTableModel;
import java.text.NumberFormat;
import java.util.*;
import java.util.List;

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
	private JMenuBar menuBar;
	private JButton bayarButton;
	private JButton batalButton;
	private JButton totalPesanan;
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
		menuItemToProduct = new HashMap<>();
		serviceProduct = new ServiceProductImpl();

		// ... (rest of your code)

		// Create a menu bar
		menuBar = new JMenuBar();

		JMenu fileMenu = new JMenu("File");

		// Create a "File Menu Item" menu item
		JMenuItem viewFileMenuItem = new JMenuItem("Ekspor to excel");
		viewFileMenuItem.addActionListener(e -> {
			// Add code to open and display the file
			// You can create a new JFrame for the chart here
		});

		fileMenu.add(viewFileMenuItem);

		// Create a "Chart" menu
		JMenu chartMenu = new JMenu("Statistik");

		// Create a "View Chart" menu item
		JMenuItem viewChartMenuItem = new JMenuItem("Lihat Statistik");
		viewChartMenuItem.addActionListener(e -> {
			// Add code to open and display the chart
			// You can create a new JFrame for the chart here
		});

		chartMenu.add(viewChartMenuItem);

		// Create a "Management" menu
		JMenu managementMenu = new JMenu("Manajemen");

		// Create a "Manage Products" menu item
		JMenuItem manageProductsMenuItem = new JMenuItem("Manajemen Makanan");
		manageProductsMenuItem.addActionListener(e -> {
			// Add code to open a management window for products
			// You can create a new JFrame for product management here
		});

		managementMenu.add(manageProductsMenuItem);

		// Add the "File", "Chart" and "Management" menus to the menu bar
		menuBar.add(fileMenu);
		menuBar.add(chartMenu);
		menuBar.add(managementMenu);


		// Create a table with column names
		String[] columnNames = {"Nama", "Jumlah", "Harga"};
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
			menuItemButton.setFont(new Font("Times New Roman", Font.BOLD, 14));
			menuPanel.add(menuItemButton);
			menuItemToProduct.put(menuItemButton, product);


			menuItemButton.addActionListener(e -> {
				// Retrieve the associated product for the clicked menu item
				Product clickedProduct = menuItemToProduct.get(menuItemButton);
				if (clickedProduct != null) {
					// Show an input dialog to get the quantity
					String qtyInput = JOptionPane.showInputDialog(frame, "Masukkan jumlah untuk " + product.getProductName() + ":", "Input Jumlah", JOptionPane.QUESTION_MESSAGE);
					// If the user provides a valid quantity, add a row to the table
					if (qtyInput != null && !qtyInput.isBlank()) {
						try {
							int quantity = Integer.parseInt(qtyInput);
							String totalPriceIDR = controllerOrder.converterCurrencyRupiah(product.getPrice() * quantity);
							Object[] row = {product.getProductName(), quantity, totalPriceIDR};
							controllerOrder.addOrderDetail(product, quantity);
							tableModel.addRow(row);
						} catch (NumberFormatException ex) {
							JOptionPane.showMessageDialog(frame, "Input tidak sah.", "Error", JOptionPane.ERROR_MESSAGE);
						}
					} else {
						JOptionPane.showMessageDialog(frame, "Maaf! Pesanan Anda belum lengkap. Silakan masukkan jumlah yang valid.", "Pesanan Belum Selesai", JOptionPane.ERROR_MESSAGE);
					}
				}

			});

		}

		// Create a button for payment
		bayarButton = new JButton("BAYAR");
		bayarButton.setForeground(Color.GREEN);
		bayarButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		bayarButton.addActionListener(e -> {
			controllerOrder.order();
		});


		batalButton = new JButton("BATAL"); // Modify the text as needed
		batalButton.setForeground(Color.RED);
		batalButton.setFont(new Font("Times New Roman", Font.BOLD, 20));
		batalButton.addActionListener(e -> {
			int confirm = JOptionPane.showConfirmDialog(frame, "Kamu yakin ingin membatalkan semua pesanan?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
			if (confirm == JOptionPane.YES_OPTION) {
				// User confirmed, clear the order
				controllerOrder.clearOrder();
			}
		});
		totalPesanan = new JButton("Total: 0"); // Modify the text as needed
		totalPesanan.setEnabled(false);
		totalPesanan.setFont(new Font("Times New Roman", Font.BOLD, 20));

		JPanel bayarAndTotalPanel = new JPanel(new GridLayout(1, 1));
		bayarAndTotalPanel.add(bayarButton);
		bayarAndTotalPanel.add(batalButton);
		bayarAndTotalPanel.add(totalPesanan);


		// Create a panel to hold the table and Bayar/Total buttons
		JPanel tableAndButtonPanel = new JPanel(new BorderLayout());
		tableAndButtonPanel.add(tableScrollPane, BorderLayout.CENTER);
		tableAndButtonPanel.add(bayarAndTotalPanel, BorderLayout.SOUTH);

		// Add components to the frame using BorderLayout
		frame.add(menuPanel, BorderLayout.WEST);
		frame.add(tableAndButtonPanel, BorderLayout.CENTER);

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(1000, 600); // Adjust the frame size as needed
		frame.setJMenuBar(menuBar);
		frame.setVisible(true);
	}



}
