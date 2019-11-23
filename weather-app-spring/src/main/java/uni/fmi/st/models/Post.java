package uni.fmi.st.models;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties({ "owner" })
@Entity
@NamedQuery(name = "Post.findByPublicPost",
query = "select u from Post u where u.publicPost = true or u.owner = ?1")
public class Post implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String comment;
	private String place;
	private float temp;
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private User owner;
	private Boolean publicPost;

	public Post() {

	}

	public Post(String comment, String place, float temp) {
		super();
		this.comment = comment;
		this.place = place;
		this.temp = temp;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public float getTemp() {
		return temp;
	}

	public void setTemp(float temp) {
		this.temp = temp;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	/**
	 * @return the publicPost
	 */
	public Boolean getPublicPost() {
		return publicPost;
	}

	/**
	 * @param publicPost the publicPost to set
	 */
	public void setPublicPost(Boolean publicPost) {
		this.publicPost = publicPost;
	}

}
