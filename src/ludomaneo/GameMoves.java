package ludomaneo;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import javax.swing.Timer;
public class GameMoves extends JPanel implements KeyListener, ActionListener, MouseListener{
   private static final long serialVersionUID = 1L;
      Layout l;
      Builder_Player p;
      Timer time;
      int current_player, dice;
      int roll, flag = 0, kill = 0;
      int delay = 10;
      public GameMoves(){
          setFocusTraversalKeysEnabled(false);
          requestFocus();
          l = new Layout(80, 50);
          p = new Builder_Player(l.height, l.width);
          current_player = dice = roll = flag = kill = 0;         
      }
      @Override
    public void paint(Graphics g){
         l.draw((Graphics2D)g);
         p.draw((Graphics2D)g);
         if(p.p1[current_player].coin == 4){
             g.setColor(Color.WHITE);
             g.fillRect(590, 100, 380, 130);
             if(current_player == 0){
                 g.setColor(Color.RED);
             } else if (current_player == 1){
                 g.setColor(Color.GREEN);
             } else if (current_player == 2){
                 g.setColor(Color.YELLOW);
             } else if (current_player == 3){
                 g.setColor(Color.BLUE);
             }
             g.setFont(new Font("serif", Font.BOLD, 40));
             g.drawString("Player" + (current_player+1) + " wins.", 600, 150);
             g.drawString("Congratulations.", 600, 200);
             l = new Layout(80, 50);
             p = new Builder_Player(l.height, l.width);
             current_player = 0;
             dice = 0;
             roll = 0;
             flag = 0;
             kill = 0;
             
         } else if (dice != 0){
             g.setColor(Color.WHITE);
             g.fillRect(590, 100, 380, 130);
             if(current_player == 0){
                 g.setColor(Color.RED);
             } else if (current_player == 1){
                 g.setColor(Color.GREEN);
             } else if (current_player == 2){
                 g.setColor(Color.YELLOW);
             } else if (current_player == 3){
                 g.setColor(Color.BLUE);
             }
             g.setFont(new Font("serif", Font.BOLD, 40));
             g.drawString("Player" + (current_player+1), 600, 150);
             g.drawString("Number on dice is " + dice, 600, 200);
         }
         if(flag == 0 && dice != 0 && dice != 6 && kill == 0){
             current_player = (current_player+1) % 4;
         }
       kill = 0; 
         }
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_ENTER && flag == 0){
            roll = 0;
            dice = 1 + (int)(Math.random() * 6);
            repaint();
            for(int i = 0; i < 4; i++){
                if(p.p1[current_player].pa[i].current!=-1  &&  p.p1[current_player].pa[i].current!=56  &&  (p.p1[current_player].pa[i].current+dice)<=56){
                    flag = 1;
                    break;
                }
            }
        if(flag == 0 && dice == 6){
            for(int i = 0; i < 4; i++){
                if(p.p1[current_player].pa[i].current == -1){
                    flag = 1;
                    break;
                }
            }
        }
        }
    }  
    @Override
    public void mouseClicked(MouseEvent e) {
        if(flag == 1){
            int x = e.getX();
            int y = e.getY();
            x = x - 80;
            y = y - 50;
            x = x / 30;
            y = y / 30;
            int value = -1;
            if(dice == 6){
                for(int i = 0; i < 4; i++){
                    if(p.p1[current_player].pa[i].x==x && p.p1[current_player].pa[i].y==y && (p.p1[current_player].pa[i].current+dice)<=56){
                        value = i;
                        flag = 0;
                        break;
                    }
                }
                if(value != -1){
                    p.p1[current_player].pa[value].current += dice;
         	    if(p.p1[current_player].pa[value].current==56) {
			p.p1[current_player].coin++;
			}
		int k=0;
		int hou=p.p1[current_player].pa[value].current;
		if((hou%13)!=0&&(hou%13)!=8&&hou<51){					
		    for(int i=0;i<4;i++){
			if(i!=current_player){
			    for(int j=0;j<4;j++){
				int temp1=Path.aX[current_player][p.p1[current_player].pa[value].current];
                                int temp2=Path.aY[current_player][p.p1[current_player].pa[value].current];
				    if(p.p1[i].pa[j].x==temp1&&p.p1[i].pa[j].y==temp2) {
					p.p1[i].pa[j].current=-1;
					kill=1;
				        k=1;
					break;
				}
                            }
			}
		if(k==1){
		        break;
                }
	    }
	}
    }
    } else {
       for(int i=0;i<4;i++) {
	    if(p.p1[current_player].pa[i].x==x && p.p1[current_player].pa[i].y==y&&(p.p1[current_player].pa[i].current+dice)<=56) {
		value=i;
		flag=0;
		break;
            }
       }
	if(value!=-1) {
	    p.p1[current_player].pa[value].current+=dice;
            if(p.p1[current_player].pa[value].current==56) {
		p.p1[current_player].coin++;
	    }
	    int k=0;
	    int hou=p.p1[current_player].pa[value].current;
		if((hou%13)!=0&&(hou%13)!=8&&hou<51){
		    for(int i=0;i<4;i++) {
			if(i!=current_player) {
			    for(int j=0;j<4;j++) {
				int temp1=Path.aX[current_player][p.p1[current_player].pa[value].current];
                                int temp2=Path.aY[current_player][p.p1[current_player].pa[value].current];
				    if(p.p1[i].pa[j].x == temp1&&p.p1[i].pa[j].y == temp2) {
					p.p1[i].pa[j].current=-1;
					kill=1;
					k=1;
					break;
                                    }
                            }
		    }
	    if(k==1)
		break;
		}
        }
    }
}
    repaint(); 
    }
}
    @Override
    public void keyReleased(KeyEvent e) {
      
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        
    }
    @Override
    public void keyTyped(KeyEvent e) {
        
    }
    @Override
    public void mousePressed(MouseEvent e) {
  
    }
    @Override
    public void mouseReleased(MouseEvent e) {
        
    }
    @Override
    public void mouseEntered(MouseEvent e) {
        
    }
    @Override
    public void mouseExited(MouseEvent e) {

    }    
}


