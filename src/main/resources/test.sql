CREATE DATABASE TEST;
USE TEST;


CREATE TABLE SANBAY (
	MaSanBay CHAR(10) PRIMARY KEY, 
	TenSanBay VARCHAR(50) NOT NULL,
    DiaChi VARCHAR(50) NOT NULL
);

CREATE TABLE HANGMAYBAYSX (
	MaHMB CHAR(10) PRIMARY KEY,
    TenHMB char(50) NOT NULL
);

CREATE TABLE MAYBAY (
	MaMB CHAR(10) PRIMARY KEY,
    TenMB CHAR(50) NOT NULL,
    MaHMB CHAR(20) NOT NULL,
    SLGhe INT NOT NULL,
    FOREIGN KEY (MaHMB) REFERENCES HANGMAYBAYSX(MaHMB) ON DELETE CASCADE
);

CREATE TABLE CHUYENBAY (
    MaChuyenBay CHAR(10) PRIMARY KEY,
    MaMB CHAR(10) NOT NULL,
    MaSanBayDi CHAR(10) NOT NULL,
    MaSanBayDen CHAR(10) NOT NULL,
    NgayGioBay DATETIME NOT NULL,
    ThoiGianBay TIME NOT NULL,
    DonGia FLOAT NOT NULL,
    FOREIGN KEY (MaMB) REFERENCES MAYBAY(MaMB) ON DELETE CASCADE,
    FOREIGN KEY (MaSanBayDi) REFERENCES SANBAY(MaSanBay) ON DELETE CASCADE,
    FOREIGN KEY (MaSanBayDen) REFERENCES SANBAY(MaSanBay) ON DELETE CASCADE
);

CREATE TABLE TRUNGGIAN (
    MaSanBayTG CHAR(10) NOT NULL,
    MaChuyenBay CHAR(10) NOT NULL,
    ThoiGianDung TIME NOT NULL,
    GhiChu VARCHAR(255),
    PRIMARY KEY (MaSanBayTG, MaChuyenBay),
    FOREIGN KEY (MaSanBayTG) REFERENCES SANBAY(MaSanBay) ON DELETE CASCADE,
    FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay) ON DELETE CASCADE
);

CREATE TABLE HANGVE (
    MaHangVe CHAR(10) PRIMARY KEY,
    TenHangVe VARCHAR(50) NOT NULL,
    TiLeGiaHangVe FLOAT NOT NULL
);

CREATE TABLE CT_HANGVE (
    MaHangVe CHAR(10),
    MaChuyenBay CHAR(10),
    SoLuong INT NOT NULL,
    SoGheDat INT NOT NULL,
    SoGheBan INT NOT NULL,
    SoGHeConLai INT NOT NULL,
    DonGiaHV FLOAT NOT NULL,
    PRIMARY KEY (MaHangVe, MaChuyenBay),
    FOREIGN KEY (MaHangVe) REFERENCES HANGVE(MaHangVe) ON DELETE CASCADE,
    FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay) ON DELETE CASCADE
);

CREATE TABLE DSGHE (
	SoGhe INT NOT NULL,
    MaMB CHAR(10) NOT NULL,
    MaHangVe CHAR(10) NOT NULL,
    GhiChu VARCHAR(255) NOT NULL,
    TinhTrang VARCHAR(10) NOT NULL,
    PRIMARY KEY(SoGhe, MaMB, GhiChu),
    FOREIGN KEY (MaMB) REFERENCES MAYBAY(MaMB) ON DELETE CASCADE,
    FOREIGN KEY (MaHangVe) REFERENCES HANGVE(MaHangVe) ON DELETE CASCADE
);

CREATE TABLE LOAIHANHKHACH (
	MaLHK CHAR(10) NOT NULL,
    TenLHK CHAR(10) NOT NULL,
    TiLeLHK FLOAT NOT NULL,
    PRIMARY KEY (MaLHK)
);

CREATE TABLE HANHKHACH (
	MaHK SERIAL PRIMARY KEY,
	HoTen VARCHAR(150) NOT NULL,
	DinhDanh VARCHAR(20),
	SoDienThoai VARCHAR(15) NOT NULL,
	Email VARCHAR(255)
);


CREATE TABLE CUSTOMER (
	id SERIAL PRIMARY KEY,
	username VARCHAR(50) NOT NULL,
    password VARCHAR(500) NOT NULL,
    email VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE PHIEUDATCHO (
	SoPhieuDatCho CHAR(15) PRIMARY KEY,
    NgayDat DATE NOT NULL,
    MaChuyenBay CHAR(10) NOT NULL,
    MaHangVe CHAR(10) NOT NULL,
    MaHK SERIAL NOT NULL,
    MaLHK CHAR(10) NOT NULL,
    SoGhe INT NOT NULL,
    GiaVe FLOAT NOT NULL,
    TinhTrang VARCHAR(25) NOT NULL,
    FOREIGN KEY (MaLHK) REFERENCES LOAIHANHKHACH(MaLHK) ON DELETE CASCADE,
    FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay) ON DELETE CASCADE,
    FOREIGN KEY (MaHK) REFERENCES HANHKHACH(MaHK),
    FOREIGN KEY (MaHangVe) REFERENCES HANGVE(MaHangVe) ON DELETE CASCADE
);

