<?php
error_reporting(0);
include_once("dbconnect.php");

$username = $_POST['username'];
$password = $_POST['password'];
$name = $_POST['name'];
$phone = $_POST['phone'];
$sex=$_POST['sex'];

$sqlinsert = "INSERT INTO `user`(`username`, `password`, `name`, `phone`, `sex`) VALUES ('$username','$password','$name','$phone','$sex')";
if ($conn->query($sqlinsert) === TRUE){
    echo "success";
}else {
    echo "failed";
}

?>