import java.io.*;
import java.net.*;
import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
class Reset implements MouseListener{
  JButton but_arr[][]=new JButton[3][3];
  int logic_arr[][];
  Reset(JButton butt_arr[][],int arr[][]){
    for(int i=0;i<3;i++){
      for(int j=0;j<3;j++)
      but_arr[i][j]=butt_arr[i][j];
    }
    logic_arr=arr;
  }
  public void mouseExited(MouseEvent e){
  }
  public void mouseEntered(MouseEvent e){
    }
  public void mouseReleased(MouseEvent e){

  }
  public void mouseClicked(MouseEvent e){

  }
public void mousePressed(MouseEvent e){
  int c=0;
    for(int i=0;i<3;i++){
      for(int j=0;j<3;j++){
        but_arr[i][j].setIcon(null);
        logic_arr[i][j]=0;
      }
    }
  }
}
public class a implements ActionListener{
static int data;
static int flag=0;
static class a2 implements ActionListener{
JButton temp;
int x;
int y;
a2(JButton b,int x,int y){
    temp=b;
    this.x=x;
    this.y=y;
  }
               public void actionPerformed(ActionEvent e) {
        if(flag==0)
		{
		data=x*3+y+1;try{
		new makemove(ip,Integer.parseInt(port)).start();
}catch(Exception e2){System.out.println(e2+"s");}
		flag=1;               
		}
               }}
static class recieve extends Thread{
	
	 private ServerSocket server;
	private  int port;
	recieve(int port) throws Exception{
	this.port=port;
	server = new ServerSocket(port);	
	}
	public void run(){
System.out.println("Listening");
        //keep listens indefinitely until receives 'exit' call or program terminates
        try{while(true){
            //creating socket and waiting for client connection
            Socket socket = server.accept();
            //read from socket to ObjectInputStream object
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            //convert ObjectInputStream object to String
            String message = (String) ois.readObject();
           int mess=Integer.parseInt(message);
		int u=0;
		if(mess%3==0)
		u=mess/3-1;
		else
		u=mess/3;
		int v=(mess+2)%3;
if(logic_mat[u][v]==0){
        but_arr[u][v].setIcon(x);
	logic_mat[u][v]=10;
	flag=0;
	if(check_victory(1))
          JOptionPane.showMessageDialog(f,"You win");
	if(check_victory(10))
          JOptionPane.showMessageDialog(f,"You Lose");
}else{
         JOptionPane.showMessageDialog(f,"Your opponent is cheating");
}
		//create ObjectOutputStream object
            //close resources
            ois.close();
            socket.close();
            //terminate the server if client sends exit request
            if(message.equalsIgnoreCase("exit")) break;
        }
        System.out.println("Shutting down Socket server!!");
        //close the ServerSocket object
        server.close();}catch(Exception e){System.out.println(e);}
    }}

static class makemove extends Thread{

	String ip;
	int port;
	makemove(String ip,int port) throws Exception{
	this.ip=ip;
	this.port=port;
	}
	public void run(){try{
        Socket socket = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;
 	 socket = new Socket(ip, port);
            //write to socket using ObjectOutputStream
            oos = new ObjectOutputStream(socket.getOutputStream());
	oos.writeObject(""+data);
           int mess=data;

		int u=0;
		if(mess%3==0)
		u=mess/3-1;
		else
		u=mess/3;
		int v=(mess+2)%3;
	if(logic_mat[u][v]==0){
        but_arr[u][v].setIcon(o);
	logic_mat[u][v]=1;
	if(check_victory(1))
          JOptionPane.showMessageDialog(f,"You win");
	if(check_victory(10))
          JOptionPane.showMessageDialog(f,"You Lose");
}else{
         JOptionPane.showMessageDialog(f,"DONT CHEAT RUN AGAIN");
}
		oos.close();
        }catch(Exception e){System.out.println(e);}
	}}

static final JFrame f=new JFrame("TIC TaC TOE by Resd@");
static String port;
static String ip;
static int count=0;static int play=0;
static int logic_mat[][]=new int[3][3];
static JButton but_arr[][]=new JButton[3][3];
static ImageIcon x = new ImageIcon("x.jpg"); // load the image to a imageIcon
static ImageIcon o = new ImageIcon("o.jpg"); // load the image to a imageIcon
JButton temp;
int but_x;
int but_y;
  a(JButton b,int x,int y){
    temp=b;
    but_x=x;
    but_y=y;
  }

