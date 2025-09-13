-- Drop database if exists DBAutoRepuestosGT;
create database DBAutoRepuestosGT;
use DBAutoRepuestosGT;

create table Distribuidores(
	id_distribuidor int auto_increment not null,
    nombre_distribuidor varchar(60) not null,
    telefono_distribuidor int not null,
    direccion varchar(100) not null,
    email_distribuidor varchar(100) not null,
    primary key PK_id_distribuidor(id_distribuidor)
);

create table Personal(
	id_personal int auto_increment not null,
    nombre_personal varchar(60) not null,
    apellido_personal varchar(60) not null,
    puesto_personal varchar(20) null,
    email_personal varchar(100) not null,
    primary key PK_id_personal(id_personal)
);

create table Productos(
	id_producto int auto_increment not null,
    nombre_producto varchar(60) not null,
    categoria_producto varchar(60) not null,
    precio_compra double not null,
    precio_venta double not null,
    id_distribuidor int not null,
    primary key PK_id_producto(id_producto),
    constraint FK_producto_distribuidor foreign key (id_distribuidor) 
		references Distribuidores(id_distribuidor) on delete cascade
);

create table Transacciones(
	id_transaccion int auto_increment not null,
    fecha_transaccion date not null,
    cantidad int not null,
    total double not null,
	id_personal int not null,
    id_producto int not null,
    primary key PK_id_transaccion(id_transaccion),
    constraint FK_transaccion_personal foreign key (id_personal) 
		references Personal(id_personal) on delete cascade,
    constraint FK_transaccion_producto foreign key (id_producto) 
		references Productos(id_producto) on delete cascade
);


-- Procedimiento almacenado para agregar distribuidores
delimiter $$
create procedure sp_AgregarDistribuidor(
    in nombreDistribuidor varchar(60),
    in telefonoDistribuidor varchar(15),
    in direccion varchar(100),
    in emailDistribuidor varchar(100)
)
begin
    insert into Distribuidores (nombre_distribuidor, telefono_distribuidor, direccion, email_distribuidor)
    values (nombreDistribuidor, telefonoDistribuidor, direccion, emailDistribuidor);
end $$
delimiter ;

call sp_AgregarDistribuidor('Partes Centrales', '24681357', 'Zona 4, Guatemala', 'centrales@gmail.com');
call sp_AgregarDistribuidor('Refacciones Rápidas', '13579246', 'Zona 11, Guatemala', 'rapidas@hotmail.com');
call sp_AgregarDistribuidor('Autos y Piezas', '35791468', 'Villa Nueva', 'autospiezas@gmail.com');
call sp_AgregarDistribuidor('Importadora Veloz', '97531864', 'Zona 9, Mixco', 'veloz@outlook.com');
call sp_AgregarDistribuidor('Repuestos del Sur', '86427531', 'Petapa', 'sur@gmail.com');
call sp_AgregarDistribuidor('Distribuidora Ramos', '75319842', 'Retalhuleu', 'ramos@hotmail.com');
call sp_AgregarDistribuidor('Partes Premium', '64208753', 'Huehuetenango', 'premium@gmail.com');
call sp_AgregarDistribuidor('Auto Suministros', '53197640', 'Zona 15, Guatemala', 'suministros@gmail.com');
call sp_AgregarDistribuidor('Mega Distribuidora', '42086531', 'Villa Canales', 'mega@outlook.com');
call sp_AgregarDistribuidor('Turbo Partes', '31975428', 'Zona 21, Guatemala', 'turbo@gmail.com');
select * from Distribuidores;

-- Procedimiento almacenado para agregar personal
delimiter $$
create procedure sp_AgregarPersonal(
    in nombrePersonal varchar(60),
    in apellidoPersonal varchar(60),
    in puestoPersonal varchar(20),
    in emailPersonal varchar(100)
)
begin
    insert into Personal (nombre_personal, apellido_personal, puesto_personal, email_personal)
    values (nombrePersonal, apellidoPersonal, puestoPersonal, emailPersonal);
