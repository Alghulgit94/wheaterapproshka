# Weather App
 App que retorna los datos de temperatura de una ciudad seleccionada

 La app utiliza los servicios de [https://openweathermap.org/current](Openweather)

## Consultar temperatura
 - Es necesario darse de alta con una cuenta en https://openweathermap.org/
 - Una vez creada la cuenta, nos dirigimos a https://home.openweathermap.org/api_keys
 - Se presenta un dashboard  con el API KEY generado por defecto, este KEY deberia ser suficiente para realizar las consultas

## Implementación API KEY en el proyecto

 - Una vez copiado el KEY nos dirigimos al archivo build.gradle a nivel APP
 - Nos dirigimos al apartado de buildTypes -> debug
 - En la linea **buildConfigField "String","OPENWEATHER_API_KEY",'"YOUR_API_KEY_HERE"'**, reemplazamos el valor de YOUR_API_KEY_HERE

 Con esto resguardamos nuestro API_KEY mediante un campo de configuración generado en tiempo de ejecucion mediante un **buildConfigField**

