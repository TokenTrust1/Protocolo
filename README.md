# Encriptación de mensajes

### Introducción

Este documento proporciona una descripción detallada de un programa Python diseñado para encriptar y desencriptar mensajes utilizando un mapeo de caracteres predefinido. El código se compone de dos funciones principales: encrypt y decrypt. Además, se utiliza un mapeo de caracteres llamado mapping para realizar las transformaciones.

### 1. Mapeo de Caracteres (mapping)

El mapeo de caracteres es un diccionario que asocia caracteres originales con caracteres encriptados. Su función es proporcionar una clave para realizar la encriptación y una clave inversa para la desencriptación. A continuación, se muestra el mapeo utilizado en el código:

mapping = {
    'a': 'z',
    'c': 'x',
    'e': 'v',
    'g': 't',
    'i': 'r',
    'j': 'p',
    'm': 'n',
    'o': 'l',
    'q': 'j',
    's': 'h',
    'u': 'f',
    'w': 'd',
    'y': 'b',
}
Como se puede observar cada letra de lado izquierdo se asocia con una letra de lado derecho
### 2. Función encrypt

La función encrypt toma un mensaje como entrada y lo encripta utilizando el mapeo definido. Aquí se detalla su funcionalidad:

Input: Recibe un mensaje.
Proceso: Busca sobre cada carácter del mensaje y verifica si existe una entrada correspondiente en el mapeo. Si existe, reemplaza el carácter con su equivalente encriptado; de lo contrario, mantiene el carácter original.
Output: Devuelve el mensaje encriptado como una cadena (str).

### 3. Función decrypt

La función decrypt toma un mensaje encriptado como entrada y lo desencripta utilizando el mapeo inverso. Aquí se detalla su funcionalidad:

Input: Recibe un mensaje encriptado.
Proceso: Busca sobre cada carácter del mensaje encriptado y verifica si existe una entrada correspondiente en el mapeo inverso. Si existe, reemplaza el carácter con su equivalente original; de lo contrario, mantiene el carácter encriptado.
Output: Devuelve el mensaje desencriptado como una cadena (str).



### 4. Ejemplo
A continuación un ejemplo de como sería la encriptación y desencriptación del mensaje

mensaje_original = "hola"
mensaje_encriptado="hllz"


### 5.Código
El programa pregunta si se desea encriptar o desencriptar un  mensaje, después de realizar alguna de las dos acciones mencionadas, se pregunta si desea volver a encriptar o desencriptar un mensaje, o en su defecto salir del programa
