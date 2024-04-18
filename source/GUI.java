
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JList;
import javax.swing.JOptionPane;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import javax.swing.table.AbstractTableModel;

import javax.swing.Box;
import javax.swing.BorderFactory;
import javax.swing.JDialog;
import javax.swing.DefaultListModel;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.PrintWriter;
import java.time.chrono.JapaneseDate;
import java.awt.event.MouseEvent;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.CardLayout;
import java.awt.Component;
import java.util.List;
import java.util.concurrent.Flow;
import java.util.ArrayList;

public class GUI extends JFrame {
    private JPanel first, second, third, addScreenEng, singleWordScr, favoriteScreen, statisticScreen, emptyScreen;
    private JButton btnFind, btnAdd, btnLike, btnConfirm, btnCancel, btnMark, btnStatistic, btnDelete;
    private JToggleButton btnConvertLang;
    private JLabel labTitle, labWord, labMean, labLogo, singleWord;
    private JTextField findArea, addVietArea, addEngArea;
    private ImageIcon FindIcon, AddIcon, LikeIcon, LogoIcon, markIcon, unmarkIcon, statisIcon, deleteIcon;
    private Image origin, resize;
    private JFrame mainFrame;
    private Dimension btnContentDimension = new Dimension(100, 30);
    private Dimension inputTextFieldDimension = new Dimension(300, 30);
    private Dimension dialogDimension = new Dimension(500, 150);
    private Dimension thirdElementDimension = new Dimension(1700, 700);
    private Dimension insideThird = new Dimension(700, 670);
    private Dimension labelDimentsion = new Dimension(600, 30);
    private Color backgroundColor = new Color(232, 232, 232);
    private boolean checkMark = false;
    private Dictionary dictionaryVie = new Dictionary();
    private Dictionary dictionaryEng = new Dictionary();
    private List<Word> favorite = new ArrayList<Word>();
    private CardLayout card;
    private String fileDir;
    private String dir;
    // private String fileDir = "D:\\1.
    // HCMUS\\JAVA\\Practices\\DictionaryApp\\data\\history.txt";
    private FileHandler writeFile = new FileHandler();

    public GUI() {
        dir = System.getProperty("user.dir");

        int pos = dir.lastIndexOf('\\');
        dir = dir.substring(0, pos);

        dictionaryVie.readData(dir + "\\data\\Viet_Anh.xml");
        dictionaryEng.readData(dir + "\\data\\Anh_Viet.xml");
        // dictionaryVie.readData("D:\\1.
        // HCMUS\\JAVA\\Practices\\DictionaryApp\\data\\Viet_Anh.xml");
        // dictionaryEng.readData("D:\\1.
        // HCMUS\\JAVA\\Practices\\DictionaryApp\\data\\Anh_Viet.xml");
        Dictionary temp = new Dictionary();
        temp.readData(dir + "\\data\\favorite_list.xml");
        fileDir = dir + "\\data\\history.txt";
        // temp.readData("D:\\1.
        // HCMUS\\JAVA\\Practices\\DictionaryApp\\data\\favorite_list.xml");
        for (int i = 0; i < temp.getListWord().size(); ++i) {
            for (int j = 0; j < dictionaryEng.getListWord().size(); ++j)
                if (temp.getListWord().get(i).getWord().equals(dictionaryEng.getListWord().get(j).getWord())) {
                    dictionaryEng.getListWord().get(j).setIsMark(true);
                    favorite.add(dictionaryEng.getListWord().get(j));

                    break;
                }
            for (int j = 0; j < dictionaryVie.getListWord().size(); ++j)
                if (temp.getListWord().get(i).getWord().equals(dictionaryVie.getListWord().get(j).getWord())) {
                    dictionaryVie.getListWord().get(j).setIsMark(true);
                    favorite.add(dictionaryVie.getListWord().get(j));
                    break;
                }
        }
        initComponents();
    }

