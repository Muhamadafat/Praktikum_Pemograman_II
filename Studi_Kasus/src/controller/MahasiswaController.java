package controller;

import dao.MahasiswaDAO;
import model.Mahasiswa;
import view.MahasiswaView;

import java.util.List;

public class MahasiswaController {
    private MahasiswaDAO mahasiswaDAO;
    private MahasiswaView view;

    public MahasiswaController(MahasiswaView view) {
        this.mahasiswaDAO = new MahasiswaDAO();
        this.view = view;
    }

    public void muatDataMahasiswa() {
        List<Mahasiswa> mahasiswaList = mahasiswaDAO.getAllMahasiswa();
        view.tampilkanDataMahasiswa(mahasiswaList);
    }

    public void tambahMahasiswa(Mahasiswa mahasiswa) {
        boolean isAdded = mahasiswaDAO.addMahasiswa(mahasiswa);
        if (isAdded) {
            muatDataMahasiswa();
        } else {
            view.showError("Gagal menambah data mahasiswa.");
        }
    }

    public void ubahMahasiswa(Mahasiswa mahasiswa) {
        boolean isUpdated = mahasiswaDAO.updateMahasiswa(mahasiswa);
        if (isUpdated) {
            muatDataMahasiswa();
        } else {
            view.showError("Gagal mengubah data mahasiswa.");
        }
    }

    public void hapusMahasiswa(int nim) {
        boolean isDeleted = mahasiswaDAO.deleteMahasiswa(nim);
        if (isDeleted) {
            muatDataMahasiswa();
        } else {
            view.showError("Gagal menghapus data mahasiswa.");
        }
    }
}