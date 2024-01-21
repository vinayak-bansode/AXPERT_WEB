package com.appointmentbooking.axpert.db.Query;

public class QueryBuilder {
    public static String registerquery = "INSERT INTO user (name, email, password,authtype,token) VALUES (?, ?, ?,?,?)";
    public static String userexistquery = "SELECT COUNT(*) FROM user u WHERE u.email = ?";
    public static String loginquery = "SELECT * FROM user u  WHERE u.email = ?";
    public static String forgotpassquery = "UPDATE user SET password = ? WHERE email = ?";
}
