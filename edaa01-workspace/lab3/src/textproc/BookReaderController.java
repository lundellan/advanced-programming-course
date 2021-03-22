package textproc;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.BorderLayout;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class BookReaderController {
		
	public BookReaderController(GeneralWordCounter counter) {
		SwingUtilities.invokeLater(() -> createWindow(counter, "BookReader", 200, 300));
	}
	
	private void createWindow(GeneralWordCounter counter, String title, int width, int height) {
		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();
		
		// pane är en behållarkomponent till vilken de övriga komponenterna (listvy, knappar etc.) ska läggas till.
		
		SortedListModel<Map.Entry<String, Integer>> windowListContent = new SortedListModel<>(counter.getWordList());
		JList<Map.Entry<String, Integer>> windowListDisplayed = new JList<>(windowListContent);
		windowListDisplayed.setLayoutOrientation(JList.VERTICAL);
		JScrollPane scrollPane = new JScrollPane(windowListDisplayed);
		
		JPanel controls = new JPanel();
		
		JButton button1 = new JButton();
		button1.add(new JLabel("Alphabetic"));
		button1.addActionListener(event -> {
			windowListContent.sort((o1, o2) -> o1.getKey().compareTo(o2.getKey()));
		});
		
		JButton button2 = new JButton();
		button2.add(new JLabel("Frequency"));
		button2.addActionListener(event -> {
			windowListContent.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));
		});
		
		JTextField textField = new JTextField();
		textField.setColumns(30);
		
		
		JButton button3 = new JButton();
		button3.add(new JLabel("Find"));
		button3.addActionListener(event -> {
			String searchWord = textField.getText().trim().toLowerCase();
			List<String> compareList = new ArrayList<>();
			for (int i = 0; i < windowListContent.getSize(); i++)	{
				compareList.add(windowListContent.getElementAt(i).getKey());
			}
			int index = 0;
			for (String s : compareList)	{
				if (s.equals(searchWord))	{
					windowListDisplayed.ensureIndexIsVisible(index);
					windowListDisplayed.setSelectedIndex(index);
					return;
				}
				index++;
			}
			Object[] options = { "OK" };
			JOptionPane.showOptionDialog(null, "Kunde inte hitta ordet", "Warning",
			JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE,
			null, options, options[0]);
		});
		
		textField.addActionListener(button3.getActionListeners()[0]);
		
		controls.add(button1);
		controls.add(button2);
		controls.add(textField);
		controls.add(button3);
		
		scrollPane.setPreferredSize(new Dimension(width, height));
		pane.add(scrollPane, BorderLayout.CENTER);
		pane.add(controls, BorderLayout.SOUTH);
		
		frame.pack();
		frame.setVisible(true);
		
	}
		
}
