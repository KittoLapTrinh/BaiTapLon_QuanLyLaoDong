-- BÀI TẬP LỚN HƯỚNG SỰ KIỆN - JAVA

USE master
DROP DATABASE QuanLyLaoDong

CREATE DATABASE	QuanLyLaoDong
GO

USE QuanLyLaoDong
GO

CREATE TABLE ChuyenMon (
	maChuyenMon varchar(10) not null primary key,
	tenChuyenMon nvarchar(100) not null
)
GO

CREATE TABLE CongNhan (
	maCongNhan varchar(10) not null primary key,
	ho nvarchar(30) not null,
	ten nvarchar(20) not null,
	gioiTinh bit not null,
	ngaySinh date not null check (YEAR(GETDATE()) - YEAR(ngaySinh) >= 18),
	diaChi nvarchar(100) not null,
	soDienThoai varchar(15) NOT NULL check (soDienThoai LIKE '0[0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9][0-9]'),
	cccd varchar(15) NOT NULL,
	bacTho nvarchar(50) NOT NULL,
	trinhDo nvarchar(20) NOT NULL,
	maChuyenMon varchar(10) foreign key references ChuyenMon(maChuyenMon),
	namKinhNghiem int NOT NULL CHECK (namKinhNghiem > 0)
)
GO

ALTER TABLE CongNhan
ADD CONSTRAINT CK__CCCD CHECK(cccd LIKE ('[0-9][0-9][0-9][0-3]' + 
	(CASE
		WHEN year(ngaySinh) % 100 < 10 THEN '0' + convert(char(1), year(ngaySinh) % 100)
		ELSE convert(char(2), year(ngaySinh) % 100)
	END)
	+ '[0-9][0-9][0-9][0-9][0-9][0-9]'))

CREATE TABLE CongTrinh (
	maCongTrinh varchar(10) not null primary key,
	tenCongTrinh nvarchar(50) NOT NULL,
	diaDiem nvarchar(100) NOT NULL,
	chuDauTu nvarchar(100) NOT NULL,
	giayPhepXD nvarchar(50) NOT NULL CHECK (giayPhepXD LIKE '%/GPXD%'),
	ngayCap date NOT NULL CHECK (ngayCap <= GETDATE()),
	ngayKhoiCong date NOT NULL,
	ngayHT date NOT NULL
)
GO

ALTER TABLE CongTrinh
ADD CONSTRAINT CK__NGAY_KHOI_CONG CHECK (ngayKhoiCong > ngayCap)

ALTER TABLE CongTrinh
ADD CONSTRAINT CK__NGAY_HOAN_THANH CHECK (ngayHT > ngayKhoiCong)

CREATE TABLE CongViec (
	maCongViec varchar(10) not null PRIMARY KEY,
	tenCongViec nvarchar(100) NOT NULL
)
GO

CREATE FUNCTION getNgayKhoiCong(@maCongTrinh varchar(10))
RETURNS varchar(10)
AS
BEGIN 
	DECLARE @ngayKhoiCong DATE

	SELECT @ngayKhoiCong = ngayKhoiCong FROM CongTrinh
	WHERE maCongTrinh = @maCongTrinh

	RETURN @ngayKhoiCong
END
GO

CREATE TABLE PhanCong (
	maPhanCong varchar(10) not null primary key,
	maCongNhan varchar(10) NOT NULL foreign key references CongNhan(maCongNhan),
	maCongViec varchar(10) NOT NULL foreign key references CongViec(maCongViec),
	maCongTrinh varchar(10) not null  foreign key references CongTrinh(maCongTrinh),
	ngayBatDau date NOT NULL,
	ngayKetThuc date NOT NULL,
	ghiChu nvarchar(100)
)
GO

ALTER TABLE PhanCong
ADD CONSTRAINT CK__NGAY_BAT_DAU CHECK (ngayBatDau >= [dbo].[getNgayKhoiCong](maCongTrinh))

ALTER TABLE PhanCong
ADD CONSTRAINT CK__NGAY_KET_THUC CHECK (ngayKetThuc > ngayBatDau)

INSERT CHUYENMON
VALUES 
	('CMLS', N'Làm sắt'),
	('CMDT', N'Đổ cột'),
	('CMST', N'Sơn tường')

INSERT CHUYENMON
VALUES 
	('CMDM', N'Đào móng'),
	('CMVV', N'Vét vôi'),
	('CMXD', N'Xây dựng')

UPDATE CHUYENMON
SET maChuyenMon = 'CMDT'
WHERE maChuyenMon = 'CMCT'

SELECT * FROM  CHUYENMON

SELECT * FROM  CONGNHAN
DELETE CONGNHAN

