-- Chèn dữ liệu vào bảng Author
INSERT INTO `Author` (`name`, `year`, `isActive`) VALUES
('Nguyễn Nhật Ánh', 1955, 1),
('Xuân Diệu', 1916, 0),
('Nam Cao', 1915, 0),
('Thach Lam', 1910, 1),
('Nguyễn Tuân', 1910, 0),
('Ngô Tất Tố', 1893, 0),
('Vũ Trọng Phụng', 1912, 1),
('Thanh Tịnh', 1911, 0),
('Nguyên Hồng', 1918, 0),
('Nguyên Ngọc', 1932, 0);

-- Chèn dữ liệu vào bảng Category
INSERT INTO `Category` (`name`, `isActive`) VALUES
('Văn học cổ điển', 1),
('Văn học hiện đại', 1),
('Khoa học tự nhiên', 1),
('Khoa học xã hội', 1),
('Kinh tế học', 1),
('Quản trị kinh doanh', 1),
('Kỹ năng sống', 1),
('Khoa học công nghệ', 1),
('Văn học thiếu nhi', 1),
('Phát triển bản thân', 1);


-- Chèn dữ liệu vào bảng Publisher
INSERT INTO `Publisher` (`name`, `isActive`) VALUES
('Nhà xuất bản Kim Đồng', 1),
('Nhà xuất bản Trẻ', 1),
('Nhà xuất bản Giáo dục', 1),
('Nhà xuất bản Văn Học', 1),
('Nhà xuất bản Lao Động', 1),
('Nhà xuất bản Tổng Hợp', 1),
('Nhà xuất bản Văn Nghệ', 1),
('Nhà xuất bản Công An Nhân Dân', 1),
('Nhà xuất bản Thanh Niên', 1),
('Nhà xuất bản Phụ Nữ', 1);

-- Chèn dữ liệu vào bảng Book
INSERT INTO `Book` (`name`, `isActive`) VALUES
('Cho Tôi Xin Một Vé Đi Tuổi Thơ', 1),
('Vợ Nhặt', 1),
('Sống Mòn', 1),
('Tắt Đèn', 1),
('Lão Hạc', 1),
('Đất Rừng Phương Nam', 1),
('Dế Mèn Phiêu Lưu Ký', 1),
('Bước Đường Cùng', 1),
('Truyện Kiều', 1),
('Chí Phèo', 1);

