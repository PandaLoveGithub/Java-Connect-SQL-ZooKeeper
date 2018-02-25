package ssms.sql;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;

@Controller
public class MyController {

    @RequestMapping(value="zoo")
    public String outcomePage(ModelMap model) {
        Connection testConnection=getConnectionToDB();
        String SQLPrint="";
        if(testConnection==null)
        {
            SQLPrint="No such thing";
        }else {
            SQLPrint = getSQL(testConnection);
        }
        model.put("MYTEXT",SQLPrint);
        return "SQLOutcome";
    }

    public String getSQL(Connection con)
    {
        Statement stmt=null;
        String query="Select * From [PandaSandbox].[dbo].[MyZoo] Where Country='Austrialia'";

        try
        {
            stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            return rs.getString("Name");
        }catch(SQLException e)
        {
            return "Magic Bunny: "+e;
        }
    }

    public Connection getConnectionToDB() {
        try
        {
            Connection connect=DriverManager.getConnection("jdbc:sqlserver://DESKTOP-G5P7VO3:1433",
                    "ZooKeeper"
                    ,"123456");
            return connect;
        }catch(Exception e)
        {
            System.out.println("!!!!!!"+e+"!!!!!!!");
            return null;
        }
    }



}
