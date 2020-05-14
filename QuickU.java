
public class QuickU 
{
	private int[] id;
	
	public QuickU(int n) 
	{
		id = new int[n];
		for(int i = 0; i < n; i++) 
		{
			id[i] = i;
		}
	}
	private int root(int i) 
	{
		while (i != id[i])
		{
			i = id[i];
		}
		return i;
	}
	public boolean connected(int firstNode, int secondNode) 
	{
		return root(firstNode) == root(secondNode);
	}
	public void union(int firstNode, int secondNode) 
	{
		int fRoot = root(firstNode);
		int sRoot = root(secondNode);
		if(firstNode == secondNode)
			return;
		id[fRoot] = sRoot;
	}

}
