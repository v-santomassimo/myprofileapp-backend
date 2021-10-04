package com.vsan.myprofileapp.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SearchForm {
	
	private String searchString;
	
	public SearchForm(String searchString) {
		this.searchString = searchString;
	}
	
	

}
