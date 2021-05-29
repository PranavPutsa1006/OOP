package applet;

import java.awt.*;
import java.applet.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.KeyListener;
import java.math.*;

public class Maze extends java.applet.Applet implements Runnable {

    int[][] maze;
    final static int backgroundCode = 0;
    final static int wallCode = 1;
    final static int pathCode = 2;
    final static int emptyCode = 3;
    final static int visitedCode = 4;
    Color[] color = new Color[5];  
    int rows = 21;          
    int columns = 21;       
    int border = 0;         
    int sleepTime = 5000;   
    int speedSleep = 50;    
    
    Thread mazeThread;   
    
    int width = -1;   
    int height = -1;  
    int totalWidth;   
    int totalHeight;  
    int left;         
    int top;          
    
    boolean mazeExists = false;  
                                 


    int status = 0;  

    final static int GO = 0,         
                     SUSPEND = 1,
                     TERMINATE = 2;

    Integer getIntParam(String paramName) {
          
       String param = getParameter(paramName);
       if (param == null)
          return null;
       int i;
       try {
          i = Integer.parseInt(param);
       }
       catch (NumberFormatException e) {
          return null;
       }
       return new Integer(i);
    }
    
    Color getColorParam(String paramName) {
         
       String param = getParameter(paramName);
       if (param == null || param.length() == 0)
          return null;
       if (Character.isDigit(param.charAt(0))) {  
          int r=0,g=0,b=0;
          int pos=0;
          int d=0;
          int len=param.length();
          while (pos < len && Character.isDigit(param.charAt(pos)) && r < 255) {
              d = Character.digit(param.charAt(pos),10);
              r = 10*r + d;
              pos++;
          }
          if (r > 255)
             return null;
          while (pos < len && !Character.isDigit(param.charAt(pos)))
             pos++;
          if (pos >= len)
             return null;
          while (pos < len && Character.isDigit(param.charAt(pos)) && g < 255) {
              d = Character.digit(param.charAt(pos),10);
              g = 10*g + d;
              pos++;
          }
          if (g > 255)
             return null;
          while (pos < len && !Character.isDigit(param.charAt(pos)))
             pos++;
          if (pos >= len)
             return null;
          while (pos < len && Character.isDigit(param.charAt(pos)) && b < 255) {
              d = Character.digit(param.charAt(pos),10);
              b = 10*b + d;
              pos++;
          }
          if (b > 255)
             return null;
          return new Color(r,g,b);          
       }
       if (param.equalsIgnoreCase("black"))
          return Color.black;
       if (param.equalsIgnoreCase("white"))
          return Color.white;
       if (param.equalsIgnoreCase("red"))
          return Color.red;
       if (param.equalsIgnoreCase("green"))
          return Color.green;
       if (param.equalsIgnoreCase("blue"))
          return Color.blue;
       if (param.equalsIgnoreCase("yellow"))
          return Color.yellow;
       if (param.equalsIgnoreCase("cyan"))
          return Color.cyan;
       if (param.equalsIgnoreCase("magenta"))
          return Color.magenta;
       if (param.equalsIgnoreCase("pink"))
          return Color.pink;
       if (param.equalsIgnoreCase("orange"))
          return Color.orange;
       if (param.equalsIgnoreCase("gray"))
          return Color.gray;
       if (param.equalsIgnoreCase("darkgray"))
          return Color.darkGray;
       if (param.equalsIgnoreCase("lightgray"))
          return Color.lightGray;
       return null;  
    }