//public class GameMoves extends JPanel implements KeyListener, ActionListener,MouseListener{
//	
//	private static final long serialVersionUID = 1L;
//	Layout la;
//	Builder_Player p;
//	Timer time;
//	int delay=10;
//	int current_player,dice;
//	int flag=0,roll,kill=0;
//	public GameMoves() {
//        setFocusTraversalKeysEnabled(false);
//        requestFocus();
//        current_player=0;
//        la = new Layout(80,50);
//        p=new Builder_Player(la.height,la.width);
//        dice=0;
//        flag=0;
//        roll=0;
//        kill=0;
//    }
//
//    @Override
//    public void paint(Graphics g) {
//    	la.draw((Graphics2D)g);
//    	p.draw((Graphics2D)g);
//    	if(p.p1[current_player].coin==4) {
//    		g.setColor(Color.WHITE);
//    		g.fillRect(590, 100, 380,130);
//    		if(current_player==0) {
//				g.setColor(Color.RED);
//			}
//			else if(current_player==1) {
//				g.setColor(Color.GREEN);
//			}
//			else if(current_player==2) {
//				g.setColor(Color.YELLOW);
//			}
//			else if(current_player==3) {
//				g.setColor(Color.BLUE);
//			}
//            g.setFont(new Font("serif", Font.BOLD, 40));
//            g.drawString("Player "+(current_player+1)+" wins.", 600, 150);
//            g.drawString("Congratulations.", 600, 200);
//            current_player=0;
//            la = new Layout(80,50);
//            p=new Builder_Player(la.height,la.width);
//            dice=0;
//            flag=0;
//            roll=0;
//            kill=0;
//    	}
//    	else if(dice!=0) {
//    		g.setColor(Color.WHITE);
//    		g.fillRect(590, 100, 380,130);
//    		if(current_player==0) {
//				g.setColor(Color.RED);
//			}
//			else if(current_player==1) {
//				g.setColor(Color.GREEN);
//			}
//			else if(current_player==2) {
//				g.setColor(Color.YELLOW);
//			}
//			else if(current_player==3) {
//				g.setColor(Color.BLUE);
//			}
//            g.setFont(new Font("serif", Font.BOLD, 40));
//            g.drawString("Player "+(current_player+1), 600, 150);
//            g.drawString("Number on dice is "+dice, 600, 200);
//    	}
//    	if(flag==0&&dice!=0&&dice!=6&&kill==0) {
//			current_player=(current_player+1)%4;
//		}
//    	kill=0;
//    }
//	@Override
//	public void keyPressed(KeyEvent e) {
//		// TODO Auto-generated method stub
//		if (e.getKeyCode() == KeyEvent.VK_ENTER&&flag==0) {
//			roll=0;
//			dice=1 + (int)(Math.random() * 6);
//			repaint();
//			for(int i=0;i<4;i++) {
//    			if(p.p1[current_player].pa[i].current!=-1&&p.p1[current_player].pa[i].current!=56&&(p.p1[current_player].pa[i].current+dice)<=56) {
//    				flag=1;
//    				break;
//    			}
//    		}
//    		if(flag==0&&dice==6) {
//    			for(int i=0;i<4;i++) {
//    				if(p.p1[current_player].pa[i].current==-1) {
//    					flag=1;
//    					break;
//    				}
//    			}
//    		}
//		}
//	}
//	
//	public void mouseClicked(MouseEvent e) {
//		if(flag==1) {
//			int x=e.getX();
//			int y=e.getY();
//			x=x-80;
//			y=y-50;
//			x=x/30;
//			y=y/30;
//			int value=-1;
//			//System.out.println(x+" "+y);
//			if(dice==6) {
//				for(int i=0;i<4;i++) {
//					if(p.p1[current_player].pa[i].x==x&&p.p1[current_player].pa[i].y==y&&(p.p1[current_player].pa[i].current+dice)<=56) {
//						value=i;
//						flag=0;
//						break;
//					}	
//				}
//				if(value!=-1) {
//					p.p1[current_player].pa[value].current+=dice;
//					if(p.p1[current_player].pa[value].current==56) {
//						p.p1[current_player].coin++;
//					}
//					int k=0;
//					int hou=p.p1[current_player].pa[value].current;
//					if((hou%13)!=0&&(hou%13)!=8&&hou<51)
//					{
//					for(int i=0;i<4;i++) {
//						if(i!=current_player) {
//							for(int j=0;j<4;j++) {
//								int tem1=Path.aX[current_player][p.p1[current_player].pa[value].current],tem2=Path.aY[current_player][p.p1[current_player].pa[value].current];
//								if(p.p1[i].pa[j].x==tem1&&p.p1[i].pa[j].y==tem2) {
//									p.p1[i].pa[j].current=-1;
//									kill=1;
//									k=1;
//									break;
//								}
//							}
//						}
//						if(k==1)
//							break;
//					}
//					}
//				}
//				else {
//					for(int i=0;i<4;i++) {
//						if(p.p1[current_player].pa[i].current==-1) {
//							p.p1[current_player].pa[i].current=0;
//							flag=0;
//							break;
//						}	
//					}
//				}
//			}
//			else {
//				for(int i=0;i<4;i++) {
//					if(p.p1[current_player].pa[i].x==x&&p.p1[current_player].pa[i].y==y&&(p.p1[current_player].pa[i].current+dice)<=56) {
//						value=i;
//						flag=0;
//						break;
//					}	
//				}
//				if(value!=-1) {
//					p.p1[current_player].pa[value].current+=dice;
//					if(p.p1[current_player].pa[value].current==56) {
//						p.p1[current_player].coin++;
//					}
//					int k=0;
//					int hou=p.p1[current_player].pa[value].current;
//					if((hou%13)!=0&&(hou%13)!=8&&hou<51)
//					{
//					for(int i=0;i<4;i++) {
//						if(i!=current_player) {
//							for(int j=0;j<4;j++) {
//								int tem1=Path.aX[current_player][p.p1[current_player].pa[value].current],tem2=Path.aY[current_player][p.p1[current_player].pa[value].current];
//								if(p.p1[i].pa[j].x==tem1&&p.p1[i].pa[j].y==tem2) {
//									p.p1[i].pa[j].current=-1;
//									kill=1;
//									k=1;
//									break;
//								}
//							}
//						}
//						if(k==1)
//							break;
//					}
//					}
//				}
//			}
//			repaint();
//		}
//	}
//
//	@Override
//	public void actionPerformed(ActionEvent e) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyReleased(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void keyTyped(KeyEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseEntered(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mouseExited(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//	@Override
//	public void mousePressed(MouseEvent e) {
//		// TODO Auto-generated method stub
//	}
//
//	@Override
//	public void mouseReleased(MouseEvent arg0) {
//		// TODO Auto-generated method stub
//		
//	}
//
//
//}
