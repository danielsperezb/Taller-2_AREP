# TALLER AREP #2: DISEÑO Y ESTRUCTURACIÓN DE APLICACIONES DISTRIBUIDAS EN INTERNET


En este taller se explorará la arquitectura de las aplicaciones distribuidas. Concretamente, exploraremos la arquitectura de  los servidores web y el protocolo http sobre el que están soportados. 

RETO :
Escriba un servidor web que soporte múlltiples solicitudes seguidas (no concurrentes). El servidor debe leer los archivos del disco local y retornar todos los archivos solicitados, incluyendo páginas html, archivos java script, css e imágenes. Construya una aplicación web con  javascript, css, e imágenes para probar su servidor. Incluya en la aplicación la comunicación asíncrona con unos servicios REST en el backend. NO use frameworks web como Spark o Spring, use solo Java y las librerías para manejo de la red.

## Empezando

Estas instrucciones te permitirán obtener una copia del proyecto y ejecutarlo en tu máquina local con fines de desarrollo y pruebas. Consulta la sección de implementación para obtener notas sobre cómo desplegar el proyecto en un sistema en vivo.

### Prerequisitos

¿Qué cosas necesitas instalar para el software?

```
- Un navegador (Preferencia que sea Mozilla o Chrome).
- Un IDLE como NetBeans para poder arrancar el servidor (Nota: Hay otras maneras).

![SERVIDOR](http://tucatalogo.digital/img_cvds/servidor.png)

- GitBash para clonar tu proyecto en tu Computadora.

```

### Instalacion

Una serie paso a paso de ejemplos que te indican cómo configurar un entorno de desarrollo.

```
    1. Clonar este respositorio en su computadora.
    2. Encontrara dos carpetas, server-web y client-web.
    3. Server-web puede abrirlo desde NETBEANS y arranca el main.java
    4. Abra el cliente: 
        4.1. Si tiene una extension en Visual Studio code puede usarla para abrir un puerto y consultar la pagina de la siguiente manera. Por Ejemplo: http://127.0.0.1:5500/index.html
        4.2 Mas sencillo, simplemente abra el index.html en Mozilla o Chrome.

```


## Ejecución de las pruebas.

Explica cómo ejecutar las pruebas automatizadas para este sistema.

### Desglosar en pruebas de extremo a extremo.

Explica qué prueban estas pruebas y por qué.

```

Pruebas con fundamente de finalizacion:

    1. Archivo HTML:

    ![HTML](https://tucatalogo.digital/img_cvds/html.png)

    2.Archivo CSS:

    ![CSS](http://tucatalogo.digital/img_cvds/css.png)

    3.Archivo JS:

    ![JS](http://tucatalogo.digital/img_cvds/js.png)

    4.Archivo PNG:

    ![PNG](http://tucatalogo.digital/img_cvds/png.png)

    5.Archivo JPG

    ![JPG](http://tucatalogo.digital/img_cvds/jpg.png)




```






## Implementación

```
    5. Consulte la extension del archivo que quiere buscar:

    6. Escribe "css" y dale en el boton "Buscar"
```

## Construido Con

* [Maven](https://maven.apache.org/) - Dependency Management
* [README](https://gist.github.com/PurpleBooth/109311bb0361f32d87a2) - Contrui este ReadMe basado en la plantilla de https://gist.github.com/PurpleBooth.


<!-- 
## Contributing

Please read [CONTRIBUTING.md](https://gist.github.com/PurpleBooth/b24679402957c63ec426) for details on our code of conduct, and the process for submitting pull requests to us. -->

<!-- ## Versioning

We use [SemVer](http://semver.org/) for versioning. For the versions available, see the [tags on this repository](https://github.com/your/project/tags).  -->

## Autores

* **Daniel Esteban Perez Bohorquez** -

## Licencia

This project is licensed under the MIT License - see the [LICENSE.md](LICENSE.md) file for details

## Agradecimientos

* Escuela Colombiana de Ingenieria