    public void init() {
         Integer parm;
         parm = getIntParam("rows");
         if (parm != null && parm.intValue() > 4 && parm.intValue() <= 500) {
            rows = parm.intValue();
            if (rows % 2 == 0)
               rows++;
         }
         parm = getIntParam("columns");
         if (parm != null && parm.intValue() > 4 && parm.intValue() <= 500) {
            columns = parm.intValue();
            if (columns % 2 == 0)
               columns++;
         }
         parm = getIntParam("border");
         if (parm != null && parm.intValue() > 0 && parm.intValue() <= 100)
            border = parm.intValue();
         parm = getIntParam("sleepTime");
         if (parm != null && parm.intValue() > 0)
            sleepTime = parm.intValue();
         parm = getIntParam("speed");
         if (parm != null && parm.intValue() > 0 && parm.intValue() < 6)
            switch (parm.intValue()) {
               case 1: speedSleep = 1; break;
               case 2: speedSleep = 25; break;
               case 3: speedSleep = 50; break;
               case 4: speedSleep = 100; break;
               case 5: speedSleep = 200; break;
            }
         color[wallCode] = getColorParam("wallColor");
         if (color[wallCode] == null) 
            color[wallCode] = Color.black;
         color[pathCode] = getColorParam("pathColor");
         if (color[pathCode] == null)
            color[pathCode] = new Color(200,0,0);
         color[emptyCode] = getColorParam("emptyColor");
         if (color[emptyCode] == null)
            color[emptyCode] = new Color(128,128,255);
         color[backgroundCode] = getColorParam("borderColor");
         if (color[backgroundCode] == null)
            color[backgroundCode] = Color.white;
         color[visitedCode] = getColorParam("visitedColor");
         if (color[visitedCode] == null)
            color[visitedCode] = color[emptyCode];
         setBackground(color[backgroundCode]);
    }
    
    void checkSize() {
          
       if (getWidth() != width || getHeight() != height) {
          width  = getWidth();
          height = getHeight();
          int w = (width - 2*border) / columns;
          int h = (height - 2*border) / rows;
          left = (width - w*columns) / 2;
          top = (height - h*rows) / 2;
          totalWidth = w*columns;
          totalHeight = h*rows; 
       }
    }

    synchronized public void start() {
        status = GO;
        if (mazeThread == null || ! mazeThread.isAlive()) {
          mazeThread = new Thread(this);
          mazeThread.start();
        }
        else
           notify();
    }

    synchronized public void stop() {
        if (mazeThread != null) {
            status = SUSPEND;
            notify();
        }
    }
    
    synchronized public void destroy() {
       if (mazeThread != null) {
          status = TERMINATE;
          notify();
       }
    }
    
    synchronized int checkStatus() {
       while (status == SUSPEND) {
          try { wait(); }
          catch (InterruptedException e) { }
       }
       return status;
    }

    public void paint(Graphics g) {
        checkSize();
        redrawMaze(g);
    }
    
    public void update(Graphics g) {  
        paint(g);                     
    }
    
    synchronized void redrawMaze(Graphics g) {
          
        g.setColor(color[backgroundCode]);
        g.fillRect(0,0,width,height);
        if (mazeExists) {
           int w = totalWidth / columns;  
           int h = totalHeight / rows;    
           for (int j=0; j<columns; j++)
               for (int i=0; i<rows; i++) {
                   if (maze[i][j] < 0)
                      g.setColor(color[emptyCode]);
                   else
                      g.setColor(color[maze[i][j]]);
                   g.fillRect( (j * w) + left, (i * h) + top, w, h );
               }
         }
    }

    synchronized void putSquare(int row, int col, int colorNum) {
           
        checkSize();
        int w = totalWidth / columns;  
        int h = totalHeight / rows;    
        Graphics me = getGraphics();
        me.setColor(color[colorNum]);
        me.fillRect( (col * w) + left, (row * h) + top, w, h );
        me.dispose();
    }

    public void run() {
           
       try { Thread.sleep(2000); } 
       catch (InterruptedException e) { }
       while (true) {
          if (checkStatus() == TERMINATE)
             break;
          makeMaze();
          if (checkStatus() == TERMINATE)
             break;
          solveMaze(1,1);
          if (checkStatus() == TERMINATE)
             break;
          synchronized(this) {
              try { wait(sleepTime); }
              catch (InterruptedException e) { }
          }
          if (checkStatus() == TERMINATE)
             break;
          mazeExists = false;
          checkSize();
          Graphics me = getGraphics();
          redrawMaze(me);   // erase old maze
          me.dispose();
       }
    }

