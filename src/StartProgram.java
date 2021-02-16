import java.util.List;
import java.util.Scanner;

import controller.GamesHelper;
import model.Games;

/**
 * @author Snipe - iwertz
 * CIS175 - Spring 2021
 * Feb 15, 2021
 */
public class StartProgram {

	static Scanner in = new Scanner(System.in);
	static GamesHelper gh = new GamesHelper();
	
	private static void addAGame() {
		// TODO Auto-generated method stub
		System.out.print("Enter a game: ");
		String game = in.nextLine();
		System.out.print("Enter a rating: ");
		String rating = in.nextLine();
		Games toAdd = new Games(game, rating);
		gh.insertItem(toAdd);
	}
	
	private static void deleteAGame() {
		// TODO Auto-generated method stub
		System.out.print("Enter the game to delete: ");
		String game = in.nextLine();
		System.out.print("Enter the rating to delete: ");
		String rating = in.nextLine();
		Games toDelete = new Games(game, rating);
		gh.deleteItem(toDelete);
	}
	
	private static void editAGame() {
		// TODO Auto-generated method stub
		System.out.println("How would you like to search? ");
		System.out.println("1 : Search by Game");
		System.out.println("2 : Search by Rating");
		int searchBy = in.nextInt();
		in.nextLine();
		List<Games> foundGames;
		if (searchBy == 1) {
			System.out.print("Enter the game name: ");
			String gameName = in.nextLine();
			foundGames = gh.searchForItemByStore(gameName);
		} else {
			System.out.print("Enter the rating: ");
			String ratingNum = in.nextLine();
			foundGames = gh.searchForItemByItem(ratingNum);
		}

		if (!foundGames.isEmpty()) {
			System.out.println("Found Results.");
			for (Games g : foundGames) {
				System.out.println(g.getId() + " : " + g.toString());
			}
			System.out.print("Which ID to edit: ");
			int idToEdit = in.nextInt();

			Games toEdit = gh.searchForItemById(idToEdit);
			System.out.println("Retrieved " + toEdit.getGame() + " from " + toEdit.getRating());
			System.out.println("1 : Update Game");
			System.out.println("2 : Update Rating");
			int update = in.nextInt();
			in.nextLine();

			if (update == 1) {
				System.out.print("New Game: ");
				String newGame = in.nextLine();
				toEdit.setGame(newGame);
			} else if (update == 2) {
				System.out.print("New Rating: ");
				String newRating = in.nextLine();
				toEdit.setRating(newRating);
			}

			gh.updateItem(toEdit);

		} else {
			System.out.println("---- No results found");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		runMenu();

	}

	public static void runMenu() {
		boolean goAgain = true;
		System.out.println("--- Welcome to our awesome games rating list! ---");
		while (goAgain) {
			System.out.println("*  Select a game:");
			System.out.println("*  1 -- Add a game");
			System.out.println("*  2 -- Edit a game");
			System.out.println("*  3 -- Delete a game");
			System.out.println("*  4 -- View all games");
			System.out.println("*  5 -- Exit");
			System.out.print("*  Your selection: ");
			int selection = in.nextInt();
			in.nextLine();

			if (selection == 1) {
				addAGame();
			} else if (selection == 2) {
				editAGame();
			} else if (selection == 3) {
				deleteAGame();
			} else if (selection == 4) {
				viewTheList();
			} else {
				gh.cleanUp();
				System.out.println("   Goodbye!   ");
				goAgain = false;
			}

		}

	}

	private static void viewTheList() {
		// TODO Auto-generated method stub
		List<Games> allGames = gh.showAllItems();
		for(Games singleGame : allGames){
			System.out.println(singleGame.returnGameDetails());
		}

	}
	
}
