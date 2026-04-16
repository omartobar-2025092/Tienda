Drop database if exists DBTienda_in5cm;
Create database DBTienda_in5cm;
Use DBTienda_in5cm;

-- Tablas
Create Table clientes(
dpi_cliente Int auto_increment not null,
nombre_cliente Varchar(50) not null,
apellido_cliente Varchar(50) not null,
direccion Varchar(100) not null,
estado Int not null,
primary key(dpi_cliente)
);

Create Table usuarios(
codigo_usuario Int auto_increment not null,
username Varchar(45) not null,
pasword Varchar(45) not null,
primary key(codigo_usuario)
);
Alter Table usuarios add column rol varchar(45);

Create Table ventas(
codigo_venta Int auto_increment not null,
fecha_venta Date not null,
total Decimal(10,2) not null,
estado Int not null,
dpi_cliente Int,
codigo_usuario Int,
primary key(codigo_venta),
constraint fk_dpi_cliente foreign key(dpi_cliente) references clientes(dpi_cliente),
constraint fk_codigo_usuario foreign key(codigo_usuario) references usuarios(codigo_usuario)
);

Create Table productos(
codigo_producto Int auto_increment not null,
nombre_producto Varchar(50) not null,
precio Decimal(10,2) not null,
stock Int not null,
estado Int not null,
primary key(codigo_producto)
);

Create Table detalleventas(
codigo_detalle_venta Int auto_increment not null,
cantidad Int not null,
precio_unitario Decimal(10,2) not null,
subtotal Decimal(10,2) not null,
codigo_producto Int,
codigo_venta Int,
primary key(codigo_detalle_venta),
constraint fk_codigo_producto foreign key(codigo_producto) references productos(codigo_producto),
constraint fk_codigo_venta foreign key(codigo_venta) references ventas(codigo_venta)
);

-- Procedimientos Almacenados
DELIMITER $$

Create procedure sp_ListarClientes()
Begin
Select * from clientes;
End$$

Create procedure sp_AgregarCliente(
In sp_nombre Varchar(50),
In sp_apellido Varchar(50),
In sp_direccion Varchar(100),
In sp_estado Int
)
Begin
Insert into clientes(nombre_cliente,apellido_cliente,direccion,estado)
values(sp_nombre,sp_apellido,sp_direccion,sp_estado);
End$$

Create procedure sp_EditarCliente(
In sp_id Int,
In sp_nombre Varchar(50),
In sp_apellido Varchar(50),
In sp_direccion Varchar(100),
In sp_estado Int
)
Begin
Update clientes set
nombre_cliente=sp_nombre,
apellido_cliente=sp_apellido,
direccion=sp_direccion,
estado=sp_estado
where dpi_cliente=sp_id;
End$$

Create procedure sp_EliminarCliente(In sp_id Int)
Begin
Delete from clientes where dpi_cliente=sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarUsuarios()
Begin
Select * from usuarios;
End$$

Create procedure sp_AgregarUsuario(
In sp_username Varchar(45),
In sp_pasword Varchar(45),
In sp_email Varchar(60),
In sp_rol Varchar(45),
In sp_estado Int
)
Begin
Insert into usuarios(username,pasword,email,rol,estado)
values(sp_username,sp_pasword,sp_email,sp_rol,sp_estado);
End$$

Create procedure sp_EditarUsuario(
In sp_id Int,
In sp_username Varchar(45),
In sp_pasword Varchar(45),
In sp_email Varchar(60),
In sp_rol Varchar(45),
In sp_estado Int
)
Begin
Update usuarios set
username=sp_username,
pasword=sp_pasword,
email=sp_email,
rol=sp_rol,
estado=sp_estado
where codigo_usuario=sp_id;
End$$

Create procedure sp_EliminarUsuario(In sp_id Int)
Begin
Delete from usuarios where codigo_usuario=sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarProductos()
Begin
Select * from productos;
End$$

Create procedure sp_AgregarProducto(
In sp_nombre Varchar(50),
In sp_precio Decimal(10,2),
In sp_stock Int,
In sp_estado Int
)
Begin
Insert into productos(nombre_producto,precio,stock,estado)
values(sp_nombre,sp_precio,sp_stock,sp_estado);
End$$

Create procedure sp_EditarProducto(
In sp_id Int,
In sp_nombre Varchar(50),
In sp_precio Decimal(10,2),
In sp_stock Int,
In sp_estado Int
)
Begin
Update productos set
nombre_producto=sp_nombre,
precio=sp_precio,
stock=sp_stock,
estado=sp_estado
where codigo_producto=sp_id;
End$$

Create procedure sp_EliminarProducto(In sp_id Int)
Begin
Delete from productos where codigo_producto=sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarVentas()
Begin
Select * from ventas;
End$$

