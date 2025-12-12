# Motor de Búsqueda Musical - Proyecto de Estructura de Datos

### Descripción del Proyecto
Este proyecto fue desarrollado como parte de la evaluación del Tema 6: "Funciones Hash". Simula el backend de una **Plataforma de Streaming** que necesita optimizar sus tiempos de respuesta.

**El Problema:**
La plataforma cuenta con un catálogo masivo de canciones (simulado con millones de registros). El sistema original utilizaba una búsqueda lineal ineficiente, provocando lentitud y quejas de los usuarios.

**La Solución:**
Implementamos y comparamos tres algoritmos de búsqueda para encontrar canciones por su código único **ISRC** (International Standard Recording Code):
1.  **Búsqueda Secuencial** (El método antiguo).
2.  **Búsqueda Binaria** (Optimizada con ordenamiento previo).
3.  **Búsqueda Hash** (La solución propuesta, usando Tablas Hash).

---

### Equipo de Desarrollo
| Integrante | Rol | Tareas Principales |
|------------|-----|--------------------|
| **David Mastache** | Project Leader | Arquitectura, Modelo `Cancion`, Coordinación GitHub. |
| **Issac Sánchez** | Developer | Lógica de algoritmos de búsqueda y pruebas de integridad. |
| **Raúl** | Developer | Implementación del `Main`, generación masiva de datos y métricas. |
| **Alexia** | Analyst | Planteamiento del problema, documentación y análisis de resultados. |

---

###  Estructura del Código
El proyecto sigue una arquitectura limpia separada por paquetes:

```text
src/
├── modelo/
│   └── Cancion.java       <-- Objeto base (Atributos: ISRC, Título, Artista, etc.)
│                              Incluye @Override de hashCode() y equals().
│
├── logica/
│   └── Busqueda.java      <-- Contiene los 3 métodos estáticos:
│                              1. secuencial()
│                              2. binaria()
│                              3. hash()
│
└── app/
    └── Main.java          <-- "El Duelo": Genera 2M+ de canciones y cronometra 
                               los tiempos de respuesta en nanosegundos.
