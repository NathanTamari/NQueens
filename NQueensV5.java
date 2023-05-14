// Title: NQueensV5
  // Author: Nathan Tamari
  // Algorithim for NQueens. Uses backtracking to solve NQueens problem


public class NQueensV5
{
  private static int[][] array, unusableStarters, tempArray;
  private static boolean done;
   
  //++++++++++++++++++++++++++++++++++++++++++++++
  // Function Starter
  //++++++++++++++++++++++++++++++++++++++++++++++
  public static int[][] starter(int size)
  {
    
    array = new int[size][size];
    unusableStarters = new int[size][size];
    tempArray = new int[size][size];
    
    for(int i=0;i<size;i++)
    {
      for(int j=0;j<size;j++)
      {
        array[i][j] = 0;
      }
    }
    done = false;
    solve(0,0,array, size);
    return tempArray;
  }
  //++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
  public static void solve(int x, int y, int[][] array, int n)
  {
    if (done!=true)
    {
      // 0= possible
      // 1= Solution
      // 2= avoid
      
      if(isCorrect(array, n)) {    
        done = true;
        return;
      }
      
      
      //initially clears 2's
      int clearTwos=-1;
      for(int i=0;i<n;i++)
      {
        if(array[n-1][i]==2) clearTwos=i;
      }
      //cleartwos is the column that the 2's are on
      if(clearTwos>-1){
        for(int i=0;i<n;i++)
        {
          array[i][clearTwos]=0;
        }
        //changes previous row (backtrack)
        int placeholder=-1;
        for(int i=0;i<n;i++)
        {
          if(array[i][clearTwos-1]!=0) {   
            placeholder=i;
          }
        } 
        if(placeholder+1<n && placeholder>-1) {
          array[placeholder][clearTwos-1]=2;
          solve(placeholder+1,clearTwos-1,array, n);
        }
        else {
          
          /////////////
          //
          ////////////////
          // clear twos
          
          if(clearTwos!=-1) {
            for(int i=0;i<n;i++)
            {
              array[i][clearTwos]=0;
            }
            
            
            for(int i=0;i<n;i++)
            {
              array[i][clearTwos-1]=0;
            }
            
            int numberToChange=-1;
            for(int i=0;i<n;i++)
            {
              if(array[i][clearTwos-2]==1) numberToChange=i;
            }
            array[numberToChange][clearTwos-2]=2;
            
            for(int i = 0; i<n; i++)
            {
              for(int j = 0;j<n;j++)
              {
                if(j>clearTwos-2)
                  array[j][i]=0;
              }
            }
            
            
            solve(numberToChange+1,clearTwos-2,array, n);
            
            //////////////////
            //
            /////////////
          }
          
          
        }
      }
      
      
      if (x>n-1) {
        
        /////////////
        ///
        ///////////////
        
        //finds rightmost columns and tries a diff number
        int lastCol=0;
        int smallCounter =0;
        for(int i=0;i<n;i++)
        {
          for(int j=0;j<n;j++)
          {
            if (array[j][i]==1) smallCounter++;
          }                     
          if (smallCounter!=0) {
            lastCol=i;
            smallCounter=0;
          }
        }
        int lastRow=0;
        //lastCol = final column with a succesfully found value in it
        //lastRow = same thing
        for(int h=0;h<n;h++)
        {
          if(array[h][lastCol]==1) lastRow = h; 
        }
        
        array[lastRow][lastCol] = 2;
        if(lastRow+1<n) {
          
          
          solve(lastRow+1,lastCol,array, n);
          
          
        }
        
        
        if(lastRow==n-1 && lastCol==1) {
          //this column has no possible solution and we must try a different value for the x value of the array
          int cantUse=0;
          for(int i=0;i<n;i++)
          {
            if(array[i][0]==1) cantUse=i;
          }
          reset(cantUse, n);//try something else
        }
        else if(lastRow==n-1 && lastCol>n-1) 
        {
          
        }
        
        else {
          solve(0,lastCol+1,array,n);
        }
      }
      
      ////////////////////
      // To set Values
      ///////////////////
      if(x==n)
      {
        x=0;
      }      
      ////////////////////////////
      
      //check horiz
      for(int i=0;i<n;i++)
      {
        //checks horizontal (vertical is already checked by going column by column)
        if(array[x][i] == 1)  {
          solve(x+1,y,array, n);      
        }
      }
      
      //check diagonal BR-->TL 
      int i = 0;
      while (x-i>-1 && y-i>-1) {
        if(array[x-i][y-i] ==1) solve(x+1,y,array,n);
        i++;
      }
      
      
      //TR-->BL
      
      i = 0;
      while (x+i<n && y-i>-1) {
        if(array[x+i][y-i] ==1) solve(x+1,y,array,n);
        i++;
      }
      
      //TL-->BR
      i = 0;
      while (x+i<n && y+i<n) {
        if(array[x+i][y+i] ==1) solve(x+1,y,array,n);
        i++;
      }
      
      //BL-->TR
      i = 0;
      while (x-i>-1 && y+i<n) {
        if(array[x-i][y+i] ==1) solve(x+1,y,array,n);
        i++;
      }
      
      array[x][y] = 1;
      solve(0,y+1,array,n);
    }
  }
  
  public static boolean isCorrect(int[][] arrayCheck, int n)
    // Checks to see if every row is filled with a number.
  {
    int counter=0;
    for(int i=0;i<n;i++)
    {
      for(int j=0;j<n;j++)
      {
        if(arrayCheck[i][j]==1) counter++;
      }
    }
    if (counter==n) {
      for(int h = 0; h<n; h++)
      {
        for(int j = 0;j<n;j++)
        {
          tempArray[h][j]=array[h][j];
        }
      }     
      return true;
    }
    else return false;
    
  }
  
  public static void reset(int unusable, int n)
  {
    unusableStarters[unusable][0]=2;
    for(int i=0;i<n;i++)
    {
      for(int j=0;j<n;j++)
      {
        if(unusableStarters[i][j]==2) array[i][j]=2;
        else array[i][j]=0; 
      }
    }
    solve(unusable+1,0,array, n);
  }
  

}