import java.util.*;
import java.io.*;

class Analytics
{
	public static void main(String [] args) throws IOException
	{
		BufferedReader reader = new BufferedReader(new FileReader("Sample Input Data Set.csv"));
		String line = null ;
		Scanner scanner = null;
		int index = 0, lastnamel=0,dobl=0,genl=0,firstnamel=0;
		ArrayList<String> lastname = new ArrayList<String>();
		ArrayList<String> dob = new ArrayList<String>();
		ArrayList<String> firstname = new ArrayList<String>();  
		ArrayList<String> gen = new ArrayList<String>();  
		while ((line = reader.readLine()) != null) 
		{
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
			while (scanner.hasNext())
			{ 
				if (index == 0)
					lastname.add(scanner.next());
				else if (index == 1)
					dob.add(scanner.next());
				else if (index == 2)
					gen.add(scanner.next());
				else if (index == 3)
					firstname.add(scanner.next());
				else
					System.out.println("invalid data::" + scanner.next());
				index++;
			}
			index = 0;
		}	
		ArrayList<String> total = new ArrayList<String>();
		lastnamel = lastname.size();
		firstnamel = firstname.size();
		dobl =dob.size();
		genl = gen.size();
		if ((genl == dobl) && (lastnamel == firstnamel) && (firstnamel==genl)){}
		else
		{
			System.out.println("Some data is missing. Please update the CSV file.");
		}
		for(int i=0; i<dobl; i++)  
        	total.add(dob.get(i) +","+ gen.get(i) + ","+ firstname.get(i) + ","+ lastname.get(i));

        Collections.sort(total, Collections.reverseOrder());
        for(int i=1; i < (total.size() - 1) ; i++)
        {
        	StringTokenizer st1 = new StringTokenizer(total.get(i),(", "));
		    StringTokenizer st2 = new StringTokenizer(total.get(i+1),(", "));
		    int st1count = st1.countTokens(), st2count = st2.countTokens();
            if (st1.nextToken().equals(st2.nextToken()))
            {
            	if (st1.nextToken().equalsIgnoreCase(st2.nextToken()))
            	{
            		if (st1.nextToken().equalsIgnoreCase(st2.nextToken()))
            		{			
            			ArrayList<String> s1 = new ArrayList<String>();
            			ArrayList<String> s2 = new ArrayList<String>();
            			while(st1.hasMoreTokens())
            				s1.add(st1.nextToken());
            			while(st2.hasMoreTokens())
            				s2.add(st2.nextToken());
            			if(s2.size() == 0)
            			{
            			    total.remove(i+1);
            			    i--;
            			}
            			else if(s1.size() == 0)
            			{
            			    total.remove(i); 
            			    i--;
            			}
            			else if (s1.get(0).equalsIgnoreCase(s2.get(0)))
            			{
            				if(s2.size()<=1)
            				{
            					total.remove(i+1);
            					i--;
            				}
            				else if(s1.size()<=1)
            				{
            					total.remove(i);
            					i--;
            				}
            				else if((s1.get(1).indexOf(s2.get(1)) == 0) && (s2.get(1).length()<=2))
            				{
            					total.remove(i+1);
            					i--;
            				}
            				else if((s2.get(1).indexOf(s1.get(1)) == 0) && (s1.get(1).length()<=2))
            				{
            					total.remove(i);
            					i--;
            				}
            			}
            			else if((s1.get(0).indexOf(s2.get(0)) == 0) && (s2.get(0).length()<=2))
            			{
            				if(s2.size()<=1)
            				{
            					total.remove(i+1);
            					i--;
            				}
            				else if(s1.size()<=1)
            				{
            					total.remove(i);
            					i--;
            				}
            				else if((s1.get(1).indexOf(s2.get(1)) == 0) && ((s2.get(1).length()<=2) || (s1.get(1).equalsIgnoreCase(s2.get(1)))))
            				{
            					total.remove(i+1);
            					i--;
            				}
            			}
            			else if((s2.get(0).indexOf(s1.get(0)) == 0) && (s1.get(0).length()<=2))
            			{
            				if(s2.size()<=1)
            				{
            					total.remove(i+1);
            					i--;
            				}
            				else if(s1.size()<=1)
            				{
            					total.remove(i);
            					i--;
            				}
            				else if((s2.get(1).indexOf(s1.get(1)) == 0) && ((s1.get(1).length()<=2) || (s2.get(1).equalsIgnoreCase(s1.get(1)))))
            				{
            					total.remove(i);
            					i--;
            				}
            			}
            			else if ( s1.size() > s2.size())
            			{
            				if(s1.get(1).equals(s2.get(0)))
            				{
            					total.remove(i+1);
            					i--;
            				}
            				else if ((s1.get(1).indexOf(s2.get(0)) == 0) && (s2.get(0).length()<=2))
            				{
            					total.remove(i+1);
            					i--;
            				}
            			}
            			else if ( s2.size() > s1.size())
            			{
            				if(s2.get(1).equals(s1.get(0)))
            				{
            					total.remove(i);
            					i--;
            				}
            				else if ((s2.get(1).indexOf(s1.get(0)) == 0) && (s1.get(0).length()<=2))
            				{
            					total.remove(i);
            					i--;
            				}
            			}             
            		}
            	}		
            }
        }
        PrintWriter pw = new PrintWriter(new File("Sample Output Data Set.csv"));
        StringBuilder sb = new StringBuilder();
        for(int j=0; j< total.size(); j++)
        {
    		sb.append(total.get(j));
        	sb.append('\n');
        }
        System.out.println("A total of "+(genl - total.size())+" dupilcates have been removed and a new Sample Output Data Set.csv file has been created. Thank You.");
        pw.write(sb.toString());
        pw.close();
	}		  
 }  