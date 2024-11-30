package Pertemuan8.src.view.jenismember;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.UUID;

import Pertemuan7.Membership.src.view.jenismember.JenisMemberFrame;
import Pertemuan8.src.dao.JenisMemberDao;

public class JenisMemberButtonSimpanActionListener implements ActionListener {

    private JenisMemberFrame jenisMemberFrame;
    private JenisMemberDao jenisMemberDao;

    public JenisMemberButtonSimpanActionListener(JenisMemberFrame jenisMemberFrame, JenisMemberDao jenisMemberDao) {
        this.jenisMemberFrame = jenisMemberFrame;
        this.jenisMemberDao = jenisMemberDao;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String nama = this.jenisMemberFrame.getName();

        JenisMember jenisMember = new JenisMember();
        jenisMember.setId(UUID.randomUUID().toString());
        jenisMember.setNama(nama);

        this.jenisMemberFrame.addJenisMember(jenisMember);
        this.jenisMemberDao.insert(jenisMember);
    }

}