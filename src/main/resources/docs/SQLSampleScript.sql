create schema JavaPOS; /*or any other name you prefer*/
use JavaPOS;
create table `biz_hub_product_master` (
  product_name varchar(20),
  product_sell_price_incl double,
  product_barcode bigint
);

insert into biz_hub_product_master values
("T-Shirt",200.0,4556433),
("Bag",200.0,4556433),
("All Purpose Flour",200.0,4556433);


select * from biz_hub_product_master