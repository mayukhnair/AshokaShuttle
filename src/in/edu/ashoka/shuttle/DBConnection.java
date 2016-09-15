/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package in.edu.ashoka.shuttle;

import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mayukh Nair
 */
public class DBConnection {
    
    public String username;
    public String userdept;
    public String userimage;
    public String usershuttle;
    public String timestamp;
    public String checkindone="n";
    public String userid;
    
    public void initConnection(String cardid){
        try {
            userid=cardid;
            Class.forName("java.sql.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://quizdatabase.chaidjlvgeec.us-east-1.rds.amazonaws.com:3306/checkinrecord","quizmaster","pickbrain");
            Statement sta=con.createStatement();
            String query="SELECT * FROM userdetails WHERE card_id='"+cardid+"'";
            ResultSet rs=sta.executeQuery(query);
            while(rs.next()){
                username=rs.getString("name");
                userdept=rs.getString("department");
                usershuttle=rs.getString("allotted_shuttle");
                userimage=rs.getString("image_link");
            }
            rs.close();
            sta.close();
            con.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void recordCheckIn(String cardid, String shuttleid){
    Thread recordCheckinThread=new Thread(new Runnable(){
        public void run(){
            try {
            Class.forName("java.sql.Driver");
            Connection con=DriverManager.getConnection("jdbc:mysql://quizdatabase.chaidjlvgeec.us-east-1.rds.amazonaws.com:3306/checkinrecord","quizmaster","pickbrain");
            Statement sta=con.createStatement();
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            Calendar calobj = Calendar.getInstance();
            timestamp=df.format(calobj.getTime());
            String query="INSERT INTO checkins VALUES('"+cardid+"', '"+shuttleid+"', '"+timestamp+"')";
            System.out.println(timestamp);
            System.out.println(userid+" "+shuttleid+" "+timestamp);
            int rs=sta.executeUpdate(query);
            sta.close();
            con.close();
            checkindone="y";
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    });
    recordCheckinThread.start();
}
    public String getName(){
           return(username);
}
    public String getDepartment(){
        return(userdept);
    }
    
    public String getImageLink(){
        return(userimage);
    }
    public String getShuttle(){
        return(usershuttle);
    }
    public String checkinConfirmed(){
        return(checkindone);
    }
}
