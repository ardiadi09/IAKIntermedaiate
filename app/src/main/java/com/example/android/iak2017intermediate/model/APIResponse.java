package com.example.android.iak2017intermediate.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class APIResponse{

	@SerializedName("sortBy")
	private String sortBy;

	@SerializedName("source")
	private String source;

	@SerializedName("articles")
	private List<ArticlesItem> articles;

	@SerializedName("status")
	private String status;

	public String getSortBy() {
		return sortBy;
	}

	public String getSource() {
		return source;
	}

	public List<ArticlesItem> getArticles() {
		return articles;
	}

	public String getStatus() {
		return status;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public void setArticles(List<ArticlesItem> articles) {
		this.articles = articles;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public APIResponse() {
	}

	@Override
 	public String toString(){
		return 
			"APIResponse{" + 
			"sortBy = '" + sortBy + '\'' + 
			",source = '" + source + '\'' + 
			",articles = '" + articles + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}