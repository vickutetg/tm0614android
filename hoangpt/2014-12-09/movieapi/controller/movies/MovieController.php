<?php
 class Controller_Movies_MovieController {
   
   public function listAction(){
     //$group = new model\users\Group;
     //group = new model.users.Group;
     
     //get token
     $token = $_REQUEST['token'];
     if(!$token){
       die('{"code":"500", "data":"Missing token"}');
     } else {
       //check token
       require_once ROOT_DIR.'/model/users/User.php'; 
       $model = new Model_Users_User();
       $res = $model->validateToken($token);
       
       if($res){
         echo "Hien thi json list movie ra day";
       } else {
         die('{"code":"500", "data":"Invalid token"}');
       }
     }
   }
 }