CREATE TABLE VECHUYENBAY (
	MaVe CHAR(15) PRIMARY KEY,
    NgayXuatVe DATE,
    MaChuyenBay CHAR(10) NOT NULL,
    MaHangVe CHAR(10) NOT NULL,
    MaHK SERIAL  NOT NULL,
    MaLHK CHAR(10) NOT NULL,
    SoGhe INT NOT NULL,
    GiaVe FLOAT NOT NULL,
    FOREIGN KEY (MaLHK) REFERENCES LOAIHANHKHACH(MaLHK) ON DELETE CASCADE,
    FOREIGN KEY (MaChuyenBay) REFERENCES CHUYENBAY(MaChuyenBay) ON DELETE CASCADE,
    FOREIGN KEY (MaHK) REFERENCES HANHKHACH(MaHK),
    FOREIGN KEY (MaHangVe) REFERENCES HANGVE(MaHangVe) ON DELETE CASCADE
);

CREATE TABLE THAMSO (
	ID INT PRIMARY KEY,
	SoSanBayTGToiDa TINYINT NOT NULL,
	TGBayToiThieu TIME NOT NULL,
	TGDungToiThieu TIME NOT NULL,
	TGDungToiDa TIME NOT NULL,
	TGDatVeChamNhat TINYINT NOT NULL,
	TGHuyChamNhat TINYINT NOT NULL
);

CREATE TABLE BCDT_NAM (
	Nam INT PRIMARY KEY,
    TongDoanhThu FLOAT NOT NULL 
);

CREATE TABLE CT_BCDT_NAM (
	Thang INT NOT NULL,
    Nam INT NOT NULL,
    SoChuyenBay INT DEFAULT 0,
    DoanhThu FLOAT,
    TiLe FLOAT,
    PRIMARY KEY(Thang, Nam),
	FOREIGN KEY(Nam) REFERENCES BCDT_NAM(Nam)

);

CREATE TABLE CT_BCDT_THANG (
	Thang INT NOT NULL,
    Nam INT NOT NULL,
    MaChuyenBay CHAR(10),
    SoVeHang1 INT,
    SoVeHang2 INT,
    DoanhThu FLOAT,
    TiLe FLOAT,
    PRIMARY KEY(Thang, Nam, MaChuyenBay),
    FOREIGN KEY(Thang) REFERENCES CT_BCDT_NAM(Thang),
	FOREIGN KEY(Nam) REFERENCES CT_BCDT_NAM(Nam)
);

-- Thêm vài ví dụ vào bảng SANBAY
INSERT INTO SANBAY (MaSanBay, TenSanBay, DiaChi)
VALUES 
    ('SGN', 'Tân Sơn Nhất', 'Hồ Chí Minh'),
    ('HAN', 'Nội Bài', 'Hà Nội'),
    ('DAD', 'Đà Nẵng', 'Đà Nẵng'),
    ('CXR', 'Cam Ranh', 'Khánh Hòa'),
    ('PQC', 'Phú Quốc', 'Kiên Giang'),
    ('VCA', 'Cần Thơ', 'Cần Thơ');
    

-- Thêm vài ví dụ vào bảng HANGMAYBAYSX
INSERT INTO HANGMAYBAYSX (MaHMB, TenHMB)
VALUES 
    ('Boeing', 'Boeing Company'),
    ('Airbus', 'Airbus SAS'),
    ('Embraer', 'Embraer S.A.'),
    ('Bombardier', 'Bombardier Inc.'),
    ('ATR', 'Avions de Transport Régional'),
    ('Mitsubishi', 'Mitsubishi Aircraft Corporation');
    
-- Thêm vài ví dụ vào bảng MAYBAY
INSERT INTO MAYBAY (MaMB, TenMB, MaHMB, SLGhe)
VALUES 
    ('MB001', 'Boeing 737', 'Boeing', 150),
    ('MB002', 'Airbus A320', 'Airbus', 180),
    ('MB003', 'Embraer 190', 'Embraer', 100),
    ('MB004', 'Bombardier Q400', 'Bombardier', 78),
    ('MB005', 'ATR 72', 'ATR', 70),
    ('MB006', 'Mitsubishi MRJ', 'Mitsubishi', 90);

-- Thêm vài ví dụ vào bảng CHUYENBAY
INSERT INTO CHUYENBAY (MaChuyenBay, MaMB, MaSanBayDi, MaSanBayDen, NgayGioBay, ThoiGianBay, DonGia)
VALUES
    ('CB001', 'MB001', 'SGN', 'HAN', '2024-04-19 08:00:00', '02:00:00', 2000.00),
    ('CB002', 'MB002', 'HAN', 'DAD', '2024-04-19 10:00:00', '01:30:00', 1500.00),
    ('CB003', 'MB003', 'DAD', 'PQC', '2024-04-19 12:00:00', '01:00:00', 1200.00),
    ('CB004', 'MB004', 'PQC', 'CXR', '2024-04-19 14:00:00', '01:15:00', 1000.00),
    ('CB005', 'MB005', 'CXR', 'SGN', '2024-04-19 16:00:00', '01:30:00', 1800.00);