Create procedure sp_AgregarVenta(
In sp_fecha Date,
In sp_total Decimal(10,2),
In sp_estado Int,
In sp_dpi_cliente Int,
In sp_codigo_usuario Int
)
Begin
Insert into ventas(fecha_venta,total,estado,dpi_cliente,codigo_usuario)
values(sp_fecha,sp_total,sp_estado,sp_dpi_cliente,sp_codigo_usuario);
End$$

Create procedure sp_EditarVenta(
In sp_id Int,
In sp_fecha Date,
In sp_total Decimal(10,2),
In sp_estado Int,
In sp_dpi_cliente Int,
In sp_codigo_usuario Int
)
Begin
Update ventas set
fecha_venta=sp_fecha,
total=sp_total,
estado=sp_estado,
dpi_cliente=sp_dpi_cliente,
codigo_usuario=sp_codigo_usuario
where codigo_venta=sp_id;
End$$

Create procedure sp_EliminarVenta(In sp_id Int)
Begin
Delete from ventas where codigo_venta=sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarDetalleVentas()
Begin
Select * from detalleventas;
End$$

Create procedure sp_AgregarDetalleVenta(
In sp_cantidad Int,
In sp_precio Decimal(10,2),
In sp_subtotal Decimal(10,2),
In sp_codigo_producto Int,
In sp_codigo_venta Int
)
Begin
Insert into detalleventas(cantidad,precio_unitario,subtotal,codigo_producto,codigo_venta)
values(sp_cantidad,sp_precio,sp_subtotal,sp_codigo_producto,sp_codigo_venta);
End$$

Create procedure sp_EditarDetalleVenta(
In sp_id Int,
In sp_cantidad Int,
In sp_precio Decimal(10,2),
In sp_subtotal Decimal(10,2),
In sp_codigo_producto Int,
In sp_codigo_venta Int
)
Begin
Update detalleventas set
cantidad=sp_cantidad,
precio_unitario=sp_precio,
subtotal=sp_subtotal,
codigo_producto=sp_codigo_producto,
codigo_venta=sp_codigo_venta
where codigo_detalle_venta=sp_id;
End$$

Create procedure sp_EliminarDetalleVenta(In sp_id Int)
Begin
Delete from detalleventas where codigo_detalle_venta=sp_id;
End$$

DELIMITER ;

-- Inserts
Insert into clientes(nombre_cliente,apellido_cliente,direccion,estado) values
('Juan','Pérez','Zona 1, Guatemala','1'),
('María','López','Zona 5, Guatemala','1'),
('Carlos','Ramírez','Mixco, Guatemala','1'),
('Ana','González','Villa Nueva, Guatemala','1'),
('Luis','Martínez','Zona 10, Guatemala','1'),
('Sofía','Hernández','Antigua Guatemala','1'),
('José','Castillo','Zona 18, Guatemala','1'),
('Carmen','Ortiz','Amatitlán, Guatemala','1'),
('Miguel','Ruiz','Zona 7, Guatemala','1'),
('Paola','Morales','San Miguel Petapa','1');

Insert into usuarios(username,pasword,rol) values
('admin','4321','ADMIN'),
('user1','pass1','USER'),
('user2','pass2','USER'),
('user3','pass3','USER'),
('user4','pass4','USER'),
('user5','pass5','USER'),
('user7','pass7','USER'),
('user8','pass8','USER'),
('user9','pass9','USER'),
('user10','pass10','USER');


Insert into productos(nombre_producto,precio,stock,estado) values
('Laptop Lenovo',5500.00,15,1),
('Mouse Logitech',150.00,50,1),
('Teclado Mecánico',350.00,30,1),
('Monitor Samsung',2200.00,20,1),
('Impresora HP',1800.00,10,1),
('Celular Xiaomi',3200.00,25,1),
('Tablet Huawei',2800.00,18,1),
('Auriculares Sony',700.00,40,1),
('Disco Duro 1TB',900.00,35,1),
('Memoria USB 32GB',120.00,100,1);

Insert into ventas(fecha_venta,total,estado,dpi_cliente,codigo_usuario) values
('2026-04-01',5650.00,1,1,2),
('2026-04-02',350.00,1,2,3),
('2026-04-03',2200.00,1,3,4),
('2026-04-04',1800.00,1,4,5),
('2026-04-05',3200.00,1,5,6),
('2026-04-06',2800.00,1,6,7),
('2026-04-07',700.00,1,7,8),
('2026-04-08',900.00,1,8,9),
('2026-04-09',120.00,1,9,10),
('2026-04-10',150.00,1,10,2);

Insert into detalleventas(cantidad,precio_unitario,subtotal,codigo_producto,codigo_venta) values
(1,5500.00,5500.00,1,1),
(1,150.00,150.00,2,1),
(1,350.00,350.00,3,2),
(1,2200.00,2200.00,4,3),
(1,1800.00,1800.00,5,4),
(1,3200.00,3200.00,6,5),
(1,2800.00,2800.00,7,6),
(1,700.00,700.00,8,7),
(1,900.00,900.00,9,8),
(1,120.00,120.00,10,9);
