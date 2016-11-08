/**
 * 
 * 'WordPuzzleSolver.java' both reads in and solves a Word Puzzle that is selected by the user.
 * The puzzles are in .txt format and read in with a BufferedReader and Scanner. There are several helper methods that
 * check Up, Down, Left, Right, Up-left, Up-right, Down-left, and Down-right for the word it is looking for. If the word
 * is found, it prints out ""word" was found at row "<row_num>", column "<col_num>" going "<direction>." However, if the
 * word is not found in the puzzle, it prints out ""word" was not found."
 * 
 * @author Jared Meyer
 */

import java.io.*;
import java.util.LinkedList;
import java.util.Scanner;

public class WordPuzzleSolver implements PuzzleSolver {
	char puzzle[][];
	LinkedList<String> answers;
    int rowSize;
    int colSize;
    String word;
    
    /**
     * Scans the file and then creates a puzzle[][] char array with the sizes given 
     * in the first line. It then adds each letter of the puzzle to a position
     * in the array. Then, it uses a LinkedList to add all the "answers" we are looking for
     * in the puzzle.
     * 
     * @param inFile File selected by user
     * 
     */
    public void readFile(File inFile) {
    	try {
    		FileInputStream instream = new FileInputStream(inFile);
    		BufferedReader reader = new BufferedReader(new InputStreamReader(instream));
    		
    		String line = reader.readLine();
    		Scanner scanner = new Scanner(line);
    		rowSize = scanner.nextInt();
    		colSize = scanner.nextInt();
    		
    		puzzle = new char[rowSize][colSize];
    		int rowCount = 0;
    	    int colCount = 0;
    	    
    	    line = reader.readLine();
    		for (int i = 0; i < rowSize; i++)	
    		{
    			scanner = new Scanner(line);
    			while (scanner.hasNext()) {
    				String word = scanner.next();
    				for (int k = 0; k < colSize; k++)	{
    					char letter = word.charAt(k);
    					puzzle[rowCount][colCount] = letter;
    					colCount++;
    				}
    			}
    			colCount = 0;
    			rowCount++;
    			line = reader.readLine();   			
    		} 
    		answers = new LinkedList<String>();
    		
    		while (line != null)	{
    			scanner = new Scanner(line);
    			while (scanner.hasNext())	{
    				String word = scanner.next();
    				answers.add(word);
    		}
    		line = reader.readLine();
    	}
    		scanner.close();
    		
    		reader.close();    		
    	} catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		ex.printStackTrace();
    	}
    }

    
    /**
     * solvePuzzle is called after readFile is done reading the file and storing the content into variables.
     * This method uses several helper methods to check whether the current word in the 'answers' list is
     * found in the Word Puzzle.
     */
	public void solvePuzzle() {

		for (int i = 0; i < answers.size(); i++) {
			word = answers.get(i);
			boolean isFound = false;
			
			for (int r = 0; r < puzzle.length; r++) {
				for (int c = 0; c < puzzle[r].length; c++) {
					
					if (puzzle[r][c] == word.charAt(0)) {

						if (checkRight(r, c, word)) {
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going R.");
							isFound = true;
						}
						
						if (checkLeft(r, c, word))	{
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going L.");
							isFound = true;
						} 
						
						if (checkDown(r, c, word))	{
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going D.");
							isFound = true;	
						}
						
						if (checkUp(r, c, word))	{
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going U.");
							isFound = true;	
						}
						
						if (checkDL(r, c, word))	{
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going DL.");
							isFound = true;	
						}
						
						if (checkDR(r, c, word))	{
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going DR.");
							isFound = true;	
						}
						
						if (checkUR(r, c, word))	{
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going UR.");
							isFound = true;	
						}
						
						if (checkUL(r, c, word))	{
							System.out.println("\"" + word + "\"" + " was found at row " + r + ", column " + c +", going UL.");
							isFound = true;	
						}
					}
				}
			}
			if (!isFound)	{
				System.out.println(word + " was not found.");
			}
		}
    }
    
    //Checks if the given word is going RIGHT in the puzzle
    private boolean checkRight(int row, int col, String word)	{
    	
    	int y = col;
    	for (char ch : word.toCharArray())	{
    		
    		if (y >= puzzle[row].length)	{
    			return false;    			
    		}
    		
    		if (puzzle[row][y] != ch)	{
    			return false;
    		}
    		y++;
    	}
    	return true;
    }
    
    //Checks if the given word is going LEFT in the puzzle
    private boolean checkLeft(int row, int col, String word)	{
    	
    	int y = col;
    	for (char ch : word.toCharArray())	{
    		
    		if (y < 0)	{
    			return false;    			
    		}
    		if (puzzle[row][y] != ch)	{
    			return false;
    		}
    		y--;
    	}
    	return true;
    }
    
    //Checks if the given word is going DOWN in the puzzle
    private boolean checkDown(int row, int col, String word)	{
    	
    	int x = row;
    	for (char ch : word.toCharArray())	{
    		
    		if (x >= puzzle[col].length)	{
    			return false;    			
    		}
    		if (puzzle[x][col] != ch)	{ 		//ERROR HERE
    			return false;
    		}
    		x++;
    	}
    	return true;
    }
    
    //Checks if the given word is going UP in the puzzle
    private boolean checkUp(int row, int col, String word)	{
    	int index = col;
    	for (char ch : word.toCharArray())	{
    		
    		if (row < 0)	{
    			return false;    			
    		}
    		if (puzzle[row][index] != ch)	{
    			return false;
    		}
    		row--;
    	}
    	return true;
    }
    
    //Checks if the given word is going DOWN-LEFT in the puzzle
    private boolean checkDL(int row, int col, String word)	{
    	int x = row;
    	int y = col;
    	for (char ch : word.toCharArray())	{
    		if (x >= puzzle[col].length)	{
    			return false;
    		}
    		if (y < 0)	{
    			return false;
    		}
    		if (puzzle[x][y] != ch)	{
    			return false;
    		}
    		x++;
    		y--;
    	}
    	return true;
    }
    
    //Checks if the given word is going DOWN-RIGHT in the puzzle
    private boolean checkDR(int row, int col, String word)	{
    	int x = row;
    	int y = col;
    	for (char ch : word.toCharArray())	{
    		if (x >= puzzle[col].length)	{
    			return false;
    		}
    		if (y >= puzzle[row].length)	{
    			return false;
    		}
    		if (puzzle[x][y] != ch)	{
    			return false;
    		}
    		x++;
    		y++;
    	}
    	return true;
    }
 
    //Checks if the given word is going UP-RIGHT in the puzzle
    private boolean checkUR(int row, int col, String word)	{
    	int x = row;
    	int y = col;
    	for (char ch : word.toCharArray())	{
    		if (x < 0)	{
    			return false;
    		}
    		if (y >= puzzle[row].length)	{
    			return false;
    		}
    		if (puzzle[x][y] != ch)	{
    			return false;
    		}
    		x--;
    		y++;
    	}
    	return true;
    }
    
    //Checks if the given word is going UP-LEFT in the puzzle
    private boolean checkUL(int row, int col, String word)	{
    	int x = row;
    	int y = col;
    	for (char ch : word.toCharArray())	{
    		if (x < 0)	{
    			return false;
    		}
    		if (y < 0)	{
    			return false;
    		}
    		if (puzzle[x][y] != ch)	{
    			return false;
    		}
    		x--;
    		y--;
    	}
    	return true;
    }
}