-- Thêm vài ví dụ vào bảng TRUNGGIAN
INSERT INTO TRUNGGIAN (MaSanBayTG, MaChuyenBay, ThoiGianDung, GhiChu)
VALUES 
    ('DAD', 'CB001', '00:30:00', 'Chờ tiếp nhiên liệu'),
    ('VCA', 'CB002', '00:45:00', 'Đón khách'),
    ('CXR', 'CB003', '00:20:00', 'Chuyển hàng hóa'),
    ('HAN', 'CB004', '00:35:00', 'Chờ phi hành đoàn mới'),
    ('SGN', 'CB005', '00:40:00', 'Kiểm tra kỹ thuật');


INSERT INTO HANGVE VALUES ('HANG1', 'Hạng 1', 1.05);
INSERT INTO HANGVE VALUES ('HANG2', 'Hạng 2', 1);

INSERT INTO CT_HANGVE (MaHangVe, MaChuyenBay, SoLuong, SoGheDat, SoGheBan, SoGheConLai, DonGiaHV)
VALUES
    ('HANG1', 'CB001', 100, 30, 20, 50, 2100),
    ('HANG2', 'CB001', 50, 10, 15, 25, 2000);
    
    
DELIMITER $$
CREATE PROCEDURE InsertSeats()
BEGIN
    DECLARE i INT DEFAULT 1;
    DECLARE j INT DEFAULT 101;

    -- Chèn dữ liệu cho 100 ghế hạng 1
    WHILE i <= 100 DO
        INSERT INTO DSGHE (SoGhe, MaMB, MaHangVe, GhiChu)
        VALUES (i, 'MB001', 'HANG1', '');
        SET i = i + 1;
    END WHILE;

    -- Chèn dữ liệu cho 50 ghế hạng 2
    WHILE j <= 150 DO
        INSERT INTO DSGHE (SoGhe, MaMB, MaHangVe, GhiChu)
        VALUES (j, 'MB001', 'HANG2', '');
        SET j = j + 1;
    END WHILE;
END $$
DELIMITER ;

CALL InsertSeats();

-- Thêm dữ liệu vào bảng LOAIHANHKHACH
INSERT INTO LOAIHANHKHACH (MaLHK, TenLHK, TiLeLHK) VALUES
('NL', 'Người lớn', 1.0), -- Giá vé người lớn không thay đổi
('TE', 'Trẻ em', 0.75),    -- Giá vé trẻ em bằng 75% giá vé người lớn
('SS', 'Sơ sinh', 0.1);     -- Giá vé sơ sinh bằng 10% giá vé người lớn


INSERT INTO CUSTOMER (id, username, password, email, role) VALUES
('1', 'Wan', '$2a$10$AvHuhg4AQ0gdBkV6f2reKOZ5DaWpLaTGhGA/mbuN6n6iT3jfjbFX.', 'duyquangho1302@gmail.com', 'ADMIN'),
('2', 'Quang', '$2a$10$GR8GkTL59Fs2JWE./hkCrePr8a9hbhpvqd7twv8iv0NeKnk3isb3e', 'henryho1302@gmail.com', 'STAFF'),
('3', 'LanhChien', '$2a$10$fykIaPKe/AV05kZtjN3iJO0H4QztTJ0sXhTc.LRbPrUaQoao.hRUi', 'tatdatda1302@gmail.com', 'USER'),
('4', 'Jeo', '$2a$10$8xx1lp.2Eacb1N4Zo9MmhOxG8TBGDBxD6csRRISRkNKKtwoukzOvC', 'tatdatda1009@gmail.com', 'USER');

UPDATE DSGHE
SET GhiChu = 'CB001'
WHERE MaMB = 'MB001';

ALTER TABLE PHIEUDATCHO
DROP FOREIGN KEY phieudatcho_ibfk_3;

ALTER TABLE VECHUYENBAY
MODIFY MaHK INT;

ALTER TABLE CUSTOMER
ADD COLUMN DinhDanh VARCHAR(20);

ALTER TABLE VECHUYENBAY
ADD COLUMN NgayDat DATE;

UPDATE CHUYENBAY
SET NgayGioBay = '2024-05-04 08:00:00'
WHERE MaChuyenBay = 'CB001';

UPDATE CHUYENBAY
SET NgayGioBay = '2024-05-04 10:00:00'
WHERE MaChuyenBay = 'CB002';

INSERT INTO THAMSO (ID, SoSanBayTGToiDa, TGBayToiThieu, TGDungToiThieu, TGDungToiDa, TGDatVeChamNhat, TGHuyChamNhat)
VALUES
(1, 2, '00:30:00', '00:10:00', '00:20:00', 24, 0);