INSERT CONGNHAN
VALUES
	('CN111', N'Nguyễn Anh', N'Tuấn', 1, '1969-05-27', N'Hà Nội', '0348567437', '001169000001', '4/7', '12/12', 'CMST', 6),
	('CN132', N'Đào Công', N'Đức', 1, '1970-03-14', N'Hà Nội', '0350393398', '001170000030', '6/7', '12/12', 'CMDM', 3),
	('CN133', N'Nguyễn Văn', N'Chung', 1, '1965-03-26', N'Hà Nội', '0351372420', '001165946357', '6/7', '12/12', 'CMLS', 8),
	('CN145', N'Nguyễn Hữu', N'Hùng', 1, '1984-02-12', N'Hà Nội', '0327909965', '001184000555', '4/7', '12/12', 'CMDT', 3),
	('CN154', N'Đinh Văn', N'Học', 1, '1974-02-05', N'Vĩnh Phúc', '0361282818', '025174027098', '2/7', '12/12', 'CMVV', 10),
	('CN155', N'Đào Đăng', N'Đại', 1, '1965-11-17', N'Hà Nội', '0312427810', '001165000098', '5/7', '12/12', 'CMXD', 7),
	('CN157', N'Đào Đức', N'Ngọc', 1, '1970-05-29', N'Hà Nội', '0323155426', '001170428502', '6/7', '12/12', 'CMXD', 5),
	('CN168', N'Đỗ Văn', N'Đông', 1, '1967-08-15', N'Thái Bình', '0328410965', '034167720272', '7/7', '12/12', 'CMDM', 5),
	('CN170', N'Nguyễn Văn', N'Dũng', 1, '1978-07-12', N'Hà Nội', '0327730880', '001178428592', '3/7', '12/12', 'CMDT', 9),
	('CN182', N'Lê Thị', N'Hà', 0, '1979-04-08', N'Hà Nội', '0330087339', '001079720285', '2/7', '12/12', 'CMXD', 2)

INSERT CONGVIEC
VALUES 
	('DMLM', N'Đào móng và lót móng'),
	('DBTM', N'Đổ bê tông móng'),
	('DBTC', N'Đổ bê tông cột'),
	('CVXT', N'Xây tường'),
	('CLMT', N'Lợp mái tôn'),
	('STTN', N'Sơn tường tòa nhà')

INSERT CONGVIEC
VALUES 
	('CMXD', N'Xây dựng')

INSERT CongTrinh
VALUES 
	('CTHT', N'Nhà hội trường', N'Xã Vĩnh Tân, huyện Vĩnh Lộc, tỉnh Thanh Hóa', N'UBND xã Vĩnh Tân', '32/GPXD', '2020-07-23', '2020-09-01', '2021-09-01'),
	('CTNO', N'Nhà ở 3 lầu 1 tum cầu thang', N'Quận Bình Thạnh, Thành phố Hồ Chí Minh', N'Nguyễn Hữu Cảnh', '53/GPXD', '2019-03-15', '2019-04-15', '2019-09-15'),
	('CTNP', N'Nhà phố 1 trệt 1 lửng 2 lầu 1 tum sân thượng', N'Ngõ 173 Hoàng Hoa Thám', N'Nguyễn Văn Nguyên', '21/GPXD', '2020-06-05', '2020-12-01', '2021-05-26')

SELECT * FROM CONGNHAN
SELECT * FROM CONGVIEC
SELECT * FROM CongTrinh

INSERT PhanCong
VALUES 
	('PCCT005', 'CN168', 'DMLM', 'CTHT', '2020-09-02', '2020-09-20', ''),
	('PCCT006', 'CN132', 'DMLM', 'CTHT', '2020-09-02', '2020-09-20', ''),
	('PCCT007', 'CN132', 'DBTM', 'CTHT', '2020-09-21', '2020-12-20', ''),
	('PCCT008', 'CN155', 'CVXT', 'CTHT', '2020-12-21', '2021-02-21', ''),
	('PCCT355', 'CN132', 'DMLM', 'CTNP', '2020-12-01', '2020-12-11', ''),
	('PCCT357', 'CN182', 'CVXT', 'CTNP', '2020-12-11', '2022-02-16', ''),
	('PCCT359', 'CN154', 'STTN', 'CTNP', '2021-04-26', '2021-05-20', ''),
	('PCCT360', 'CN111', 'STTN', 'CTNP', '2021-05-20', '2021-05-25', ''),
	('PCCT400', 'CN132', 'DMLM', 'CTNO', '2019-04-16', '2019-05-02', ''),
	('PCCT420', 'CN155', 'CVXT', 'CTNO', '2019-05-13', '2019-07-15', ''),
	('PCCT421', 'CN157', 'CVXT', 'CTNO', '2019-05-13', '2019-07-15', ''),
	('PCCT422', 'CN182', 'CVXT', 'CTNO', '2019-05-13', '2019-07-01', '')
