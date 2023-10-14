package org.example.challenge3;

import org.example.challenge3.view.BinarFudView;

import javax.swing.*;

public class Main {

		public static void main(String[] args) {
			try {
				//here you can put the selected theme class name in JTattoo
				UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException |
			         UnsupportedLookAndFeelException ex) {
				java.util.logging.Logger.getLogger(BinarFudView.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
			}
			SwingUtilities.invokeLater(BinarFudView::new);
		}
}
