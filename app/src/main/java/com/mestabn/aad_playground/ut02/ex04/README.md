# UT-02 Ejercicio 04

Desarrolla una aplicación que permita trabajar con shared preferences. Queremos persistir
información sobre clientes y facturas de clientes. Una factura está asociada a un cliente. Una
factura puede tener de 1 a N líneas de facturas. Cada línea de factura tendrá sólo un producto.

Para ello, debes implementar lo siguiente:

- La clase CustomerSharPrefLocalSource se gestionará en un fichero shared preferences (xml) **
  encriptado**.
- La clase InvoiceSharPrefLocalSource se gestionará en un fichero shared preferences (xml)
- Una clase principal (Activity) que permita probar que todo funciona correctamente.

Nota: La clase CustomerSharPrefLocalSource y InvoiceSharPrefLocalSource, y el fichero Models no pueden ser
modificadas.

## Documentación a entregar

- Hay que crear una rama llamada: ``ut02_ex04_sharedpref`` que salga de **develop**.
- Se creará una **Pull Request** con la solución propuesta añadiendo información útil.
- Me pondrá como revisor.

## Objetivo a conseguir

Desarrollar aplicaciones que gestionen información almacenada en Shared Preferences.

## Criterios de evaluación

Los siguientes criterios permitiran saber si se ha conseguido el **objetivo**

- El alumno sabe guardar datos en un fichero xml (Shared Preferences).
- El alumno sabe eliminar datos en un fichero xml (Shared Preferences).
- El alumno sabe actualizar datos en un fichero xml (Shared Preferences).
- El alumno sabe leer información de un fichero xml (Shared Preferences).
- El alumno sabe serializar.
- El alumno documento el código (Pull Request)

## Cosas a tener en cuenta

Se valorará positivamente:

- Elección de los nombres para las clases, funciones, variables.
- Uso de código optimizado en Kotlin.
- Que se pruebe a través de un Activity.
- Que se sigan los principios SOLID: responsabilidad única, abstracción de clases, etc.