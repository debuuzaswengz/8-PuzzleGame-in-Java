import java.util.*;


public class PuzzleGame
{
  public static ArrayList<ArrayList<Integer>> population = new ArrayList<ArrayList<Integer>>();
  public static ArrayList<Float> fitnessArray = new ArrayList<Float>();
  public static Integer GoalArray[] = {1,2,3,8,0,4,7,6,5};
  public static int count =0;
  public static int generation =0;
  public static int [] index_Array = new int [9]; 
  public static void main (String [] args)
  {
    int w=0;
    DataEntry();
    while (w!=9)
    {
     Integer well [] = Mutation();
     if(well.length!=1)
     {
     for (int i =0; i<well.length;i++)
     {
       if(well[i]==GoalArray[i])
       {
         w=w+1;
       }
     }
     if (w==9)
     { 
       System.out.println("Your Goal");
       System.out.println("------------------------");
       BoarderBox(well);
       System.out.println("------------------------");
     }
     else 
     {
       Population(well);
       w=0;
     } 
    }
    else 
    {
      System.out.println("Sorry game is over");
      break;
    }
  }
  }
  /** The boader Box it print the tiles of the chromosome */
  public static void BoarderBox(Integer v[])
  {
    Integer arr[][]= new Integer[7][6];
    int f=0;
    for(int a= 0; a<arr.length;a++)
    {
      System.out.print(" | ");
      for (int b=0; b<arr[a].length;b++)
      {
        if(b%2!=0)
        {
          System.out.print(" | ");
        }
        else if (b%2==0&& a%2==0)
        {
          System.out.print(" - ");
        }
        else
        {
          if (b%2==0)
          {
            arr[a][b]=v[f];
            f=f+1;
            System.out.print(" "+arr[a][b]+" ");

          }
        }
      }
        System.out.println("");
    }
  }
  
  public static void DataEntry()
  {
    Scanner x = new Scanner(System.in);
    int value;
    Integer initialArray[] = new Integer[9];
    System.out.println("Please int the Initial State of the Puzzle: Digits from 0-8");
      for (int i=0;i<initialArray.length;i++)
      {
        do {
            initialArray[i]=x.nextInt();

        } while (initialArray[i]<0 || initialArray[i] >8);
      }
      System.out.println("");
      moves(initialArray);
  
    }
    /** The population method takes Integer Array as an argument and add it into the population Arraylist as
     *  well as it fitness Value  */

