package Var2.Lab8;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Main {

    static JFrame jFrame = getJFrame();
    static int numb_rows_panel = 2;
    static JPanel jPanel = new JPanel(new GridLayout(numb_rows_panel, 2));
    static JPanel jPanel1 = new JPanel();
    static JMenuBar jMenuBar = new JMenuBar();
    static JMenu jMenu;

    public static void main(String[] args) throws IOException {
        Bank bank = createBank();

        JTabbedPane jTabbedPane = new JTabbedPane();
        jFrame.add(jTabbedPane);
        jTabbedPane.add(jPanel, "Info about branches");
        jTabbedPane.add(jPanel1, "Contributions");

        setJPanelBranch(bank);
        showJPanel1Contrib(bank);

        jFrame.setJMenuBar(jMenuBar);
        jMenu = new JMenu("Create");
        JMenuItem jMenuItem = new JMenuItem("Deposit");
        JMenuItem jMenuItem2 = new JMenuItem("Branch");
        jMenu.add(jMenuItem);
        jMenu.add(jMenuItem2);
        jMenuBar.add(jMenu);

        jMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jTextField = new JTextField();
                JTextField jTextField1 = new JTextField();
                JRadioButton jRadioButton = new JRadioButton("ShortTermInvest");
                JRadioButton jRadioButton1 = new JRadioButton("LongTermInvest");
                ButtonGroup buttonGroup = new ButtonGroup();
                buttonGroup.add(jRadioButton);
                buttonGroup.add(jRadioButton1);

                DefaultListModel<Branch> defaultListModel = new DefaultListModel<>();
                defaultListModel.addAll(bank.getBranchList());
                JList<Branch> branchJList = new JList<>(defaultListModel);

                final JComponent[] inputs = new JComponent[]{
                        new JLabel("Full Name"),
                        jTextField,
                        new JLabel("Deposit amount"),
                        jTextField1,
                        new JLabel("Type"),
                        jRadioButton,
                        jRadioButton1,
                        new JLabel("Branch "),
                        branchJList
                };

                //вызов диалога
                int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
                if(result ==0) {

                    // определяем тип вклада
                    if (jRadioButton.isSelected()) {
                        Deposit contribution =
                                new ShortTermInvest(jTextField.getText(), Integer.parseInt(jTextField1.getText()));

                        //записываем в филиал новый вклад
                        branchJList.getSelectedValue().addContrib(contribution);
                    }else{
                        Deposit contribution =
                                new LongTermInvest(jTextField.getText(), Integer.parseInt(jTextField1.getText()));

                        //записываем в филиал новый вклад
                        branchJList.getSelectedValue().addContrib(contribution);
                    }
                    setJPanelBranch(bank);
                    showJPanel1Contrib(bank);
                }
            }
        });

        jMenuItem2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jTextField = new JTextField();

                final JComponent[] inputs = new JComponent[]{
                        new JLabel("Name :"),
                        jTextField
                };

                // вызов диалога
                int result = JOptionPane.showConfirmDialog(null, inputs, "Creating branch", JOptionPane.OK_CANCEL_OPTION);
                if(result == 0){
                    bank.addBranch(new Branch(jTextField.getText()));
                    setJPanelBranch(bank);
                    showJPanel1Contrib(bank);
                    jFrame.pack();
                }
            }
        });
        jFrame.pack();

    }

    static JFrame getJFrame() {
        JFrame jFrame = new JFrame() {
        };
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 300, dimension.height / 2 - 300, 600, 500);
        jFrame.setTitle("My app");
        return jFrame;
    }

    static Bank createBank() {
        Bank bank = new Bank("Tinko-ff");
        Branch branch = new Branch("Tomsk");
        Branch branch1 = new Branch("Nsb");
        bank.addBranch(branch);
        bank.addBranch(branch1);

        Deposit contribution = new ShortTermInvest("Elton John", 10000);
        Deposit contribution1 = new ShortTermInvest("Marshall Welles", 10200);
        branch.addContrib(contribution);
        branch1.addContrib(contribution1);
        Deposit contribution2 = new LongTermInvest("Mickael Jordan", 13000);
        Deposit contribution3 = new LongTermInvest("Mr Collins", 700);
        branch.addContrib(contribution2);
        branch1.addContrib(contribution3);
        return bank;
    }

    static void setJPanelBranch(Bank bank) {
        jPanel.removeAll();
        numb_rows_panel = bank.getBranchList().size();
        jPanel.setLayout(new GridLayout(numb_rows_panel, 2));

        for (Branch branch : bank.getBranchList()) {
            JLabel jLabel = new JLabel(branch.getName());
            JTextField jTextField = new JTextField(String.valueOf(branch.getSumOfContrib()));
            jPanel.add(jLabel);
            jPanel.add(jTextField);
        }
    }

    static void showJPanel1Contrib(Bank bank){
        jPanel1.removeAll();
        JButton jButton = new JButton("Select branch");

        DefaultListModel<Branch> defaultListModel = new DefaultListModel<>();
        defaultListModel.addAll(bank.getBranchList());
        JList<Branch> branchJList = new JList<>(defaultListModel);
        jPanel1.add(branchJList);
        jPanel1.add(jButton);

        JList<Deposit> contributionJList = new JList<>();
        jPanel1.add(contributionJList);

        JButton jButton1 = new JButton("Calculate");


        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Branch selectedBranch = branchJList.getSelectedValue();
                DefaultListModel<Deposit> defaultListModel1 = new DefaultListModel<>();
                defaultListModel1.addAll(selectedBranch.getContributionList());
                contributionJList.setModel(defaultListModel1);
                jPanel1.add(jButton1);
                jFrame.pack();

            }
        });

        jButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                JTextField jTextField = new JTextField();

                final JComponent[] inputs = new JComponent[]{
                        new JLabel("Enter months amount"),
                        jTextField,
                };
                int result = JOptionPane.showConfirmDialog(null, inputs, "Calc contribution", JOptionPane.OK_CANCEL_OPTION);
                if(result == 0){
                    double i = contributionJList.getSelectedValue().countSumContr(Integer.parseInt(jTextField.getText()));
                    i = Math.round(i);
                    String s = "Final deposit : " + i + "$";
                    JOptionPane.showMessageDialog(jButton,s);
                }

            }
        });


    }

}
