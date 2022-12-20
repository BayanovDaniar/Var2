package Var2.Lab9;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    static JFrame jFrame = getJFrame();
    static int numb_rows_panel = 2;
    static JPanel jPanel = new JPanel(new GridLayout(numb_rows_panel, 2));
    static JPanel jPanel1 = new JPanel();
    static JMenuBar jMenuBar = new JMenuBar();
    //�������� � ����������� �������� ���� � ������� ��������� ��� �������� ���������
    static JMenu jMenuFile = new JMenu("File");
    static JMenuItem jMenuItemNew = new JMenuItem("New");;
    static JMenuItem jMenuItemOpen = new JMenuItem("Open");
    static JMenuItem jMenuItemSave = new JMenuItem("Save");
    static Bank bank;
    static JTabbedPane jTabbedPane = new JTabbedPane();


    public static void main(String[] args) throws IOException {



        //�������� ����� ����������� ���������� ����
        start();

        //������� ��������� ��� ������� �������� ������ �����
        jMenuItemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //��������� ����
                JTextField jTextField = new JTextField();

                //������ ���������
                final JComponent[] inputs = new JComponent[]{
                        new JLabel("Bank name: "),
                        jTextField
                };

                //�������� ������ ��� ����� ������
                int result = JOptionPane.showConfirmDialog(null, inputs, "Creating bank", JOptionPane.OK_CANCEL_OPTION);

                if (result == JOptionPane.OK_OPTION) {
                    //����� ������������ ����� ����
                    //������� ����� ���� � ����������� ��� ��������
                    bank = new Bank(jTextField.getText());
                    //������������� �������� ����
                    jFrame.setTitle(jTextField.getText());
                    //�������� ����� ����������� ��������� ������ �� ����. ����
                    show();
                }

            }
        });

        //������� ��������� ��� ������� "���������"
        jMenuItemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //������� ������� jFileChooser ��� ����������� ������ ���� � �����
                JFileChooser jFileChooser = new JFileChooser();

                //�������� ������ ����������
                int i = jFileChooser.showSaveDialog(null);
                if(i == JFileChooser.APPROVE_OPTION){
                    //���� ������������ ����� "��"
                    //�������� ���� ��������� ������������� � ���������� ��� � ���������� �������� � ����� ���������� �����
                    String filepath = jFileChooser.getSelectedFile().getPath() + ".xml";
                    //��������� ��� ���� ������� �����
                    saveBank(filepath,bank);
                }
            }
        });

        //������� ��������� ��� ������� "open"
        jMenuItemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ////������� ������� jFileChooser ��� ����������� ������ ����
                JFileChooser jFileChooser = new JFileChooser();
                //�������� ���������� ���� ��� �������� �����
                int i = jFileChooser.showOpenDialog(null);
                if(i == JFileChooser.APPROVE_OPTION){
                    //�������� ���� � �����
                    String filepath = jFileChooser.getSelectedFile().getPath() ;
                    //�������� ����� ������ � ���������� ���� ����������� ����� ����������
                    bank = getBank(filepath);
                    //������ ��������� ����
                    jFrame.setTitle(bank.getName());
                    show();
                }
            }
        });



    }

    static void show() {


        jMenuBar.removeAll();

        start();


        jFrame.add(jTabbedPane);
        jTabbedPane.add(jPanel, "Info about branches");
        jTabbedPane.add(jPanel1, "Contributions");

        setJPanelBranch(bank);
        showJPanel1Contrib(bank);

        jFrame.setJMenuBar(jMenuBar);
        JMenu jMenu = new JMenu("Create");
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

                //����� �������
                int result = JOptionPane.showConfirmDialog(null, inputs, "My custom dialog", JOptionPane.OK_CANCEL_OPTION);
                if(result ==0) {

                    // ���������� ��� ������
                    if (jRadioButton.isSelected()) {
                        Deposit contribution =
                                new ShortTermInvest(jTextField.getText(), Integer.parseInt(jTextField1.getText()));

                        //���������� � ������ ����� �����
                        branchJList.getSelectedValue().addContrib(contribution);
                    }else{
                        Deposit contribution =
                                new LongTermInvest(jTextField.getText(), Integer.parseInt(jTextField1.getText()));

                        //���������� � ������ ����� �����
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

                // ����� �������
                int result = JOptionPane.showConfirmDialog(null, inputs, "Creating branch", JOptionPane.OK_CANCEL_OPTION);
                if(result == 0){
                    bank.addBranch(new Branch(jTextField.getText()));
                    setJPanelBranch(bank);
                    showJPanel1Contrib(bank);
                }
            }
        });

    }

    static void start(){
        //����������� ��������� ����� ���� ��� ����������� �������, ������� ��� ��������� ����
        jMenuFile.add(jMenuItemNew);
        jMenuFile.add(jMenuItemOpen);
        jMenuFile.add(jMenuItemSave);
        jMenuBar.add(jMenuFile);
        jFrame.setJMenuBar(jMenuBar);
    }

    //��� ����� ������ ��� ������������ �� ���� 6
    public static Bank getBank(String fileName) {
        try(XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(fileName))){
            return (Bank) xmlDecoder.readObject();
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    public static void saveBank(String fileName, Bank saveBank){
        try(XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream(fileName))){
            xmlEncoder.writeObject(saveBank);
        } catch (IOException e) {
            e.printStackTrace();
        }
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
