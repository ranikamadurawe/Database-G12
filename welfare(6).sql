-- phpMyAdmin SQL Dump
-- version 4.8.3
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Nov 27, 2018 at 07:47 PM
-- Server version: 10.1.36-MariaDB
-- PHP Version: 7.2.10

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `welfare`
--

-- --------------------------------------------------------

--
-- Table structure for table `branch`
--

CREATE TABLE `branch` (
  `name` varchar(255) NOT NULL,
  `address` mediumtext NOT NULL,
  `branchid` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `branch`
--

INSERT INTO `branch` (`name`, `address`, `branchid`) VALUES
('\"HeadOffice\"', '\"35/A, Torrington Road, Maradana, Colombo 20, Sri Lanka\"', 'b0001'),
('\"SecondOffice\"', '\"A/3, Galle Road, Hikkaduwa, Galle, Sri Lanka\"', 'b0002'),
('\"ThirdOffice\"', '\"A/55, Galle Road, Moratuwa, Sri Lanka\"', 'b0003'),
('GalleOffice', 'Somewhere,Galle', 'b0004');

--
-- Triggers `branch`
--
DELIMITER $$
CREATE TRIGGER `checkbranch` BEFORE INSERT ON `branch` FOR EACH ROW BEGIN
if (new.address = '' or new.name='' or new.branchid = '') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `deptid` varchar(5) NOT NULL,
  `name` varchar(40) NOT NULL,
  `building` varchar(40) NOT NULL,
  `branchid` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`deptid`, `name`, `building`, `branchid`) VALUES
('d0001', 'Marketing Dept', 'Two Towers 1', 'b0001'),
('d0002', 'Computing Dept', 'Big Building', 'b0002'),
('d0003', 'Architecture Dept', 'sumanadasa', 'b0003'),
('d0004', 'po', 'oya', 'b0001'),
('d0005', 'doob', 'k', 'b0004');

--
-- Triggers `department`
--
DELIMITER $$
CREATE TRIGGER `checkdep` BEFORE INSERT ON `department` FOR EACH ROW BEGIN
if (new.deptid = '' or new.name='' or new.branchid = '' or new.building = '') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `empcontact`
--

