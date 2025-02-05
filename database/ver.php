<?php
 $conexion = new mysqli("localhost","root","","bd");
 $inputData = file_get_contents('php://input');
 $sql = "SELECT Titulo, Desarrollo, Fecha FROM recordatorios";
 $res = $conexion->query($sql);
 $recordatorios = [];
 while($row = $res->fetch_assoc()){
    $recordatorios[] = [
        "Titulo" => $row["Titulo"],
        "Desarrollo" => $row["Desarrollo"],
        "Fecha" => $row["Fecha"]];
 }
 echo json_encode([
    "status" => "success",
    "data" => $recordatorios
]);
 // Cerrar la conexión
 $conexion->close();
 ?>