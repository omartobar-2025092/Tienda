const BASE = 'http://localhost:8080'
const ES_ADMIN = (typeof ROL_USUARIO !== 'undefined') && ROL_USUARIO === 'ADMIN'

function botones(edit, del) {
  if (ES_ADMIN) {
    return `<button class="btn-edit" onclick="${edit}">Editar</button><button class="btn-del" onclick="${del}">Borrar</button>`
  }
  return `<button class="btn-edit" onclick="${edit}">Editar</button>`
}


async function api(method, url, data) {
  const res = await fetch(BASE + url, {
    method: method,
    headers: { 'Content-Type': 'application/json' },
    body: data ? JSON.stringify(data) : null
  })
  if (!res.ok) { const msg = await res.text(); throw new Error(msg) }
  const ct = res.headers.get('content-type')
  return ct && ct.includes('application/json') ? res.json() : res.text()
}

function showTab(name, btn) {
  document.querySelectorAll('.section').forEach(s => s.classList.remove('active'))
  document.querySelectorAll('nav button').forEach(b => b.classList.remove('active'))
  document.getElementById('tab-' + name).classList.add('active')
  btn.classList.add('active')
}

function estadoTexto(estado) { return estado == 1 ? 'Activo' : 'Inactivo' }

function limpiar(ids) {
  ids.forEach(id => { let el = document.getElementById(id); if (el) el.value = '' })
}

//
// CLIENTES — PK: dpiCliente | campos: nombreCliente, apellidoCliente, direccion, estado
//

async function loadClientes() {
  const tbody = document.getElementById('tbody-clientes')
  tbody.innerHTML = '<tr><td colspan="6">Cargando...</td></tr>'
  try {
    const data = await api('GET', '/clientes')
    if (!data.length) { tbody.innerHTML = '<tr><td colspan="6">No hay clientes</td></tr>'; return }
    tbody.innerHTML = data.map(c => `
      <tr>
        <td>${c.dpiCliente}</td><td>${c.nombreCliente}</td><td>${c.apellidoCliente}</td>
        <td>${c.direccion}</td><td>${estadoTexto(c.estado)}</td>
        <td>${botones(`editCliente(${c.dpiCliente},'${c.nombreCliente}','${c.apellidoCliente}','${c.direccion}',${c.estado})`,`deleteCliente(${c.dpiCliente})`)}</td>
      </tr>`).join('')
  } catch { tbody.innerHTML = '<tr><td colspan="6">Error al cargar</td></tr>' }
}

async function saveCliente() {
  const id = document.getElementById('cliente-id').value
  const data = {
    dpiCliente: parseInt(document.getElementById('cliente-dpi').value),
    nombreCliente: document.getElementById('cliente-nombre').value,
    apellidoCliente: document.getElementById('cliente-apellido').value,
    direccion: document.getElementById('cliente-direccion').value,
    estado: parseInt(document.getElementById('cliente-estado').value)
  }
  try {
    if (id) { await api('PUT', '/clientes/' + id, data); alert('Actualizado') }
    else { await api('POST', '/clientes', data); alert('Guardado') }
    cancelCliente(); loadClientes()
  } catch(e) { alert('Error: ' + e.message) }
}

function editCliente(id, nombre, apellido, direccion, estado) {
  document.getElementById('cliente-id').value = id
  document.getElementById('cliente-dpi').value = id
  document.getElementById('cliente-nombre').value = nombre
  document.getElementById('cliente-apellido').value = apellido
  document.getElementById('cliente-direccion').value = direccion
  document.getElementById('cliente-estado').value = estado
}

function cancelCliente() { limpiar(['cliente-id','cliente-dpi','cliente-nombre','cliente-apellido','cliente-direccion']) }

async function deleteCliente(id) {
  if (!confirm('¿Borrar cliente?')) return
  try { await api('DELETE', '/clientes/' + id); loadClientes() }
  catch(e) { alert('Error: ' + e.message) }
}

//
// PRODUCTOS — PK: codigoProducto | campos: nombreProducto, precio, stock, estado
//

