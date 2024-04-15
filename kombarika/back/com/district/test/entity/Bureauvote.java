package com.district.test.entity;

import com.dao.annotation.*;
import com.dao.annotation.conf.ForeignType;


@Table(name = "bureauvote")
public class Bureauvote {
	@Column(name = "code_bureau")
	String codeBureau;

	@Column(name = "centre_vote")
	String centreVote;

	@ForeignKey(mappedBy = "id_fokontany", foreignType = ForeignType.OneToMany)
	Fokontany idFokontany;

	@PrimaryKey
	@Column(name = "id")
	String id;

	@Column(name = "bureau_vote")
	String bureauVote;



	public Bureauvote(){}

	public String getCodeBureau(){
		return this.codeBureau;
	}
	public void setCodeBureau(String codeBureau){
		this.codeBureau = codeBureau;
	}

	public String getCentreVote(){
		return this.centreVote;
	}
	public void setCentreVote(String centreVote){
		this.centreVote = centreVote;
	}

	public Fokontany getIdFokontany(){
		return this.idFokontany;
	}
	public void setIdFokontany(Fokontany idFokontany){
		this.idFokontany = idFokontany;
	}

	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id = id;
	}

	public String getBureauVote(){
		return this.bureauVote;
	}
	public void setBureauVote(String bureauVote){
		this.bureauVote = bureauVote;
	}


}