   public static void Population(Integer arrs[])
  {
    if (!population.contains(arrs))
    {
    population.add(count,new ArrayList<>(Arrays.asList(arrs)));
    fitnessArray.add(FitnessCalculation(arrs));
    count = count+1;
    }
  }
/**the SelectionOne method is a returning method which is ruturning an integer Array. Its select each chromosomes based 
 * on there fitness value of 0.1 as basic one. the value of y is the value that check if there is a chromosomes to 
 * to cross with or what, if not the method return an Integer array of size one to the crossing position. If the chromosome
 * is selected into its fitness value is set to be zero. Since each each chromosomes as one chance of selection.
 * 
 * All this implies into SelectionTwo method
  */
  public static Integer [] selectionOne()
  {
    int a,y=0;
    float c=0;
    Integer p [] = new Integer [1];
    for (int u=0; u<fitnessArray.size();u++)
    {
      if (fitnessArray.get(u)>=0.1)
        {
          y=y+1;
        }
        if (y==2)
        {
          break;
        }
    }
    if (y==2)
    {
      for (a = 0; a<fitnessArray.size();a++)
      {
        if(fitnessArray.get(a)>= 0.1)
        {
          fitnessArray.set(a,c);
          break;
        }
    }
     int chromoSize1 = population.get(a).size();
     Integer chromesomeOne [] = new Integer [chromoSize1];
     for (int i= 0;i<chromesomeOne.length;i++)
     {
       chromesomeOne[i]=population.get(a).get(i);
     }
     if(FitnessCalculation(chromesomeOne)!=0)
     {
      return chromesomeOne;
     }
     else 
     {
       return p;
     }
  }
  y=0;
  return p;
}
  public static Integer [] selectionTwo()
  {
    int b;
    int y=0;
    float d =0;
    Integer r [] = new Integer [1];
    for (int u=0; u<fitnessArray.size();u++)
    {
      if (fitnessArray.get(u)>=0.1)
        {
          y=y+1;
        }
        if (y==2)
        {
          break;
        }
    }
    if (y==2)
    {
      
    for ( b = 0; b<fitnessArray.size();b++)
    {
      if(fitnessArray.get(b)>= 0.1)
      {
        fitnessArray.set(b,d);
       
        break;
      }
    }
    int chromoSize2 = population.get(b).size();
     Integer chromesomeTwo [] = new Integer [chromoSize2];
     for (int i= 0;i<chromesomeTwo.length;i++)
     {
       chromesomeTwo[i]=population.get(b).get(i);
     }
     return chromesomeTwo;
    }
    y=0;
    return r;

  }
  /**Crossing Position method is a returning method that first chech if there is a chromosome which have been selected by 
   * selection method one and two , if not the method return a method array s into the Mutation and then teminated. 
   * Secondly, the value i is the one which is used to select the crossing position randomly from 2 to 6 values. I make my 
   * program to bear one child not two so that the it does not take time to run. After the crossing the method return a array of Integer
   * holdong the child chromosomes to the Mutattion Method.
   */
  public static Integer []  crossingPostion()
  {
    
    Integer s [] = new Integer [1];
    
    if (selectionOne().length!=1 && selectionTwo().length!=1)
    {
      Integer chomosome1[]=selectionOne();
    Integer chomosome2[]= selectionTwo();
    Integer child []= new Integer [9] ;
     int i =4 + (int)(Math.random()*2);
     
    int a=0,b;
    
    for( a=0;a<=i;a++)
    {
      child[a]=chomosome1[a];
    }
    for( b=a;b<chomosome2.length;b++)
    {
      child[b]=chomosome2[b];
    }
    b=0;
    
      return child;
    }
  
    return s;
  }
  /** the fitness calculation method calculate the fitness and return its value */
  public static float FitnessCalculation(Integer chrome[])
  {
    float fit=0;
    for (int a= 0; a<chrome.length;a++)
    {
      if(chrome[a]== GoalArray[a])
      {
        fit=fit+1;
      }
    }
    return fit/9;
  }
  /** The repeatation check method check if there is a repeat numbers in the array and return the number of repeatation 
   * it takes the array as argument also so.
   */
  public static int repeatCheck(Integer mutat[])
  {
    int counter =0,m=0;
    for(int k=0;k<mutat.length;k++)
    {
      for(int j=0;j<mutat.length;j++)
      {
        if (j!=k)
        {
          if (mutat[k]==mutat[j])
          {
            index_Array[m]=k;
            counter=counter+1;
            m=m+1;
          }
        }
    }
    }
    return counter;
    }
    /**The wrong Item check method return the an array that contain wrong Items only and it even accept and array as a argument
     * 
     */
    public static Integer [] wrongItemCheck(Integer wrongArray[])
    {
      Integer wrongA [] = new Integer [9];
      int m=0;
      for (int t=0;t<wrongArray.length;t++)
      {
        if (wrongArray[t]!=GoalArray[t])
        {
          wrongA[m]= wrongArray[t];
          m=m+1;
        }
      }
      return wrongA;
    }
    /** The Mutation method envoke the crossing method and assign it to an new Integer array that hold everything avout the 
     * array. it even asing a value called valueS by calling repeatcheck method to  it so that it checks how many repeat in the 
     * after crosing has taken places.it have an array called justArray which store temporary items to help that the mutation take place
     * 
     */
    public static Integer [] Mutation()
    {
      
      Integer m [] = new Integer [1];
      if (crossingPostion().length!=1)
      {
        Integer offspring[]= crossingPostion();
        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(offspring));
        int valueS= repeatCheck(offspring);
        Integer justArray []= {0,1,2,3,4,5,6,7,8};
        if (valueS==0)
        {
          Integer offspring1 [] = wrongItemCheck(offspring);
          for (int f=0;f<2;f++)
          {
            for (int h=0;h<offspring.length;h++)
            {
              if (offspring1[f]==GoalArray[h])
            {
              offspring[h]=offspring1[f];
            }
          }
        }
        
      }
      else if(valueS==2)
      {
        for (int j=0;j<list.size();j++)
        {
        if (!list.contains(justArray[j]))
        {
          for (int n=0;n<1;n++)
          {
          int g=index_Array[n];
          if (offspring[g]!=GoalArray[g])
          {
            offspring[g]=justArray[j];
          }
          else 
          {
            int t=index_Array[n+1];
            offspring[t]=justArray[t];
          }
          }
            break;
          }
        }
      }
      else if (valueS==4)
      {
        int n=0;
        for (int j=0;j<list.size();j++)
        {
        if (!list.contains(justArray[j]))
        {
          
          int g=index_Array[n];
          if (offspring[g]!=GoalArray[g])
          {
            offspring[g]=justArray[j];
          }
          n=n+1;
          }
        }

      }
      Integer offspring2 [] = wrongItemCheck(offspring);
          for (int f=0;f<2;f++)
          {
            for (int h=0;h<offspring.length;h++)
            {
              if (offspring2[f]==GoalArray[h])
            {
              offspring[h]=offspring2[f];
            }
          }
        }
      
      return offspring;

      }