async function loadProductos() {
  const tbody = document.getElementById('tbody-productos')
  tbody.innerHTML = '<tr><td colspan="6">Cargando...</td></tr>'
  try {
    const data = await api('GET', '/productos')
    if (!data.length) { tbody.innerHTML = '<tr><td colspan="6">No hay productos</td></tr>'; return }
    tbody.innerHTML = data.map(p => `
      <tr>
        <td>${p.codigoProducto}</td><td>${p.nombreProducto}</td><td>Q ${p.precio}</td>
        <td>${p.stock}</td><td>${estadoTexto(p.estado)}</td>
        <td>${botones(`editProducto(${p.codigoProducto},'${p.nombreProducto}',${p.precio},${p.stock},${p.estado})`,`deleteProducto(${p.codigoProducto})`)}</td>
      </tr>`).join('')
  } catch { tbody.innerHTML = '<tr><td colspan="6">Error al cargar</td></tr>' }
}

async function saveProducto() {
  const id = document.getElementById('producto-id').value
  const data = {
    nombreProducto: document.getElementById('producto-nombre').value,
    precio: parseFloat(document.getElementById('producto-precio').value),
    stock: parseInt(document.getElementById('producto-stock').value),
    estado: parseInt(document.getElementById('producto-estado').value)
  }
  try {
    if (id) { await api('PUT', '/productos/' + id, data); alert('Actualizado') }
    else { await api('POST', '/productos', data); alert('Guardado') }
    cancelProducto(); loadProductos()
  } catch(e) { alert('Error: ' + e.message) }
}

function editProducto(id, nombre, precio, stock, estado) {
  document.getElementById('producto-id').value = id
  document.getElementById('producto-nombre').value = nombre
  document.getElementById('producto-precio').value = precio
  document.getElementById('producto-stock').value = stock
  document.getElementById('producto-estado').value = estado
}

function cancelProducto() { limpiar(['producto-id','producto-nombre','producto-precio','producto-stock']) }

async function deleteProducto(id) {
  if (!confirm('¿Borrar producto?')) return
  try { await api('DELETE', '/productos/' + id); loadProductos() }
  catch(e) { alert('Error: ' + e.message) }
}

//
// USUARIOS — PK: codigoUsuario | campos: username, pasword
//

async function loadUsuarios() {
  const tbody = document.getElementById('tbody-usuarios')
  tbody.innerHTML = '<tr><td colspan="6">Cargando...</td></tr>'
  try {
    const data = await api('GET', '/usuarios')
    if (!data.length) { tbody.innerHTML = '<tr><td colspan="6">No hay usuarios</td></tr>'; return }
        tbody.innerHTML = data.map(u => `
        <tr>
            <td>${u.codigoUsuario}</td>
            <td>${u.username}</td>
            <td>${botones(`editUsuario(${u.codigoUsuario},'${u.username}')`,`deleteUsuario(${u.codigoUsuario})`)}</td>
            </tr>`).join('')
  } catch { tbody.innerHTML = '<tr><td colspan="6">Error al cargar</td></tr>' }
}

async function saveUsuario() {
  const id = document.getElementById('usuario-id').value
  const data = {
    username: document.getElementById('usuario-username').value
  }
  try {
    if (id) { await api('PUT', '/usuarios/' + id, data); alert('Actualizado') }
    else { await api('POST', '/usuarios', data); alert('Guardado') }
    cancelUsuario(); loadUsuarios()
  } catch(e) { alert('Error: ' + e.message) }
}

function editUsuario(id, username) {
  document.getElementById('usuario-id').value = id
  document.getElementById('usuario-username').value = username
}

function cancelUsuario() { limpiar(['usuario-id','usuario-username','usuario-pass']) }

async function deleteUsuario(id) {
  if (!confirm('¿Borrar usuario?')) return
  try { await api('DELETE', '/usuarios/' + id); loadUsuarios() }
  catch(e) { alert('Error: ' + e.message) }
}

//
// VENTAS — PK: codigoVenta | campos: dpiCliente, codigoUsuario, total, fechaVenta, estado
//

async function loadVentas() {
  const tbody = document.getElementById('tbody-ventas')
  tbody.innerHTML = '<tr><td colspan="6">Cargando...</td></tr>'
  try {
    const data = await api('GET', '/ventas')
    if (!data.length) { tbody.innerHTML = '<tr><td colspan="6">No hay ventas</td></tr>'; return }
    tbody.innerHTML = data.map(v => `
      <tr>
        <td>${v.codigoVenta}</td><td>${v.dpiCliente}</td><td>${v.codigoUsuario}</td>
        <td>${v.fechaVenta ?? ''}</td><td>Q ${v.total}</td>
        <td>${botones(`editVenta(${v.codigoVenta},${v.dpiCliente},${v.codigoUsuario},${v.total},'${v.fechaVenta ?? ''}',${v.estado})`,`deleteVenta(${v.codigoVenta})`)}</td>
      </tr>`).join('')
  } catch { tbody.innerHTML = '<tr><td colspan="6">Error al cargar</td></tr>' }
}

