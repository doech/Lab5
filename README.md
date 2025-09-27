# ✨Lab5 — Jetpack Compose + Navigation + Retrofit + Corrutinas #
Ale Sierra #24405

## 📦 Estructura y archivos: ##
* data/model: define “cómo son” los datos que vienen de la API (clases de datos que reflejan el JSON).
* data/remote: concentra todo lo relacionado con red: la base URL y la interfaz de la API. Así, si cambia el proveedor o las rutas, solo se toca aquí.
* data/repository: actúa como puente entre la red y la UI. Llama a la API y adapta la respuesta para que la pantalla la use fácil (por ejemplo, calculando el id del Pokémon o armando URLs de imagen).
* ui/list y ui/detail: cada pantalla con su propio ViewModel y su estado. La UI no sabe de HTTP ni de hilos: solo pinta “loading / datos / error”.
* navigation: define rutas y el gráfico de navegación (el “mapa” de pantallas).
* MainActivity: es el punto de entrada y solo monta el NavHost dentro del tema; no hace lógica de negocio.


## 🧭 ¿Cómo usé Navigation (Compose)? ##

La app tiene dos destinos: list y detail. El NavHost vive en un único lugar y conoce estas rutas. 
Al tocar un Pokémon en la lista, se navega al detalle pasando el nombre como argumento.
En la pantalla de detalle se recupera ese argumento y se dispara la carga de la información (sprites) en su ViewModel.

## ⚙️ ¿Cómo usé corrutinas? ##

Cada ViewModel lanza corrutinas para hacer trabajo de entrada/salida sin bloquear la interfaz.
Patrón de estado por pantalla: al iniciar, se marca “cargando”; si la llamada a la API sale bien, se actualiza con los datos; si falla, se guarda un mensaje de error.
Beneficio: la UI permanece fluida, y el código para manejar el resultado es claro y secuencial (sin callbacks anidados).

## 🌐 ¿Cómo usé Retrofit para la API? ##

Se configuró una interfaz que describe los endpoints necesarios de PokeAPI (lista de Pokémon y detalle por nombre).
El repositorio llama a esos endpoints y transforma las respuestas en estructuras más útiles para la UI (por ejemplo, extrayendo el id desde la URL del Pokémon y preparando enlaces de imagen).
La UI nunca ve detalles de HTTP ni de serialización: solo recibe datos ya listos.
