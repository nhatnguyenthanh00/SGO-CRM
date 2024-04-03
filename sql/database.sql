DROP SCHEMA IF EXISTS `sgocrm_db` ;

CREATE SCHEMA IF NOT EXISTS `sgocrm_db`;

USE `sgocrm_db`;

CREATE TABLE `users` (
                         `user_Id` varchar(255) PRIMARY KEY,
                         `fullname` nvarchar(255),
                         `username` varchar(255),
                         `password` varchar(255),
                         `phonenumber` varchar(255),
                         `token` varchar(255)
);

CREATE TABLE `roles` (
                         `role_Id` bigint PRIMARY KEY,
                         `role_name` varchar(255),
                         `role_description` nvarchar(255)
);

CREATE TABLE `fbAdAccount` (
                               `adAccount_Id` bigint PRIMARY KEY,
                               `name` nvarchar(255),
                               `status` varchar(255),
                               `fbAccount_Id` varchar(255),
                               `page_Id` bigint,
                               `monthly_budget` decimal
);

CREATE TABLE `fbAdPage` (
                            `page_Id` bigint PRIMARY KEY,
                            `page_name` nvarchar(255),
                            `fbPage_Id` varchar(255),
                            `user_Id` varchar(255),
                            `campaign_Id` bigint
);

CREATE TABLE `campaigns` (
                             `campaign_Id` bigint PRIMARY KEY,
                             `campaign_name` nvarchar(255),
                             `startDate` date,
                             `endDate` date
);

CREATE TABLE `UserCampaign` (
                                `user_Id` varchar(255),
                                `campaign_Id` bigint,
                                PRIMARY KEY (`user_Id`, `campaign_Id`)
);

CREATE TABLE `UserRole` (
                            `user_Id` varchar(255),
                            `role_Id` bigint,
                            PRIMARY KEY (`user_Id`, `role_Id`)
);

ALTER TABLE `fbAdPage` ADD FOREIGN KEY (`user_Id`) REFERENCES `users` (`user_Id`);

ALTER TABLE `UserCampaign` ADD FOREIGN KEY (`user_Id`) REFERENCES `users` (`user_Id`);

ALTER TABLE `UserCampaign` ADD FOREIGN KEY (`campaign_Id`) REFERENCES `campaigns` (`campaign_Id`);

ALTER TABLE `fbAdPage` ADD FOREIGN KEY (`campaign_Id`) REFERENCES `campaigns` (`campaign_Id`);

ALTER TABLE `UserRole` ADD FOREIGN KEY (`user_Id`) REFERENCES `users` (`user_Id`);

ALTER TABLE `UserRole` ADD FOREIGN KEY (`role_Id`) REFERENCES `roles` (`role_Id`);

ALTER TABLE `fbAdAccount` ADD FOREIGN KEY (`page_Id`) REFERENCES `fbAdPage` (`page_Id`);

INSERT INTO `roles` (`role_Id`, `role_description`,`role_name`) VALUES
(1, "Quản Trị Viên (Admin) quản lý và bảo trì hệ thống, đồng thời đảm bảo an ninh. Họ có quyền toàn diện để cập nhật, giải quyết sự cố và quản lý dữ liệu, đảm bảo hệ thống hoạt động ổn định và an toàn.", "Admin"),
(2, "Tập trung vào việc tạo chiến dịch quảng cáo và tăng nhận thức thương hiệu để thu hút khách hàng.", "Sale Employee"),
(3, "Chịu trách nhiệm bán sản phẩm/dịch vụ, tư vấn và duy trì mối quan hệ với khách hàng để đạt mục tiêu doanh số.", "Marketing Employee");

INSERT INTO `users` VALUES
(1,"Admin","admin","123456789","0868524122","token_admin"),
(2,"Nhân viên A","user1","password1","0000000001","token_user1"),
(3,"Nhân viên B","user2","password2","0000000002","token_user2");

INSERT INTO `userrole` VALUES
(1,1),
(2,2),
(3,2);
