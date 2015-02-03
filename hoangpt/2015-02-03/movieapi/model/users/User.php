<?php

class Model_Users_User {
  
  
  public function validateToken($token){
    global $pdo;
    
    $token = base64_decode($token);
    $userinfos = explode(":", $token);
    
    if(sizeof($userinfos) > 1){
      $userId = $userinfos[0];
      $userName = $userinfos[1];

      $sql = "SELECT * FROM user WHERE id = $userId AND username = '$userName'";
      $rs = $pdo->query($sql)->fetch();

      if($rs){
        return true;
      }      
    }

    return false;
  }
}