async function saveVenta() {
  const id = document.getElementById('venta-id').value
  const data = {
    dpiCliente: parseInt(document.getElementById('venta-cliente').value),
    codigoUsuario: parseInt(document.getElementById('venta-usuario').value),
    total: parseFloat(document.getElementById('venta-total').value),
    fechaVenta: document.getElementById('venta-fecha').value || null,
    estado: parseInt(document.getElementById('venta-estado').value)
  }
  try {
    if (id) { await api('PUT', '/ventas/' + id, data); alert('Actualizado') }
    else { await api('POST', '/ventas', data); alert('Guardado') }
    cancelVenta(); loadVentas()
  } catch(e) { alert('Error: ' + e.message) }
}

function editVenta(id, cliente, usuario, total, fecha, estado) {
  document.getElementById('venta-id').value = id
  document.getElementById('venta-cliente').value = cliente
  document.getElementById('venta-usuario').value = usuario
  document.getElementById('venta-total').value = total
  document.getElementById('venta-fecha').value = fecha
  document.getElementById('venta-estado').value = estado
}

function cancelVenta() { limpiar(['venta-id','venta-cliente','venta-usuario','venta-total','venta-fecha']) }

async function deleteVenta(id) {
  if (!confirm('¿Borrar venta?')) return
  try { await api('DELETE', '/ventas/' + id); loadVentas() }
  catch(e) { alert('Error: ' + e.message) }
}

//
// DETALLES — PK: codigoDetalleVenta | campos: codigoVenta, codigoProducto, cantidad, precioUnitario, subtotal
//

async function loadDetalles() {
  const tbody = document.getElementById('tbody-detalles')
  tbody.innerHTML = '<tr><td colspan="7">Cargando...</td></tr>'
  try {
    const data = await api('GET', '/detalleventas')
    if (!data.length) { tbody.innerHTML = '<tr><td colspan="7">No hay detalles</td></tr>'; return }
    tbody.innerHTML = data.map(d => `
      <tr>
        <td>${d.codigoDetalleVenta}</td><td>${d.codigoVenta}</td><td>${d.codigoProducto}</td>
        <td>${d.cantidad}</td><td>Q ${d.precioUnitario}</td><td>Q ${d.subtotal}</td>
        <td>${botones(`editDetalle(${d.codigoDetalleVenta},${d.codigoVenta},${d.codigoProducto},${d.cantidad},${d.precioUnitario},${d.subtotal})`,`deleteDetalle(${d.codigoDetalleVenta})`)}</td>
      </tr>`).join('')
  } catch { tbody.innerHTML = '<tr><td colspan="7">Error al cargar</td></tr>' }
}

async function saveDetalle() {
  const id = document.getElementById('detalle-id').value
  const data = {
    codigoVenta: parseInt(document.getElementById('detalle-venta').value),
    codigoProducto: parseInt(document.getElementById('detalle-producto').value),
    cantidad: parseInt(document.getElementById('detalle-cantidad').value),
    precioUnitario: parseFloat(document.getElementById('detalle-precio').value),
    subtotal: parseFloat(document.getElementById('detalle-subtotal').value)
  }
  try {
    if (id) { await api('PUT', '/detalleventas/' + id, data); alert('Actualizado') }
    else { await api('POST', '/detalleventas', data); alert('Guardado') }
    cancelDetalle(); loadDetalles()
  } catch(e) { alert('Error: ' + e.message) }
}

function editDetalle(id, venta, producto, cantidad, precio, subtotal) {
  document.getElementById('detalle-id').value = id
  document.getElementById('detalle-venta').value = venta
  document.getElementById('detalle-producto').value = producto
  document.getElementById('detalle-cantidad').value = cantidad
  document.getElementById('detalle-precio').value = precio
  document.getElementById('detalle-subtotal').value = subtotal
}

function cancelDetalle() { limpiar(['detalle-id','detalle-venta','detalle-producto','detalle-cantidad','detalle-precio','detalle-subtotal']) }

async function deleteDetalle(id) {
  if (!confirm('¿Borrar detalle?')) return
  try { await api('DELETE', '/detalleventas/' + id); loadDetalles() }
  catch(e) { alert('Error: ' + e.message) }
}

// INIT
loadClientes()
loadProductos()
loadUsuarios()
loadVentas()
loadDetalles()