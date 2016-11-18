CREATE TABLE `rideplus_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rideplus_location` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `zip_code` varchar(30) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

CREATE TABLE `rideplus_driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `phone_number` varchar(10) DEFAULT NULL,
  `address` varchar(128) DEFAULT NULL,
  `car_model` varchar(25) DEFAULT NULL,
  `car_number` varchar(20) DEFAULT NULL,
  `status` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `rideplus_driverstatus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `status` varchar(30) DEFAULT NULL,
  `checkin_location_id` int(11) NOT NULL,
  `checkout_location_id` int(11) DEFAULT NULL,
  `driver_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `driver_id` (`driver_id`),
FOREIGN KEY (`driver_id`) REFERENCES `rideplus_driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `rideplus_requestride` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `requested_time` time NOT NULL,
  `wait_time` time NOT NULL,
  `travel_time` time NOT NULL,
  `date` datetime NOT NULL,
  `status` varchar(30) DEFAULT NULL,
  `customer_id` int(11) NOT NULL,
  `from_location_id` int(11) NOT NULL,
  `to_location_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `to_location_id` (`to_location_id`),
  KEY `customer_id` (`customer_id`),
  KEY `from_location_id` (`from_location_id`),
FOREIGN KEY (`to_location_id`) REFERENCES `rideplus_location` (`id`),
FOREIGN KEY (`customer_id`) REFERENCES `rideplus_customer` (`id`),
FOREIGN KEY (`from_location_id`) REFERENCES `rideplus_location` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `rideplus_rideinformation` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `travel_time` decimal(6,2) NOT NULL,
  `distance` decimal(6,2) NOT NULL,
  `cost` decimal(8,2) NOT NULL,
  `payment_mode` varchar(20) DEFAULT NULL,
  `RidePlus_Share` decimal(6,2) NOT NULL,
  `Driver_Share` decimal(6,2) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `driver_id` int(11) NOT NULL,
  `request_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `request_id` (`request_id`),
  KEY `customer_id` (`customer_id`),
  KEY `driver_id` (`driver_id`),
FOREIGN KEY (`request_id`) REFERENCES `rideplus_requestride` (`id`),
FOREIGN KEY (`customer_id`) REFERENCES `rideplus_customer` (`id`),
FOREIGN KEY (`driver_id`) REFERENCES `rideplus_driver` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


CREATE TABLE `rideplus_rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` int(11) DEFAULT NULL,
  `feedback` varchar(30) DEFAULT NULL,
  `ride_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `rideplus_rating_8606bde1` (`ride_id`),
FOREIGN KEY (`ride_id`) REFERENCES `rideplus_rideinformation` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;





SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `rideplus_orders` (
  `orderNumber` tinyint NOT NULL,
  `Total_Cost` tinyint NOT NULL,
  `RidePlus_TotalShare` tinyint NOT NULL,
  `Driver_TotalShare` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;


SET @saved_cs_client     = @@character_set_client;
SET character_set_client = utf8;
/*!50001 CREATE TABLE `Rideplus_Request_ride` (
  `date` tinyint NOT NULL,
  `Request_id` tinyint NOT NULL,
  `Requested_Time` tinyint NOT NULL,
  `Wait_Time` tinyint NOT NULL,
  `Customer` tinyint NOT NULL,
  `From_Location` tinyint NOT NULL,
  `To_Location` tinyint NOT NULL,
  `status` tinyint NOT NULL
) ENGINE=MyISAM */;
SET character_set_client = @saved_cs_client;

