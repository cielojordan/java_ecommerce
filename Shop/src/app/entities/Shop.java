package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Shop {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column
	Long idArticles;
	
	@Column
	String articleName;
	
	@Column
	Integer quantity;
	
	@Column
	Double articlePrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdArticles() {
		return idArticles;
	}

	public void setIdArticles(Long idArticles) {
		this.idArticles = idArticles;
	}

	public String getArticleName() {
		return articleName;
	}

	public void setArticleName(String articleName) {
		this.articleName = articleName;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(Double articlePrice) {
		this.articlePrice = articlePrice;
	}

	@Override
	public String toString() {
		return "Shop [id=" + id + ", idArticles=" + idArticles + ", articleName=" + articleName + ", quantity="
				+ quantity + ", articlePrice=" + articlePrice + "]";
	}
	
	
}
