-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 25, 2023 at 09:03 AM
-- Server version: 10.4.21-MariaDB
-- PHP Version: 8.0.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `helpdesk`
--

-- --------------------------------------------------------

--
-- Table structure for table `request_form`
--

CREATE TABLE IF NOT EXISTS `request_form` (
  `id` int(11) NOT NULL,
  `request_type` varchar(16) NOT NULL,
  `email` varchar(255) NOT NULL,
  `title` varchar(128) NOT NULL,
  `description` varchar(512) NOT NULL,
  `is_picture_included` tinyint(1) NOT NULL,
  `area_of_interest` varchar(50) NOT NULL,
  `user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `request_form`
--

INSERT INTO `request_form` (`id`, `request_type`, `email`, `title`, `description`, `is_picture_included`, `area_of_interest`, `user_id`) VALUES
(1, 'Bug', 'fslakfjkl@klfnsalkf', 'fsaklnf', 'kjaslkfhlsafl', 0, 'HR, Logistics, ', 3),
(2, 'Bug', 'fslakfjkl@klfnsalkf', 'treciadienis', 'kjaslkfhlsafl', 0, 'HR, Logistics, ', 4),
(3, 'Feature', 'lmflsam@kljsa', 'ketvirtadienis', 'klnfsklankl', 1, 'Marketing, HR, ', 4),
(4, 'Bug', 'domas@domas', 'domas', 'daomsd', 0, 'Logistics, ', 4),
(5, 'Bug', 'domas@domas.domas', 'domas', 'domas', 0, 'HR, ', 4),
(6, 'Bug', 'domas@domas', 'domas', 'dadsadadsad', 0, 'HR, ', 5),
(7, 'Bug', 'domas@domas', 'domas', 'sadassdadsad', 0, 'HR, ', 5),
(8, 'Bug', 'domas@domas', 'domas', 'domasdomsaofdskjfdow', 0, 'IT, ', 5),
(9, 'Bug', 'domas1@domas1.domas', 'doams1', 'domas1', 0, 'Marketing, ', 6),
(10, 'Bug', 'domas1@domas1.domas', 'domas2', 'domas1', 0, 'Marketing, ', 6);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `user_id` int(11) NOT NULL,
  `is_admin` tinyint(1) NOT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `date` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `is_admin`, `username`, `password`, `email`, `date`) VALUES
(2, 0, 'rob123', '$2a$12$8TgPiee0COttGRM6GxJdf.nSdI4J2r8jPicVyscUjQY.EuhlVp./G', 'robtob@gmail.com', '2023-04-04 08:31:23'),
(4, 0, 'rob12345', '$2a$12$rEfK0DwYmIlcyFXHyig1iOfR0uo0w9wx91k3Cui3q5bU4DBZSYtOm', 'robtob11@gmail.com', '2023-04-04 09:14:33'),
(5, 1, 'domas', '$2a$12$YzvIJueafmWag2kUBhY4vebjNOLCsKaak/vcZ4KbRPnQq75P.u8DG', 'domas@domas.domas', '2023-04-25 06:58:24'),
(6, 0, 'domas1', '$2a$12$ybAhq5v05wehvOSH8UQIzOZAVJ/67joEIJ794acQXQU9Dv9I99aha', 'domsa1@domas1.domas', '2023-04-25 07:00:32');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `request_form`
--
ALTER TABLE `request_form`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `request_form`
--
ALTER TABLE `request_form`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
