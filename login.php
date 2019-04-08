<?php
error_reporting(0);
include_once("dbconnect.php");
$username = $_POST['username'];
$password = $_POST['password'];

$sql = "SELECT * FROM USER WHERE username = '$username' AND PASSWORD = '$password'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
	echo "success";
}else{
    echo $username + $password ;
}

?>