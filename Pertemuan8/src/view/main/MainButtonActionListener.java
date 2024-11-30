package Pertemuan8.src.view.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Pertemuan7.Membership.src.view.main.MainFrame;

public class MainButtonActionListener implements ActionListener {

    private MainFrame mainFrame;

    public MainButtonActionListener(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFrame.getButtonJenisMember()) {
            mainFrame.showJenisMemberFrame();
        } else {
            mainFrame.showMemberFrame();
        }
    }
}
