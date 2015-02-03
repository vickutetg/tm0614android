<?php 
//connect to central DB (procedure)
//check token




//first check get param from request (GET, POST)
$search = ""; //"", null, 0
if(isset($_GET['search'])){
  $search = $_GET['search'];
} else {
  //check body payload
  //get all headers support mobile
  $headers = getallheaders();
  
  if(isset($headers['Content-Type'])){ //post always have
    if('application/json' == $headers['Content-Type']){
      //read content body from input stream
      $inputJSON = file_get_contents('php://input'); 
      $inputs= json_decode( $inputJSON, TRUE ); //lib php_json
      $search = $inputs['search'];

    } else if('application/xml' == $headers['Content-Type']) {
      $inputXML = file_get_contents('php://input'); 

      //use parser to convert from string to object, param
    } else {
      die('"code":205, "message":"Do not support POST HTTP"');
    }
  } else {//get
    
  }
}

//query
$query = "SELECT * FROM movie";

//if has request search
if($search){
  $query .= " WHERE name LIKE '%$search%'";
}

//result
$result = $pdo->query($query)->fetchAll(PDO::FETCH_OBJ);

//encode result to json
$json = json_encode($result);

header("Content-Type: application/json");
die($json);
?>