  public void actionPerformed(ActionEvent e){
  if(logic_mat[but_x][but_y]==0){
    if(play==0){
      if(count==0){
        temp.setIcon(o);
        logic_mat[but_x][but_y]=10;
        if(check_victory(10)){
          for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
              logic_mat[i][j]=2;
            }
          }
          JOptionPane.showMessageDialog(f,"Player who plays with 0 wins");
        }
          }
     else{
      temp.setIcon(x);
      logic_mat[but_x][but_y]=1;
      if(check_victory(1)){
        for(int i=0;i<3;i++){
          for(int j=0;j<3;j++){
            logic_mat[i][j]=2;
          }
        }

      JOptionPane.showMessageDialog(f,"Player who plays with x wins");
        }
      }
  count+=1;
  count%=2;
    }

    if(play==1){
      temp.setIcon(x);
      logic_mat[but_x][but_y]=1;
        simpulate_pc();
      }
  }}
static int move=0;

public static void simpulate_pc(){
  if(move==0){
          but_arr[0][0].setIcon(o);
          logic_mat[0][0]=10;
          move++;
        }
  else if(move==1&&logic_mat[1][1]==0){
    if(logic_mat[0][1]==1||logic_mat[0][2]==1||logic_mat[2][1]==1||logic_mat[2][2]==1){
      but_arr[2][0].setIcon(o);
      logic_mat[2][0]=10;
    }
    else{
      but_arr[0][2].setIcon(o);
      logic_mat[0][2]=10;
    }
    move++;
  }
  else if(move==1&&logic_mat[1][1]==1){
    but_arr[2][2].setIcon(o);
    logic_mat[2][2]=10;
    move=4;
  }
  else if(move==2)
   {
    find_best_move(0);
  }
  else if(move==5)
    win_in_one();
    else if(move==7)
      tie();
  else if(move==4){
    if(logic_mat[0][2]==1)
      {
        but_arr[2][0].setIcon(o);
        logic_mat[2][0]=10;
        move=5;
      }
      else if(logic_mat[2][0]==1)
        {
          but_arr[0][2].setIcon(o);
          logic_mat[0][2]=10;
          move=5;
        }
        else{
		tie();
 	          move=7;
        }

  }

    if(check_victory(10)){
      for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
          logic_mat[i][j]=2;
        }
      }
      JOptionPane.showMessageDialog(f,"Mortals it's 100 years before you can defeat me....");
      play=0;
    }
      }
      public static void tie(){
        if(!win_in_one())
        {for(int i=0;i<3;i++){
          for(int j=0;j<3;j++){
            if(logic_mat[i][j]==0)
            {
              logic_mat[i][j]=1;
              if(check_victory(1)){
              logic_mat[i][j]=10;
              but_arr[i][j].setIcon(o);
              break;
            }
            else
            logic_mat[i][j]=0;
          }
        }
      }
    }}
