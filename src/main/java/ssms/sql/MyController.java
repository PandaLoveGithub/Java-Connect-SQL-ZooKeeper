package ssms.sql;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import java.sql.*;
import java.util.ArrayList;

@Controller
public class MyController {

    private ArrayList<String> sqlPrinter=new ArrayList<String>();

    @RequestMapping(value="zoo")
    public String outcomePage(ModelMap model) {
        Connection testConnection=getConnectionToDB();
        ArrayList<String> modelPrinter=new ArrayList<>();
        if(testConnection==null)
        {
            modelPrinter.add("no connection at all");
        }else {
            modelPrinter=getSQL(testConnection);
        }
        model.put("MYTEXT",modelPrinter);
        return "SQLOutcome";
    }

    public ArrayList getSQL(Connection con)
    {
        Statement stmt=null;
        String query="Select * From [PandaSandbox].[dbo].[MyZoo] Where Country='Brazil'";

        try
        {
            stmt=con.createStatement();
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
                String queryResult="The quantity of "+rs.getString("Name")+" is "+
                        rs.getInt("Quantity")+" which is from "+rs.getString("Country")
                        +" cost us $"+rs.getFloat("Price")+"each on the date "+rs.getDate("Date");
                sqlPrinter.add(queryResult);
            }
            return sqlPrinter;
        }catch(SQLException e)
        {
            sqlPrinter.add("We cannot connect to the SQL but cannot run the sql");
            return sqlPrinter;
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
