package dao;

import model.Mahasiswa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MahasiswaDAO {
    public List<Mahasiswa> getAllMahasiswa() {
        List<Mahasiswa> mahasiswaList = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM mahasiswa")) {

            while (rs.next()) {
                Mahasiswa mahasiswa = new Mahasiswa(
                        rs.getInt("nim"),
                        rs.getString("nama"),
                        rs.getString("jurusan"),
                        rs.getString("email"),
                        rs.getString("alamat")
                );
                mahasiswaList.add(mahasiswa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mahasiswaList;
    }

    public boolean addMahasiswa(Mahasiswa mahasiswa) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "INSERT INTO mahasiswa (nim, nama, jurusan, email, alamat) VALUES (?, ?, ?, ?, ?)")
        ) {
            pstmt.setInt(1, mahasiswa.getNim());
            pstmt.setString(2, mahasiswa.getNama());
            pstmt.setString(3, mahasiswa.getJurusan());
            pstmt.setString(4, mahasiswa.getEmail());
            pstmt.setString(5, mahasiswa.getAlamat());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean updateMahasiswa(Mahasiswa mahasiswa) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "UPDATE mahasiswa SET nama=?, jurusan=?, email=?, alamat=? WHERE nim=?")
        ) {
            pstmt.setString(1, mahasiswa.getNama());
            pstmt.setString(2, mahasiswa.getJurusan());
            pstmt.setString(3, mahasiswa.getEmail());
            pstmt.setString(4, mahasiswa.getAlamat());
            pstmt.setInt(5, mahasiswa.getNim());
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteMahasiswa(int nim) {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(
                     "DELETE FROM mahasiswa WHERE nim=?")
        ) {
            pstmt.setInt(1, nim);
            pstmt.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}