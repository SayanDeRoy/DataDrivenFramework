package com.infy.practice.testcases;

import com.infy.practice.base.TestBase;
import org.testng.annotations.Test;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DBValidation extends TestBase {

    @Test
    public void getEmpData() throws SQLException, ClassNotFoundException {
        String query = "Select 8 from emp";
        executeQueryAndGetStringValue(query, "EMPNO");
    }


}
