package ceit.aut.ac.ir.gui;

import ceit.aut.ac.ir.model.Note;
import ceit.aut.ac.ir.utils.FileUtils;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.Date;


public class CMainPanel extends JPanel {

    private JTabbedPane tabbedPane;
    private JList<File> directoryList;

    public CMainPanel() {

        setLayout(new BorderLayout());

        initDirectoryList(); // add JList to main Panel

        initTabbedPane(); // add TabbedPane to main panel
    }

    private void initTabbedPane() {
        tabbedPane = new JTabbedPane();
        add(tabbedPane, BorderLayout.CENTER);
    }

    private void initDirectoryList() {
        File[] files = FileUtils.getFilesInDirectory();
        directoryList = new JList<>(files);

        directoryList.setBackground(new Color(211, 211, 211));
        directoryList.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        directoryList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        directoryList.setVisibleRowCount(-1);
        directoryList.setMinimumSize(new Dimension(130, 100));
        directoryList.setMaximumSize(new Dimension(130, 100));
        directoryList.setFixedCellWidth(130);
        directoryList.setCellRenderer(new MyCellRenderer());
        directoryList.addMouseListener(new MyMouseAdapter());

        add(new JScrollPane(directoryList), BorderLayout.WEST);
    }


    public void addNewTab() {
        String title = JOptionPane.showInputDialog(directoryList, "Enter title");
        String date = (new Date()).toString();
        JTextArea textPanel = createTextPanel();
        textPanel.setText("Write Something here...");
        tabbedPane.addTab(title + "#" + date, textPanel);
    }

    public void openExistingNote(Note note) {
        JTextArea existPanel = createTextPanel();
        existPanel.setText(note.getContent());

        int tabIndex = tabbedPane.getTabCount() + 1;
        tabbedPane.addTab(note.getTitle() + "#" + note.getDate(), existPanel);
        tabbedPane.setSelectedIndex(tabIndex - 1);
    }

    public void saveNote() {
        String titleAndDate = tabbedPane.getTitleAt(tabbedPane.getSelectedIndex());
        StringBuilder title = new StringBuilder();
        StringBuilder date = new StringBuilder();
        boolean flag = false;
        for(int i = 0; i < titleAndDate.length(); ++i) {
            if(titleAndDate.charAt(i) == '#') {
                flag = true;
            } else if(flag) {
                date.append(titleAndDate.charAt(i));
            } else {
                title.append(titleAndDate.charAt(i));
            }
        }
        JTextArea textPanel = (JTextArea) tabbedPane.getSelectedComponent();
        String content = textPanel.getText();
        FileUtils.NoteWriter(new Note(title.toString(), content, date.toString()));
        updateListGUI();
    }

    public void saveAllTabs() {
        int index = tabbedPane.getTabCount();
        while(index != 0) {
            tabbedPane.setSelectedIndex(--index);
            saveNote();
        }
    }

    private JTextArea createTextPanel() {
        JTextArea textPanel = new JTextArea();
        textPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return textPanel;
    }

    private void updateListGUI() {
        File[] newFiles = FileUtils.getFilesInDirectory();
        directoryList.setListData(newFiles);
    }


    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent eve) {
            // Double-click detected
            if (eve.getClickCount() == 2) {
                int index = directoryList.locationToIndex(eve.getPoint());
                System.out.println("Item " + index + " is clicked...");
                File curr[] = FileUtils.getFilesInDirectory();
                Note note = FileUtils.NoteReader(curr[index]);
                openExistingNote(note);
            }
        }
    }


    private class MyCellRenderer extends DefaultListCellRenderer {

        @Override
        public Component getListCellRendererComponent(JList list, Object object, int index, boolean isSelected, boolean cellHasFocus) {
            if (object instanceof File) {
                File file = (File) object;
                setText(file.getName());
                setIcon(FileSystemView.getFileSystemView().getSystemIcon(file));
                if (isSelected) {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                } else {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setEnabled(list.isEnabled());
            }
            return this;
        }
    }
}