-- Chèn dữ liệu vào bảng BookAuthor
INSERT INTO `BookAuthor` (`authorID`, `bookID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(5, 5),
(6, 6),
(7, 7),
(8, 8),
(9, 9),
(10, 10);

-- Chèn dữ liệu vào bảng BookCategory
INSERT INTO `BookCategory` (`categoryID`, `bookID`) VALUES
(1, 1),
(1, 2),
(1, 3),
(1, 4),
(1, 5),
(1, 6),
(1, 7),
(1, 8),
(1, 9),
(1, 10);

-- Chèn dữ liệu vào bảng VersionOfBook
INSERT INTO `VersionOfBook` (`ISBN`, `bookID`, `img`, `publisherID`, `edition`, `location`, `price`, `quantity`, `available`) VALUES
('978-604-2-10764-5', 1, 'src/main/java/asset/img/30giaykhoahoc.webp', 2, '1st', 'Hà Nội', 100000, 50, 50),
('978-604-2-23577-2', 2, 'src/main/java/asset/img/baovenguoitocao.jpg', 1, '2nd', 'TP.HCM', 120000, 40, 35),
('978-604-3-40077-3', 3, 'src/main/java/asset/img/dacnhantam.jpg', 3, '3rd', 'Đà Nẵng', 150000, 30, 25),
('978-604-3-40077-4', 4, 'src/main/java/asset/img/giaotiepbatkyai.png', 4, '1st', 'Huế', 110000, 40, 30),
('978-604-3-40077-5', 5, 'src/main/java/asset/img/ketoanviahe.png', 5, '1st', 'Đà Lạt', 130000, 35, 20),
('978-604-3-40077-6', 6, 'src/main/java/asset/img/kheoankheonoi.png', 6, '2nd', 'Hà Nội', 160000, 50, 45),
('978-604-3-40077-7', 7, 'src/main/java/asset/img/khoahoc.webp', 7, '1st', 'TP.HCM', 175000, 25, 15),
('978-604-3-40077-8', 8, 'src/main/java/asset/img/khoinghiep.jpg', 8, '2nd', 'Đà Nẵng', 200000, 20, 15),
('978-604-3-40077-9', 9, 'src/main/java/asset/img/nangtamanhhuong.png', 9, '1st', 'Huế', 195000, 45, 35),
('978-604-3-40078-0', 10, 'src/main/java/asset/img/sachtrangcntt.png', 10, '3rd', 'Đà Lạt', 220000, 30, 25);

-- Chèn dữ liệu vào bảng Person
INSERT INTO `Person` (`id`, `name`, `tel`, `address`, `schoolYear`, `isActive`) VALUES
('A123456789', 'Nguyễn Văn A', '0909123456', 'Hà Nội', '2023-2024', 1),
('B987654321', 'Trần Thị B', '0912345678', 'TP.HCM', '2022-2023', 1),
('C567890123', 'Lê Văn C', '0923456789', 'Đà Nẵng', '2024-2025', 1),
('D123456780', 'Nguyễn Thị D', '0908765432', 'Huế', '2021-2022', 1),
('E234567891', 'Lê Thị E', '0919876543', 'Đà Lạt', '2020-2021', 1),
('F345678912', 'Phan Văn F', '0921987654', 'Quảng Ninh', '2019-2020', 1),
('G456789123', 'Trần Văn G', '0932198765', 'Nha Trang', '2022-2023', 1),
('H567891234', 'Phạm Thị H', '0943219876', 'Hải Phòng', '2023-2024', 1),
('I678912345', 'Ngô Văn I', '0954321987', 'Cần Thơ', '2024-2025', 1),
('J789123456', 'Lê Văn J', '0965432198', 'Biên Hòa', '2023-2024', 1);

-- Chèn dữ liệu vào bảng Role
INSERT INTO `Role` (`id`, `name`) VALUES
('01', 'Admin'),
('02', 'Thủ thư'),
('03', 'Độc giả');

-- Chèn dữ liệu vào bảng Account
INSERT INTO `Account` (`id`, `password`, `positionID`) VALUES
('A123456789', 'password123', '01'),
('B987654321', 'password456', '02'),
('C567890123', 'password789', '03'),
('D123456780', 'password234', '02'),
('E234567891', 'password345', '01'),
('F345678912', 'password456', '03'),
('G456789123', 'password567', '01'),
('H567891234', 'password678', '02'),
('I678912345', 'password789', '02'),
('J789123456', 'password890', '03');

-- Chèn dữ liệu vào bảng Borrowing
INSERT INTO `Borrowing` (`readerID`, `borrowStaffID`, `dueDate`) VALUES
('C567890123', 'B987654321', '2024-10-15'),
('A123456789', 'B987654321', '2024-10-20'),
('D123456780', 'B987654321', '2024-11-05'),
('E234567891', 'A123456789', '2024-11-10'),
('F345678912', 'A123456789', '2024-11-15'),
('G456789123', 'A123456789', '2024-11-20'),
('H567891234', 'C567890123', '2024-12-01'),
('I678912345', 'C567890123', '2024-12-05'),
('J789123456', 'D123456780', '2024-12-10'),
('A123456789', 'D123456780', '2024-12-15');

-- Chèn dữ liệu vào bảng BorrowDetail
INSERT INTO `BorrowDetail` (`borrowID`, `ISBN`, `quantity`) VALUES
(1, '978-604-2-10764-5', 1),
(2, '978-604-2-23577-2', 1),
(3, '978-604-3-40077-3', 2),
(4, '978-604-3-40077-4', 1),
(5, '978-604-3-40077-5', 2),
(6, '978-604-3-40077-6', 3),
(7, '978-604-3-40077-7', 1),
(8, '978-604-3-40077-8', 2),
(9, '978-604-3-40077-9', 3),
(10, '978-604-3-40078-0', 1);

-- Chèn dữ liệu vào bảng Supplier
INSERT INTO `Supplier` (`name`, `address`, `tel`) VALUES
('Công ty Sách A', 'Hà Nội', '0987654321'),
('Công ty Sách B', 'TP.HCM', '0976543210'),
('Công ty Sách C', 'Đà Nẵng', '0965432109'),
('Công ty Sách D', 'Huế', '0954321098'),
('Công ty Sách E', 'Quảng Ninh', '0943210987'),
('Công ty Sách F', 'Nha Trang', '0932109876'),
('Công ty Sách G', 'Hải Phòng', '0921098765'),
('Công ty Sách H', 'Cần Thơ', '0910987654'),
('Công ty Sách I', 'Biên Hòa', '0909876543'),
('Công ty Sách J', 'Đà Lạt', '0898765432');

-- Chèn dữ liệu vào bảng Importing
INSERT INTO `Importing` (`supplierID`, `staffID`, `fee`) VALUES
(1, 'B987654321', 500000),
(2, 'B987654321', 300000),
(3, 'A123456789', 700000),
(4, 'A123456789', 450000),
(5, 'C567890123', 600000),
(6, 'C567890123', 350000),
(7, 'D123456780', 800000),
(8, 'D123456780', 400000),
(9, 'E234567891', 750000),
(10, 'E234567891', 550000);

-- Chèn dữ liệu vào bảng ImportDetail
-- INSERT INTO `ImportDetail` (`importID`, `ISBN`, `quantity`) VALUES
-- (1, '978-604-2-10764-5', 50),
-- (2, '978-604-3-40077-3', 30),
-- (3, '978-604-3-40077-4', 40),
-- (4, '978-604-3-40077-5', 60),
-- (5, '978-604-3-40077-6', 20),
-- (6, '978-604-3-40077-7', 70),
-- (7, '978-604-3-40077-8', 80),
-- (8, '978-604-3-40077-9', 90),
-- (9, '978-604-3-40078-0', 25),
-- (10, '978-604-2-23577-2', 35);