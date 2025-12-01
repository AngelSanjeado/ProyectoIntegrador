🎫 Sistema de Ticketing de Incidencias - LAB LIS
Proyecto final para la materia de Diseño y Programación Orientada a Objetos.

Este sistema es una aplicación de consola desarrollada en Java diseñada para gestionar, rastrear y resolver fallas técnicas en los equipos de cómputo del Laboratorio (LAB LIS). Permite la interacción entre usuarios que reportan fallas y el personal técnico encargado de resolverlas.


🚀 Características Principales
El sistema cumple con el ciclo de vida completo de una incidencia, incluyendo:


Reporte de Incidencias: Los usuarios pueden reportar fallas clasificándolas por tipo (Hardware, Software, Red).

Generación de Folios: Creación automática de IDs únicos (ej. HW-001, RED-005) basados en el tipo de falla.

Roles de Usuario:

Usuario: Reporta fallas indicando su matrícula, descripción y ubicación (Salón).

Técnico: Visualiza sus tareas asignadas y actualiza el estado (En Proceso, Terminada).

Gerente (Admin): Rol especial con permisos para asignar prioridades y distribuir el trabajo.

Algoritmo de Asignación Inteligente: El sistema asigna automáticamente las incidencias a los técnicos basándose en dos criterios:

Especialidad: Un fallo de Red solo se asigna a un técnico de Red.

Nivel de Experiencia (Match de Prioridad):

Prioridad BAJA → Técnicos JUNIOR.

Prioridad MEDIA → Técnicos SEMI-SENIOR.

Prioridad ALTA → Técnicos SENIOR.

Gestión de Estados: Control de flujo desde SIN_ASIGNAR → ASIGNADA → EN_PROCESO → TERMINADA.

🛠️ Tecnologías y Conceptos Aplicados
Lenguaje: Java (JDK 8+)

Paradigma: Programación Orientada a Objetos (POO).

Conceptos Clave:

Herencia y Polimorfismo: Uso de clase abstracta Persona para Tecnico y Usuario.

Encapsulamiento: Protección de datos sensibles mediante modificadores de acceso y Getters/Setters.

Abstracción: Modelado de clases como Incidencia y GestionIncidencia.

Genéricos: Implementación de métodos de búsqueda reutilizables (<T extends Persona>).

Enums: Para el control estricto de tipos (TipoIncidencia, NivelTecnico, EstadoIncidencia, Salon).

📋 Estructura del Proyecto
Main.java: Punto de entrada y menús del sistema.

GestionIncidencia.java: Controlador principal ("Cerebro" del sistema). Maneja las listas y la lógica de negocio.

Incidencia.java: Modelo del ticket con contadores estáticos para IDs.

Tecnico.java: Extiende de Persona. Incluye especialidad, nivel y lista de tareas propias.

Usuario.java: Extiende de Persona. Representa al reportante.

Persona.java: Clase padre abstracta.

Enums: EstadoIncidencia, NivelTecnico, Prioridad, Salon, TipoIncidencia.

💻 Instrucciones de Ejecución
Para correr el proyecto desde la terminal, sigue estos pasos:

Clonar o Descargar el proyecto.

Compilar: Navega a la carpeta del código fuente (src) y ejecuta:

Bash

javac Main.java
Ejecutar:

Bash

java Main
🔐 Credenciales de Prueba
El sistema inicia con datos precargados para facilitar las pruebas. Utiliza las siguientes matrículas para iniciar sesión en el menú de técnicos:

1. Rol: Gerente (Administrador)
Matrícula: TG100 (Antonio)

Permisos: Asignar prioridad y ejecutar la asignación automática.

2. Rol: Técnico Senior (Hardware)
Matrícula: TH201 (Teresa)

Permisos: Resolver incidencias de prioridad ALTA.

3. Rol: Técnico Semi-Senior (Hardware)
Matrícula: TH202 (Omar)

Permisos: Resolver incidencias de prioridad MEDIA.

4. Rol: Técnico Junior (Hardware)
Matrícula: TH203 (Katia)

Permisos: Resolver incidencias de prioridad BAJA.


Facultad de Contaduría y Administración, UV Coatzacoalcos
