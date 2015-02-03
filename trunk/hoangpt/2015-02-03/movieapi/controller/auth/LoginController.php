<?php

class Controller_Auth_LoginController {
  
  public function loginAction(){
    //require Login model
    require_once ROOT_DIR.'/model/auth/Login.php';
    $username = $_REQUEST['username'];
    $password = $_REQUEST['password'];
    
    $model = new Model_Auth_Login();
    $result = $model->auth($username, $password);
    
    header("Content-Type", "application/json");
    
    if($result){
      //not use cookie
      $resObj = new stdClass();
      $resObj->code = 200;
      $resObj->data = array(
        "username" => $username,
        "token" => base64_encode($result)
      );
      
      die(json_encode($resObj));
    } else {
      $resObj = new stdClass();
      $resObj->code = 200;
      $resObj->data = "This account is not found";
      die(json_encode($resObj));
    }
  }
  
  public function logoutAction(){
    
  }
}


