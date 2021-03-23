package JavaCode;

import java.io.InputStream;
import java.sql.*;
import org.jasypt.util.password.StrongPasswordEncryptor;

class User{
    String name, email, password, mobile, dob, city, gender;
    byte[] photo;
    
    public void setUser(String[] userDetails, InputStream file){
        name = userDetails[0];
        email = userDetails[1];
        
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        password = encryptor.encryptPassword(userDetails[2]);
        
        mobile = userDetails[3];
        dob = userDetails[4];
        city = userDetails[5];
        gender = userDetails[6];
        
        String[] userInfo = {name, email, password, mobile, dob, city, gender};
        
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/math_game","root","anshul");
            String a = "select * from user where email = ?";
            PreparedStatement st = conn.prepareStatement(a);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            if(!rs.next()){
                String insert = "insert into user values(?, ?, ?, ?, ?, ?, ?, ?, ?);";
                st = conn.prepareStatement(insert);
                
                for(int i = 1; i<=7; i++)
                    st.setString(i, userInfo[i-1]);
                st.setBinaryStream(8, file);
                st.setInt(9, 0);
                
                int n = st.executeUpdate();
                if(n==1)
                    System.out.println("user added successfully");
                else
                    throw new Exception("Error while adding user");
            }else{
                throw new Exception("User already registered");
            }
        }catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }finally{
            if(conn!=null){
                try{
                    conn.close();
                }catch(SQLException ex){
                    System.out.println("ERROR in closing: " + ex.getMessage());
                }
            }
        }
    }
    
    public void setHighScore(String email, int highScore){
        Connection conn = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/math_game","root","anshul");
            String a = "update user SET high_score = ? where email = ?";
            PreparedStatement st = conn.prepareStatement(a);
            st.setString(1, String.valueOf(highScore));
            st.setString(2, email);
            int effect = st.executeUpdate();
            if(effect!=1){
                throw new Exception("Score not updated");
            }else{
                System.out.println("Score updated successfully");
            }
        }catch(Exception e){
            System.out.println("ERROR: " + e.getMessage());
        }finally{
            if(conn!=null){
                try{
                    conn.close();
                }catch(SQLException ex){
                    System.out.println("ERROR in closing: " + ex.getMessage());
                }
            }
        }
    }
}