    void makeMaze() {
           
        if (maze == null)
           maze = new int[rows][columns];
        int i,j;
        int emptyCt = 0; 
        int wallCt = 0;  
        int[] wallrow = new int[(rows*columns)/2];  
        int[] wallcol = new int[(rows*columns)/2];
        for (i = 0; i<rows; i++)  
            for (j = 0; j < columns; j++)
                maze[i][j] = wallCode;
        for (i = 1; i<rows-1; i += 2)  
            for (j = 1; j<columns-1; j += 2) {
                emptyCt++;
                maze[i][j] = -emptyCt;  
                if (i < rows-2) {  
                    wallrow[wallCt] = i+1;
                    wallcol[wallCt] = j;
                    wallCt++;
                }
                if (j < columns-2) {  
                    wallrow[wallCt] = i;
                    wallcol[wallCt] = j+1;
                    wallCt++;
                }
             }
        mazeExists = true;
        checkSize();
        if (checkStatus() == TERMINATE)
           return;
        Graphics me = getGraphics();
        redrawMaze(me);  
        me.dispose();
        int r;
        for (i=wallCt-1; i>0; i--) {
            r = (int)(Math.random() * i);  
            if (checkStatus() == TERMINATE)
               return;
            tearDown(wallrow[r],wallcol[r]);
            wallrow[r] = wallrow[i];
            wallcol[r] = wallcol[i];
        }
        for (i=1; i<rows-1; i++)  
           for (j=1; j<columns-1; j++)
              if (maze[i][j] < 0)
                  maze[i][j] = emptyCode;
    }

    synchronized void tearDown(int row, int col) {
      
            if (row % 2 == 1 && maze[row][col-1] != maze[row][col+1]) {
                       
                fill(row, col-1, maze[row][col-1], maze[row][col+1]);
                maze[row][col] = maze[row][col+1];
                putSquare(row,col,emptyCode);
                try { wait(speedSleep); }
                catch (InterruptedException e) { }
             }
            else if (row % 2 == 0 && maze[row-1][col] != maze[row+1][col]) {
                     
                fill(row-1, col, maze[row-1][col], maze[row+1][col]);
                maze[row][col] = maze[row+1][col];
                putSquare(row,col,emptyCode);
                try { wait(speedSleep); }
                catch (InterruptedException e) { }
             }
    }

    void fill(int row, int col, int replace, int replaceWith) {
           
        if (maze[row][col] == replace) {
            maze[row][col] = replaceWith;
            fill(row+1,col,replace,replaceWith);
            fill(row-1,col,replace,replaceWith);
            fill(row,col+1,replace,replaceWith);
            fill(row,col-1,replace,replaceWith);
        }
    }

    boolean solveMaze(int row, int col) {
               
         if (maze[row][col] == emptyCode) {
             maze[row][col] = pathCode;      // add this cell to the path
             if (checkStatus() == TERMINATE)
                return false;
             putSquare(row,col,pathCode);
             if (row == rows-2 && col == columns-2)
                 return true;  // path has reached goal
             try { Thread.sleep(speedSleep); }
             catch (InterruptedException e) { }
             if ( (solveMaze(row-1,col) && checkStatus() != TERMINATE)  ||    
                  (solveMaze(row,col-1) && checkStatus() != TERMINATE)  ||     
                  (solveMaze(row+1,col) && checkStatus() != TERMINATE)  ||
                  solveMaze(row,col+1) )
                return true;
             if (checkStatus() == TERMINATE)
                return false;
             
             maze[row][col] = visitedCode;   
             putSquare(row,col,visitedCode);
             synchronized(this) {
               try { wait(speedSleep); }
               catch (InterruptedException e) { }
             }
             if (checkStatus() == TERMINATE)
                return false;
          }
          return false;
    }

}
