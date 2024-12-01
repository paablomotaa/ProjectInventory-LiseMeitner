# PROYECTO INVENTORY
# Proyecto Inventory - Módulo Category 📦

La siguiente información corresponde al apartado **Categoría** de la aplicación. Se implementaron las interfaces de creación y edición, lista y consulta de detalles de las mismas.

---

## 📋 Cambios implementados

### 1. **Clase `Category`**
La clase `Category` representa una categoría del inventario. Incluye los siguientes atributos:
- **id (String):** Identificador único de la categoría.
- **name (String):** Nombre completo de la categoría.
- **shortName (String):** Nombre abreviado de la categoría.
- **description (String):** Descripción detallada de la categoría.
- **imageUrl (String):** URL de la imagen asociada con la categoría.
- **createdDate (Date):** Fecha en la que se creó la categoría.
- **type (CategoryType):** Tipo de categoría, definido por un `enum` que puede ser: `BASICO`, `ECONOMICO`, `ECOLOGICO` o `PREMIUM`. Por defecto es `BASICO`.
- **isFungible (Boolean):** Indica si los productos de esta categoría son fungibles o no (por defecto es `false`).

### 2. **Vista: Lista de Categorías (`CategoryList`)**
Esta vista muestra todas las categorías existentes en una lista con desplazamiento vertical (scroll). Los usuarios podrán seleccionar cualquier categoría para ver sus detalles. Además cada elemento de la lista tendrá una imagen propia al tipo de categoría.

- **Diseño:** Una lista en con scroll vertical que se adapta al número de categorías creadas.
- **Interacción:** Al hacer clic en una categoría, se abrirá una pantalla con más información. Desde ella también podrán acceder a la edición de la categoría pulsando el icono de edición.

---

### 3. **Vista: Crear Categoría (`CategoryCreate`)**
Esta vista permitirá a los usuarios añadir nuevas categorías al inventario. Incluye:
- Un formulario con los campos obligatorios: **Nombre**, **Nombre corto** y **Descripción**.
- Un menú desplegable con los cuatro tipo de categorías.
- Un check box para confirmar si es o no fungible.
- Fecha de solo lectura con el día de creación de la categoría.
- Botón **Guardar** para salvar los cambios

---

### 4. **Vista: Editar Categoría (`CategoryEdit`)**
Esta vista permitirá a los usuarios editar categorías ya creadas y borrarlas. Incluye:
- Un formulario con los campos obligatorios: **Nombre**, **Nombre corto** y **Descripción**.
- Un menú desplegable con los cuatro tipo de categorías.
- Un check box para confirmar si es o no fungible.
- Fecha de solo lectura con el día de creación de la categoría.
- Botón **Actualizar** para salvar los cambios

---

### 5. **Vista: Detalles de Categoría**
Muestra información detallada de una categoría específica seleccionada en la lista. Los detalles incluyen:
- Imagen de la categoría.
- Nombre de la categoría.
- Nombre corto de la categoría.
- Descripción.
- Fecha de creación.
- Tipo de categoría.
- Si es fungible o no

- **Interacción:** Desde esta pantalla, los usuarios podrán regresar a la lista principal.

---

## 📚 Tecnologías Utilizadas

- **Lenguaje:** Kotlin

---

## 📄 Notas Adicionales

---

## Licencia
