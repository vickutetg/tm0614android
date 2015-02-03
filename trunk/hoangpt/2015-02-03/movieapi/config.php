<?php
//convert to object connection
$pdo = new PDO("mysql:host=localhost;dbname=movie", "root", "123456789");

class Config {
  public $host;
  public $dbname;
  public $user;
  public $password;
  public $pdo;
  
  function __construct($host, $dbname, $user, $password) {
    $this->host = $host;
    $this->dbname = $dbname;
    $this->user = $user;
    $this->password = $password;
  }
  
  function connect(){
    $this->pdo = new PDO("mysql:host=localhost;dbname=movie", "root", "123456789");
  }
  
  function close(){
  }
}
