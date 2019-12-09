import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedList;

public class CE203_2018_Ex2 implements ActionListener {

    //Ex 2 class.

    LinkedList<String> wordList = new LinkedList<>();
    JTextField uInput;
    JButton addBut, displayBut, searchBut, remlastBut, remallBut, clearBut;
    JLabel wDisplay, studentInfo;
    JPanel northPanel, southPanel;

    private void initUi()
    {
        // Method for creation of GUI elements; buttons, JPanels, labels and text fields.
        // Method presents the GUI in the desired format including setting the grid layout and changing alignments.

        JFrame frame = new JFrame();

        frame.setResizable(false);

        frame.setMinimumSize(new Dimension(900,150));
        frame.setTitle("Exercise 2");
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));
        frame.setSize(400,150);
        northPanel = new JPanel();
        southPanel = new JPanel();
        northPanel.setLayout(new GridLayout(1,6));
        southPanel.setLayout(new GridLayout(3, 1));
        frame.add(northPanel);
        frame.add(southPanel);


        uInput = new JTextField();

        addBut = new JButton("Add word");
        displayBut = new JButton("Show all words");
        searchBut = new JButton("Search a word");
        remlastBut = new JButton("Remove last word");
        remallBut = new JButton("Remove all words");
        clearBut = new JButton("Reset list");

        wDisplay = new JLabel();
        studentInfo = new JLabel("Name: Alex Sikorski       Registration Number: 1603930");
        studentInfo.setAlignmentX((float) 0.5);


        northPanel.add(addBut);
        northPanel.add(displayBut);
        northPanel.add(searchBut);
        northPanel.add(remlastBut);
        northPanel.add(remallBut);
        northPanel.add(clearBut);
        southPanel.add(wDisplay);
        southPanel.add(uInput);
        southPanel.add(studentInfo);

        frame.setVisible(true);
    }

    public CE203_2018_Ex2(){

        // Constructor allows the GUI to function as intended with the use of action commands.
        // These are later implemented with action.equals which allow the buttons to have unique actions.

        initUi();

        addBut.setActionCommand("addBt");
        displayBut.setActionCommand("displayBt");
        searchBut.setActionCommand("searchBt");
        remlastBut.setActionCommand("removeLastBt");
        remallBut.setActionCommand("removeAllBt");
        clearBut.setActionCommand("clearBt");

        addBut.addActionListener(this);
        displayBut.addActionListener(this);
        searchBut.addActionListener(this);
        remlastBut.addActionListener(this);
        remallBut.addActionListener(this);
        clearBut.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        // Method for button handler. Button handlers that match commands have unique functions.
        // if, else if, else are used to manage the actions.
        // Validation with he use of .matches and setting a string pattern to "[a-zA-Z][a-zA-Z-0-9]*" which allows for only letters and numbers.
        // Indexing with for loops used to select values in the array. Specifically useful in removing last entry and searching for words.
        // Variable uInput holds the users input.

        String action = e.getActionCommand();
        if (action.equals("addBt"))
        {
            String input = uInput.getText();
            String pattern = "[a-zA-Z][a-zA-Z-0-9]*";
            if (input.matches(pattern))
            {
                wordList.add(input);
                wDisplay.setText("The word '" + input + "' has been appended to the list");
            }
            else
                {
                wDisplay.setText("The string '" + input + "' needs to be a valid word in order to be added. Please try again.");
                }
        }
        else if (action.equals("displayBt"))
        {
            String displayString = "";
            String input = uInput.getText();
            String pattern = "(.)*(\\w)*([d" + input + "])";
            for (int i = 0; i < wordList.size(); i++)
            {
                if (wordList.get(i).matches(pattern))
                {
                    displayString += wordList.get(i) + " ";
                }
            }

            wDisplay.setText("Current words in the list are: " + displayString);

        }
        else if (action.equals("searchBt"))
        {
            int aCount = 0;
            String outputString = "";
            ArrayList<Integer> positions = new ArrayList<>();
            String input = uInput.getText().toLowerCase();
            if (input == null || input.equals(""))
            {
                outputString = "The number of words in the list: " + wordList.size() + " words.";
            }
            else
                {
                for (int i = 0; i < wordList.size(); i++)
                {
                    if (input.equals(wordList.get(i).toLowerCase()))
                    {
                        aCount++;
                        positions.add(i);
                    }
                }
                if (aCount == 0)
                {
                    outputString = "The word is not in the list.";
                } else
                    {
                    outputString = "The word you are looking for has " + aCount + " entries on positions ";
                    for (int i = 0; i < positions.size(); i++)
                    {
                        outputString += positions.get(i) + " ";
                    }
                }
            }

            wDisplay.setText(outputString);

        }
        else if(action.equals("removeLastBt"))
        {
            String input = uInput.getText().toLowerCase();
            int removalCount = 0;
            for (int i = wordList.size()-1; i>=0; i--)
            {
                if(wordList.get(i).toLowerCase().equals(input))
                {
                    wordList.remove(i);
                    removalCount++;
                    break;
                }
            }
            if (removalCount==1)
            {
                wDisplay.setText("The last entry " + input + " has been removed.");
            }
            else
                {
                wDisplay.setText("Could not find a word within the list. Please add a word.");
            }

        }
        else if(action.equals("removeAllBt"))
        {
            String input = uInput.getText();

            int removalCount = 0;

            for (int i = wordList.size()-1; i>=0; i--)
            {
                if(wordList.get(i).equals(input))
                {
                    wordList.remove(i);
                    removalCount++;
                }
            }
            if (removalCount>0)
            {
                wDisplay.setText("All words successfully removed.");
            }
            else
                {
                wDisplay.setText("Could not find any words within the list. Please add words and try again.");
            }
        }
        else if(action.equals("clearBt"))
        {
            wordList.removeAll(wordList);
            wDisplay.setText("List successfully cleared.");
        }

    }
}
