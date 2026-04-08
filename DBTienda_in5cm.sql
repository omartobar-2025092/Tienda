Drop database if exists DBTienda_in5cm;
Create database DBTienda_in5cm;
Use DBTienda_in5cm;

-- Tablas --

Create Table Clientes(
dpi_cliente Int auto_increment not null,
nombre_cliente Varchar (50) not null,
apellido_cliente Varchar (50) not null,
direccion Varchar(100) not null,
estado Int not null,
primary	key (dpi_cliente)
);

Create Table Usuarios(
codigo_usuario Int auto_increment not null,
username Varchar (45) not null,
pasword Varchar (45) not null,
email Varchar (60) not null,
rol Varchar(45) not null,
estado Int not null,
primary	key (codigo_usuario)
);

Create Table Ventas (
codigo_venta Int auto_increment not null,
fecha_venta Date not null,
total Decimal (10,2) not null,
estado Int not null,
dpi_cliente Int,
codigo_usuario Int,
primary key (codigo_venta),
constraint fk_dpi_cliente foreign key (dpi_cliente) references Clientes(dpi_cliente),
constraint fk_codigo_usuario foreign key (codigo_usuario) references Usuarios(codigo_usuario)
);

Create Table Productos(
codigo_producto Int auto_increment not null,
nombre_producto Varchar (50) not null,
precio Decimal (10,2) not null,
stock Int not null,
estado Int not null,
primary key (codigo_producto)
);

Create Table DetalleVentas (
codigo_detalle_venta Int auto_increment not null,
cantidad Int not null,
precio_unitario Decimal (10,2) not null,
subtotal Decimal (10,2) not null,
codigo_producto Int,
codigo_venta Int,
primary key (codigo_detalle_venta),
constraint fk_codigo_producto foreign key (codigo_producto) references Productos(codigo_producto),
constraint fk_codigo_venta foreign key (codigo_venta) references Ventas(codigo_venta)
);

-- Procedimientos Almacenados --
DELIMITER $$

Create procedure sp_ListarClientes()
Begin
	Select * from Clientes;
End$$

Create procedure sp_AgregarCliente(
	In sp_nombre Varchar(50),
	In sp_apellido Varchar(50),
	In sp_direccion Varchar(100),
	In sp_estado Int
)
Begin
	Insert into Clientes(nombre_cliente, apellido_cliente, direccion, estado)
	values(sp_nombre, sp_apellido, sp_direccion, sp_estado);
End$$

Create procedure sp_EditarCliente(
	In sp_id Int,
	In sp_nombre Varchar(50),
	In sp_apellido Varchar(50),
	In sp_direccion Varchar(100),
	In sp_estado Int
)
Begin
	Update Clientes
	set nombre_cliente = sp_nombre,
		apellido_cliente = sp_apellido,
		direccion = sp_direccion,
		estado = sp_estado
	where dpi_cliente = sp_id;
End$$

Create procedure sp_EliminarCliente(
	In sp_id Int
)
Begin
	Delete from Clientes where dpi_cliente = sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarUsuarios()
Begin
	Select * from Usuarios;
End$$

Create procedure sp_AgregarUsuario(
	In sp_username Varchar(45),
	In sp_pasword Varchar(45),
	In sp_email Varchar(60),
	In sp_rol Varchar(45),
	In sp_estado Int
)
Begin
	Insert into Usuarios(username, pasword, email, rol, estado)
	values(sp_username, sp_pasword, sp_email, sp_rol, sp_estado);
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
	Update Usuarios
	set username = sp_username,
		pasword = sp_pasword,
		email = sp_email,
		rol = sp_rol,
		estado = sp_estado
	where codigo_usuario = sp_id;
End$$

Create procedure sp_EliminarUsuario(
	In sp_id Int
)
Begin
	Delete from Usuarios where codigo_usuario = sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarProductos()
Begin
	Select * from Productos;
End$$

Create procedure sp_AgregarProducto(
	In sp_nombre Varchar(50),
	In sp_precio Decimal(10,2),
	In sp_stock Int,
	In sp_estado Int
)
Begin
	Insert into Productos(nombre_producto, precio, stock, estado)
	values(sp_nombre, sp_precio, sp_stock, sp_estado);
End$$

Create procedure sp_EditarProducto(
	In sp_id Int,
	In sp_nombre Varchar(50),
	In sp_precio Decimal(10,2),
	In sp_stock Int,
	In sp_estado Int
)
Begin
	Update Productos
	set nombre_producto = sp_nombre,
		precio = sp_precio,
		stock = sp_stock,
		estado = sp_estado
	where codigo_producto = sp_id;
End$$

Create procedure sp_EliminarProducto(
	In sp_id Int
)
Begin
	Delete from Productos where codigo_producto = sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarVentas()
Begin
	Select * from Ventas;
End$$

Create procedure sp_AgregarVenta(
	In sp_fecha Date,
	In sp_total Decimal(10,2),
	In sp_estado Int,
	In sp_dpi_cliente Int,
	In sp_codigo_usuario Int
)
Begin
	Insert into Ventas(fecha_venta, total, estado, dpi_cliente, codigo_usuario)
	values(sp_fecha, sp_total, sp_estado, sp_dpi_cliente, sp_codigo_usuario);
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
	Update Ventas
	set fecha_venta = sp_fecha,
		total = sp_total,
		estado = sp_estado,
		dpi_cliente = sp_dpi_cliente,
		codigo_usuario = sp_codigo_usuario
	where codigo_venta = sp_id;
End$$

Create procedure sp_EliminarVenta(
	In sp_id Int
)
Begin
	Delete from Ventas where codigo_venta = sp_id;
End$$

DELIMITER ;

DELIMITER $$

Create procedure sp_ListarDetalleVentas()
Begin
	Select * from DetalleVentas;
End$$

Create procedure sp_AgregarDetalleVenta(
	In sp_cantidad Int,
	In sp_precio Decimal(10,2),
	In sp_subtotal Decimal(10,2),
	In sp_codigo_producto Int,
	In sp_codigo_venta Int
)
Begin
	Insert into DetalleVentas(cantidad, precio_unitario, subtotal, codigo_producto, codigo_venta)
	values(sp_cantidad, sp_precio, sp_subtotal, sp_codigo_producto, sp_codigo_venta);
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
	Update DetalleVentas
	set cantidad = sp_cantidad,
		precio_unitario = sp_precio,
		subtotal = sp_subtotal,
		codigo_producto = sp_codigo_producto,
		codigo_venta = sp_codigo_venta
	where codigo_detalle_venta = sp_id;
End$$

Create procedure sp_EliminarDetalleVenta(
	In sp_id Int
)
Begin
	Delete from DetalleVentas where codigo_detalle_venta = sp_id;
End$$

DELIMITER ;