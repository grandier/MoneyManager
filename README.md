# Money Manager
A Money Manager App based on Android using NodeJS and PostgreSQL.

---
## Gambaran Umum Program
Sebuah Aplikasi yang membantu memanage keuangan personal berbasis Android yang dibuat menggunakan ```Android Studio``` berbasis Java untuk Front-End dan juga ```NodeJS``` yang berbasis JavaScript untuk Back-End dan juga Menggunakan Database berbasis ```PostgreSQL``` yang dibantu dengan layanan Cloud oleh Microsoft yaitu ```Azure```.

---
## Object Oriented Programming Project for Assistent Selection Program

This project is made by myself, a student of computer engineering Universitas Indonesia:

1. Kemas Rafly Omar Thoriq 

---
## Postman Documentation Collection
[![Run in Postman](https://run.pstmn.io/button.svg)](https://documenter.getpostman.com/view/18433581/UzBmMmzw)

---
## Penjelasan mengenai Table pada Program
### 1.  ```users```

Table users adalah table yang berguna untuk menyimpan data ```User``` dari appnya. Tabel ini memiliki beberapa attributes, diantaranya adalah:
```
1. User_ID
2. username
3. Password
```

### 2.  ```Pengeluaran```

Table Pengeluaran adalah table yang berguna untuk menyimpan data ```Pengeluaran``` dari appnya. Tabel ini memiliki beberapa attributes, diantaranya adalah:
```
1. Pengeluaran_ID
2. Deskripsi
3. Jumlah
4. Tanggal
5. KatPeng_ID
6. User_ID
```

### 3.  ```Pendapatan```

Table Pendapatan adalah table yang berguna untuk menyimpan data ```Pendapatan``` dari appnya. Tabel ini memiliki beberapa attributes, diantaranya adalah:
```
1. Pendapatan_ID
2. Deskripsi
3. Jumlah
4. Tanggal
5. KatPend_ID
6. User_ID
```

### 4.  ```Kategori_Pengeluaran```

Table Kategori_Pengeluaran adalah table yang berguna untuk menyimpan data ```Kategori_Pengeluaran``` dari appnya. Tabel ini memiliki beberapa attributes, diantaranya adalah:
```
1. KatPeng_ID
2. Jenis
3. User_ID
```

### 5.  ```Kategori_Pendapatan```

Table Kategori_Pendapatan adalah table yang berguna untuk menyimpan data ```Kategori_Pendapatan``` dari appnya. Tabel ini memiliki beberapa attributes, diantaranya adalah:
```
1. KatPend_ID
2. Jenis
3. User_ID
```

---
# Tampilan Relation Table dan UML
```Table Relational atau ERD:```

![alt text!](https://github.com/grandier/MoneyManager/blob/master/Assets/ERD_MoneyManager.png)

```UML:```

![alt text!](https://github.com/grandier/MoneyManager/blob/master/Assets/UML_MoneyManager.png)


---
# Tampilan FlowChart

```FlowChart:```

![alt text!](https://github.com/grandier/MoneyManager/blob/master/Assets/Flowchart_MoneyManager.png)

---
