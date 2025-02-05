<?php
 $conexion = new mysqli("localhost","root","","bd");
 $inputData = file_get_contents('php://input');

 // Decodificar el JSON recibido
 $data = json_decode($inputData, true); // true convierte el JSON en un array asociativo
 $Titulo = $data['titulo'];
 $Desarrollo = $data['desarrollo'];
 $Fecha = $data['fecha'];
 $sql = "DELETE FROM recordatorios WHERE Titulo = '$Titulo' && Desarrollo = '$Desarrollo' && Fecha = '$Fecha'";
 // Ejecutar la consulta
 if ($conexion->query($sql) == TRUE) {
    $res = 1;
 } else {
    $res = -1;
 }
 echo '{"usr":'.$res.'}'; 
 // Cerrar la conexión
 $conexion->close();
 ?>