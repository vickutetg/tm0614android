<?php
//connect to central DB (procedure)
$pdo = new PDO("mysql:host=localhost;dbname=movie", "root", "123456789");

//get request from param, body
$headers = getallheaders();
if('application/json' == $headers['Content-Type']){
    //process if mobile
} else {
  //if missing any request
  if(isset($_REQUEST['name']) && isset($_REQUEST['type'])){
    $name = $_REQUEST['name'];
    $type = $_REQUEST['type'];
    $year = $_REQUEST['year'];
    $description = $_REQUEST['description'];
  } else {
    $arrJson = array("code"=> 205, "message"=>"Param missing");
    die(json_encode($arrJson));
  }
}

$addSQL = "INSERT INTO movie ( `description`, `name`, `type`, `year`) "
    . "VALUES ( '$description', '$name', '$type', '$year')";

$number = $pdo->query($addSQL);
if($number) {
  die('"code":200, "message":"Add successfully"');
} else {
  die('"code":206, "message":"Database overload"');
}