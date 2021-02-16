package model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Snipe - iwertz
 * CIS175 - Spring 2021
 * Feb 15, 2021
 */

@Entity
@Table(name="games")
public class Games {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ID")
	private int id;
	@Column(name="GAME")
	private String game;
	@Column(name="RATING")
	private String rating;
	
	public Games() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Games(String game, String rating) {
		super();
		this.game = game;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}
	
	public String returnGameDetails( ) {
		return game + ": " + rating + " stars";
	}
	
}
