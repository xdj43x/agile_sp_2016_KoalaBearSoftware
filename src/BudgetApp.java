// Imports
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Class to test
public class BudgetApp extends JFrame {

    private final CardLayout cLayout;
    private final JPanel mainPane;

    public final String PANEL = "Main Panel";
    public final String SAVED_Panel = "Saved Panel";

    boolean isPanelVisible;

    // create text fields, labels, and button
    private JButton calculate;
    private JButton saveBudget;

    int numberOfButtonClicks = 0;

    private JLabel expenses;
    private JTextField expensesTextField;
    private JLabel budget;
    private JTextField budgetTextField;

    private JLabel output;
    private JLabel randomText = new JLabel("Monthly Budget");
    private JLabel[] saved = new JLabel[5];


    // Create JFrame size
    private final int WINDOW_WIDTH = 350;
    private final int WINDOW_HEIGHT = 300;

    // Create JFrame view
    public BudgetApp() {

        mainPane = new JPanel();
        mainPane.setPreferredSize(new Dimension(400,300));
        cLayout = new CardLayout();
        mainPane.setLayout(cLayout);

        setTitle("Budget App");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setResizable(false);
        setBackground(Color.blue);

        JPanel panel = new JPanel();
        getContentPane().add(panel);

        // Set properties for all labels, text fields, and button
        expenses = new JLabel("Monthly Expenses");
        expensesTextField = new JTextField();
        budget = new JLabel("Monthly budget");
        budgetTextField = new JTextField();
        output = new JLabel("");
        calculate = new JButton("Calculate");
        saveBudget = new JButton("Save");
        // Set sizes
        expenses.setPreferredSize(new Dimension(300, 30));
        expensesTextField.setPreferredSize(new Dimension(300, 30));
        budget.setPreferredSize(new Dimension(300, 30));
        budgetTextField.setPreferredSize(new Dimension(300, 30));
        output.setPreferredSize(new Dimension(300, 30));
        calculate.setPreferredSize(new Dimension(300, 30));
        saveBudget.setPreferredSize(new Dimension(300, 30));

        // Action listener for button
        calculate.addActionListener(new calculateActionListener());
        saveBudget.addActionListener(new saveBudgetActionListener());

        // Add everything to the panel
        panel.add(expenses);
        panel.add(expensesTextField);
        panel.add(budget);
        panel.add(budgetTextField);
        panel.add(output);
        panel.add(calculate);
        panel.add(saveBudget);
        saveBudget.setVisible(false);

        JPanel savedLayout = new JPanel();
        getContentPane().add(savedLayout);

        for (int i = 0;i < 5; i++) {
            saved[i] = randomText;
            saved[i].setPreferredSize(new Dimension(300, 30));
            savedLayout.add(saved[numberOfButtonClicks]);
        }

        savedLayout.setVisible(false);

        mainPane.add(PANEL, panel);
        mainPane.add(SAVED_Panel, savedLayout);
        showMainPane();

        JButton button = new JButton("Show Saved");
        button.addActionListener(e -> switchPanes() );
        setLayout(new BorderLayout());
        add(mainPane,BorderLayout.CENTER);
        add(button,BorderLayout.SOUTH);
        pack();
        setVisible(true);
    }

    void switchPanes() {
        if (isPanelVisible) {showSavedPane();}
        else { showMainPane();}
    }

    void showMainPane() {
        cLayout.show(mainPane, PANEL);
        isPanelVisible = true;
    }

    void showSavedPane() {
        cLayout.show(mainPane, SAVED_Panel);
        isPanelVisible = false;
    }


    // Main method to start the panel
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new BudgetApp().setVisible(true);
            }
        });
    }

    // Button press calls this method
    private class calculateActionListener implements ActionListener {

        // When button is pressed
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String expensesString = expensesTextField.getText();
                String budgetString = budgetTextField.getText();
                int expensesNumber = Integer.parseInt(expensesString);
                int budgetNumber = Integer.parseInt(budgetString);


                double total = budgetNumber - expensesNumber;

                if (total < 0) {
                    output.setText("Your over budget!! " + String.valueOf(total));
                } else {
                    output.setText("Monthly Spending Limit = " + String.valueOf(total));
                }
                saveBudget.setVisible(true);
            }
            catch (Exception c) {
                output.setText("Only Numbers and nothing blank!");
            }

        }
    }

    private class saveBudgetActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String expensesString = expensesTextField.getText();
                String budgetString = budgetTextField.getText();
                int expensesNumber = Integer.parseInt(expensesString);
                int budgetNumber = Integer.parseInt(budgetString);

                int total = budgetNumber - expensesNumber;

                if(numberOfButtonClicks <= 0) {
                    saved[0].setText("Monthly Budget " + total);
                    numberOfButtonClicks++;
                } else if (numberOfButtonClicks == 1) {
                    saved[1].setText("<html>" + saved[0].getText() + "Monthly Budget " + total + "</html>");
                    numberOfButtonClicks++;
                } else if (numberOfButtonClicks == 2) {
                    saved[2].setText("<html>"+  saved[1].getText() + "<br>Monthly Budget " + total + "</html>");
                    numberOfButtonClicks++;
                }

            }
            catch (Exception c) {
                output.setText("Only Numbers and nothing blank!");
            }
        }
    }

}