public static void find_best_move(int x){
  if(!win_in_one())
  {
    if(x==0)
    {
      if((logic_mat[0][1]==1&&logic_mat[1][0]==1)||(logic_mat[1][0]==1&&logic_mat[0][2]==1)||(logic_mat[0][1]==1&&logic_mat[2][0]==1))
      {

        logic_mat[2][2]=10;
        but_arr[2][2].setIcon(o);
      }
      else if((logic_mat[2][1]==1&&logic_mat[1][0]==1)||(logic_mat[1][0]==1&&logic_mat[2][2]==1)){
        logic_mat[0][2]=10;
        but_arr[0][2].setIcon(o);
      }
      else if(logic_mat[0][1]==1&&logic_mat[1][2]==1){
        logic_mat[2][0]=10;
        but_arr[2][0].setIcon(o);
      }
    }
  }
}
public static boolean win_in_one(){
  for(int i=0;i<3;i++)
  {
    for(int j=0;j<3;j++){
      if(logic_mat[i][j]==0)
      {
        logic_mat[i][j]=10;
        if(!check_victory(10))
        logic_mat[i][j]=0;
        else
        {
          but_arr[i][j].setIcon(o);
          return true;
        }
      }
    }
  }
return false;
}
  public static boolean check_victory(int x){
    for(int i=0;i<3;i++)
    if(logic_mat[i][0]+logic_mat[i][1]+logic_mat[i][2]==3*x)
    return true;
    for(int i=0;i<3;i++)
    if(logic_mat[0][i]+logic_mat[1][i]+logic_mat[2][i]==3*x)
    return true;
    if(logic_mat[0][0]+logic_mat[1][1]+logic_mat[2][2]==3*x)
    return true;
    if(logic_mat[0][2]+logic_mat[1][1]+logic_mat[2][0]==3*x)
    return true;
    return false;
  }
    public static void main(String args[]) throws Exception{
      JMenu reset=new JMenu("New Game");
      JMenu connect=new JMenu("CONNECT");
      JMenu pc=new JMenu("Play with pc");
      JMenuBar menu=new JMenuBar();
for(int i=0;i<3;i++){
  for(int j=0;j<3;j++){
    but_arr[i][j]=null;
  }
}
      menu.add(reset);

      menu.add(connect);

      menu.add(pc);
      menu.setBounds(0,0,400,20);
	for(int i=0;i<3;i++){
        for(int j=0;j<3;j++){
          JButton b=new JButton();
          b.setBounds(40+100*i,40+100*j,100,100);
          b.addActionListener(new a(b,j,i));
	b.addActionListener(new a2(b,j,i));		
          f.add(b);
but_arr[j][i]=b;
        }
      }
	connect.addMouseListener(new click());
            reset.addMouseListener(new Reset(but_arr,logic_mat));
            reset.addMouseListener(new MouseAdapter() {
               public void mousePressed(MouseEvent e) {
                 play=0;
               }
               });
            pc.addMouseListener(new MouseAdapter() {
               public void mousePressed(MouseEvent e) {
                 for(int i=0;i<3;i++){
                   for(int j=0;j<3;j++){
                     but_arr[i][j].setIcon(null);
                     logic_mat[i][j]=0;
                   }
                 }
                     count=0;
                     play=1;
                     move=0;
                               JOptionPane.showMessageDialog(f,"Mortals you can not defeat me....");
                    simpulate_pc();
               }
            });
      f.add(menu);
      f.setSize(400,600);
      f.setDefaultCloseOperation(2);
      f.setLayout(null);
      f.setVisible(true);


    }
static class click implements MouseListener{
  public void mouseExited(MouseEvent e){
  }
  public void mouseEntered(MouseEvent e){
    }
  public void mouseReleased(MouseEvent e){

  }
  public void mouseClicked(MouseEvent e){

  }
public void mousePressed(MouseEvent e){
 final JFrame new_frame=new JFrame("CONNECT");
	a.play=2;
  JLabel l1=new JLabel();
  JLabel l2=new JLabel();
  l1.setText("Enter ip");
    l1.setBounds(5,50,70,20);
    new_frame.add(l1);
  l2.setText("Enter port");
    l2.setBounds(5,90,90,20);
    new_frame.add(l2);
	a.f.setVisible(false);
 final JTextField ip=new JTextField();
    ip.setBounds(100,50,100,20);
    new_frame.add(ip);
 final JTextField port=new JTextField();
    port.setBounds(100,90,100,20);
    new_frame.add(port);
   JButton click=new JButton("CLICK");

   click.setBounds(80,140,100,40);
   new_frame.add(click);
click.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e)  {
                 a.ip=ip.getText();
		a.port=port.getText();try{
		new a.recieve(Integer.parseInt(a.port)).start();
}catch(Exception e2){System.out.println(e2);}  
    		new_frame.setVisible(false);
		a.f.setVisible(true);             }
               });

    new_frame.setSize(250,200);
    new_frame.setLayout(null);
    new_frame.setVisible(true);
    new_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	

  }
}
}


