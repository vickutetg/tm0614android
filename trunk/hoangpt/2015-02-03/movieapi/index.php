<?php
require_once './config.php';

$module= $_REQUEST['module'];
$controller = $_REQUEST['controller'];
$controller = ucfirst($controller);
$action = $_REQUEST['action'];

//detect alsolute path
define('ROOT_DIR', dirname(__FILE__));


require_once "controller/$module/{$controller}Controller.php";

//init class
$upmodule = ucfirst($module);
$controlName = "Controller_{$upmodule}_{$controller}Controller";
$control = new $controlName;

$actionName = "{$action}Action";
$control->{$actionName}();