      return m;
  }
  /** the moves method check the zero tile into the puzzel and store each and every move inot the population  */
  public static void moves( Integer array[])
  {

    for (int a=0; a<24;a++)
    {
      if(array[0]== 0)
        {
          for (int k=0;k<1;k++)
          {
            int sum,temp;
            sum=array[0];
            temp = array[1];
            array[0]=temp;
            array[1]=sum;
            Population(array);
        }
        for (int k=0;k<1;k++)
        {
          int sum,temp,temp2;
          sum=array[3];
          temp = array[1];
          temp2=array[0];
          array[0]=sum;
          array[3]=temp;
          array[1]=temp2;
          Population(array);
          
        }
      }

     if(array[2]== 0)
      {
        for (int k=0;k<1;k++)
        {
          int sum,temp;
          sum=array[2];
          temp = array[1];
          array[2]=temp;
          array[1]=sum;
          Population(array);
          

        }
        for (int k=0;k<1;k++)
        {
          int sum,temp,temp2;
          sum=array[5];
          temp = array[1];
          temp2= array[2];
          array[5]=temp;
          array[2]=sum;
          array[1]=temp2;
          Population(array);
        }
    }
     
     if(array[6]== 0)
    {
      for (int k=0;k<1;k++)
      {
        int sum,temp;
        sum=array[6];
        temp = array[3];
        array[6]=temp;
        array[3]=sum;
        Population(array);
      }
      for (int k=0;k<1;k++)
      {
        int sum,temp,temp2;
        sum=array[7];
        temp = array[3];
        temp2= array[6];
        array[7]=temp;
        array[6]=sum;
        array[3]=temp2;
        Population(array);
        
      }

  }
  
   if(array[8]== 0)
  {
    for (int k=0;k<1;k++)
    {
      int sum,temp;
      sum=array[8];
      temp = array[5];
      array[8]=temp;
      array[5]=sum;
      Population(array);
      

    }
    for (int k=0;k<1;k++)
    {
      int sum,temp,temp2;
      sum=array[7];
      temp = array[5];
      temp2= array[8];
      array[7]=temp;
      array[8]=sum;
      array[5]=temp2;
      Population(array);
      
    }
}
 if (array[1]==0)
{
  for (int k=0;k<1;k++)
  {
    int sum,temp;
    sum=array[1];
    temp = array[0];
    array[1]=temp;
    array[0]=sum;
    Population(array);
    }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[1];
    temp = array[0];
    temp2= array[2];
    array[2]=temp;
    array[0]=sum;
    array[1]=temp2;

    Population(array);
    

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[4];
    temp = array[2];
    temp2= array[1];
    array[4]=temp;
    array[1]=sum;
    array[2]=temp2;
    Population(array);
    
  }
}
if(array[3]==0)
{
  for (int k=0;k<1;k++)
  {
    int sum,temp;
    sum=array[3];
    temp = array[0];
    array[3]=temp;
    array[0]=sum;
    Population(array);
    

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[3];
    temp = array[0];
    temp2= array[6];
    array[6]=temp;
    array[0]=sum;
    array[3]=temp2;

    Population(array);
    

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[4];
    temp = array[6];
    temp2= array[3];
    array[4]=temp;
    array[3]=sum;
    array[6]=temp2;
    Population(array);
    
  }
}
if(array[5]==0)
{
  for (int k=0;k<1;k++)
  {
    int sum,temp;
    sum=array[5];
    temp = array[2];
    array[5]=temp;
    array[2]=sum;
    
    Population(array);

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[5];
    temp = array[2];
    temp2= array[8];
    array[8]=temp;
    array[2]=sum;
    array[5]=temp2;

    Population(array);

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[4];
    temp = array[8];
    temp2= array[5];
    array[4]=temp;
    array[5]=sum;
    array[8]=temp2;

    Population(array);
  }
}

if(array[7]==0)
{
  for (int k=0;k<1;k++)
  {
    int sum,temp;
    sum=array[7];
    temp = array[6];
    array[7]=temp;
    array[6]=sum;

    Population(array);

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[7];
    temp = array[6];
    temp2= array[8];
    array[8]=temp;
    array[6]=sum;
    array[7]=temp2;

    Population(array);

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[4];
    temp = array[8];
    temp2= array[7];
    array[4]=temp;
    array[7]=sum;
    array[8]=temp2;

    Population(array);
  }
}
if (array[4]==0)
{
  for (int k=0;k<1;k++)
  {
    int sum,temp;
    sum=array[4];
    temp = array[1];
    array[4]=temp;
    array[1]=sum;

    Population(array);
  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[4];
    temp = array[3];
    temp2= array[1];
    array[4]=temp;
    array[1]=sum;
    array[3]=temp2;

    Population(array);

  }
  for (int k=0;k<1;k++)
  {
    int sum,temp,temp2;
    sum=array[4];
    temp = array[7];
    temp2= array[3];
    array[4]=temp;
    array[3]=sum;
    array[7]=temp2;

    Population(array);

  }
  for(int h=0;h<1;h++)
  {
    int sum1,temp1,temp2;
    sum1= array[4];
    temp1=array[7];
    temp2=array[5];
    array[4]=temp2;
    array[7]=sum1;
    array[5]=temp1;

    Population(array);
  }
}
}
}
  public static void GoalState()
  {

    Scanner x = new Scanner(System.in);
    System.out.println("Please int the Goal State of the Puzzle: Digits from 0-8");
      for (int i=0;i<GoalArray.length;i++)
      {
        do {
            GoalArray[i]=x.nextInt();

        } while (GoalArray[i]<0 || GoalArray[i] >8);
      }
  }
  }
