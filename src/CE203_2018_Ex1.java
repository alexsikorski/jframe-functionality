import javax.swing.*;
import java.awt.*;
import java.awt.Color;


public class CE203_2018_Ex1 {

    // Ex 1 class.

    private JTextField txtFieldRed;
    private JTextField txtFieldGreen;
    private JTextField txtFieldBlue;
    private JButton displayBut;
    private JButton resetBut;
    private JLabel wMsg;
    private JLabel eMsg;
    private int notNum = 0;


    private void initUi(){

        // Method for creation of GUI elements; buttons, JPanels, labels and text fields.
        // Method presents the GUI in the desired format including setting the grid layout and changing alignments.

        JFrame frame = new JFrame("Exercise 1");

        frame.setResizable(false);

        txtFieldRed = new JTextField();
        txtFieldGreen = new JTextField();
        txtFieldBlue = new JTextField();

        JPanel northPanel = new JPanel();
        JPanel southPanel = new JPanel();
        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.Y_AXIS));
        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout gridLayout = new GridLayout(2,3,5,5);
        southPanel.setLayout(gridLayout);
        BoxLayout boxLayout = new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS);
        frame.setLayout(boxLayout);
        frame.add(northPanel);
        frame.add(southPanel);

        eMsg = new JLabel("");
        wMsg = new JLabel("CE203 Assignment 1, submitted by: 1603930");

        eMsg.setPreferredSize(new Dimension(50,20));
        wMsg.setPreferredSize(new Dimension(20,20));

        wMsg.setForeground(Color.BLUE);

        displayBut = new JButton("Change Colour");
        resetBut = new JButton("Reset");

        northPanel.setAlignmentX((float)0.5);
        northPanel.setAlignmentY((float)0.5);
        resetBut.setAlignmentX((float) 0.5);
        wMsg.setAlignmentX((float)0.5);
        eMsg.setAlignmentX((float)0.5);

        northPanel.add(resetBut);
        northPanel.add(wMsg);
        northPanel.add(new JPanel());
        northPanel.add(eMsg);

        southPanel.add(txtFieldRed);
        southPanel.add(txtFieldGreen);
        southPanel.add(txtFieldBlue);
        southPanel.add(new JPanel());
        southPanel.add(displayBut);

        frame.setVisible(true);
        frame.repaint();
    }

    private int valueValid(String value,JTextField txtField)
    {
        // Method validates value that is entered into text fields according to specification.
        // Method will returns integer. If integer = -1 then the input is not an integer.
        // String value = text acquired from the text field.
        // JTextField  txtField = text field object that will be validated.

        int cValue = -1;
        try
        {
            // Validation allows values above 255 to be changed into 255 and values below 0 to be changed into 200.
            // Also prevents crashing, checks if input is not an integer.

            cValue = Integer.parseInt(value);
            if (cValue > 255)
            {
                cValue = 255;
            }

            else if (cValue < 0)
            {
                cValue= 200;
            }
            txtField.setText(Integer.toString(cValue));
            eMsg.setText("");
        }

        catch(NumberFormatException e)
        {
            // If value is not an integer, notNum is added to which is used for an error later.

            if (!txtField.getText().equals(""))
            {
                notNum += 1;
            }

            txtField.setText("");

        }

        finally
        {
            return cValue;
        }
    }

    public CE203_2018_Ex1()
    {

        // Constructor implemented which allows the UI to function as intended.

        initUi();
        displayBut.addActionListener(e ->
        {
            int red = valueValid(txtFieldRed.getText(),txtFieldRed);
            int green = valueValid(txtFieldGreen.getText(),txtFieldGreen);
            int blue = valueValid(txtFieldBlue.getText(),txtFieldBlue);

            if (red != -1 && green != -1 && blue != -1)
            {
                Color textColor = new Color(red, green, blue);
                wMsg.setForeground(textColor);
                wMsg.setText("CE203 Assignment 1, submitted by: 1603930");
            }

            wMsg.repaint();

            if (notNum == 0)
            {
                eMsg.setText("");
            }

            else
                {
                eMsg.setText("Error! You can only enter an integer value.");
                }
            notNum = 0;
        });

        resetBut.addActionListener(e->
        {
            txtFieldRed.setText("");
            txtFieldGreen.setText("");
            txtFieldBlue.setText("");
            wMsg.setForeground(Color.BLUE);
            eMsg.setText("");
        });
    }
}
