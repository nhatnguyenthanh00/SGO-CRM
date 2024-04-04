-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
<<<<<<< HEAD
-- Thời gian đã tạo: Th4 04, 2024 lúc 08:41 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4
=======
-- Thời gian đã tạo: Th4 03, 2024 lúc 11:37 AM
-- Phiên bản máy phục vụ: 10.4.28-MariaDB
-- Phiên bản PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sgocrm_db`
--

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `campaigns`
--

DROP SCHEMA IF EXISTS `sgocrm_db` ;
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


<<<<<<< HEAD
/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Cơ sở dữ liệu: `sgocrm_db`
--

create database sgocrm_db;
use sgocrm_db;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `campaigns`
--

CREATE TABLE `campaigns` (
                             `campaign_id` bigint(20) NOT NULL,
                             `campaign_name` varchar(255) DEFAULT NULL,
                             `end_date` datetime(6) DEFAULT NULL,
                             `start_date` datetime(6) DEFAULT NULL,
                             `status` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fb_ad_account`
--

CREATE TABLE `fb_ad_account` (
                                 `ad_account_id` bigint(20) NOT NULL,
                                 `fb_account_id` varchar(255) DEFAULT NULL,
                                 `monthly_budget` decimal(38,2) DEFAULT NULL,
                                 `name` varchar(255) DEFAULT NULL,
                                 `status` varchar(255) DEFAULT NULL,
                                 `page_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fb_ad_page`
--

CREATE TABLE `fb_ad_page` (
                              `page_id` bigint(20) NOT NULL,
                              `fb_page_id` varchar(255) DEFAULT NULL,
                              `page_name` varchar(255) DEFAULT NULL,
                              `campaign_id` bigint(20) DEFAULT NULL,
                              `user_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
=======
CREATE TABLE `campaigns` (
  `campaign_id` bigint(20) NOT NULL,
  `campaign_name` varchar(255) DEFAULT NULL,
  `end_date` datetime(6) DEFAULT NULL,
  `start_date` datetime(6) DEFAULT NULL,
  `status` INT DEFAULT (-1)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fb_ad_account`
--

CREATE TABLE `fb_ad_account` (
  `ad_account_id` bigint(20) NOT NULL,
  `fb_account_id` varchar(255) DEFAULT NULL,
  `monthly_budget` decimal(38,2) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `page_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `fb_ad_page`
--

CREATE TABLE `fb_ad_page` (
  `page_id` bigint(20) NOT NULL,
  `fb_page_id` varchar(255) DEFAULT NULL,
  `page_name` varchar(255) DEFAULT NULL,
  `campaign_id` bigint(20) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
  `role_id` bigint(20) NOT NULL,
  `role_description` text DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`role_id`, `role_description`, `role_name`) VALUES
(1, 'Quản Trị Viên (Admin) quản lý và bảo trì hệ thống, đồng thời đảm bảo an ninh. Họ có quyền toàn diện để cập nhật, giải quyết sự cố và quản lý dữ liệu, đảm bảo hệ thống hoạt động ổn định và an toàn.', 'Admin'),
(2, 'Tập trung vào việc tạo chiến dịch quảng cáo và tăng nhận thức thương hiệu để thu hút khách hàng.', 'Sale Employee'),
(3, 'Chịu trách nhiệm bán sản phẩm/dịch vụ, tư vấn và duy trì mối quan hệ với khách hàng để đạt mục tiêu doanh số.', 'Marketing Employee');
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

-- --------------------------------------------------------

--
<<<<<<< HEAD
-- Cấu trúc bảng cho bảng `roles`
--

CREATE TABLE `roles` (
                         `role_id` bigint(20) NOT NULL,
                         `role_description` text DEFAULT NULL,
                         `role_name` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `roles`
--

INSERT INTO `roles` (`role_id`, `role_description`, `role_name`) VALUES
                                                                     (1, 'Quản Trị Viên (Admin) quản lý và bảo trì hệ thống, đồng thời đảm bảo an ninh. Họ có quyền toàn diện để cập nhật, giải quyết sự cố và quản lý dữ liệu, đảm bảo hệ thống hoạt động ổn định và an toàn.', 'Admin'),
                                                                     (2, 'Tập trung vào việc tạo chiến dịch quảng cáo và tăng nhận thức thương hiệu để thu hút khách hàng.', 'Sale Employee'),
                                                                     (3, 'Chịu trách nhiệm bán sản phẩm/dịch vụ, tư vấn và duy trì mối quan hệ với khách hàng để đạt mục tiêu doanh số.', 'Marketing Employee');

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
                         `user_id` varchar(255) NOT NULL,
                         `fullname` varchar(255) DEFAULT NULL,
                         `password` varchar(255) DEFAULT NULL,
                         `phonenumber` varchar(255) DEFAULT NULL,
                         `token` varchar(255) DEFAULT NULL,
                         `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `fullname`, `password`, `phonenumber`, `token`, `username`) VALUES
                                                                                                ('0001', 'Đức Bảo', '$2a$10$6Aoq/XayrpsMdzuQhYmHs.JJ3WaoCE5j/hlLCCS.EdMXmFQVAj8dq', '0338171059', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZWR1Y2JhbyIsImlhdCI6MTcxMjEzNTUzMywiZXhwIjoxNzEyMTM5MTMzfQ.uvlrijQ271v_LTNG8MLXWu57f2qP7cUJib_Jp1U4BvI', 'leducbao00'),
                                                                                                ('0002', ' Đức Bảo ', '$2a$10$44wQcNwrfQDKMOzgqiNBdO7524IDLKCmkuhBscFr9IQTCel.egRke', '0338171053', NULL, 'leducbao1'),
                                                                                                ('0003', ' Đức Bảo ', '$2a$10$QDjH7dai13bTfC4rwkBjTenkdbExOsr7s65T.RIOb75Uu8/jrRp3O', '0338171054', NULL, 'leducbao2'),
                                                                                                ('0004', ' Đức Bảo ', '$2a$10$/bhu5O2yxbK2KrOlav5Rhu2P5KRYIay.F7zWZ2lW98DM.n/fhcrcC', '0338171055', NULL, 'leducbao3'),
                                                                                                ('0005', 'Lý ĐỨC ', '$2a$10$8NZuaXZ1Ps2hujt47AluqexorQEUGvqBXo7OlK.DeG./dMrOSkc2i', '0041338171', NULL, 'leducbaos1'),
                                                                                                ('0006', 'Lý thông', '$2a$10$M3W756eVofW/W1jMKZr/2OaBQQmng7rHNC01N4FcOd44Ze1oBl.OC', '0341338171', NULL, 'leducbaos2'),
                                                                                                ('0007', 'Lý thông', '$2a$10$obHy1P58HhWFX7O/NhTNB.qmHGqvzPuq/RhBWh97KBrSxjBYH4JvK', '0012345678', NULL, 'leduc123891'),
                                                                                                ('0008', 'Sơn Tùng', '$2a$10$4pOc0RvJ/zrhopt4jzT6V.b29lVkctF3u7BoO0DdNZtaxf15qp2Pi', '0978123456', NULL, 'leduc123189'),
                                                                                                ('0009', 'Sơn Tùng', '$2a$10$1VrOWtce/N3DfrYZTvx3nOAwFfXbLsH5Wvi1DhCLAQT54mMC4VYNy', '0978123457', NULL, 'leduc123188'),
                                                                                                ('0010', 'Sơn Tùng', '$2a$10$qIZHEDhJMQbEt5ZXxOhinuynqEd2leMSJvpQLAm3floWMhVunc2HC', '0978123458', NULL, 'leduc12318a'),
                                                                                                ('0011', 'Sơn Tùng', '$2a$10$n.bkpE1POXJWsLz9GT.Wb.17LhPXTCYuSWEbKjqBKOkBBRo/siYZG', '0978123459', NULL, 'Sontung123'),
                                                                                                ('0012', 'Sơn Tùng', '$2a$10$wwe4PFmrKcmhJPxOj.9KV.AljfKpOfM2AG7qDw2TplMuk8TJy5NP.', '0978123450', NULL, 'Sontung1231'),
                                                                                                ('0013', 'Sơn Tùng', '$2a$10$bbSbIpiA5mFsd.tZcSknX.lPrFjNywYC8AT0pkNz/veCRGkMdXe4y', '0978123123', NULL, 'Sontung12'),
                                                                                                ('0014', 'Nguyễn Văn A', '$2a$10$7p1245lqYssS.VO7Yx8N4.naiMz/mAsQu4vIxLIIdUaQFvnD3SSRG', '0978123124', NULL, 'nguyenvana'),
                                                                                                ('0015', 'Nguyễn Văn A', '$2a$10$jhIjFMGi6O5Wpz.0BKUNiOo8SmrnLVKA0h7GFFak/oL9Ozqb.TMPa', '0978123125', NULL, 'nguyenvana1991'),
                                                                                                ('0016', 'Nguyễn Văn A', '$2a$10$pksXTUuS.RSntpNxa/yzvOg8V2e6CMBpO6MYfCR/7HQhDuzdeWJqm', '0978123127', NULL, 'eedecadeslatery'),
                                                                                                ('0017', 'Nguyễn Thành Đạt', '$2a$10$UMFqLx2JHJs52MEpGL/ZUeRNBHB1gAg2FtnDuORSfDAMoFz4cM6f6', '0978123128', NULL, 'thanhdat999'),
                                                                                                ('0018', 'Đan Trường', '$2a$10$V9tZDErNDgP/Ov9rEGX23OY9V13lkvNOdGsd130rLVJUQ0vLOFdp6', '0978123129', NULL, 'Dantruong999');

=======
-- Cấu trúc bảng cho bảng `users`
--

CREATE TABLE `users` (
  `user_id` varchar(255) NOT NULL,
  `fullname` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `users`
--

INSERT INTO `users` (`user_id`, `fullname`, `password`, `phonenumber`, `token`, `username`) VALUES
('0001', 'Đức Bảo', '$2a$10$6Aoq/XayrpsMdzuQhYmHs.JJ3WaoCE5j/hlLCCS.EdMXmFQVAj8dq', '0338171052', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZWR1Y2JhbyIsImlhdCI6MTcxMjEzNTUzMywiZXhwIjoxNzEyMTM5MTMzfQ.uvlrijQ271v_LTNG8MLXWu57f2qP7cUJib_Jp1U4BvI', 'leducbao'),
('0002', ' Đức Bảo ', '$2a$10$44wQcNwrfQDKMOzgqiNBdO7524IDLKCmkuhBscFr9IQTCel.egRke', '0338171052', NULL, 'leducbao1'),
('0003', 'Nguyễn Thành Nhật', '$2a$10$QDjH7dai13bTfC4rwkBjTenkdbExOsr7s65T.RIOb75Uu8/jrRp3O', '0868524122', NULL, 'nhatthanh00'),
('0004', 'No Name', '$2a$10$/bhu5O2yxbK2KrOlav5Rhu2P5KRYIay.F7zWZ2lW98DM.n/fhcrcC', '0987654321', NULL, 'vodanh123');
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_campaign`
--

CREATE TABLE `user_campaign` (
<<<<<<< HEAD
                                 `user_id` varchar(255) NOT NULL,
                                 `campaign_id` bigint(20) NOT NULL
=======
  `user_id` varchar(255) NOT NULL,
  `campaign_id` bigint(20) NOT NULL
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_id-gen`
--

CREATE TABLE `user_id-gen` (
<<<<<<< HEAD
                               `next_val` bigint(20) DEFAULT NULL
=======
  `next_val` bigint(20) DEFAULT NULL
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_id-gen`
--

INSERT INTO `user_id-gen` (`next_val`) VALUES
<<<<<<< HEAD
    (19);
=======
(3);
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
<<<<<<< HEAD
                             `user_id` varchar(255) NOT NULL,
                             `role_id` bigint(20) NOT NULL
=======
  `user_id` varchar(255) NOT NULL,
  `role_id` bigint(20) NOT NULL
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
<<<<<<< HEAD
                                                   ('0001', 1),
                                                   ('0001', 3),
                                                   ('0002', 1),
                                                   ('0003', 1),
                                                   ('0006', 2),
                                                   ('0007', 2),
                                                   ('0008', 1),
                                                   ('0008', 2),
                                                   ('0009', 2),
                                                   ('0010', 2),
                                                   ('0011', 2),
                                                   ('0012', 2),
                                                   ('0013', 1),
                                                   ('0013', 2),
                                                   ('0013', 3),
                                                   ('0014', 1),
                                                   ('0014', 2),
                                                   ('0014', 3),
                                                   ('0015', 1),
                                                   ('0015', 2),
                                                   ('0015', 3),
                                                   ('0016', 2),
                                                   ('0017', 2),
                                                   ('0018', 2);
=======
('0001', 1),
('0002', 1),
('0003',1),
('0003',2),
('0004',2);
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `campaigns`
--
ALTER TABLE `campaigns`
<<<<<<< HEAD
    ADD PRIMARY KEY (`campaign_id`);
=======
  ADD PRIMARY KEY (`campaign_id`);
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- Chỉ mục cho bảng `fb_ad_account`
--
ALTER TABLE `fb_ad_account`
<<<<<<< HEAD
    ADD PRIMARY KEY (`ad_account_id`),
=======
  ADD PRIMARY KEY (`ad_account_id`),
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
  ADD KEY `FKepywkplw2lxrfta0q8f3w89bd` (`page_id`);

--
-- Chỉ mục cho bảng `fb_ad_page`
--
ALTER TABLE `fb_ad_page`
<<<<<<< HEAD
    ADD PRIMARY KEY (`page_id`),
=======
  ADD PRIMARY KEY (`page_id`),
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
  ADD KEY `FKk2kqkic3omp7iq0gka06s3b4t` (`campaign_id`),
  ADD KEY `FKc4w72w9x264p5b1ul9r40o9yr` (`user_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
<<<<<<< HEAD
    ADD PRIMARY KEY (`role_id`);
=======
  ADD PRIMARY KEY (`role_id`);
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
<<<<<<< HEAD
    ADD PRIMARY KEY (`user_id`);
=======
  ADD PRIMARY KEY (`user_id`);
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- Chỉ mục cho bảng `user_campaign`
--
ALTER TABLE `user_campaign`
<<<<<<< HEAD
    ADD PRIMARY KEY (`user_id`,`campaign_id`),
=======
  ADD PRIMARY KEY (`user_id`,`campaign_id`),
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
  ADD KEY `FKbu0y6e2r7humst2jpigb12jqw` (`campaign_id`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
<<<<<<< HEAD
    ADD PRIMARY KEY (`user_id`,`role_id`),
=======
  ADD PRIMARY KEY (`user_id`,`role_id`),
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
  ADD KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `campaigns`
--
ALTER TABLE `campaigns`
<<<<<<< HEAD
    MODIFY `campaign_id` bigint(20) NOT NULL AUTO_INCREMENT;
=======
  MODIFY `campaign_id` bigint(20) NOT NULL AUTO_INCREMENT;
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- AUTO_INCREMENT cho bảng `fb_ad_account`
--
ALTER TABLE `fb_ad_account`
<<<<<<< HEAD
    MODIFY `ad_account_id` bigint(20) NOT NULL AUTO_INCREMENT;
=======
  MODIFY `ad_account_id` bigint(20) NOT NULL AUTO_INCREMENT;
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- AUTO_INCREMENT cho bảng `fb_ad_page`
--
ALTER TABLE `fb_ad_page`
<<<<<<< HEAD
    MODIFY `page_id` bigint(20) NOT NULL AUTO_INCREMENT;
=======
  MODIFY `page_id` bigint(20) NOT NULL AUTO_INCREMENT;
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
<<<<<<< HEAD
    MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
=======
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `fb_ad_account`
--
ALTER TABLE `fb_ad_account`
<<<<<<< HEAD
    ADD CONSTRAINT `FKepywkplw2lxrfta0q8f3w89bd` FOREIGN KEY (`page_id`) REFERENCES `fb_ad_page` (`page_id`);
=======
  ADD CONSTRAINT `FKepywkplw2lxrfta0q8f3w89bd` FOREIGN KEY (`page_id`) REFERENCES `fb_ad_page` (`page_id`);
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e

--
-- Các ràng buộc cho bảng `fb_ad_page`
--
ALTER TABLE `fb_ad_page`
<<<<<<< HEAD
    ADD CONSTRAINT `FKc4w72w9x264p5b1ul9r40o9yr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
=======
  ADD CONSTRAINT `FKc4w72w9x264p5b1ul9r40o9yr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
  ADD CONSTRAINT `FKk2kqkic3omp7iq0gka06s3b4t` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`campaign_id`);

--
-- Các ràng buộc cho bảng `user_campaign`
--
ALTER TABLE `user_campaign`
<<<<<<< HEAD
    ADD CONSTRAINT `FKbu0y6e2r7humst2jpigb12jqw` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`campaign_id`),
=======
  ADD CONSTRAINT `FKbu0y6e2r7humst2jpigb12jqw` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`campaign_id`),
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
  ADD CONSTRAINT `FKf2vpgmjiog9o1b5xoniu38uuk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
<<<<<<< HEAD
    ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
=======
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
>>>>>>> a7d153c29dd88085befd0862452ac3c76d10db0e
  ADD CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
