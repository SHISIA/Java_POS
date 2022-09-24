CREATE DATABASE `JavaPOS` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
CREATE TABLE `biz_hub_product_master` (
  `product_barcode` varchar(30) DEFAULT NULL,
  `product_name` text,
  `count` decimal(10,0) DEFAULT NULL,
  `product_sell_price_incl` int DEFAULT NULL,
  `BarCode` bigint DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `JavaPOS`.`biz_hub_product_master`
(`product_barcode`,
`product_name`,
`count`,
`product_sell_price_incl`,
`BarCode`)
VALUES
(<{product_barcode: }>,CREATE TABLE `DBs` (
  `DbName` varchar(30) DEFAULT NULL,
  `DBPath` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `JavaPOS`.`DBs`
(`DbName`,
`DBPath`)
VALUES
(<{DbName: }>,
<{DBPath: }>);

<{product_name: }>,
<{count: }>,
<{product_sell_price_incl: }>,
<{BarCode: }>);
