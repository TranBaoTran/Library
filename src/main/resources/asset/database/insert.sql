-- Chèn dữ liệu vào bảng Author
INSERT INTO `Author` (`name`, `year`, `isActive`) VALUES
('Pamela Hutchinson', 1953, 1),
('Dale Carnegie', 1888, 1),
('Jo Condrill, Bennie Bough', null, 1),
('Darrell Mullis & Judith Orloff', 1910, 1),
('Trác Nhã', 1929, 1),
('John C. Maxwell', 1947, 1),
('Bộ Thông tin và Truyền thông', null, 1),
('Kevin Mitnick', 1963, 0),
('Clifford Stoll', 1950, 0),
('Tony Buổi Sáng', 1978, 0),
('Dương Ngọc Duy', 1990, 0);

-- Chèn dữ liệu vào bảng Category
INSERT INTO `Category` (`name`, `isActive`) VALUES
('Điện ảnh, kỹ thuật làm phim', 1),
('Tâm lý - Kỹ năng sống', 1),
('Kỹ năng giao tiếp', 1),
('Khởi Nghiệp Kinh Doanh Thực Chiến', 1),
('Phát triển bản thân', 1),
('Tài liệu chuyên khảo về công nghệ thông tin và truyền thông', 1),
('Công nghệ thông tin, An ninh mạng', 1),
('Văn học thiếu nhi', 0),
('Phát triển bản thân', 0);


-- Chèn dữ liệu vào bảng Publisher
INSERT INTO `Publisher` (`name`, `isActive`) VALUES
('Nhà xuất bản Kim Đồng', 1),
('Nhà xuất bản Tổng hợp TPHCM', 1),
('Nhà xuất bản Công Thương', 1),
('Nhà xuất bản Thế Giới', 1),
('Nhà xuất bản Văn học', 1),
('Nhà xuất bản Thông tin và Truyền thông', 1),
('Nhà xuất bản Hồng Đức', 1),
('Nhà xuất bản Trẻ', 1),
('Nhà xuất bản Thanh Niên', 0),
('Nhà xuất bản Phụ Nữ', 0);

-- Chèn dữ liệu vào bảng Book
INSERT INTO `Book` (`name`, `isActive`) VALUES
('30 Giây Điện Ảnh', 1),
('Đắc Nhân Tâm', 1),
('Giao Tiếp Với Bất Kỳ Ai', 1),
('Kế Toán Vỉa Hè', 1),
('Khéo Ăn Nói Sẽ Có Được Thiên Hạ', 1),
('Nâng Tầm Ảnh Hưởng', 1),
('Sách Trắng Công Nghệ Thông Tin Và Truyền Thông', 1),
('Nghệ Thuật Ẩn Mình', 1),
('Gián Điệp Mạng', 1),
('Cafe Cùng Tony', 1),
('Đời Đổi Thay Khi Chúng Ta Thay Đổi', 1);

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
(10, 10),
(11, 11);

-- Chèn dữ liệu vào bảng BookCategory
INSERT INTO `BookCategory` (`categoryID`, `bookID`) VALUES
(1, 1),
(2, 2),
(3, 3),
(4, 4),
(3, 5),
(5, 6),
(6, 7),
(7, 8),
(7, 9),
(5, 10),
(5, 11);

