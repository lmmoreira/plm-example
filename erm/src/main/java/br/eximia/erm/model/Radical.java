package br.eximia.erm.model;

import javax.persistence.AttributeOverride;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;

import br.eximia.springutils.data.domain.AbstractGenericEntity;

@Entity
@AttributeOverride(name = "id", column = @Column(name = "IDRADICAL"))
@Table(name = "RADICAIS")
public class Radical extends AbstractGenericEntity<Radical, Long> {

	private static final long serialVersionUID = 1L;

	@JoinColumn(name = "PROGRAMA", referencedColumnName = "IDPROGRAMA")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@Valid
	private Programa programa;
	
	@JoinColumn(name = "ATA", referencedColumnName = "IDATA")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@Valid
	private Ata ata;

	@JoinColumn(name = "SUBATA", referencedColumnName = "IDSUBATA")
	@ManyToOne(optional = false, cascade = CascadeType.MERGE)
	@Valid
	private SubAta subAta;

	@Basic(optional = false)
	@Column(name = "RADICAL")
	private Long radical = 0L;

	public Radical() {
	}

	public Radical(Programa programa, Ata ata, SubAta subAta) {
		super();
		this.ata = ata;
		this.subAta = subAta;
		this.programa = programa;
	}

	public Ata getAta() {
		return ata;
	}

	public void setAta(Ata ata) {
		this.ata = ata;
	}

	public Programa getPrograma() {
		return programa;
	}

	public void setPrograma(Programa programa) {
		this.programa = programa;
	}

	public SubAta getSubAta() {
		return subAta;
	}

	public void setSubAta(SubAta subAta) {
		this.subAta = subAta;
	}

	public Long getRadical() {
		return radical;
	}

	public void setRadical(Long radical) {
		this.radical = radical;
	}

	@Override
	public String toString() {
		return this.getRadical().toString();
	}

	@Override
	public int compareTo(Radical o) {
		return this.getRadical().compareTo(o.getRadical());
	}
	
	public Radical plusOne(){
		this.setRadical(this.getRadical() + 1L);
		return this;
	}

}