    private void initComponents() {

        // set up default
        setSize(Toolkit.getDefaultToolkit().getScreenSize());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame = this;
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // lưu file data
                SaveData();
            }
        });
        // component

        Color boxColor = new Color(207, 207, 207);

        // referencesize
        Dimension textDimension = new Dimension(600, 30);
        Dimension buttonDimension = new Dimension(70, 30);
        Dimension JPFirstDimension = new Dimension(1700, 70);
        Dimension JPSecondDimension = new Dimension(1700, 40);

        first = new JPanel();
        second = new JPanel();
        third = new JPanel();

        // set layout
        setLayout(new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS));
        this.setBackground(backgroundColor);

        first.setLayout(new BoxLayout(first, BoxLayout.X_AXIS));
        first.setPreferredSize(JPFirstDimension);
        first.setMinimumSize(JPFirstDimension);
        first.setMaximumSize(JPFirstDimension);
        first.setBackground(backgroundColor);

        second.setLayout(new BoxLayout(second, BoxLayout.X_AXIS));
        second.setPreferredSize(JPSecondDimension);
        second.setMinimumSize(JPSecondDimension);
        second.setMaximumSize(JPSecondDimension);
        second.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        second.setBackground(backgroundColor);

        // deal with first layout
        labTitle = new JLabel("DICTIONARY APP");
        labTitle.setFont(new Font("Serif", Font.BOLD, 25));
        labTitle.setAlignmentX(CENTER_ALIGNMENT);

        LogoIcon = new ImageIcon(dir + "\\media\\logo.png");
        // resize logo
        origin = LogoIcon.getImage();
        resize = origin.getScaledInstance(45, 45, Image.SCALE_SMOOTH);
        LogoIcon = new ImageIcon(resize);

        labLogo = new JLabel();
        labLogo.setIcon(LogoIcon);

        first.add(Box.createHorizontalGlue());
        first.add(labTitle);
        first.add(Box.createRigidArea(new Dimension(20, 0)));
        first.add(labLogo);
        first.add(Box.createHorizontalGlue());

        first.setVisible(true);

        // deal with second layout
        findArea = new JTextField();
        findArea.setText("Enter your keyword...");
        findArea.setFont(new Font("Serif", Font.PLAIN, 18));
        findArea.setPreferredSize(textDimension);
        findArea.setMinimumSize(textDimension);
        findArea.setMaximumSize(textDimension);
        findArea.setBackground(boxColor);
        findArea.setBorder(BorderFactory.createLineBorder(boxColor));
        findArea.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Xử lý sự kiện khi chuột được nhấp
                if (findArea.getText().equals("Enter your keyword..."))
                    findArea.setText("");
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // Xử lý sự kiện khi chuột được nhấn
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // Xử lý sự kiện khi chuột được thả ra
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // Xử lý sự kiện khi chuột vào vùng
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Xử lý sự kiện khi chuột ra khỏi vùng
                if (findArea.getText().isEmpty())
                    findArea.setText("Enter your keyword...");
            }
        });

        FindIcon = new ImageIcon(dir + "\\media\\search.png");
        origin = FindIcon.getImage();
        resize = origin.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        FindIcon = new ImageIcon(resize);

        AddIcon = new ImageIcon(dir + "\\media\\add.png");
        origin = AddIcon.getImage();
        resize = origin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        AddIcon = new ImageIcon(resize);

        LikeIcon = new ImageIcon(dir + "\\media\\star.png");
        origin = LikeIcon.getImage();
        resize = origin.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        LikeIcon = new ImageIcon(resize);

        btnAdd = new JButton();
        btnAdd.setIcon(AddIcon);
        btnAdd.setPreferredSize(buttonDimension);
        btnAdd.setMinimumSize(buttonDimension);
        btnAdd.setMaximumSize(buttonDimension);
        btnAdd.setBackground(backgroundColor);
        btnAdd.setBorderPainted(false);
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnAddPerformed(e);
            }
        });

        btnFind = new JButton();
        btnFind.setIcon(FindIcon);
        btnFind.setPreferredSize(buttonDimension);
        btnFind.setMinimumSize(buttonDimension);
        btnFind.setMaximumSize(buttonDimension);
        btnFind.setBackground(boxColor);
        btnFind.setBorder(BorderFactory.createEmptyBorder(0, 5, 0, 0));
        btnFind.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnFindPerformed(e);
            }
        });

        btnLike = new JButton();
        btnLike.setIcon(LikeIcon);
        btnLike.setPreferredSize(buttonDimension);
        btnLike.setMaximumSize(buttonDimension);
        btnLike.setMinimumSize(buttonDimension);
        btnLike.setBackground(backgroundColor);
        btnLike.setBorderPainted(false);

        btnLike.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnLikePerformed(e);
            }
        });

        btnConvertLang = new JToggleButton();
        btnConvertLang.setText("Vie");
        btnConvertLang.setPreferredSize(buttonDimension);
        btnConvertLang.setMinimumSize(buttonDimension);
        btnConvertLang.setMaximumSize(buttonDimension);

        btnConvertLang.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnConvertLangPerformed(e);
            }
        });

        btnStatistic = new JButton();
        statisIcon = new ImageIcon(dir + "\\media\\statistics.png");
        origin = statisIcon.getImage();
        resize = origin.getScaledInstance(20, 20, Image.SCALE_SMOOTH);
        statisIcon = new ImageIcon(resize);
        btnStatistic.setIcon(statisIcon);
        btnStatistic.setPreferredSize(buttonDimension);
        btnStatistic.setMaximumSize(buttonDimension);
        btnStatistic.setMinimumSize(buttonDimension);
        btnStatistic.setBackground(backgroundColor);
        btnStatistic.setBorderPainted(false);
        btnStatistic.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // showStatisticScreen(statisticScreen);
                ChooseDateDialog dialog = new ChooseDateDialog(mainFrame, "Choose date", true);
                dialog.dispose();
                dialog.setSize(500, 200);
                dialog.setLocation(500, 200);
                dialog.setVisible(true);
            }
        });

        second.add(Box.createRigidArea(new Dimension(80, 0)));
        second.add(findArea);
        second.add(btnFind);
        second.add(Box.createHorizontalGlue());
        second.add(btnStatistic);
        second.add(btnLike);
        second.add(btnAdd);
        second.add(btnConvertLang);
        second.setVisible(true);
        second.add(Box.createRigidArea(new Dimension(20, 0)));

        card = new CardLayout();
        third.setLayout(card);
        singleWordScr = new JPanel();
        favoriteScreen = new JPanel();
        statisticScreen = new JPanel();
        emptyScreen = new JPanel();

        third.add("singleWord", singleWordScr);
        third.add("favoriteList", favoriteScreen);
        third.add("statisticList", statisticScreen);
        third.add("emptyScreen", emptyScreen);

        JPanel outside = new JPanel();

        outside.setPreferredSize(thirdElementDimension);
        outside.setMinimumSize(thirdElementDimension);
        outside.setMaximumSize(thirdElementDimension);
        outside.setBackground(backgroundColor);
        outside.add(third);
        add(first);
        add(second);
        add(outside);

    }

    private void SaveData() {
        // lưu các thông tin đã thay đổi vào file
        try {
            PrintWriter writerF = new PrintWriter(
                    dir + "\\data\\favorite_list.xml");
            PrintWriter writerE = new PrintWriter(dir + "\\data\\Anh_Viet.xml",
                    "UTF-8");
            PrintWriter writerV = new PrintWriter(dir + "\\data\\Viet_Anh.xml",
                    "UTF-8");
            writerE.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<dictionary>");
            for (int i = 0; i < dictionaryEng.getListWord().size(); ++i) {
                writerE.write("<record>\n<word>");
                writerE.write(
                        dictionaryEng.getListWord().get(i).getWord().replaceAll("&([^;]+(?!(?:\\w|;)))", "&amp;$1")
                                .replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;"));
                writerE.write("</word>\n<meaning>");
                writerE.write(dictionaryEng.getListWord().get(i).getTempWord().replaceAll("&([^;]+(?!(?:\\w|;)))",
                        "&amp;$1").replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;"));
                writerE.write("\n</meaning>\n</record>\n");
            }
            writerE.write("</dictionary>");

            writerV.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<dictionary>");
            for (int i = 0; i < dictionaryVie.getListWord().size(); ++i) {
                writerV.write("<record>\n<word>");
                writerV.write(
                        dictionaryVie.getListWord().get(i).getWord().replaceAll("&([^;]+(?!(?:\\w|;)))", "&amp;$1")
                                .replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;"));
                writerV.write("</word>\n<meaning>");
                writerV.write(dictionaryVie.getListWord().get(i).getTempWord().replaceAll("&([^;]+(?!(?:\\w|;)))",
                        "&amp;$1").replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;"));
                writerV.write("\n</meaning>\n</record>\n");
            }
            writerV.write("</dictionary>");

            writerF.write("<?xml version=\"1.0\" encoding=\"utf-8\"?>\n<dictionary>");
            for (int i = 0; i < favorite.size(); ++i) {
                writerF.write("<record>\n<word>");
                writerF.write(
                        favorite.get(i).getWord().replaceAll("&([^;]+(?!(?:\\w|;)))", "&amp;$1")
                                .replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;"));
                writerF.write("</word>\n<meaning>");
                writerF.write(favorite.get(i).getTempWord().replaceAll("&([^;]+(?!(?:\\w|;)))",
                        "&amp;$1").replace("<", "&lt;").replace(">", "&gt;").replace("&", "&amp;"));
                writerF.write("\n</meaning>\n</record>\n");
            }
            writerF.write("</dictionary>");

            writerE.close();
            writerV.close();
            writerF.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void btnConvertLangPerformed(ActionEvent e) {
        String currentVal = btnConvertLang.getText();
        if (currentVal.equals("Vie"))
            btnConvertLang.setText("Eng");
        else
            btnConvertLang.setText("Vie");
    }

    private void btnAddPerformed(ActionEvent e) {
        if (btnConvertLang.getText().equals("Vie")) {
            addVieDialog dialog = new addVieDialog(mainFrame, "Add VietNamese", true);
            dialog.dispose();
            dialog.setSize(500, 200);
            dialog.setLocation(500, 200);
            dialog.setVisible(true);
        } else {
            addEngDialog dialog = new addEngDialog(mainFrame, "Add English", true);
            dialog.dispose();
            dialog.setSize(500, 200);
            dialog.setLocation(500, 200);
            dialog.setVisible(true);
        }

    }

    private void btnFindPerformed(ActionEvent e) {
        checkMark = false;
        String keyword = findArea.getText();
        writeFile.WriteToFile(fileDir, keyword);
        findArea.setText("Enter your keyword...");
        Word result = null;
        // trường hợp tìm tiếng Việt
        if (btnConvertLang.getText().equals("Vie")) {
            for (int i = 0; i < dictionaryVie.getListWord().size(); ++i) {
                if (dictionaryVie.getListWord().get(i).getWord().equals(keyword)) {
                    result = dictionaryVie.getListWord().get(i);
                    break;
                }
            }
        }
        // trường hợp tìm tiếng anh
        else {
            for (int i = 0; i < dictionaryEng.getListWord().size(); ++i) {
                if (dictionaryEng.getListWord().get(i).getWord().equals(keyword)) {
                    result = dictionaryEng.getListWord().get(i);
                    break;
                }
            }

        }
        if (result != null) {
            System.out.println("Founded");
            showSingleWordScreen(result, singleWordScr);
        } else {
            System.out.println("Not found");
            JOptionPane.showMessageDialog(mainFrame, "Not Founded", "Notification", JOptionPane.INFORMATION_MESSAGE);
            showEmptyScreen(emptyScreen);
        }
    }

    private JPanel showEmptyScreen(JPanel emptyScreen) {
        emptyScreen.removeAll();
        emptyScreen.setPreferredSize(insideThird);
        emptyScreen.setMinimumSize(insideThird);
        emptyScreen.setMaximumSize(insideThird);
        emptyScreen.setBackground(Color.WHITE);
        emptyScreen.repaint();
        emptyScreen.revalidate();
        card.show(third, "emptyScreen");
        return emptyScreen;

    }

    private JPanel showSingleWordScreen(Word w, JPanel singleWordScreen) {
        markIcon = new ImageIcon(dir + "\\media\\mark.png");
        unmarkIcon = new ImageIcon(dir + "\\media\\unmark.png");
        deleteIcon = new ImageIcon(dir + "\\media\\bin.png");
        btnDelete = new JButton();

        singleWordScreen.removeAll(); // xóa thông tin từ tìm kiếm phía trước
        origin = markIcon.getImage();
        resize = origin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        markIcon = new ImageIcon(resize);
        origin = unmarkIcon.getImage();
        resize = origin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        unmarkIcon = new ImageIcon(resize);
        origin = deleteIcon.getImage();
        resize = origin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        deleteIcon = new ImageIcon(resize);

        singleWord = new JLabel(w.getWord());
        singleWord.setFont(new Font("Serif", Font.BOLD, 25));
        singleWord.setAlignmentX((float) 0.6);
        singleWord.setPreferredSize(labelDimentsion);
        singleWord.setMaximumSize(labelDimentsion);
        singleWord.setMinimumSize(labelDimentsion);
        singleWord.setBackground(Color.WHITE);

        btnMark = new JButton();
        if (w.getIsMark() == true) {
            btnMark.setIcon(markIcon);
            btnDelete.setEnabled(false);
        } else {
            btnMark.setIcon(unmarkIcon);
            btnDelete.setEnabled(true);
        }
        btnMark.setBackground(Color.WHITE);
        btnMark.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnMark.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                if (w.getIsMark() == false) {
                    w.setIsMark(true);
                    favorite.add(w);
                    checkMark = false;
                } else {
                    // tìm và xóa ra khỏi danh sách favorite

                    w.setIsMark(false);
                    favorite.remove(favorite.indexOf(w));
                    checkMark = true;
                    // tìm và unmark trong list origin
                    if (btnConvertLang.getText().equals("Vie")) {

                        int pos = dictionaryVie.getListWord().indexOf(w);
                        if (pos != -1)
                            dictionaryVie.getListWord().get(pos).setIsMark(false);
                    } else {
                        int pos = dictionaryEng.getListWord().indexOf(w);
                        if (pos != -1)
                            dictionaryEng.getListWord().get(pos).setIsMark(false);
                    }
                }
                showSingleWordScreen(w, singleWordScreen);
                btnMarkPerformed(e);
            }
        });

        btnDelete.setBackground(Color.WHITE);
        btnDelete.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        btnDelete.setIcon(deleteIcon);
        btnDelete.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                btnDeleteWordPerformed(e, w, btnConvertLang.getText());
            }
        });

        JPanel mainWordLine = new JPanel();
        mainWordLine.setLayout(new BoxLayout(mainWordLine, BoxLayout.X_AXIS));
        mainWordLine.setPreferredSize(new Dimension(750, 40));
        mainWordLine.setMinimumSize(new Dimension(750, 40));
        mainWordLine.setMaximumSize(new Dimension(750, 40));
        mainWordLine.add(Box.createRigidArea(new Dimension(10, 0)));
        mainWordLine.add(singleWord);
        mainWordLine.add(Box.createHorizontalGlue());
        mainWordLine.add(btnMark);
        mainWordLine.add(Box.createRigidArea(new Dimension(10, 0)));
        mainWordLine.add(btnDelete);
        mainWordLine.add(Box.createRigidArea(new Dimension(10, 0)));
        mainWordLine.setBackground(Color.WHITE);
        DefaultListModel<Kind> listKind = new DefaultListModel<Kind>();
        for (int i = 0; i < w.getListKind().size(); ++i) {
            listKind.addElement(w.getListKind().get(i));
        }
        JList<Kind> lkind = new JList<Kind>(listKind);
        lkind.setCellRenderer(new TypeRenderer());
        JScrollPane pane = new JScrollPane(lkind);
        pane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pane.setBackground(Color.WHITE);
        singleWordScreen.setLayout(new BoxLayout(singleWordScreen, BoxLayout.Y_AXIS));
        singleWordScreen.setPreferredSize(insideThird);
        singleWordScreen.setMinimumSize(insideThird);
        singleWordScreen.setMaximumSize(insideThird);
        singleWordScreen.add(mainWordLine);
        singleWordScreen.add(pane);
        singleWordScreen.repaint();
        singleWordScreen.revalidate();
        card.show(third, "singleWord");
        return singleWordScreen;
    }

    // show favorite list dưới dạng bảng để sắp xếp từ A đến Z và từ Z đến A
    private void showFavoriteListScreen(JPanel favoriteScreen) {
        favoriteScreen.removeAll();
        favoriteScreen.setLayout(new BoxLayout(favoriteScreen, BoxLayout.Y_AXIS));
        favoriteScreen.setPreferredSize(insideThird);
        favoriteScreen.setMinimumSize(insideThird);
        favoriteScreen.setMaximumSize(insideThird);
        favoriteScreen.setBackground(Color.WHITE);
        MyTableModel myTableModel = new MyTableModel(favorite);
        JTable jTable = new JTable(myTableModel);
        jTable.setFont(new Font("Serief", Font.PLAIN, 25));
        jTable.setAutoCreateRowSorter(true);
        jTable.setRowHeight(50);
        jTable.setBackground(Color.WHITE);
        jTable.setPreferredScrollableViewportSize(new Dimension(700, 600));
        jTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = jTable.getSelectedRow();
            if (selectedRow >= 0) {
                int modelRow = jTable.convertRowIndexToModel(selectedRow);
                Word selectedWord = favorite.get(modelRow);
                showSingleWordScreen(selectedWord, singleWordScr);
            }
        });
        JScrollPane pane = new JScrollPane(jTable);
        favoriteScreen.add(pane);
        favoriteScreen.repaint();
        favoriteScreen.revalidate();
        card.show(third, "favoriteList");
    }

    class MyTableModel extends AbstractTableModel {
        private String[] columnNames = { "Favorite Word" };
        private List<Word> data;

        public MyTableModel(List<Word> data) {
            this.data = data;
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames[columnIndex];
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex).getWord();
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return Word.class;
        }

        @Override
        public boolean isCellEditable(int row, int col) {
            return true;
        }

        public void setData(List<Word> data) {
            this.data = data;
        }
    };

    private JPanel showStatisticScreen(JPanel statisticScreen, String from, String to) {
        // set layout
        statisticScreen.removeAll();
        statisticScreen.setLayout(new BoxLayout(statisticScreen, BoxLayout.Y_AXIS));
        statisticScreen.setPreferredSize(insideThird);
        statisticScreen.setMinimumSize(insideThird);
        statisticScreen.setMaximumSize(insideThird);
        statisticScreen.setBackground(Color.WHITE);
        // read data from history file
        ArrayList<History> hisList = new ArrayList<History>();
        hisList = writeFile.ReadFile(fileDir, from, to);
        DefaultListModel<History> data = new DefaultListModel<History>();
        for (int i = 0; i < hisList.size(); ++i)
            data.addElement(hisList.get(i));
        JList<History> apply = new JList<History>(data);
        apply.setCellRenderer(new StatisticRenderer());
        JScrollPane pane = new JScrollPane(apply);
        pane.setBorder(BorderFactory.createLineBorder(Color.WHITE));
        pane.setBackground(Color.WHITE);

        JLabel title = new JLabel("Search History");
        title.setFont(new Font("Serif", Font.BOLD, 35));
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setForeground(Color.pink);
        statisticScreen.add(title);
        statisticScreen.add(pane);
        statisticScreen.repaint();
        statisticScreen.revalidate();
        card.show(third, "statisticList");
        return statisticScreen;

    }

    private void btnDeleteWordPerformed(ActionEvent e, Word w, String typeLang) {
        if (typeLang.equals("Vie")) {
            dictionaryVie.getListWord().remove(w);
            favorite.remove(w);
            showEmptyScreen(emptyScreen);

        } else {
            dictionaryEng.getListWord().remove(w);
            favorite.remove(w);
            showEmptyScreen(emptyScreen);

        }
    }

    private void btnMarkPerformed(ActionEvent e) {
        markIcon = new ImageIcon(dir + "\\media\\mark.png");
        unmarkIcon = new ImageIcon(dir + "\\media\\unmark.png");
        origin = markIcon.getImage();
        resize = origin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        markIcon = new ImageIcon(resize);
        origin = unmarkIcon.getImage();
        resize = origin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        unmarkIcon = new ImageIcon(resize);
        if (checkMark == false) {
            checkMark = true;
            btnMark.setIcon(markIcon);
        } else {
            checkMark = false;
            btnMark.setIcon(unmarkIcon);
        }
    }

    private void btnLikePerformed(ActionEvent e) {

        showFavoriteListScreen(favoriteScreen);
    }

    private void btnConfirmAddPerformed(ActionEvent e) {
        String word = "";
        String mean = "";
        if (btnConvertLang.getText().equals("Eng")) {
            word = addEngArea.getText();
            mean = addVietArea.getText();
        } else {
            word = addVietArea.getText();
            mean = addEngArea.getText();
        }
        Word addWord = new Word();
        addWord.setWord(word);
        addWord.setTempWord("- " + mean);
        if (btnConvertLang.getText().equals("Vie"))
            dictionaryVie.getListWord().add(addWord);
        else
            dictionaryEng.getListWord().add(addWord);
    }

    // chưa xử lý(cho phép người dùng chọn thời gian thống kê)
    class StatisticDialog extends JDialog {
        public StatisticDialog(JFrame parent, String title, boolean modal) {
            super(parent, title, modal);
            // định nghĩa các thành phần trong dialog
        }
    }

    class addEngDialog extends JDialog {
        public addEngDialog(JFrame parent, String title, boolean modal) {
            super(parent, title, modal);

            // định nghĩa các thành phần trong dialog
            addScreenEng = new JPanel();

            btnCancel = new JButton("Cancel");
            btnCancel.setPreferredSize(btnContentDimension);
            btnCancel.setMinimumSize(btnContentDimension);
            btnCancel.setMaximumSize(btnContentDimension);

            btnConfirm = new JButton("Confirm");
            btnConfirm.setPreferredSize(btnContentDimension);
            btnConfirm.setMinimumSize(btnContentDimension);
            btnConfirm.setMaximumSize(btnContentDimension);

            addEngArea = new JTextField();
            addEngArea.setPreferredSize(inputTextFieldDimension);
            addEngArea.setMinimumSize(inputTextFieldDimension);
            addEngArea.setMaximumSize(inputTextFieldDimension);

            addVietArea = new JTextField();
            addVietArea.setPreferredSize(inputTextFieldDimension);
            addVietArea.setMinimumSize(inputTextFieldDimension);
            addVietArea.setMaximumSize(inputTextFieldDimension);
            labWord = new JLabel("Your word: ");
            labMean = new JLabel("Meaning: ");
            GridBagLayout gridlayout = new GridBagLayout();
            addScreenEng.setLayout(gridlayout);
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.insets = new Insets(4, 4, 4, 4);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridx = 0;
            constraint.gridy = 0;
            addScreenEng.add(labWord, constraint);
            constraint.gridwidth = 2;
            constraint.gridx = 1;
            addScreenEng.add(addEngArea, constraint);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridx = 0;
            constraint.gridy = 1;
            constraint.gridwidth = 1;
            addScreenEng.add(labMean, constraint);
            constraint.gridx = 1;
            constraint.gridwidth = 2;
            addScreenEng.add(addVietArea, constraint);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridy = 2;
            constraint.gridx = 0;
            constraint.gridwidth = 1;
            addScreenEng.add(btnCancel, constraint);
            constraint.gridx = 1;
            addScreenEng.add(Box.createRigidArea(new Dimension(200, 0)), constraint);
            constraint.gridx = 2;
            addScreenEng.add(btnConfirm, constraint);
            getContentPane().add(addScreenEng);

            btnCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setModal(false);
                    getOwner().setEnabled(true);
                    dispose(); // đóng dialog khi người dùng hủy thao tác thêm từ

                }
            });
            btnConfirm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnConfirmAddPerformed(e);
                    setModal(false);
                    getOwner().setEnabled(true);
                    dispose(); // đóng dialog khi người dùng hủy thao tác thêm từ

                }
            });
        }
    }

    class ExampleRenderer extends JPanel implements ListCellRenderer<Example> {
        private JLabel ex = new JLabel();
        private JLabel mean = new JLabel();

        public ExampleRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

            // ex.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
            // ex.setForeground(Color.GRAY);
            // // ex.setPreferredSize(labelDimentsion);
            // // ex.setMinimumSize(labelDimentsion);
            // // ex.setMaximumSize(labelDimentsion);
            // ex.setAlignmentX(Component.LEFT_ALIGNMENT);
            // ex.setBackground(Color.CYAN);
            // mean.setFont(new Font("Serif", Font.ITALIC, 18));
            // mean.setForeground(Color.gray);
            // // mean.setPreferredSize(labelDimentsion);
            // // mean.setMaximumSize(labelDimentsion);
            // // mean.setMinimumSize(labelDimentsion);
            // mean.setAlignmentX(Component.LEFT_ALIGNMENT);
            // mean.setBackground(Color.BLUE);
            // setBackground(Color.white);
            add(ex);
            add(mean);

        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Example> list, Example example, int index,
                boolean isSelected, boolean cellHasFocus) {
            ex.setText("<html>| " + example.getEx() + "</html>");
            mean.setText("<html>" + example.getTrans() + "</html>");
            ex.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 18));
            ex.setForeground(Color.GRAY);

            ex.setAlignmentX(Component.LEFT_ALIGNMENT);
            ex.setBackground(Color.CYAN);
            mean.setFont(new Font("Serif", Font.ITALIC, 18));
            mean.setForeground(Color.gray);

            mean.setAlignmentX(Component.LEFT_ALIGNMENT);
            mean.setBackground(Color.BLUE);
            setBackground(Color.white);

            if (example.getTrans().length() > 78 || example.getEx().length() > 78) {
                mean.setPreferredSize(new Dimension(600, 30 * (1 + example.getTrans().length() / 78)));
                mean.setMaximumSize(new Dimension(600, 30 * (1 + example.getTrans().length() / 78)));
                mean.setMinimumSize(new Dimension(600, 30 * (1 + example.getTrans().length() / 78)));

                ex.setPreferredSize(new Dimension(600, 30 * (1 + example.getEx().length() / 78)));
                ex.setMinimumSize(new Dimension(600, 30 * (1 + example.getEx().length() / 78)));
                ex.setMaximumSize(new Dimension(600, 30 * (1 + example.getEx().length() / 78)));
                // mean.setPreferredSize(new Dimension(600, height * (1 + t)));
                // mean.setMinimumSize(new Dimension(600, height * (2 + t)));
                // mean.setMaximumSize(new Dimension(600, height * (2 + t)));

            } else {
                mean.setPreferredSize(labelDimentsion);
                mean.setMinimumSize(labelDimentsion);
                mean.setMaximumSize(labelDimentsion);
                ex.setPreferredSize(labelDimentsion);
                ex.setMinimumSize(labelDimentsion);
                ex.setMaximumSize(labelDimentsion);
            }

            // if (example.getEx().length() > 78) {

            // ex.setPreferredSize(new Dimension(600, 30 * (1 + example.getEx().length() /
            // 78)));
            // ex.setMinimumSize(new Dimension(600, 30 * (1 + example.getEx().length() /
            // 78)));
            // ex.setMaximumSize(new Dimension(600, 30 * (1 + example.getEx().length() /
            // 78)));
            // ex.setBorder(BorderFactory.createLineBorder(Color.CYAN));

            // // ex.setPreferredSize(new Dimension(600, height * (1 + t)));
            // // ex.setMinimumSize(new Dimension(600, height * (1 + t)));
            // // ex.setMaximumSize(new Dimension(600, height * (1 + t)));
            // } else {
            // ex.setPreferredSize(labelDimentsion);
            // ex.setMinimumSize(labelDimentsion);
            // ex.setMaximumSize(labelDimentsion);
            // ex.setBorder(BorderFactory.createLineBorder(Color.CYAN));
            // }

            return this;
        }

    }

    class MeaningRenderer extends JPanel implements ListCellRenderer<Mean> {
        private ImageIcon icon = new ImageIcon(dir + "\\media\\bookmark.png");
        private JLabel meantitle = new JLabel("", icon, SwingConstants.LEFT);
        private JList<Example> exlist = new JList<Example>();

        public MeaningRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            meantitle.setPreferredSize(labelDimentsion);
            meantitle.setMaximumSize(labelDimentsion);
            meantitle.setMinimumSize(labelDimentsion);
            meantitle.setFont(new Font("Serif", Font.PLAIN, 20));
            meantitle.setAlignmentX((float) 0.6);
            setBackground(Color.WHITE);
            add(meantitle);
            add(exlist);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Mean> list, Mean mean, int index,
                boolean isSelected, boolean cellHasFocus) {
            meantitle.setText("<html>" + mean.getMean() + "</html>");
            if (mean.getMean().length() > 72) {
                meantitle.setPreferredSize(new Dimension(600, 45 * (mean.getMean().length() / 72 + 1)));
                meantitle.setMaximumSize(new Dimension(600, 45 * (mean.getMean().length() / 72 + 1)));
                meantitle.setMinimumSize(new Dimension(600, 45 * (mean.getMean().length() / 72 + 1)));
            } else {
                meantitle.setPreferredSize(labelDimentsion);
                meantitle.setMaximumSize(labelDimentsion);
                meantitle.setMinimumSize(labelDimentsion);
            }

            Image origin = icon.getImage();
            Image resize = origin.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resize);
            meantitle.setIcon(icon);
            DefaultListModel<Example> temp = new DefaultListModel<Example>();
            temp.addAll(mean.getListExamples());
            Example[] temp1 = new Example[mean.getListExamples().size()];
            for (int i = 0; i < temp.size(); ++i) {
                temp1[i] = temp.get(i);
            }
            exlist.setListData(temp1);
            exlist.setCellRenderer(new ExampleRenderer());
            return this;
        }
    }

    class TypeRenderer extends JPanel implements ListCellRenderer<Kind> {
        private JLabel typeTil = new JLabel();
        private JList<Mean> meanList = new JList<Mean>();

        public TypeRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            typeTil.setPreferredSize(labelDimentsion);
            typeTil.setMinimumSize(labelDimentsion);
            typeTil.setMaximumSize(labelDimentsion);
            setBackground(Color.WHITE);
            typeTil.setAlignmentX((float) 0.6);
            typeTil.setFont(new Font("Serif", Font.PLAIN, 18));
            typeTil.setForeground(Color.PINK);
            add(typeTil);
            add(meanList);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Kind> list, Kind kind, int index,
                boolean isSelected,
                boolean cellHasFocus) {
            typeTil.setText("<html>" + kind.getKind() + "</html>");
            if (kind.getKind().length() > 78) {
                typeTil.setPreferredSize(new Dimension(600, 45 * (kind.getKind().length() / 78 + 1)));
                typeTil.setMinimumSize(new Dimension(600, 45 * (kind.getKind().length() / 78 + 1)));
                typeTil.setMaximumSize(new Dimension(600, 45 * (kind.getKind().length() / 78 + 1)));
            } else {
                typeTil.setPreferredSize(labelDimentsion);
                typeTil.setMinimumSize(labelDimentsion);
                typeTil.setMaximumSize(labelDimentsion);
            }

            Mean[] temp = new Mean[kind.getDefinitions().size()];
            for (int i = 0; i < kind.getDefinitions().size(); ++i) {
                temp[i] = kind.getDefinitions().get(i);
            }
            meanList.setListData(temp);
            meanList.setCellRenderer(new MeaningRenderer());
            return this;
        }
    }

    class favoriteRenderer extends JPanel implements ListCellRenderer<Word> {
        private JLabel favWord = new JLabel();
        private JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);
        private ImageIcon icon = new ImageIcon(dir + "\\media\\bookmark.png");

        public favoriteRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            favWord.setFont(new Font("Serif", Font.PLAIN, 25));

            // resize icon
            origin = icon.getImage();
            resize = origin.getScaledInstance(30, 30, Image.SCALE_SMOOTH);
            icon = new ImageIcon(resize);
            favWord.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            sep.setBackground(Color.lightGray);
            favWord.setBackground(Color.WHITE);
            favWord.setIconTextGap(20);
            setBackground(Color.WHITE);
            add(favWord);
            add(sep);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends Word> list, Word word, int index,
                boolean isSelected, boolean cellHasFocus) {
            favWord.setText(word.getWord());
            favWord.setIcon(icon);
            return this;
        }
    }

    class StatisticRenderer extends JPanel implements ListCellRenderer<History> {
        private JLabel key = new JLabel();
        private JLabel times = new JLabel();
        private JSeparator sep = new JSeparator(JSeparator.HORIZONTAL);

        public StatisticRenderer() {
            setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            key.setFont(new Font("Serif", Font.PLAIN, 25));
            times.setFont(new Font("Serif", Font.PLAIN, 25));

            JPanel temp = new JPanel();
            temp.setLayout(new BoxLayout(temp, BoxLayout.X_AXIS));
            temp.setBackground(Color.WHITE);
            temp.setBorder(BorderFactory.createLineBorder(Color.WHITE));
            temp.add(key);
            temp.add(Box.createHorizontalGlue());
            temp.add(times);
            temp.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
            sep.setBackground(Color.lightGray);
            add(temp);
            add(sep);
        }

        @Override
        public Component getListCellRendererComponent(JList<? extends History> list, History history, int index,
                boolean isSelected, boolean cellHasFocus) {
            key.setText(history.getKeyWord());
            times.setText(String.valueOf(history.getTimes()));
            return this;
        }
    }

    class addVieDialog extends JDialog {
        public addVieDialog(JFrame parent, String title, boolean modal) {
            super(parent, title, modal);

            // định nghĩa các thành phần trong dialog
            addScreenEng = new JPanel();

            btnCancel = new JButton("Cancel");
            btnCancel.setPreferredSize(btnContentDimension);
            btnCancel.setMinimumSize(btnContentDimension);
            btnCancel.setMaximumSize(btnContentDimension);

            btnConfirm = new JButton("Confirm");
            btnConfirm.setPreferredSize(btnContentDimension);
            btnConfirm.setMinimumSize(btnContentDimension);
            btnConfirm.setMaximumSize(btnContentDimension);

            addEngArea = new JTextField();
            addEngArea.setPreferredSize(inputTextFieldDimension);
            addEngArea.setMinimumSize(inputTextFieldDimension);
            addEngArea.setMaximumSize(inputTextFieldDimension);

            addVietArea = new JTextField();
            addVietArea.setPreferredSize(inputTextFieldDimension);
            addVietArea.setMinimumSize(inputTextFieldDimension);
            addVietArea.setMaximumSize(inputTextFieldDimension);
            labWord = new JLabel("Your word: ");
            labMean = new JLabel("Meaning: ");
            GridBagLayout gridlayout = new GridBagLayout();
            addScreenEng.setLayout(gridlayout);
            GridBagConstraints constraint = new GridBagConstraints();
            constraint.insets = new Insets(4, 4, 4, 4);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridx = 0;
            constraint.gridy = 0;
            addScreenEng.add(labWord, constraint);
            constraint.gridwidth = 2;
            constraint.gridx = 1;
            addScreenEng.add(addVietArea, constraint);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridx = 0;
            constraint.gridy = 1;
            constraint.gridwidth = 1;
            addScreenEng.add(labMean, constraint);
            constraint.gridx = 1;
            constraint.gridwidth = 2;
            addScreenEng.add(addEngArea, constraint);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridy = 2;
            constraint.gridx = 0;
            constraint.gridwidth = 1;
            addScreenEng.add(btnCancel, constraint);
            constraint.gridx = 1;
            addScreenEng.add(Box.createRigidArea(new Dimension(200, 0)), constraint);
            constraint.gridx = 2;
            addScreenEng.add(btnConfirm, constraint);
            getContentPane().add(addScreenEng);

            btnCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setModal(false);
                    getOwner().setEnabled(true);
                    dispose(); // đóng dialog khi người dùng hủy thao tác thêm từ

                }
            });
            btnConfirm.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    btnConfirmAddPerformed(e);
                    setModal(false);
                    getOwner().setEnabled(true);
                    dispose(); // đóng dialog khi người dùng hủy thao tác thêm từ
                    // .dfd
                }
            });
        }

    }

    class ChooseDateDialog extends JDialog {
        String selectedDayFrom, selectedMonthFrom, selectedYearFrom;
        String selectedDayTo, selectedMonthTo, selectedYearTo;

        public ChooseDateDialog(JFrame parent, String title, boolean modal) {
            super(parent, title, modal);

            JPanel chooseDateScreen = new JPanel();
            JPanel dateFrom = new JPanel();
            JPanel dateTo = new JPanel();
            JButton btnCancel, btnOk;
            JLabel labFrom, labTo;

            btnCancel = new JButton("Cancel");
            btnCancel.setFont(new Font("Serif", Font.BOLD, 20));
            btnCancel.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            btnCancel.setFocusable(false);
            btnCancel.setPreferredSize(btnContentDimension);
            btnCancel.setMinimumSize(btnContentDimension);
            btnCancel.setMaximumSize(btnContentDimension);
            btnOk = new JButton("OK");
            btnOk.setFont(new Font("Serif", Font.BOLD, 20));
            btnOk.setBorder(BorderFactory.createLineBorder(Color.lightGray));
            btnOk.setPreferredSize(btnContentDimension);
            btnOk.setMinimumSize(btnContentDimension);
            btnOk.setMaximumSize(btnContentDimension);
            btnOk.setFocusable(false);
            labFrom = new JLabel("From");
            labFrom.setFont(new Font("Serif", Font.BOLD, 20));
            labFrom.setAlignmentX(LEFT_ALIGNMENT);
            labFrom.setPreferredSize(btnContentDimension);
            labFrom.setMinimumSize(btnContentDimension);
            labFrom.setMaximumSize(btnContentDimension);
            labTo = new JLabel("To");
            labTo.setFont(new Font("Serif", Font.BOLD, 20));
            labTo.setAlignmentX(LEFT_ALIGNMENT);
            labTo.setPreferredSize(btnContentDimension);
            labTo.setMinimumSize(btnContentDimension);
            labTo.setMaximumSize(btnContentDimension);

            String[] days = new String[31];
            for (int i = 0; i < 31; ++i)
                if (i < 9) {
                    days[i] = "0" + String.valueOf(i + 1);
                } else
                    days[i] = String.valueOf(i + 1);
            JComboBox<String> dayComboBox = new JComboBox<>(days);
            JComboBox<String> dayComboBox2 = new JComboBox<>(days);

            String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
            JComboBox<String> monthComboBox = new JComboBox<>(months);
            JComboBox<String> monthComboBox2 = new JComboBox<>(months);

            String[] years = new String[100];
            for (int i = 0; i < 100; ++i)
                years[i] = String.valueOf(2023 + i);
            JComboBox<String> yearComboBox = new JComboBox<>(years);
            JComboBox<String> yearComboBox2 = new JComboBox<>(years);

            dateFrom.setLayout(new BoxLayout(dateFrom, BoxLayout.X_AXIS));
            dateFrom.setPreferredSize(new Dimension(150, 30));
            dateFrom.setMinimumSize(new Dimension(150, 30));
            dateFrom.setMaximumSize(new Dimension(150, 30));
            dateTo.setLayout(new BoxLayout(dateTo, BoxLayout.X_AXIS));
            dateTo.setPreferredSize(new Dimension(150, 30));
            dateTo.setMinimumSize(new Dimension(150, 30));
            dateTo.setMaximumSize(new Dimension(150, 30));

            dateFrom.add(dayComboBox);
            dateFrom.add(monthComboBox);
            dateFrom.add(yearComboBox);

            dateTo.add(dayComboBox2);
            dateTo.add(monthComboBox2);
            dateTo.add(yearComboBox2);

            GridBagLayout gridLayout = new GridBagLayout();
            GridBagConstraints constraint = new GridBagConstraints();
            chooseDateScreen.setLayout(gridLayout);
            constraint.insets = new Insets(4, 4, 4, 4);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridx = 0;
            constraint.gridy = 0;
            chooseDateScreen.add(labFrom, constraint);
            constraint.gridx = 1;
            chooseDateScreen.add(Box.createRigidArea(new Dimension(20, 0)));
            constraint.gridx = 2;
            chooseDateScreen.add(labTo, constraint);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridx = 0;
            constraint.gridy = 1;
            chooseDateScreen.add(dateFrom, constraint);
            constraint.gridx = 1;
            chooseDateScreen.add(Box.createRigidArea(new Dimension(20, 0)));
            constraint.gridx = 2;
            chooseDateScreen.add(dateTo, constraint);
            constraint.fill = GridBagConstraints.HORIZONTAL;
            constraint.gridx = 0;
            constraint.gridy = 2;
            chooseDateScreen.add(btnCancel, constraint);
            constraint.gridx = 1;
            chooseDateScreen.add(Box.createRigidArea(new Dimension(20, 0)));
            constraint.gridx = 2;
            chooseDateScreen.add(btnOk, constraint);

            getContentPane().add(chooseDateScreen);
            btnCancel.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setModal(false);
                    getOwner().setEnabled(true);
                    dispose(); // đóng dialog khi người dùng hủy thao tác thêm từ
                }
            });
            btnOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    selectedDayFrom = (String) dayComboBox.getSelectedItem();
                    selectedMonthFrom = (String) monthComboBox.getSelectedItem();
                    selectedYearFrom = (String) yearComboBox.getSelectedItem();

                    selectedDayTo = (String) dayComboBox2.getSelectedItem().toString();
                    selectedMonthTo = (String) monthComboBox2.getSelectedItem().toString();
                    selectedYearTo = (String) yearComboBox2.getSelectedItem().toString();

                    String from = selectedYearFrom + "-" + selectedMonthFrom + "-" + selectedDayFrom;
                    String to = selectedYearTo + "-" + selectedMonthTo + "-" + selectedDayTo;
                    from = from.replace("Jan", "01").replace("Feb", "02").replace("Mar", "03").replace("Apr", "04")
                            .replace("May", "05").replace("Jun", "06").replace("Jul", "07").replace("Aug", "08")
                            .replace("Sep", "09").replace("Oct", "10").replace("Nov", "11").replace("Dec", "12");
                    to = to.replace("Jan", "01").replace("Feb", "02").replace("Mar", "03").replace("Apr", "04")
                            .replace("May", "05").replace("Jun", "06").replace("Jul", "07").replace("Aug", "08")
                            .replace("Sep", "09").replace("Oct", "10").replace("Nov", "11").replace("Dec", "12");
                    if (to.compareToIgnoreCase(from) >= 0) {
                        showStatisticScreen(statisticScreen, from, to);
                    } else {
                        JOptionPane.showMessageDialog(mainFrame, "Invalid Date", "Notification",
                                JOptionPane.INFORMATION_MESSAGE);
                    }

                    setModal(false);
                    getOwner().setEnabled(true);
                    dispose(); // đóng dialog khi người dùng hủy thao tác thêm từ

                }
            });

        }
    }

    public static void main(String[] args) {
        GUI gui = new GUI();
        gui.setVisible(true);

    }
};

// some problem: Lướt thông tin từ theo chiều dọc trường hợp thông tin quá nhiều
// chọn ngày để xem lịch sử tìm kiếm