-- Chèn dữ liệu vào bảng VersionOfBook
INSERT INTO `VersionOfBook` (`ISBN`, `bookID`, `img`, `publisherID`, `edition`, `location`, `price`, `quantity`, `available`) VALUES
('978-604-2-28251-2', 1, './src/main/resources/asset/img/30giaydienanh.webp', 1, '1st', 'Hà Nội', 140000, 50, 50),
('978-604-3-92743-6', 2, './src/main/resources/asset/img/dacnhantam.jpg', 2, '3rd', 'Đà Nẵng', 80000, 30, 25),
('978-604-4-02274-1', 3, './src/main/resources/asset/img/giaotiepbatkyai.png', 3, '1st', 'Huế', 50000, 40, 30),
('978-604-7-78761-6', 4, './src/main/resources/asset/img/ketoanviahe.png', 4, '1st', 'Đà Lạt', 130000, 35, 20),
('978-604-3-62922-4', 5, './src/main/resources/asset/img/kheoankheonoi.png', 5, '2nd', 'Hà Nội', 138000, 50, 45),
('978-604-553-860-4', 6, './src/main/resources/asset/img/nangtamanhhuong.png', 3, '1st', 'TP.HCM', 88000, 25, 15),
('978-604-952-579-5', 7, './src/main/resources/asset/img/sachtrangcntt.png', 6, '3rd', 'Đà Lạt', 220000, 30, 25),
('978-604-87-0693-1', 8, './src/main/resources/asset/img/nghethuatnminh.jpg', 7, '2nd', 'Đà Nẵng', 130000, 20, 15),
('978-604-1-08637-4', 9, './src/main/resources/asset/img/giandiepmang.jpg', 8, '1st', 'Huế', 140000, 45, 35),
('978-604-0-15014-8', 10, './src/main/resources/asset/img/cafecungtony.jpg', 8, '1st', 'Huế', 80000, 40, 32),
('978-604-56-0506-3', 11, './src/main/resources/asset/img/doidoithaykhichungtathaydoi.jpg', 2, '1st', 'Huế', 95000, 20, 16);

-- Chèn dữ liệu vào bảng Person
INSERT INTO `Person` (`id`, `name`, `tel`, `address`, `schoolYear`, `isActive`) VALUES
('SV00000001', 'Nguyễn Văn A', '0909123456', 'Hà Nội', '2023-2024', 1),
('SV00000002', 'Trần Thị B', '0912345678', 'TP.HCM', '2022-2023', 1),
('SV00000003', 'Lê Văn C', '0923456789', 'Đà Nẵng', '2024-2025', 1),
('SV00000004', 'Nguyễn Thị D', '0908765432', 'Huế', '2021-2022', 1),
('SV00000005', 'Lê Thị E', '0919876543', 'Đà Lạt', '2020-2021', 1),
('SV00000006', 'Phan Văn F', '0921987654', 'Quảng Ninh', '2019-2020', 1),
('SV00000007', 'Trần Văn G', '0932198765', 'Nha Trang', '2022-2023', 1),
('GV00000001', 'Phạm Thị H', '0943219876', 'Hải Phòng', null, 1),
('GV00000002', 'Ngô Văn I', '0954321987', 'Cần Thơ', null, 1),
('GV00000003', 'Lê Văn J', '0965432198', 'Biên Hòa', null, 1),
('TT00000001', 'Phan Thị Anh Thư', '0901236548', 'Tiền Giang', null, 1),
('AD00000001', 'Trần Bảo Trân', '0901244444', 'TP HCM', null, 1),
('TK00000001', 'Trần Đức An', '0901228399', 'TP HCM', null, 1),
('QL00000001', 'Trần Thị Thủy', '0901446548', 'Quảng Trị', null, 1),
('QL00000002', 'Nguyễn Thanh Tiến', '0922236548', 'Bình Dương', null, 1);

-- Chèn dữ liệu vào bảng Role
INSERT INTO `Role` (`id`, `name`) VALUES
('AD', 'Admin'),
('TT', 'Thủ thư'),
('TK', 'Thủ kho'),
('QL', 'Quản lý'),
('GV', 'Giảng viên'),
('SV', 'Sinh viên');

-- Chèn dữ liệu vào bảng Account
INSERT INTO `Account` (`id`, `password`, `positionID`) VALUES
('SV00000001', '123456', 'SV'),
('SV00000002', '123456', 'SV'),
('SV00000003', '123456', 'SV'),
('SV00000004', '123456', 'SV'),
('SV00000005', '123456', 'SV'),
('SV00000006', '123456', 'SV'),
('SV00000007', '123456', 'SV'),
('GV00000001', '111111', 'GV'),
('GV00000002', '111111', 'GV'),
('GV00000003', '111111', 'GV'),
('TT00000001', '000000', 'TT'),
('AD00000001', '222222', 'AD'),
('TK00000001', '333333', 'TK'),
('QL00000001', '444444', 'QL'),
('QL00000002', '444444', 'QL');

