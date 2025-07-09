package frontend;

import backend.*;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class FormPeminjaman extends javax.swing.JFrame {

    public FormPeminjaman() {
        initComponents();
        tampilkanCmb();
        tampilkanData();
        kosongkanFormPeminjaman();
    }

    public void kosongkanFormPeminjaman() {
        txtId.setText("0");
        if (cmbAnggota.getItemCount() > 0) {
            cmbAnggota.setSelectedIndex(0);
        }
        if (cmbBuku.getItemCount() > 0) {
            cmbBuku.setSelectedIndex(0);
        }
        txtTglPinjam.setText("");
        txtTglKembali.setText("");
        txtStatus.setText("0");
        
        txtId.setEnabled(false);
        cmbAnggota.setEnabled(true);
        cmbBuku.setEnabled(true);
        txtTglPinjam.setEnabled(true);
        txtTglKembali.setEnabled(false);
        txtStatus.setEnabled(false);
        btnSimpan.setEnabled(true); 
    }
    
    public void siapkanFormPengembalian(Peminjaman p) {
        txtId.setText(String.valueOf(p.getIdPeminjaman()));
        cmbAnggota.setSelectedItem(p.getAnggota());
        cmbBuku.setSelectedItem(p.getBuku());
        txtTglPinjam.setText(p.getTanggalPinjam());
        txtTglKembali.setText("");
        txtStatus.setText("1");

        txtId.setEnabled(false);
        cmbAnggota.setEnabled(false);
        cmbBuku.setEnabled(false);
        txtTglPinjam.setEnabled(false);
        txtTglKembali.setEnabled(true);
        txtStatus.setEnabled(false);
    }

    public void tampilkanCmb() {
        DefaultComboBoxModel modelAnggota = new DefaultComboBoxModel();
        for (Anggota agt : new Anggota().getAll()) {
            modelAnggota.addElement(agt);
        }
        cmbAnggota.setModel(modelAnggota);

        DefaultComboBoxModel modelBuku = new DefaultComboBoxModel();
        for (Buku bk : new Buku().getAll()) {
            modelBuku.addElement(bk);
        }
        cmbBuku.setModel(modelBuku);
    }

    public void tampilkanData() {
        String[] kolom = {"ID", "Nama Anggota", "Judul Buku", "Tgl Pinjam", "Tgl Kembali", "Status"};
        ArrayList<Peminjaman> list = new Peminjaman().getAll();
        Object rowData[] = new Object[6];

        tblPeminjaman.setModel(new DefaultTableModel(new Object[][]{}, kolom));

        for (Peminjaman p : list) {
            rowData[0] = p.getIdPeminjaman();
            rowData[1] = p.getAnggota().getNama();
            rowData[2] = p.getBuku().getJudul();
            rowData[3] = p.getTanggalPinjam();
            rowData[4] = p.getTanggalKembali();
            rowData[5] = (p.getStatus() == 0) ? "Belum Kembali" : "Sudah Kembali"; 

            ((DefaultTableModel) tblPeminjaman.getModel()).addRow(rowData);
        }
    }
    

public void populateTable(ArrayList<Peminjaman> list) {
    DefaultTableModel model = (DefaultTableModel) tblPeminjaman.getModel();
    model.setRowCount(0);

    for (Peminjaman pj : list) {
        Object[] row = new Object[]{
            pj.getIdPeminjaman(),
            pj.getAnggota().getNama(),
            pj.getBuku().getJudul(),
            ubahFormatTanggal(pj.getTanggalPinjam()),
            ubahFormatTanggal(pj.getTanggalKembali()),
            pj.getStatus() == 1 ? "Dikembalikan" : "Dipinjam"
        };
        // -------------------------
        
        model.addRow(row);
    }
}

public void tampilkanSemuaData() {
    ArrayList<Peminjaman> semuaPeminjaman = new Peminjaman().getAll();
    populateTable(semuaPeminjaman);
}

public String ubahFormatTanggal(String tanggalSql) {
    if (tanggalSql == null || tanggalSql.isEmpty()) {
        return "";
    }

    try {
        DateTimeFormatter formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        DateTimeFormatter formatterOutput = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        LocalDate tanggal = LocalDate.parse(tanggalSql, formatterInput);

        return tanggal.format(formatterOutput);
        
    } catch (DateTimeParseException e) {
        System.err.println("Gagal mem-parsing tanggal, format tidak sesuai: " + tanggalSql);
        return tanggalSql;
    }
}

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator1 = new javax.swing.JSeparator();
        lblId = new javax.swing.JLabel();
        lblAnggota = new javax.swing.JLabel();
        lblIdBuku = new javax.swing.JLabel();
        lblTglPinjam = new javax.swing.JLabel();
        lblTglKembali = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtTglPinjam = new javax.swing.JTextField();
        txtTglKembali = new javax.swing.JTextField();
        btnSimpan = new javax.swing.JButton();
        btnTambahBaru = new javax.swing.JButton();
        btnHapus = new javax.swing.JButton();
        lblFormat = new javax.swing.JLabel();
        lblFormat1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblPeminjaman = new javax.swing.JTable();
        cmbAnggota = new javax.swing.JComboBox<>();
        cmbBuku = new javax.swing.JComboBox<>();
        txtStatus = new javax.swing.JTextField();
        lblStatus = new javax.swing.JLabel();
        txtCariId = new javax.swing.JTextField();
        btnCari = new javax.swing.JButton();
        txtResetCari = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        lblId.setText("ID");

        lblAnggota.setText("ID Anggota");

        lblIdBuku.setText("ID Buku");

        lblTglPinjam.setText("Tanggal Pinjam");

        lblTglKembali.setText("Tanggal Kembali");

        txtId.setEnabled(false);

        btnSimpan.setText("Simpan");
        btnSimpan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSimpanActionPerformed(evt);
            }
        });

        btnTambahBaru.setText("Tambah Baru");
        btnTambahBaru.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTambahBaruActionPerformed(evt);
            }
        });

        btnHapus.setText("Hapus");
        btnHapus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHapusActionPerformed(evt);
            }
        });

        lblFormat.setText("Format : YYYY/MM/DD");

        lblFormat1.setText("Format : YYYY/MM/DD");

        tblPeminjaman.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tblPeminjaman.setShowGrid(true);
        tblPeminjaman.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblPeminjamanMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblPeminjaman);

        cmbAnggota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbAnggotaActionPerformed(evt);
            }
        });

        txtStatus.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtStatusActionPerformed(evt);
            }
        });

        lblStatus.setText("STATUS");

        txtCariId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCariIdActionPerformed(evt);
            }
        });

        btnCari.setText("Cari");
        btnCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCariActionPerformed(evt);
            }
        });

        txtResetCari.setText("Reset");
        txtResetCari.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtResetCariActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(lblAnggota)
                                    .addComponent(lblIdBuku))
                                .addGap(36, 36, 36)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cmbAnggota, 0, 206, Short.MAX_VALUE)
                                    .addComponent(cmbBuku, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblTglKembali)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtTglKembali, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFormat1))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(lblTglPinjam)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtTglPinjam)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblFormat)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnTambahBaru)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnHapus)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnSimpan)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                .addGap(84, 84, 84)
                                .addComponent(btnCari)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCariId, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtResetCari))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblId)
                        .addGap(87, 87, 87)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lblStatus)
                        .addGap(22, 22, 22))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lblId)
                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblStatus, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblAnggota)
                    .addComponent(cmbAnggota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblIdBuku)
                    .addComponent(cmbBuku, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTglPinjam)
                    .addComponent(txtTglPinjam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFormat))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblTglKembali)
                    .addComponent(txtTglKembali, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFormat1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnSimpan)
                    .addComponent(txtCariId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTambahBaru)
                    .addComponent(btnHapus)
                    .addComponent(txtResetCari)
                    .addComponent(btnCari))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSimpanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSimpanActionPerformed
    try {
        // Langkah 1: Ambil semua data dari komponen form
        int id = Integer.parseInt(txtId.getText());
        Anggota anggotaTerpilih = (Anggota) cmbAnggota.getSelectedItem();
        Buku bukuTerpilih = (Buku) cmbBuku.getSelectedItem();
        String tglPinjamInput = txtTglPinjam.getText().trim();
        String tglKembaliInput = txtTglKembali.getText().trim();

        // Langkah 2: Siapkan format untuk validasi dan penyimpanan
        SimpleDateFormat formatTampilan = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat formatDb = new SimpleDateFormat("yyyy-MM-dd");
        formatTampilan.setLenient(false); // Validasi tanggal yang ketat

        // Langkah 3: Validasi dan konversi tanggal
        Date datePinjam = null;
        if (!tglPinjamInput.isEmpty()) {
            datePinjam = formatTampilan.parse(tglPinjamInput);
        } else {
            // Jika tanggal pinjam kosong, hentikan proses (penting untuk data baru)
            System.err.println("Error: Tanggal Pinjam tidak boleh kosong.");
            return;
        }

        Date dateKembali = null;
        if (!tglKembaliInput.isEmpty()) {
            dateKembali = formatTampilan.parse(tglKembaliInput);
            // Validasi urutan tanggal
            if (dateKembali.before(datePinjam)) {
                System.err.println("Error: Tanggal Kembali tidak boleh sebelum Tanggal Pinjam.");
                return;
            }
        }

        // Langkah 4: Siapkan objek Peminjaman
        Peminjaman p;
        if (id == 0) { // Jika membuat data baru
            p = new Peminjaman();
        } else { // Jika mengedit data yang ada
            p = new Peminjaman().getById(id);
        }

        // Langkah 5: Atur semua properti objek Peminjaman
        p.setIdPeminjaman(id);
        p.setAnggota(anggotaTerpilih);
        p.setBuku(bukuTerpilih);
        p.setTanggalPinjam(formatDb.format(datePinjam)); // Simpan dalam format yyyy-MM-dd
        p.setTanggalKembali(dateKembali != null ? formatDb.format(dateKembali) : null);
        p.setStatus((dateKembali != null) ? 1 : 0); // Status 1 jika sudah kembali, 0 jika belum

        // Langkah 6: Panggil method save dari backend
        p.save();

        // Langkah 7: Perbarui tampilan dan kosongkan form
        tampilkanSemuaData();
        kosongkanFormPeminjaman();
        System.out.println("Proses simpan/update selesai.");

    } catch (ParseException e) {
        // Error jika pengguna memasukkan format tanggal yang salah
        System.err.println("Format tanggal salah. Harusnya yyyy/MM/dd. Error: " + e.getMessage());
    } catch (Exception e) {
        // Menangkap error lain yang mungkin terjadi
        System.err.println("Terjadi kesalahan saat menyimpan: " + e.getMessage());
        e.printStackTrace();
        }
    }//GEN-LAST:event_btnSimpanActionPerformed

    private void btnTambahBaruActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTambahBaruActionPerformed
        kosongkanFormPeminjaman();
    }//GEN-LAST:event_btnTambahBaruActionPerformed

    private void btnHapusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHapusActionPerformed
        int row = tblPeminjaman.getSelectedRow();
        
        if(row == -1){
            System.out.println("Pilih data yang ingin dihapus.");
            return;
        }

        int id = Integer.parseInt(tblPeminjaman.getValueAt(row, 0).toString());

        Peminjaman p = new Peminjaman().getById(id);
        p.delete();
        
        tampilkanData();
        kosongkanFormPeminjaman();
    }//GEN-LAST:event_btnHapusActionPerformed

    private void cmbAnggotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmbAnggotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cmbAnggotaActionPerformed

    private void txtStatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtStatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtStatusActionPerformed

    private void tblPeminjamanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblPeminjamanMouseClicked
        try {
        int row = tblPeminjaman.getSelectedRow();
        if (row == -1) {
            return;
        }

        int id = Integer.parseInt(tblPeminjaman.getValueAt(row, 0).toString());
        Peminjaman p = new Peminjaman().getById(id);

        if (p.getIdPeminjaman() == 0) {
            return;
        }

        txtId.setText(String.valueOf(p.getIdPeminjaman()));
        txtTglPinjam.setText(ubahFormatTanggal(p.getTanggalPinjam()));
        txtTglKembali.setText(ubahFormatTanggal(p.getTanggalKembali()));
        cmbAnggota.setSelectedItem(p.getAnggota());
        cmbBuku.setSelectedItem(p.getBuku());
        txtStatus.setText(String.valueOf(p.getStatus()));
        
        boolean sudahDikembalikan = (p.getStatus() == 1);

        // Hanya tombol Simpan yang diatur berdasarkan status
        btnSimpan.setEnabled(!sudahDikembalikan);
        
        // --- PERUBAHAN DI SINI ---
        // Tombol Hapus dibuat selalu aktif
        btnHapus.setEnabled(true); 
        // -------------------------

        // Anda juga bisa mengatur komponen lain jika perlu
        cmbAnggota.setEnabled(!sudahDikembalikan);
        cmbBuku.setEnabled(!sudahDikembalikan);
        txtTglPinjam.setEnabled(!sudahDikembalikan);
        txtTglKembali.setEnabled(!sudahDikembalikan);
        txtStatus.setEnabled(!sudahDikembalikan);

    } catch (Exception e) {
        System.err.println("Terjadi error saat memuat data dari tabel ke form.");
        e.printStackTrace();
        }
    }//GEN-LAST:event_tblPeminjamanMouseClicked

    private void txtCariIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCariIdActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCariIdActionPerformed

    private void btnCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCariActionPerformed
        String idAnggotaStr = txtCariId.getText().trim();

    // Jika input kosong, langsung hentikan proses tanpa pesan
    if (idAnggotaStr.isEmpty()) {
        return;
    }

    try {
        int idAnggota = Integer.parseInt(idAnggotaStr);
        Anggota anggotaDitemukan = new Anggota().getById(idAnggota);

        if (anggotaDitemukan.getIdAnggota() > 0) {
            // Jika ditemukan, pilih di ComboBox
            cmbAnggota.setSelectedItem(anggotaDitemukan);

            // Ambil data peminjaman untuk anggota ini
            ArrayList<Peminjaman> listTerfilter = new Peminjaman().getAllByAnggotaId(idAnggota);
            
            // Tampilkan data yang sudah difilter ke tabel
            populateTable(listTerfilter);

        } else {
            // Jika anggota tidak ditemukan, kosongkan tabel
            populateTable(new ArrayList<>()); // Mengirim list kosong ke tabel
        }
    } catch (NumberFormatException e) {
        // Jika input bukan angka, proses berhenti secara hening.
        // Anda bisa menambahkan System.out.println("Input bukan angka") di sini untuk debugging jika perlu.
        System.out.println("Input pada pencarian anggota bukan angka: " + idAnggotaStr);
        }
    }//GEN-LAST:event_btnCariActionPerformed

    private void txtResetCariActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtResetCariActionPerformed
        tampilkanSemuaData();
        txtCariId.setText(""); 
    }//GEN-LAST:event_txtResetCariActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPeminjaman.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPeminjaman().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCari;
    private javax.swing.JButton btnHapus;
    private javax.swing.JButton btnSimpan;
    private javax.swing.JButton btnTambahBaru;
    private javax.swing.JComboBox<String> cmbAnggota;
    private javax.swing.JComboBox<String> cmbBuku;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lblAnggota;
    private javax.swing.JLabel lblFormat;
    private javax.swing.JLabel lblFormat1;
    private javax.swing.JLabel lblId;
    private javax.swing.JLabel lblIdBuku;
    private javax.swing.JLabel lblStatus;
    private javax.swing.JLabel lblTglKembali;
    private javax.swing.JLabel lblTglPinjam;
    private javax.swing.JTable tblPeminjaman;
    private javax.swing.JTextField txtCariId;
    private javax.swing.JTextField txtId;
    private javax.swing.JButton txtResetCari;
    private javax.swing.JTextField txtStatus;
    private javax.swing.JTextField txtTglKembali;
    private javax.swing.JTextField txtTglPinjam;
    // End of variables declaration//GEN-END:variables
}
