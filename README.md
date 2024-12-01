# PROYECTO INVENTORY
# Proyecto Inventory - Módulo Category 📦

La siguiente información corresponde al apartado **Categoría** de la aplicación. Se implementaron las interfaces de creación y edición, lista y consulta de detalles de las mismas.

---

## 📋 Cambios implementados

### 1. **Clase `Category`**
La clase `Category` representa una categoría del inventario.

---

### 2. **Vista: Lista de Categorías (`CategoryList`)**
Esta vista muestra todas las categorías existentes en una lista con desplazamiento vertical (scroll). Los usuarios podrán seleccionar cualquier categoría para ver sus detalles. Además cada elemento de la lista tendrá una imagen propia al tipo de categoría.

---

### 3. **Vista: Crear Categoría (`CategoryCreate`)**
Esta vista permite a los usuarios añadir nuevas categorías al inventario mediante un formulario con campos obligatorios, un menú desplegable , un checkbox, una fecha de creación y un botón Guardar.

---

### 4. **Vista: Editar Categoría (`CategoryEdit`)**
Esta vista permite a los usuarios añadir nuevas categorías al inventario mediante un formulario con campos obligatorios, un menú desplegable , un checkbox, una fecha de creación y un botón Actualizar para guardar los cambios.

---

### 5. **Vista: Detalles de Categoría**
Muestra información detallada de una categoría específica seleccionada en la lista.

---

# Proyecto Inventory - Módulo Product 📦

La siguiente información corresponde al apartado **Producto** de la aplicación. Se implementaron las interfaces de creación y edición, lista y consulta de detalles de las mismas.

---

### 1. **Clase `Producto`**
La clase `Producto` representa un producto del inventario. 

---

### 2. **Vista: Lista de Productos (`ProductList`)**
Esta vista muestra todas los productos existentes en una lista con desplazamiento vertical (scroll). Los usuarios podrán seleccionar cualquier categoría para ver sus detalles. Además cada elemento de la lista tendrá una imagen propia al tipo de productos con un AppBar con el titulo siendo el nombre del inventario, tres _ActionButton_ uno para ver la cuenta, otro para filtrar los productos y otro para buscar un producto, y un _FloatingButton_ para poder añadir un nuevo producto.

---

### 3. **Vista: Crear Producto (`ProductCreate`)**
Añade un AppBar con el titulo _Crear producto_. Tambien contiene un Card donde esta todas las propiedades necesarias de producto, todas añadidas como _TextField_, algunas estan añadidas como _DropdownMenu_ y las fechas como _DateField_, ademas de un _Button_ para crear el producto.

---

### 4. **Vista: Editar Producto (`ProductEdit`)**
Añade un AppBar con el titulo _Editar producto_. implementa lo mismo que **Crear producto** pero _Code_ no se puede editar por que es un _TextField_ de solo lectura.

---

### 5. **Vista: Detalles de Categoría**
Añade un AppBar con el titulo siendo el nombre del producto y un _ActionButton_ para poder editar. Añade lo mismo que **Crear producto** pero todos son _TextField_ de solo lectura.

---

---

## BASES


### BASECARDS

-Tiene el composable **CardRow** que crea una tarjeta con un _Row_ para mostrar el contenido de forma horizontal

---

### BASEDATE

-Tiene el composable **DateField** que crea una _TextField_ de solo lectura que activa un **dialog**

-Tiene el composable **DialogDate** que crea una _Dialog_ que muestra un **DatePicker**

---

### BaseDropdownMenu

-Tiene el composable **BaseDropdownMenu** que crea una _TextField_ de solo lectura que al clickar muestra _DropdownMenuItem_ con opciones dadas a través de una lista.

---

### BaseImage

-Tiene el composable **BaseImageBig** que crea una _Image_ con un tamaño grande y con forma redonda.

-Tiene el composable **BaseImageMedium** que crea una _Image_ con un tamaño mediana y con forma redonda.

-Tiene el composable **BaseImageSmall** que crea una _Image_ con un tamaño pequeña y con forma redonda.

---

### BaseStructure

-Tiene el composable **BaseStructureCompletePadding** que crea un _Column_ dentro de un _Box_ con padding en todas las direcciones.

-Tiene el composable **BaseStructureCompletePaddingUpSide** que crea un _Column_ dentro de un _Box_ con padding en arriba y a los lados.

-Tiene el composable **BaseStructureCompBaseStructureCompletePaddingUpSideletePadding** que crea un _Column_ con padding en arriba y a los lados.

-Tiene el composable **BaseStructureCollumnPadding** que crea un _Column_ con padding en todas las direcciones.

-Tiene el composable **BaseRow** que crea un _Row_ con padding en todas las direcciones.

---

### BaseText

-Tiene el composable **BaseText** que crea un _Text_ simple.

---

### BaseTextField

-Tiene el composable **BaseTextField** que crea un _TextField_ de una sola linea.

-Tiene el composable **BaseTextFieldRead** que crea un _TextField_ de una sola linea de solo lectura.

---

### TopAppBar

-Tiene el composable **TopAppBarTitle** que crea un _Scaffold_ con un **topBar** y un **navigationIcon** para volver atras.

-Tiene el composable **TopAppBarFloating** que crea un _Scaffold_ con un **topBar**, un **navigationIcon** para volver atras y un **floatingButton**.

-Tiene el composable **TopAppBarOneAction** que crea un _Scaffold_ con un **topBar**, un **navigationIcon** para volver atras y un **actionButton**.

-Tiene el composable **TopAppBarComplete** que crea un _Scaffold_ con un **topBar**, un **navigationIcon** para volver atras, un **floatingButton** y tres **actionButton**.

---

---

## 📚 Tecnologías Utilizadas

- **Lenguaje:** Kotlin

---

## 📄 Notas Adicionales

---

## Licencia