end $$
delimiter ;

call sp_AgregarPersonal('Miguel', 'Vargas', 'Vendedor', 'miguel@gmail.com');
call sp_AgregarPersonal('Sandra', 'Ruiz', 'Cajero', 'sandra@hotmail.com');
call sp_AgregarPersonal('Alberto', 'Silva', 'Gerente', 'alberto@gmail.com');
call sp_AgregarPersonal('Carmen', 'Juárez', 'Vendedor', 'carmen@gmail.com');
call sp_AgregarPersonal('Fernando', 'Vega', 'Supervisor', 'fernando@hotmail.com');
call sp_AgregarPersonal('Rosa', 'Campos', 'Vendedor', 'rosa@gmail.com');
call sp_AgregarPersonal('Alejandro', 'Díaz', 'Cajero', 'alejandro@gmail.com');
call sp_AgregarPersonal('Elena', 'Ortiz', 'Contador', 'elena@gmail.com');
call sp_AgregarPersonal('Ricardo', 'Molina', 'Vendedor', 'ricardo@gmail.com');
call sp_AgregarPersonal('Daniela', 'Soto', 'Vendedor', 'daniela@gmail.com');
select * from Personal;

-- Procedimiento almacenado para agregar productos
delimiter $$
create procedure sp_AgregarProducto(
    in nombreProducto varchar(60),
    in categoriaProducto varchar(60),
    in precioCompra double,
    in precioVenta double,
    in idDistribuidor int
)
begin
    insert into Productos (nombre_producto, categoria_producto, precio_compra, precio_venta, id_distribuidor)
    values (nombreProducto, categoriaProducto, precioCompra, precioVenta, idDistribuidor);
end $$
delimiter ;

call sp_AgregarProducto('Filtro de combustible', 'Filtros', 65, 95, 1);
call sp_AgregarProducto('Bujía Champion', 'Encendido', 45, 70, 2);
call sp_AgregarProducto('Discos de freno', 'Frenos', 150, 220, 3);
call sp_AgregarProducto('Amortiguador trasero', 'Suspensión', 280, 420, 4);
call sp_AgregarProducto('Bomba de agua', 'Enfriamiento', 520, 780, 5);
call sp_AgregarProducto('Batería Bosch', 'Eléctrico', 750, 1100, 6);
call sp_AgregarProducto('Motor de arranque', 'Eléctrico', 950, 1400, 7);
call sp_AgregarProducto('Filtro de gasolina', 'Filtros', 55, 85, 8);
call sp_AgregarProducto('Banda de distribución', 'Motor', 480, 720, 9);
call sp_AgregarProducto('Tensor de correa', 'Motor', 290, 450, 10);
select * from Productos;

-- Procedimiento almacenado para agregar transacciones
delimiter $$
create procedure sp_AgregarTransaccion(
    in fechaTransaccion date,
    in cantidad int,
    in total double,
    in idPersonal int,
    in idProducto int
)
begin
    insert into Transacciones (fecha_transaccion, cantidad, total, id_personal, id_producto)
    values (fechaTransaccion, cantidad, total, idPersonal, idProducto);
end $$
delimiter ;

call sp_AgregarTransaccion('2025-09-01', 3, 285, 1, 1);
call sp_AgregarTransaccion('2025-09-01', 2, 140, 2, 2);
call sp_AgregarTransaccion('2025-09-02', 1, 220, 3, 3);
call sp_AgregarTransaccion('2025-09-02', 2, 840, 4, 4);
call sp_AgregarTransaccion('2025-09-03', 1, 780, 5, 5);
call sp_AgregarTransaccion('2025-09-03', 2, 2200, 6, 6);
call sp_AgregarTransaccion('2025-09-04', 1, 1400, 7, 7);
call sp_AgregarTransaccion('2025-09-04', 3, 255, 8, 8);
call sp_AgregarTransaccion('2025-09-05', 2, 1440, 9, 9);
call sp_AgregarTransaccion('2025-09-05', 1, 450, 10, 10);
select * from Transacciones;