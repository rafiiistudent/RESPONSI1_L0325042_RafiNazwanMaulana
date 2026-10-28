# Sistem Logistik Bantuan

Program Java sederhana untuk mendata barang bantuan pendidikan yang akan dikirim ke daerah 3T (Terdepan, Terluar, Tertinggal). Program ini dibuat sebagai tugas Responsi 1 Praktikum Pemrograman Berorientasi Objek.

## Fitur
- Menambahkan data barang bantuan.
- Memvalidasi kode barang dengan awalan `EDX-`.
- Memastikan berat barang lebih dari 0 kg.
- Menyimpan beberapa barang menggunakan `ArrayList`.
- Menghitung jumlah barang dan total berat muatan.
- Menentukan jenis armada berdasarkan total berat.
- Mengekspor manifes pengiriman ke file teks.

## Teknologi
- Java
- ArrayList
- Exception Handling
- File I/O

## Struktur File
- `BarangBantuan.java` — menyimpan data barang.
- `BeratInvalidException.java` — menangani berat barang yang tidak valid.
- `SistemLogistikBantuan.java` — menjalankan menu, validasi, perhitungan, dan ekspor manifes.

## Ketentuan Armada
| Total Berat | Armada |
|---|---|
| Kurang dari 50 kg | Mobil Pickup |
| 50 kg atau lebih | Truk Ekspedisi |

## Cara Menjalankan
1. Buka project di IDE Java.
2. Pastikan ketiga file berada dalam package yang sama.
3. Jalankan `SistemLogistikBantuan.java`.
4. Pilih menu untuk menambahkan barang atau menyelesaikan pendataan.
5. Setelah memilih menu selesai, periksa file `manifest_pengiriman.txt` pada direktori kerja program.

## Hasil
File manifes berisi daftar barang, jumlah barang, total berat muatan, dan jenis armada yang digunakan.

**Nama:** Rafi Nazwan Maulana  
**NIM:** L0325042  
**Program Studi:** S-1 Informatika  
**Universitas Sebelas Maret**
