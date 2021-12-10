insert into ciudad values(4,"Armenia");
insert into ciudad values(2,"Calarca");
insert into ciudad values(3,"Cali");

insert into usuario values("01","alba3@gmail.com","alba marin","123","albam",1);
insert into usuario values("02","fabian@gmail.com","fabian ramirez","1234","fabianm",2);
insert into usuario values("03","brayan@gmail.com","brayan gil","12345","brayanm",3);

insert  into usuario_telefonos values("01","171717");
insert  into usuario_telefonos values("02","4578847");
insert  into usuario_telefonos values("03","3248974");


insert into compra values (1,"2000/07/22","efectivo","01");
insert into compra values (2,"2019/04/22","credito","02");
insert into compra values (3,"2009/10/22","contado","01");




insert into producto values (10,"celular samnsung",10,"2020/05/01","celular",800000,10,1,"01");
insert into producto values (11,"tablet lg",15,"2022/03/15","tablet",500000,10,2,"01");
insert into producto values (12,"para guardar informacion",5,"2022/09/10","memoria",200000,12,3,"02");

insert into producto_categorias values(10,0);
insert into producto_categorias values(11,0);
insert into producto_categorias values(12,0);
insert into producto_categorias values(10,3);

insert into subasta values (1,"2021/12/24",10);
insert into subasta values (2,"2021/01/31",11);
insert into subasta values (3,"2021/10/20",12);

insert into subasta_usuario values (1,"2021/11/24",800000,1,"01");
insert into subasta_usuario values (2,"2021/06/24",800000,2,"02");
insert into subasta_usuario values (3,"2021/12/14",100000,3,"03");

insert into producto_imagen values (10,"ruta/img.jpg");
insert into producto_imagen values (11,"ruta/img.jpg2");
insert into producto_imagen values (12,"ruta/img.jpg3");

insert into comentario values (1,4,"2021/12/24","esta disponible?","si",10,"01");
insert into comentario values (2,5,"2021/02/24","esta disponible?","no",11,"02");
insert into comentario values (3,2,"2021/10/24","tiene fallas","si",10,"01");
insert into comentario values (4,5,"2021/10/24","nada","si",10,"03");

insert into detalle_compra values (1,800000,1,1,10);
insert into detalle_compra values (2,500000,2,2,11);
insert into detalle_compra values (3,800000,2,3,10);

insert into producto_favoritos values (10,"01");
insert into producto_favoritos values (11,"02");
insert into producto_favoritos values (12,"01");

insert into chat values(1,10,"01");
insert into chat values(2,11,"02");
insert into chat values(3,12,"03");

insert into mensaje values (1,"alba","2024/09/20",1);
insert into mensaje values (2,"Fabian","2021/10/20",2);
insert into mensaje values (3,"Brayan","2021/06/15",3);
