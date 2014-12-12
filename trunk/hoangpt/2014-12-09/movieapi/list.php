<?php 
//connect to DB
$pdo = new PDO("mysql:host=localhost;dbname=movie", "root", "123456789");

//query
$query = "SELECT * FROM movie";

//result
$result = $pdo->query($query)->fetchAll();

//encode result to json
$json = json_encode($result);

header("Content-Type: application/json");
die($json);
?>