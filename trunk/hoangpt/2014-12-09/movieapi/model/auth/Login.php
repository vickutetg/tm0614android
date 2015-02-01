<?php
class Model_Auth_Login {
  
  public function auth($username, $password){
    global $pdo;
    
    //crypto
    $salt = "m0vi3@_";
    $password = md5($salt.$password);
    $sql = "SELECT * FROM user WHERE username='$username' AND password = '$password'";

    $record = $pdo->query($sql)->fetch(PDO::FETCH_OBJ);
    if($record){
      return $record->id . ":" .$record->username ;
    }
    
    return false;
  }
}

