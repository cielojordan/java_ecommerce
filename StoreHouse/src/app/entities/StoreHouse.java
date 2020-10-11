package app.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StoreHouse {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	Long id;
	
	@Column
	Long idArticle ;
	
	@Column
	String articleName ;
	
	@Column
	Double articlePrice;
	
	@Column
	Integer quantity;
	
	public Double getArticlePrice() {
		return articlePrice;
	}

	public void setArticlePrice(Double articlePrice) {
		this.articlePrice = articlePrice;
	}

	public Long getIdArticle() {
		return idArticle;
	}

	public void setIdArticle(Long idArticle) {
		this.idArticle = idArticle;
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

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "StoreHouse [id=" + id + ", idArticle=" + idArticle + ", articleName=" + articleName + ", quantity="
				+ quantity + "]";
	}
	
	
	
}
