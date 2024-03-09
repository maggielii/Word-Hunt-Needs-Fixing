package code;

import java.util.Scanner;

public class WordHunt {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String word = input.next();
        int R = input.nextInt();
        int C = input.nextInt();
        
        char[][] grid = new char[R][C];
        String st = "";
        
        for (int x = 0; x < R; x++) 
        {
            st = input.next();
            grid[x] = st.toCharArray();
        }

        //display
      	for(int x = 0; x < grid.length; x++)
      	{
      		for(int y = 0; y < grid[0].length; y++)
      		{
      			System.out.print(grid[x][y] + " ");
      		}
      		System.out.println(" ");
      	}
      		
        int count = 0;
        for (int i = 0; i < R; i++) 
        {
            for (int j = 0; j < C; j++) 
            {
                if (grid[i][j] == word.charAt(0)) 
                {
                    count += flood(grid, word, i, j, 0, false, 0);
                }
            }
        }

        System.out.println(count);
    }

    private static int flood(char[][] grid, String word, int x, int y, int index, boolean bend, int direction) {
        if (index == word.length()) 
        {
            return 1;
        }
        if (x < 0 || y < 0 || x >= grid.length || y >= grid[0].length || grid[x][y] != word.charAt(index)) 
        {
            return 0;
        }

        int count = 0;
        grid[x][y] = 0;

        if(direction == 0 || direction == 1)
        {
        	count += flood(grid, word, x - 1, y, index + 1, bend, 1); // Up
        }
        if(direction == 0 || direction == 2)
        {
        	 count += flood(grid, word, x + 1, y, index + 1, bend, 2); // Down
        }
        if(direction == 0 || direction == 3)
        {
        	count += flood(grid, word, x, y - 1, index + 1, bend, 3); // Left
        }
        if(direction == 0 || direction == 4)
        {
        	count += flood(grid, word, x, y + 1, index + 1, bend, 4); // Right
        }
        if(direction == 0 || direction == 5)
        {
        	count += flood(grid, word, x - 1, y - 1, index + 1, bend, 5); // Up-Left
        }
        if(direction == 0 || direction == 6)
        {
        	count += flood(grid, word, x - 1, y + 1, index + 1, bend, 6); // Up-Right
        }
        if(direction == 0 || direction == 7)
        {
        	count += flood(grid, word, x + 1, y - 1, index + 1, bend, 7); // Down-Left
        }
        if(direction == 0 || direction == 8)
        {
        	count += flood(grid, word, x + 1, y + 1, index + 1, bend, 8); // Down-Right
        }

        if (!bend && index > 0) 
        {
        	if(direction == 1 || direction == 2 || direction == 3 || direction == 4)
        	{
	        	count += flood(grid, word, x - 1, y, index + 1, true, 1); // Up
	            count += flood(grid, word, x + 1, y, index + 1, true, 2); // Down
	            count += flood(grid, word, x, y - 1, index + 1, true, 3); // Left
	            count += flood(grid, word, x, y + 1, index + 1, true, 4); // Right
        	}
        	if(direction == 5 || direction == 6 || direction == 7 || direction == 8)
        	{
	        	count += flood(grid, word, x - 1, y - 1, index + 1, true, 5); // Up-Left
	            count += flood(grid, word, x - 1, y + 1, index + 1, true, 6); // Up-Right
	            count += flood(grid, word, x + 1, y - 1, index + 1, true, 7); // Down-Left
	            count += flood(grid, word, x + 1, y + 1, index + 1, true, 8); // Down-Right
        	}
        }

        grid[x][y] = word.charAt(index);
        return count;
    }
}


/*
NATURE
6
9
NATSFEGQN
SAIBMRHFA
CFTJCUCLT
KBHUPTANU
DPRRRJDIR
IEEKMEGBE
*/