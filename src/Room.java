import java.util.*;

public class Room
{
	private int wall = 0;


	private int obstacle = 1;
	private int floor = 2;
	private int door = 3;
	private int agentsPlaced = 0;
	ArrayList<Agent> queue = new ArrayList<Agent>();
	private int totalFloors = 0;
	//    0 1 2 3
	//    _ _ _ _
	// 0 | | | | |  x = (1,2)
	// 1 | | | | |
	// 2 | |x| | |
	// 3 | | | | |
	private Space[][] space;
	public Room()
	{	
		test();
	}

	public void initiateRoom()
	{
		ArrayList<Integer> priorities = new ArrayList<Integer>();

		for(int i = 0; i < space.length; i++)
		{
			for(int j = 0; j < space[i].length; j++)
			{
				if(space[i][j].hasAgent())
				{
					int temp = space[i][j].getAgent().getPriority();
					if(!priorities.contains(temp))
					{
						priorities.add(temp);
					}
				}
			}
		}

		while(priorities.isEmpty())
		{
			int highest = getHighest(priorities);
			for(int i = 0; i < space.length; i++)
			{
				for(int j = 0; j < space[i].length; j++)
				{
					if(space[i][j].hasAgent())
					{
						if(space[i][j].getAgent().getPriority() == highest)
						{
							queue.add(space[i][j].getAgent());
						}
					}
				}
			}	
		}

	}

	private int getHighest(ArrayList<Integer> in)
	{
		Iterator<Integer> iterator = in.iterator();
		int temp = 0;
		while(iterator.hasNext())
		{
			int tempNext = iterator.next();
			if(tempNext >= temp)
			{
				temp = tempNext;
			}
		}

		in.remove((Integer)(temp));
		return temp;
	}

	public void updateRoom()
	{

	}

	private void test()
	{
		space = new Space[20][20];

		for(int i = 0; i < space.length; i++)
		{
			space[0][i] = new Space(wall);
			space[space.length - 1][i] = new Space(wall);
		}
		for(int i = 0; i < space.length; i++)
		{
			space[i][space.length - 1] = new Space(wall);
		}

		for(int i = 0; i < space.length; i++)
		{
			if(i == 9)
			{
				space[i][0] = new Space(door);
			}
			else
			{
				space[i][0] = new Space(wall);
			}
		}

		for(int i = 1; i < space.length -1; i++)
		{
			for(int j = 1; j < space[i].length -1; j++)
			{
				totalFloors++;
				space[i][j] = new Space(floor);
			}
		}
	}

	public void setAgent(String agentType)
	{
		agentsPlaced++;
		Random random = new Random();
		ArrayList<Location> available = new ArrayList<Location>();

		for(int i = 0; i < space.length; i++)
		{
			for(int j = 0; j < space[i].length; j++)
			{
				if((space[i][j].getType() == 1 || space[i][j].getType() == 2) && (!space[i][j].hasAgent()))
				{
					available.add(new Location(i,j));
				}
			}
		}

		int index = random.nextInt(available.size());

		Agent temp = createAgent(agentType);
		space[available.get(index).getX()][available.get(index).getY()].setAgent(temp);
	}

	private Agent createAgent(String agentType)
	{
		if(agentType.compareToIgnoreCase("C") == 0)
		{
			return new CalmAgent();
		}
		else
		{
			return new PanickedAgent();	
		}
	}

	public boolean full()
	{
		return (agentsPlaced == totalFloors);
	}

	public String toString()
	{
		String test = "";
		for(int i = 0; i < space.length; i++)
		{
			for(int j = 0;  j < space.length; j++)
			{
				test = test + space[i][j];
			}
			test = test +"\n";
		}
		return test;
	}
}