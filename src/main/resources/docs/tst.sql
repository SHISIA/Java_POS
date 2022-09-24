select * from biz_hub_product_master;


alter table biz_hub_product_master drop column BarCode ;

# product_barcode, product_name, count, product_sell_price_incl, BarCode
insert into biz_hub_product_master values ("	fgfdgnfnb",'nyaema fwe',  1500,2234.3, 100233742971);

insert into biz_hub_product_master values ("qqwerfcdc",'medisic',  12,2233.3, 2321242112112);

insert into biz_hub_product_master values ("meaty",'mtii',  12,2233.3, 2321242112112)


alter table biz_hub_product_master add column BarCode 
bigint after product_sell_price_incl;

set sql_safe_updates=0;
delete from biz_hub_product_master;


update biz_hub_product_master set barcode=100233742971 where product_name="Meat Pie";
