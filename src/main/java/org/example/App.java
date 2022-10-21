package org.example;
import org.jsoup.Jsoup;
import  org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import java.util.Scanner;


public class App 
{
    public  static  String getdata( String c)throws Exception
    {   StringBuffer br= new StringBuffer();
        br.append("<html>" + "<body style='text-align:centre; color: green;'>");
        br.append(c.toUpperCase()+ "<br>");
        String url="https://www.worldometers.info/coronavirus/country/"+c+"/";
        Document Docs=Jsoup.connect(url).get();
       Elements elements= Docs.select("#maincounter-wrap");

        elements.forEach((e) ->{

           String text=e.select("h1").text();
           String count=e.select(".maincounter-number>span").text();
           br.append(text).append("  ").append(count).append("<br>");

        });
        br.append("</body>"+"</html>");
        return br.toString();
    }
    public static void main( String[] args )throws Exception
    {
      //  Scanner sc=new Scanner(System.in);
      //  System.out.println("Enter Country:");
       // String  coun=sc.next();
       // System.out.println(getdata(coun));
        JFrame root=new JFrame("Details of a country");
        root.setSize(500,500);
        Font f=new Font("Poppins", Font.BOLD,30);
        JTextField field=new JTextField();
        JLabel label=new JLabel();
        field.setFont(f);
        field.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setFont(f);
        JButton button=new JButton("Submit");
        button.setSize(50,50);
        button.setFont(f);
        button.addActionListener(event ->  {
         try { String md=field.getText();
            String re =getdata(md);
            label.setText(re);
         }
         catch (Exception e){
             e.printStackTrace();
         }

        });
        root.setLayout(new BorderLayout());
        root.add(field,BorderLayout.NORTH);
        root.add(label,  BorderLayout.CENTER);
        root.add(button, BorderLayout.SOUTH);
        root.setVisible(true);






    }
}