-- Chèn dữ liệu vào bảng Borrowing
INSERT INTO `Borrowing` (`readerID`, `borrowStaffID`, `returnStaffID`, `borrowDate`, `dueDate`, `returnDate`)
VALUES 
('SV00000001', 'TT00000001', 'TT00000001', '2024-10-01', '2024-10-15', NULL),
('SV00000003', 'TT00000001', NULL, '2024-10-03', '2024-10-17', NULL),
('SV00000006', 'TT00000001', 'TT00000001', '2024-10-05', '2024-10-19', '2024-10-18'),
('SV00000007', 'TT00000001', NULL, '2024-10-06', '2024-10-20', NULL),
('SV00000005', 'TT00000001', 'TT00000001', '2024-10-01', '2024-10-15', NULL),
('GV00000001', 'TT00000001', NULL, '2024-10-03', '2024-10-17', NULL),
('GV00000002', 'TT00000001', NULL, '2024-10-05', '2024-10-19', NULL),
('GV00000003', 'TT00000001', NULL, '2024-10-06', '2024-10-20', NULL),
('SV00000002', 'TT00000001', 'TT00000001', '2024-10-07', '2024-10-21', '2024-10-22');

-- Chèn dữ liệu vào bảng BorrowDetail
INSERT INTO `BorrowDetail` (borrowID, ISBN, quantity, description)
VALUES 
(1, '978-604-2-28251-2', 1, 'Mượn phiên bản 1st tại Hà Nội'),
(2, '978-604-3-92743-6', 2, 'Mượn phiên bản 3rd tại Đà Nẵng'),
(3, '978-604-4-02274-1', 1, 'Mượn phiên bản 1st tại Huế'),
(4, '978-604-7-78761-6', 1, 'Mượn phiên bản 1st tại Đà Lạt'),
(5, '978-604-3-62922-4', 1, 'Mượn phiên bản 2nd tại Hà Nội'),
(1, '978-604-553-860-4', 1, 'Mượn phiên bản 1st tại TP.HCM'),
(3, '978-604-952-579-5', 2, 'Mượn phiên bản 3rd tại Đà Lạt'),
(2, '978-604-87-0693-1', 1, 'Mượn phiên bản 2nd tại Đà Nẵng'),
(5, '978-604-1-08637-4', 1, 'Mượn phiên bản 1st tại Huế'),
(4, '978-604-0-15014-8', 2, 'Mượn phiên bản 1st tại Huế'),
(1, '978-604-56-0506-3', 1, 'Mượn phiên bản 1st tại Huế');


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
('Công ty Sách J', 'Đà Lạt', '0898765432'),
('Công ty Sách T', 'Tiền Giang', '0898568990');


-- Chèn dữ liệu vào bảng Importing
INSERT INTO `Importing` (`supplierID`, `staffID`, `fee`) VALUES
(1, 'SV00000002', 500000),
(2, 'SV00000002', 300000),
(3, 'SV00000001', 700000),
(4, 'SV00000001', 450000),
(5, 'SV00000003', 600000),
(6, 'SV00000003', 350000),
(7, 'SV00000004', 800000),
(8, 'SV00000004', 400000),
(9, 'SV00000005', 750000),
(10, 'SV00000005', 550000),
(11, 'SV00000001', 550000);


-- Chèn dữ liệu vào bảng ImportDetail
INSERT INTO `ImportDetail` (importID, ISBN, quantity)
VALUES 
(1, '978-604-2-28251-2', 50),
(2, '978-604-3-92743-6', 30),
(3, '978-604-4-02274-1', 40),
(4, '978-604-7-78761-6', 35),
(5, '978-604-3-62922-4', 50),
(6, '978-604-553-860-4', 25),
(7, '978-604-952-579-5', 30),
(8, '978-604-87-0693-1', 20),
(9, '978-604-1-08637-4', 45),
(10, '978-604-0-15014-8', 40),
(11, '978-604-56-0506-3', 20);