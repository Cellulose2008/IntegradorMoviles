<?php
 $conexion = new mysqli("localhost","root","","bd");
 $inputData = file_get_contents('php://input');

 // Decodificar el JSON recibido
 $data = json_decode($inputData, true); // true convierte el JSON en un array asociativo
 $Titulo = $data['titulo'];
 $Desarrollo = $data['desarrollo'];
 $Fecha = $data['fecha'];
 $Id = $data['id'];
 $sql = "UPDATE recordatorios SET Titulo = '$Titulo', Desarrollo = '$Desarrollo', Fecha = '$Fecha' WHERE Id_Rec = '$Id'";
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