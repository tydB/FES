// import java.util.ArrayList;
public class Agent
{
	private Location target = null;
	private Location position = null;
	private String type = "C";
	private int priority = 1;
	private boolean allowed = true;

	public Agent()
	{

	}

	// public Agent(Agent in)
	// {
	// 	target = null;
	// 	position = null;
	// 	type = "C";
	// 	priority = 1;
	// 	allowed = true;
	// }

	public void update()
	{

	}
	// when an agent first enters a room a target is set for the agent to path to
	// this target is the location of the next door it needs to use
	public void setTarget(Location t)
	{
		target = t;
	}
	public void setType(String t)
	{
		type = t;
	}
	public void setPriority(int i)
	{
		priority = i;
	}
	// this is not final
	public Location getNextMove(int doors,Space[][] room)
	{
		return position;
	}
	// if the room moves the agent update its Position
	public void updatePosition(Location p)
	{
		position = p;
	}
	// this is where pathfinding goes. It will probably be really hard
	public void ceatePath()
	{

	}
	public String toString()
	{
		return "Agent " + type + ": \n target: " + target + "\n position: " + position;
	}
	public String getType()
	{
		return type;
	}
	public int getPriority()
	{
		return priority;
	}

	public Location getPosition()
	{
		return position;
	}

	public void stop()
	{
		// System.out.println("Asda");
		// System.out.println(allowed);
		allowed = false;
		// System.out.println(allowed);
	}

	public void move()
	{
		allowed = true;
	}

	public boolean ableToMove()
	{
		return allowed;
	}
}