CREATE TABLE `empcontact` (
  `eid` varchar(5) NOT NULL,
  `contact` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empcontact`
--

INSERT INTO `empcontact` (`eid`, `contact`) VALUES
('e0000', '8321321332'),
('e0002', '0912233268'),
('e0003', '0912233268'),
('e0004', '0323233332'),
('e0005', '0912233268'),
('e0006', '0123456789'),
('e0007', '0912233268'),
('e0009', '1234567890'),
('e0010', '8321321332'),
('e0012', '0999999999');

--
-- Triggers `empcontact`
--
DELIMITER $$
CREATE TRIGGER `checkphone` BEFORE INSERT ON `empcontact` FOR EACH ROW BEGIN
if (new.eid = '' or new.contact='') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
IF (NEW.contact REGEXP '^[0-9]{10}$' ) = 0 THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Invalid Phone!!!';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `empemergenct`
--

CREATE TABLE `empemergenct` (
  `cid` varchar(5) NOT NULL,
  `name` varchar(40) NOT NULL,
  `phone` varchar(10) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `eid` varchar(5) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `empemergenct`
--

INSERT INTO `empemergenct` (`cid`, `name`, `phone`, `email`, `eid`) VALUES
('c0001', 'ranika', '077123456', 'ranika@gmail.com', 'e0001'),
('c0002', 'sachith', '011123456', 'sachith@gmail.com', 'e0002'),
('c0003', 'Thamindu', '076123456', 'thamindu@gmail.com', 'e0003');

--
-- Triggers `empemergenct`
--
DELIMITER $$
CREATE TRIGGER `checkemergencyphone` BEFORE INSERT ON `empemergenct` FOR EACH ROW BEGIN
if (new.cid = '' or new.name=''  or new.phone=''  or new.email=''  or new.eid='') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
IF (NEW.phone REGEXP '^[0-9]{10}$' ) = 0 THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Invalid Phone!!!';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `employeeadditional`
--

CREATE TABLE `employeeadditional` (
  `eid` varchar(5) NOT NULL,
  `s` int(11) DEFAULT NULL,
  `sda` varchar(255) DEFAULT NULL,
  `No_Children` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employeeadditional`
--

INSERT INTO `employeeadditional` (`eid`, `s`, `sda`, `No_Children`) VALUES
('e0000', 5, 'da', 3),
('e0001', 90, 'd', NULL),
('e0002', NULL, NULL, NULL),
('e0003', NULL, NULL, NULL),
('e0004', NULL, NULL, NULL),
('e0005', 3, 'okay', 1),
('e0006', 1, 'bye', 2),
('e0007', 2, 'random', 4),
('e0009', 5, 'something', 0),
('e0010', 1, 'ela', 2),
('e0012', 2, 'e', 5);

-- --------------------------------------------------------

--
-- Table structure for table `employeedetails`
--

CREATE TABLE `employeedetails` (
  `eid` varchar(5) NOT NULL,
  `supervisorid` varchar(5) DEFAULT NULL,
  `deptid` varchar(5) NOT NULL,
  `status` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `issuper` tinyint(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employeedetails`
--

INSERT INTO `employeedetails` (`eid`, `supervisorid`, `deptid`, `status`, `title`, `issuper`) VALUES
('e0000', 'e0001', 'd0002', 'temporary', 'hr', 1),
('e0001', 'e0002', 'd0001', 'temporary', 'admin', 0),
('e0002', 'e0003', 'd0001', 'temporary', 'hr', 1),
('e0003', 'e0004', 'd0001', 'temporary', 'hr', 1),
('e0004', 'e0005', 'd0001', 'temporary', 'hr', 0),
('e0005', NULL, 'd0001', 'temporary', 'hr', 0),
('e0006', NULL, 'd0001', 'temporary', 'hr', 0),
('e0007', NULL, 'd0001', 'temporary', 'hr', 0),
('e0009', NULL, 'd0001', 'temporary', 'hr', 0),
('e0010', NULL, 'd0002', 'temporary', 'hr', 0),
('e0012', NULL, 'd0002', 'permanent', 'hr', NULL),
('e1234', NULL, 'd0001', 'freelancer\r\n', 'admin', NULL);

--
-- Triggers `employeedetails`
--
DELIMITER $$
CREATE TRIGGER `checksuper` AFTER UPDATE ON `employeedetails` FOR EACH ROW BEGIN
IF (NEW.supervisorid <> OLD.supervisorid) THEN
	  UPDATE employeedetails SET issuper = 1 where supervisorid=NEW.supervisorid;
      IF (SELECT count(eid) FROM `employeedetails` WHERE supervisorid=OLD.supervisorid = 0) THEN
      	  UPDATE employeedetails SET issuper = 0 where supervisorid=OLD.supervisorid;
          END IF;
END IF;
END
$$
DELIMITER ;
DELIMITER $$
CREATE TRIGGER `checkva` BEFORE INSERT ON `employeedetails` FOR EACH ROW BEGIN
if (new.eid = '' or new.supervisorid=''  or new.deptid=''  or new.title=''  or new.status='') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `employeepersonal`
--

CREATE TABLE `employeepersonal` (
  `eid` varchar(5) NOT NULL,
  `name` varchar(50) NOT NULL,
  `nic` varchar(10) NOT NULL,
  `nationality` varchar(30) DEFAULT NULL,
  `maritalstatus` enum('Married','Single','Divorced') DEFAULT NULL,
  `birthdate` date DEFAULT NULL,
  `address` varchar(100) NOT NULL,
  `email` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `employeepersonal`
--

INSERT INTO `employeepersonal` (`eid`, `name`, `nic`, `nationality`, `maritalstatus`, `birthdate`, `address`, `email`) VALUES
('e0000', 'ranikz', '888858888v', '123', 'Married', NULL, 'ii', 'u@d.com'),
('e0001', 'ranika', '970061432v', 'sinhala', 'Single', NULL, 'galle', 'dday@gmail.com'),
('e0002', 'ranika', '970062432v', 'sinhala', 'Single', NULL, 'colombo', 'bdy@gmail.com'),
('e0003', 'ranika', '3rfesdf', 'si', 'Married', NULL, 'matara', 'o@gmail.com'),
('e0004', 'sacith', '423423422v', 'burger', 'Divorced', NULL, 'angedara', 's@sam.com'),
('e0005', 'dula', '123456789v', 'sin', 'Married', NULL, 'asd', 'ra@gmail.com'),
('e0006', 'MAN', '949394949v', 'ranka', 'Married', NULL, 'fkdjflksdjfklasdjf', 'r@gmail.com'),
('e0007', 'ranikx', '987654321v', 'illla', 'Married', NULL, 'illla', '1001@gmail.cm'),
('e0009', 'ran', '888888888v', 'pizza', 'Married', '0002-02-02', 'c', 'r2@d.com'),
('e0010', 'ranikz', '888858388v', '123', 'Married', NULL, 'ii', 'u@d.com'),
('e0012', 'ras', '214214214v', 'sdfsdf', 'Married', '0002-11-30', 'kkkkkk', 'h@hh.com'),
('e1234', 'rabu', '555555555v', 'sdfsf', 'Married', NULL, 'adsd', 'd@g.com');

--
-- Triggers `employeepersonal`
--
DELIMITER $$
CREATE TRIGGER `checknic` BEFORE INSERT ON `employeepersonal` FOR EACH ROW BEGIN
if (new.eid = '' or new.name=''  or new.nationality=''  or new.maritalstatus=''  or new.address='') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;

IF (NEW.nic REGEXP '^[0-9]{9}v$' ) = 0 THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Invalid NIC!!!';
ELSEIF (NEW.email REGEXP '^[A-Z0-9._%-]+@[A-Z0-9.-]+.[A-Z]{2,63}$' ) = 0  THEN 
	  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Invalid Email!!!';
END IF; 
IF (new.birthdate > date(now())) THEN 
    signal sqlstate '12345'
    set MESSAGE_TEXT ='invalid bday';

END IF; 

END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `emppay`
--

CREATE TABLE `emppay` (
  `eid` varchar(5) NOT NULL,
  `epid` varchar(255) NOT NULL,
  `epf` int(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `emppay`
--

INSERT INTO `emppay` (`eid`, `epid`, `epf`) VALUES
('e0000', 'pg0001', 200),
('e0001', 'pg0001', 0),
('e0002', 'pg0002', 0),
('e0003', 'pg0003', 0),
('e0007', 'pg0001', 1001),
('e0009', 'pg0001', 5),
('e0010', 'pg0001', 200),
('e0012', 'pg0001', 0);

--
-- Triggers `emppay`
--
DELIMITER $$
CREATE TRIGGER `empay` BEFORE INSERT ON `emppay` FOR EACH ROW BEGIN 
if (new.eid = '' or new.epf=''  or new.epid='') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
IF (NEW.epf>101 ) THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Wrong EMP';
END IF; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `estatus`
--

CREATE TABLE `estatus` (
  `statusdesc` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `estatus`
--

INSERT INTO `estatus` (`statusdesc`) VALUES
('freelancer\r\n'),
('permanent'),
('temporary');

-- --------------------------------------------------------

--
-- Table structure for table `jobtitiles`
--

CREATE TABLE `jobtitiles` (
  `rolename` varchar(30) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `jobtitiles`
--

INSERT INTO `jobtitiles` (`rolename`, `name`) VALUES
('admin', 'admin'),
('hr', 'hr'),
('Johhny', 'level1 employee');

--
-- Triggers `jobtitiles`
--
DELIMITER $$
CREATE TRIGGER `check` BEFORE INSERT ON `jobtitiles` FOR EACH ROW BEGIN
if (new.rolename = '' or new.name='') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `leaveleft`
--

CREATE TABLE `leaveleft` (
  `eid` varchar(5) NOT NULL,
  `annual` int(11) NOT NULL,
  `casual` int(11) NOT NULL,
  `maturity` int(11) NOT NULL,
  `nopay` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leaveleft`
--

INSERT INTO `leaveleft` (`eid`, `annual`, `casual`, `maturity`, `nopay`) VALUES
('e0001', 0, 0, 50, 0),
('e0002', 9, 18, 2, 6),
('e0003', 10, 15, 6, 7),
('e0012', 0, 0, 50, 0);

-- --------------------------------------------------------

--
-- Table structure for table `leavesubmissions`
--

CREATE TABLE `leavesubmissions` (
  `eid` varchar(5) NOT NULL,
  `leaveid` varchar(5) NOT NULL,
  `type` enum('maturity','annual','casual','nopay') NOT NULL,
  `date` date NOT NULL,
  `enddate` date NOT NULL,
  `reason` mediumtext NOT NULL,
  `status` enum('Pending','Rejected','Accepted') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `leavesubmissions`
--

INSERT INTO `leavesubmissions` (`eid`, `leaveid`, `type`, `date`, `enddate`, `reason`, `status`) VALUES
('e0001', 'l0005', 'casual', '2018-11-08', '2018-11-15', 'yay', ''),
('e0001', 'l0006', 'casual', '2018-11-29', '2018-11-30', 'yay', ''),
('e0001', 'l0007', 'casual', '2018-11-29', '2018-11-30', 'yay', 'Pending'),
('e0001', 'l0008', 'annual', '2018-11-28', '2018-11-30', 'sick', 'Pending');

--
-- Triggers `leavesubmissions`
--
DELIMITER $$
CREATE TRIGGER `checkday` BEFORE INSERT ON `leavesubmissions` FOR EACH ROW BEGIN 
DECLARE TEMPKODE VARCHAR(5);

select CONCAT('l',LPAD(CAST(substring_index(leaveid,'l',-1) as unsigned)+1,4,'0')) into TEMPKODE from leavesubmissions order by leaveid desc limit 1 ;

SET NEW.LEAVEID := TEMPKODE;

if (new.eid = ''  or new.type=''  or new.date=''  or new.enddate='' or new.reason = '' or new.status='') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;

if (new.status != 'Pending') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='a new leave cannot be already accepted';
end if;

IF (new.date < date(now())) THEN 
    signal sqlstate '12345'
    set MESSAGE_TEXT ='starting day after today';

END IF; 

IF (new.date > new.enddate) THEN 
    signal sqlstate '12345'
    set MESSAGE_TEXT ='ending day before starting dat=y';

END IF; 

if (new.type = 'nopay') THEN 	
	if(select nopay from leaveleft where eid=new.eid ) = 0 THEN     			SIGNAL SQLSTATE '12345'          
    	SET MESSAGE_TEXT = 'You have no nopay leaves remaning';     	ELSE     	
        update leaveleft set nopay=nopay-1 where eid=new.eid;     	end if; 
end if;

if (new.type = 'maturity') THEN 	
	if(select maturity from leaveleft where eid=new.eid) = 0 THEN     			SIGNAL SQLSTATE '12345'          
    	SET MESSAGE_TEXT = 'You have no maternity leaves remaning';     	ELSE     	
        update leaveleft set maturity=maturity-1 where eid=new.eid;     	end if; 
end if;

if (new.type = 'casual') THEN 	
	if(select casual from leaveleft where eid=new.eid)=0 THEN     			SIGNAL SQLSTATE '12345'          
    	SET MESSAGE_TEXT = 'You have no casual leaves remaning';     	ELSE     	
        update leaveleft set casual=casual-1 where eid=new.eid;     	end if; 
end if;

if (new.type = 'annual') THEN 	
	if(select annual from leaveleft where eid=new.eid)=0 THEN     			SIGNAL SQLSTATE '12345'          
    	SET MESSAGE_TEXT = 'You have no annual leaves remaning';     	ELSE     	
        update leaveleft set annual=annual-1 where eid=new.eid;
        
        end if; 
end if;


END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `paygrade`
--

CREATE TABLE `paygrade` (
  `pgid` varchar(255) NOT NULL,
  `salary` decimal(10,2) NOT NULL,
  `casual` int(11) NOT NULL,
  `nopay` int(11) NOT NULL,
  `annual` int(11) NOT NULL,
  `maturity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `paygrade`
--

INSERT INTO `paygrade` (`pgid`, `salary`, `casual`, `nopay`, `annual`, `maturity`) VALUES
('pg0000', '0.00', 0, 0, 0, 50),
('pg0001', '0.00', 0, 0, 0, 50),
('pg0002', '5000000.00', 18, 6, 9, 2),
('pg0003', '3000000.00', 15, 7, 10, 6);

--
-- Triggers `paygrade`
--
DELIMITER $$
CREATE TRIGGER `verifydata` BEFORE INSERT ON `paygrade` FOR EACH ROW BEGIN 
IF (NEW.maturity < 0 ) THEN 
  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Please enter a positive number for maternity leaves';
ELSEIF (NEW.annual < 0 )THEN 
	  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Please enter a positive number for annual leaves';
ELSEIF (NEW.casual < 0 )THEN 
	  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Please enter a positive number for casual leaves';
ELSEIF (NEW.salary < 0 )THEN 
	  SIGNAL SQLSTATE '12345'
     SET MESSAGE_TEXT = 'Please enter a positive number for salary';     
END IF; 
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `useraccount`
--

CREATE TABLE `useraccount` (
  `eid` varchar(5) NOT NULL,
  `password` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `useraccount`
--

INSERT INTO `useraccount` (`eid`, `password`) VALUES
('e0001', 'temporary'),
('e0002', 'temporary2'),
('e0003', 'temporary3'),
('e0004', 'ranikz'),
('e0005', 'hi');

--
-- Triggers `useraccount`
--
DELIMITER $$
CREATE TRIGGER `checkf` BEFORE INSERT ON `useraccount` FOR EACH ROW begin
if (new.eid = '') THEN
	signal SQLSTATE '12345'
    	set MESSAGE_TEXT='Please fill out the form completly';
end if;
end
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `userroles`
--

CREATE TABLE `userroles` (
  `name` varchar(30) NOT NULL,
  `accesslevel` varchar(40) NOT NULL,
  `cuname` varchar(255) NOT NULL,
  `cpass` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `userroles`
--

INSERT INTO `userroles` (`name`, `accesslevel`, `cuname`, `cpass`) VALUES
('admin', 'complete', 'administrator', 'admin'),
('hr', 'complete', 'hr', 'hrpassword'),
('level1 employee', 'minimal', 'emp', 'emp'),
('managerial_emp', 'PIMedit', 'manager', 'man');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `branch`
--
ALTER TABLE `branch`
  ADD PRIMARY KEY (`branchid`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`deptid`),
  ADD KEY `branchid` (`branchid`);

--
-- Indexes for table `empcontact`
--
ALTER TABLE `empcontact`
  ADD PRIMARY KEY (`eid`,`contact`);

--
-- Indexes for table `empemergenct`
--
ALTER TABLE `empemergenct`
  ADD PRIMARY KEY (`cid`),
  ADD KEY `eid` (`eid`);

--
-- Indexes for table `employeeadditional`
--
ALTER TABLE `employeeadditional`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `employeedetails`
--
ALTER TABLE `employeedetails`
  ADD PRIMARY KEY (`eid`),
  ADD KEY `supervisorid` (`supervisorid`),
  ADD KEY `employeedetails_ibfk_2` (`deptid`),
  ADD KEY `employeedetails_ibfk_3` (`status`),
  ADD KEY `employeedetails_ibfk_4` (`title`);

--
-- Indexes for table `employeepersonal`
--
ALTER TABLE `employeepersonal`
  ADD PRIMARY KEY (`eid`),
  ADD UNIQUE KEY `nic` (`nic`);

--
-- Indexes for table `emppay`
--
ALTER TABLE `emppay`
  ADD PRIMARY KEY (`eid`),
  ADD KEY `epid` (`epid`);

--
-- Indexes for table `estatus`
--
ALTER TABLE `estatus`
  ADD PRIMARY KEY (`statusdesc`),
  ADD KEY `statusdesc` (`statusdesc`);

--
-- Indexes for table `jobtitiles`
--
ALTER TABLE `jobtitiles`
  ADD PRIMARY KEY (`rolename`),
  ADD KEY `accessrole` (`name`);

--
-- Indexes for table `leaveleft`
--
ALTER TABLE `leaveleft`
  ADD PRIMARY KEY (`eid`);

--
-- Indexes for table `leavesubmissions`
--
ALTER TABLE `leavesubmissions`
  ADD PRIMARY KEY (`leaveid`),
  ADD KEY `eid` (`eid`);

--
-- Indexes for table `paygrade`
--
ALTER TABLE `paygrade`
  ADD PRIMARY KEY (`pgid`);

--
-- Indexes for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD PRIMARY KEY (`eid`),
  ADD KEY `eid` (`eid`);

--
-- Indexes for table `userroles`
--
ALTER TABLE `userroles`
  ADD PRIMARY KEY (`name`);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `department`
--
ALTER TABLE `department`
  ADD CONSTRAINT `department_ibfk_1` FOREIGN KEY (`branchid`) REFERENCES `branch` (`branchid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `empcontact`
--
ALTER TABLE `empcontact`
  ADD CONSTRAINT `empcontact_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `empemergenct`
--
ALTER TABLE `empemergenct`
  ADD CONSTRAINT `empemergenct_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`);

--
-- Constraints for table `employeeadditional`
--
ALTER TABLE `employeeadditional`
  ADD CONSTRAINT `employeeadditional_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`);

--
-- Constraints for table `employeedetails`
--
ALTER TABLE `employeedetails`
  ADD CONSTRAINT `employeedetails_ibfk_1` FOREIGN KEY (`supervisorid`) REFERENCES `employeedetails` (`eid`) ON DELETE SET NULL ON UPDATE CASCADE,
  ADD CONSTRAINT `employeedetails_ibfk_2` FOREIGN KEY (`deptid`) REFERENCES `department` (`deptid`) ON UPDATE CASCADE,
  ADD CONSTRAINT `employeedetails_ibfk_5` FOREIGN KEY (`status`) REFERENCES `estatus` (`statusdesc`) ON UPDATE CASCADE,
  ADD CONSTRAINT `employeedetails_ibfk_6` FOREIGN KEY (`title`) REFERENCES `jobtitiles` (`rolename`);

--
-- Constraints for table `employeepersonal`
--
ALTER TABLE `employeepersonal`
  ADD CONSTRAINT `employeepersonal_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `emppay`
--
ALTER TABLE `emppay`
  ADD CONSTRAINT `emppay_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `emppay_ibfk_2` FOREIGN KEY (`epid`) REFERENCES `paygrade` (`pgid`) ON UPDATE CASCADE;

--
-- Constraints for table `jobtitiles`
--
ALTER TABLE `jobtitiles`
  ADD CONSTRAINT `jobtitiles_ibfk_1` FOREIGN KEY (`name`) REFERENCES `userroles` (`name`) ON UPDATE CASCADE;

--
-- Constraints for table `leaveleft`
--
ALTER TABLE `leaveleft`
  ADD CONSTRAINT `leaveleft_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `leavesubmissions`
--
ALTER TABLE `leavesubmissions`
  ADD CONSTRAINT `leavesubmissions_ibfk_2` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `useraccount`
--
ALTER TABLE `useraccount`
  ADD CONSTRAINT `useraccount_ibfk_1` FOREIGN KEY (`eid`) REFERENCES `employeedetails` (`eid`) ON DELETE CASCADE ON UPDATE CASCADE;

DELIMITER $$
--
-- Events
--
CREATE DEFINER=`root`@`localhost` EVENT `dailyupdate` ON SCHEDULE EVERY 1 DAY STARTS '2018-11-29 01:00:00' ON COMPLETION NOT PRESERVE ENABLE DO update leaveleft as e join (SELECT eid,nopay,maturity,annual,casual FROM emppay join paygrade on epid=pgid) as c on e.eid=c.eid set e.annual=c.annual,e.casual=c.casual,e.maturity=c.maturity,e.nopay=c.nopay$$

DELIMITER ;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
