-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 16, 2022 at 05:24 AM
-- Server version: 10.4.22-MariaDB
-- PHP Version: 8.1.2

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `atm-app-db`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `account_number` varchar(11) NOT NULL,
  `security_pin` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `balance` bigint(20) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `account`
--

INSERT INTO `account` (`account_number`, `security_pin`, `name`, `balance`, `created_at`, `updated_at`) VALUES
('12345', 12, 'Alvin', 200000, '2022-05-15 15:02:00', '2022-05-15 15:02:00'),
('54321', 13, 'zaza', 51000, '2022-05-15 15:02:28', '2022-05-15 15:02:28'),
('7890', 1, 'ais', 100000, '2022-05-16 03:20:22', '2022-05-16 03:20:22');

-- --------------------------------------------------------

--
-- Table structure for table `transaction`
--

CREATE TABLE `transaction` (
  `id` int(11) NOT NULL,
  `account_from` varchar(11) NOT NULL,
  `account_to` varchar(11) DEFAULT NULL,
  `balance` bigint(20) NOT NULL,
  `type` enum('DEPOSIT','WITHDRAW','TRANSFER','') NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `updated_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `transaction`
--

INSERT INTO `transaction` (`id`, `account_from`, `account_to`, `balance`, `type`, `created_at`, `updated_at`) VALUES
(2, '54321', NULL, 5000, 'DEPOSIT', '2022-05-15 15:08:52', '2022-05-15 15:08:52'),
(3, '12345', NULL, 10000, 'WITHDRAW', '2022-05-15 15:08:52', '2022-05-15 15:08:52'),
(4, '12345', '54321', 20000, 'TRANSFER', '2022-05-15 15:08:52', '2022-05-15 15:08:52'),
(6, '54321', NULL, 5000, 'DEPOSIT', '2022-05-15 16:01:22', '2022-05-15 16:01:22'),
(7, '12345', NULL, 10000, 'WITHDRAW', '2022-05-15 16:01:22', '2022-05-15 16:01:22'),
(8, '12345', '54321', 20000, 'TRANSFER', '2022-05-15 16:01:22', '2022-05-15 16:01:22'),
(9, '12345', NULL, 0, 'DEPOSIT', '2022-05-16 02:04:41', '2022-05-16 02:04:41'),
(10, '12345', NULL, 0, 'DEPOSIT', '2022-05-16 02:05:23', '2022-05-16 02:05:23'),
(11, '12345', NULL, 0, 'DEPOSIT', '2022-05-16 02:06:34', '2022-05-16 02:06:34'),
(12, '12345', NULL, 15000, 'DEPOSIT', '2022-05-16 02:08:25', '2022-05-16 02:08:25'),
(13, '12345', NULL, 12000, 'WITHDRAW', '2022-05-16 02:33:02', '2022-05-16 02:33:02'),
(14, '54321', NULL, 12000, 'DEPOSIT', '2022-05-16 02:41:10', '2022-05-16 02:41:10'),
(15, '54321', NULL, 1000, 'WITHDRAW', '2022-05-16 02:41:18', '2022-05-16 02:41:18'),
(16, '54321', '12345', 20000, 'TRANSFER', '2022-05-16 02:43:18', '2022-05-16 02:43:18'),
(17, '12345', NULL, 12000, 'DEPOSIT', '2022-05-16 03:16:12', '2022-05-16 03:16:12'),
(18, '12345', NULL, 5000, 'WITHDRAW', '2022-05-16 03:16:19', '2022-05-16 03:16:19'),
(19, '12345', '12345', 20000, 'TRANSFER', '2022-05-16 03:16:32', '2022-05-16 03:16:32'),
(20, '12345', '12345', 10000, 'TRANSFER', '2022-05-16 03:16:39', '2022-05-16 03:16:39'),
(21, '7890', NULL, 200000, 'DEPOSIT', '2022-05-16 03:20:33', '2022-05-16 03:20:33'),
(22, '7890', '12345', 100000, 'TRANSFER', '2022-05-16 03:20:51', '2022-05-16 03:20:51');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`account_number`);

--
-- Indexes for table `transaction`
--
ALTER TABLE `transaction`
  ADD PRIMARY KEY (`id`),
  ADD KEY `account_from` (`account_from`),
  ADD KEY `account_to` (`account_to`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `transaction`
--
ALTER TABLE `transaction`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `transaction`
--
ALTER TABLE `transaction`
  ADD CONSTRAINT `transaction_ibfk_1` FOREIGN KEY (`account_from`) REFERENCES `account` (`account_number`) ON DELETE CASCADE,
  ADD CONSTRAINT `transaction_ibfk_2` FOREIGN KEY (`account_to`) REFERENCES `account` (`account_number`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
