package ru.klingenberg.portlib;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class IndexCalculatorComponent extends JPanel {

    private final JTextArea resultPane = new JTextArea();
    private final JPanel inputFieldsPanel = new JPanel();
    private final JPanel panel = this;
    private final List<JTextField> inputFields;
    private final IndexCalculator indexCalculator = new IndexCalculator();
    private Integer[][] result;

    public IndexCalculatorComponent() {
        panel.setLayout(new GridBagLayout());
        inputFields = new ArrayList<>();

        JPanel buttons = new JPanel();
        buttons.setLayout(new GridLayout(2, 1));

        buttons.add(addButton());
        buttons.add(deleteButton());

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.gridwidth = 1;
        panel.add(buttons, gbc);

        JTextField inputField = new JTextField(30);
        inputFields.add(inputField);
        inputFieldsPanel.setLayout(new GridLayout(0, 1));
        inputFieldsPanel.add(inputField);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        panel.add(inputFieldsPanel);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        panel.add(calculateButton(), gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        JScrollPane scrollPane = new JScrollPane(resultPane, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setPreferredSize(new Dimension(400, 100));
        resultPane.setEditable(false);
        resultPane.setLineWrap(true);
        panel.add(scrollPane, gbc);

        panel.revalidate();
    }

    public Integer[][] getResult() {
        return result;
    }

    private JButton addButton() {
        JButton addButton = new JButton("+");
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField newTextField = new JTextField(30);
                inputFields.add(newTextField);
                inputFieldsPanel.add(newTextField);
                panel.revalidate();
            }
        });
        return addButton;
    }

    private JButton deleteButton() {
        JButton deleteButton = new JButton("-");
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = inputFields.size();
                if (i > 1) {
                    inputFields.remove(i - 1);
                    inputFieldsPanel.remove(i - 1);
                }
                panel.revalidate();
            }
        });
        return deleteButton;
    }

    private JButton calculateButton() {
        JButton calculateButton = new JButton("calculate indexes");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] strings = new String[inputFields.size()];
                for (int i = 0; i < inputFields.size(); i++) {
                    strings[i] = inputFields.get(i).getText();
                }
                try {
                    result = indexCalculator.allPossibleCombinations(strings);
                } catch (RuntimeException exception) {
                    resultPane.setText(exception.getMessage());
                    return;
                }
                StringBuilder stringBuffer = new StringBuilder();
                for (int i = 0; i < result.length; i++) {
                    stringBuffer.append("[");
                    for (int x = 0; x < result[i].length; x++) {
                        stringBuffer.append(result[i][x])
                                .append(x == result[i].length - 1 ? "" : ", ");
                    }
                    stringBuffer.append(i == result.length - 1 ? "]" : "]; ");
                }
                resultPane.setText(stringBuffer.toString());
            }
        });
        return calculateButton;
    }
//        JPanel panel = this;
//        IndexCalculatorPanel form = new IndexCalculatorPanel();
//        panel.add(form);
//        inputFields = new ArrayList<>();
//        List<JTextField> inputFields = new ArrayList<>();
//        panel.setLayout(new GridLayout(0,2));
//        GridBagConstraints gridBagConstraints = new GridBagConstraints();
//        JPanel textFieldsPanel = new JPanel();
//        textFieldsPanel.setLayout(new GridLayout(0,1));
//
//        JButton addString = new JButton("+");
//        addString.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                JTextField newTextField = new JTextField(30);
//                inputFields.add(newTextField);
//                textFieldsPanel.add(newTextField);
//                panel.revalidate();
//            }
//        });
//        panel.add(addString);
//        panel.add(textFieldsPanel);
//        JTextField textField = new JTextField(30);
//        textField.setEditable(true);
//        textFieldsPanel.add(textField);
//        inputFields.add(textField);
//
//        JButton calculateButton = new JButton("Calculate");
//        panel.add(calculateButton);
//
//        JTextPane result = new JTextPane();
//        panel.add(result);
//
//        calculateButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                for (JTextField textF : inputFields){
//                    String text = textF.getText();
//                    String[] strings = new String[inputFields.size()];
//                    for (int i = 0; i<inputFields.size(); i++){
//                        strings[i] = inputFields.get(i).getText();
//                    }
//                    Integer[][] integers = indexCalculator.allPossibleCombinations(strings);
//                    StringBuilder stringBuffer = new StringBuilder();
//                    for (int i = 0; i< integers.length; i++){
//                        stringBuffer.append("[");
//                        for (int x = 0; x < integers[i].length; x++){
//                            stringBuffer.append(integers[i][x])
//                                    .append(x == integers[i].length -1 ? "" : ", ");
//                        }
//                        stringBuffer.append(i == integers.length-1 ? "]" : "]; ");
//                    }
//                    result.setText(stringBuffer.toString());
//                }
//                panel.revalidate();
//            }
//        });
//    }
}
