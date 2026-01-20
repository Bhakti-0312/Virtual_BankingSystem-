import javax.swing.*;
import java.awt.*;
import java.sql.*;

class Elogin extends JFrame {
    Elogin() {
        Font f = new Font("Futura", Font.BOLD, 40);
        Font f2 = new Font("Calibri", Font.PLAIN, 22);

        JLabel title = new JLabel("Login", JLabel.CENTER);
        JLabel l1 = new JLabel("Enter Username");
        JTextField t1 = new JTextField(10);
        JLabel l2 = new JLabel("Enter Password");
        JPasswordField t2 = new JPasswordField(10);
        JButton b3 = new JButton("Show");
        JButton b4 = new JButton("Hide");
        JButton b1 = new JButton("Submit");
        JButton b2 = new JButton("Back");

        title.setFont(f);
        l1.setFont(f2);
        t1.setFont(f2);
        l2.setFont(f2);
        t2.setFont(f2);
        b1.setFont(f2);
        b2.setFont(f2);
        b3.setFont(f2);
        b4.setFont(f2);



        Container c = getContentPane();
        c.setLayout(null);

        title.setBounds(250, 30, 300, 50);
        l1.setBounds(250, 100, 300, 30);
        t1.setBounds(250, 140, 300, 30);
        l2.setBounds(250, 200, 300, 30);
        t2.setBounds(250, 240, 300, 30);
        b3.setBounds(600, 240, 150, 40);
        b4.setBounds(600, 240, 150, 40);
        b1.setBounds(300, 300, 200, 40);
        b2.setBounds(300, 360, 200, 40);

        c.add(title);
        c.add(l1);
        c.add(t1);
        c.add(l2);
        c.add(t2);
        c.add(b1);
        c.add(b2);
        c.add(b3);

        b3.addActionListener(
                a->{
                    c.add(b4);
                }
        );

        b1.addActionListener(
                a->
                {

                    if (t1.getText().isEmpty())
                    {
                        JOptionPane.showMessageDialog(null, "Required Username");
                      return;
                    }

                    String url = "jdbc:mysql://localhost:3306/3dec";
                    try (Connection con = DriverManager.getConnection(url, "root", "Bhaktispatil07@"))
                    {
                        String sql = "select * from users where username=? and password=?";
                        try (PreparedStatement pst = con.prepareStatement(sql))
                        {
                            pst.setString(1, t1.getText());

                            String s1 = new String(t2.getPassword());

                            pst.setString(2, s1);

                            ResultSet rs = pst.executeQuery();

                            if(rs.next())
                            {
                                JOptionPane.showMessageDialog(null, "Successful");
                                new Home(t1.getText());
                                dispose();
                            }
                            else
                            {
                                JOptionPane.showMessageDialog(null, "Invalid username or password");
                                t1.setText("");
                                t2.setText("");
                            }

                        }
                    }
                    catch (Exception e)
                    {
                        JOptionPane.showMessageDialog(null, e.getMessage());
                    }
                }
        );

        b2.addActionListener(
                a->{
                    new Landing();
                    dispose();
                }
        );



        setVisible(true);
        setSize(800, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Login");
    }

    public static void main(String[] args) {
        new Elogin();
    }
}

