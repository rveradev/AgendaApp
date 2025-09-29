# Agenda Personal App

Este proyecto es un prototipo de una **Agenda Personal** desarrollado en **Android Studio con Kotlin**. El objetivo es demostrar la interoperabilidad Kotlin-Java y la implementación.

## Requisitos Funcionales Implementados

1.  **Registro de Eventos:** Permite ingresar eventos con Título, Fecha (usando un Material Date Picker) y Descripción (opcional).
2.  **Visualización de Eventos:** Muestra la lista de eventos en la vista principal (`MainActivity`) usando un `RecyclerView`.
3.  **Filtrado y Ordenamiento:** La lista puede ordenarse por **Título** y por **Fecha**.
4.  **Detalle de Evento:** Al hacer clic en un evento, se abre una nueva vista (`EventDetailActivity`) con la información completa.


## Arquitectura y Componentes Clave

El proyecto sigue un patrón de **Separación de Responsabilidades** (similar a MVVM simplificado), utilizando un `object` Singleton para gestionar los datos.

| Componente | Rol Principal | Características Aplicadas |
| :--- | :--- | :--- |
| **`MainActivity.kt`** | **Vista/UI**. Muestra la lista y maneja el `Spinner` de ordenamiento. | `View Binding`, `lateinit`, Lambdas como Listeners, `with`, `run`. |
| **`EventoRepository.kt`** | **Datos y Lógica Simple**. Maneja la lista de eventos, el *sorting* y el *filtering*. | `object` (Singleton), `List`, `MutableList`, `sortedBy`, `Null Safety`. |
| **`Evento.kt`** | **Modelo de Datos**. | `data class`, `val` |
| **`IdentificadorUnico.java`**| **Utilidad Java**. Genera UUIDs. | **Interoperabilidad Kotlin-Java** |

## Integración y Ejecución

### Habilitar View Binding

La característica **View Binding** se habilitó en el archivo `build.gradle` (Módulo: app) para sustituir el uso de `findViewById`.

```gradle
android {
    buildFeatures {
        viewBinding true
    }
}
```

### Ejecución desde Terminal

Para construir e instalar la aplicación en un dispositivo o emulador conectado, se utiliza el siguiente comando de Gradle:

```bash
./gradlew installDebug
```