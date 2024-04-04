-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Máy chủ: 127.0.0.1
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

CREATE SCHEMA IF NOT EXISTS `sgocrm_db`;

USE `sgocrm_db`;

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
('0001', 'Đức Bảo', '$2a$10$6Aoq/XayrpsMdzuQhYmHs.JJ3WaoCE5j/hlLCCS.EdMXmFQVAj8dq', '0338171052', 'eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJsZWR1Y2JhbyIsImlhdCI6MTcxMjEzNTUzMywiZXhwIjoxNzEyMTM5MTMzfQ.uvlrijQ271v_LTNG8MLXWu57f2qP7cUJib_Jp1U4BvI', 'leducbao'),
('0002', ' Đức Bảo ', '$2a$10$44wQcNwrfQDKMOzgqiNBdO7524IDLKCmkuhBscFr9IQTCel.egRke', '0338171052', NULL, 'leducbao1'),
('0003', 'Nguyễn Thành Nhật', '$2a$10$QDjH7dai13bTfC4rwkBjTenkdbExOsr7s65T.RIOb75Uu8/jrRp3O', '0868524122', NULL, 'nhatthanh00'),
('0004', 'No Name', '$2a$10$/bhu5O2yxbK2KrOlav5Rhu2P5KRYIay.F7zWZ2lW98DM.n/fhcrcC', '0987654321', NULL, 'vodanh123');
-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_campaign`
--

CREATE TABLE `user_campaign` (
  `user_id` varchar(255) NOT NULL,
  `campaign_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_id-gen`
--

CREATE TABLE `user_id-gen` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_id-gen`
--

INSERT INTO `user_id-gen` (`next_val`) VALUES
(3);

-- --------------------------------------------------------

--
-- Cấu trúc bảng cho bảng `user_role`
--

CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Đang đổ dữ liệu cho bảng `user_role`
--

INSERT INTO `user_role` (`user_id`, `role_id`) VALUES
('0001', 1),
('0002', 1),
('0003',1),
('0003',2),
('0004',2);

--
-- Chỉ mục cho các bảng đã đổ
--

--
-- Chỉ mục cho bảng `campaigns`
--
ALTER TABLE `campaigns`
  ADD PRIMARY KEY (`campaign_id`);

--
-- Chỉ mục cho bảng `fb_ad_account`
--
ALTER TABLE `fb_ad_account`
  ADD PRIMARY KEY (`ad_account_id`),
  ADD KEY `FKepywkplw2lxrfta0q8f3w89bd` (`page_id`);

--
-- Chỉ mục cho bảng `fb_ad_page`
--
ALTER TABLE `fb_ad_page`
  ADD PRIMARY KEY (`page_id`),
  ADD KEY `FKk2kqkic3omp7iq0gka06s3b4t` (`campaign_id`),
  ADD KEY `FKc4w72w9x264p5b1ul9r40o9yr` (`user_id`);

--
-- Chỉ mục cho bảng `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`role_id`);

--
-- Chỉ mục cho bảng `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`);

--
-- Chỉ mục cho bảng `user_campaign`
--
ALTER TABLE `user_campaign`
  ADD PRIMARY KEY (`user_id`,`campaign_id`),
  ADD KEY `FKbu0y6e2r7humst2jpigb12jqw` (`campaign_id`);

--
-- Chỉ mục cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD PRIMARY KEY (`user_id`,`role_id`),
  ADD KEY `FKt7e7djp752sqn6w22i6ocqy6q` (`role_id`);

--
-- AUTO_INCREMENT cho các bảng đã đổ
--

--
-- AUTO_INCREMENT cho bảng `campaigns`
--
ALTER TABLE `campaigns`
  MODIFY `campaign_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `fb_ad_account`
--
ALTER TABLE `fb_ad_account`
  MODIFY `ad_account_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `fb_ad_page`
--
ALTER TABLE `fb_ad_page`
  MODIFY `page_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT cho bảng `roles`
--
ALTER TABLE `roles`
  MODIFY `role_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- Các ràng buộc cho các bảng đã đổ
--

--
-- Các ràng buộc cho bảng `fb_ad_account`
--
ALTER TABLE `fb_ad_account`
  ADD CONSTRAINT `FKepywkplw2lxrfta0q8f3w89bd` FOREIGN KEY (`page_id`) REFERENCES `fb_ad_page` (`page_id`);

--
-- Các ràng buộc cho bảng `fb_ad_page`
--
ALTER TABLE `fb_ad_page`
  ADD CONSTRAINT `FKc4w72w9x264p5b1ul9r40o9yr` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKk2kqkic3omp7iq0gka06s3b4t` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`campaign_id`);

--
-- Các ràng buộc cho bảng `user_campaign`
--
ALTER TABLE `user_campaign`
  ADD CONSTRAINT `FKbu0y6e2r7humst2jpigb12jqw` FOREIGN KEY (`campaign_id`) REFERENCES `campaigns` (`campaign_id`),
  ADD CONSTRAINT `FKf2vpgmjiog9o1b5xoniu38uuk` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

--
-- Các ràng buộc cho bảng `user_role`
--
ALTER TABLE `user_role`
  ADD CONSTRAINT `FKj345gk1bovqvfame88rcx7yyx` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`),
  ADD CONSTRAINT `FKt7e7djp752sqn6w22i6ocqy6q` FOREIGN KEY (`role_id`) REFERENCES `roles` (`role_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
