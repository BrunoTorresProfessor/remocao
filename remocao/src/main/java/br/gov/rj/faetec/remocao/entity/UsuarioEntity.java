package br.gov.rj.faetec.remocao.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuario", schema = "remocao")
public class UsuarioEntity implements Serializable,UserDetails  {


	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_usuario")
	private Long idUsuario;

	@Column(name = "email") 
	private String email;
	
	@Column(name = "nome") 
	private String nome;

	@Column(name = "senha") 
	private String senha;
	
	@ManyToMany
	@JoinTable(name="usuario_permissao",
    joinColumns={@JoinColumn(name="usuario_id", referencedColumnName = "id_usuario")},
    inverseJoinColumns={@JoinColumn(name="permissao_id", referencedColumnName = "id_permissao")})
	private List<PermissaoEntity> permissoes;	
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_area_conhecimento", referencedColumnName = "id_area_conhecimento")
    private AreaConhecimentoEntity areaConhecimentoEntity;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_carga_horaria", referencedColumnName = "id_carga_horaria")
    private CargaHorariaEntity cargaHorariaEntity;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_cargo", referencedColumnName = "id_cargo")
    private CargoEntity cargoEntity;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_unidade", referencedColumnName = "id_unidade")
    private UnidadeEntity unidadeEntity;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_vinculo", referencedColumnName = "id_vinculo")
    private VinculoEntity vinculoEntity;
	
	public AreaConhecimentoEntity getAreaConhecimentoEntity() {
		return areaConhecimentoEntity;
	}

	public void setAreaConhecimentoEntity(AreaConhecimentoEntity areaConhecimentoEntity) {
		this.areaConhecimentoEntity = areaConhecimentoEntity;
	}

	public CargaHorariaEntity getCargaHorariaEntity() {
		return cargaHorariaEntity;
	}

	public void setCargaHorariaEntity(CargaHorariaEntity cargaHorariaEntity) {
		this.cargaHorariaEntity = cargaHorariaEntity;
	}

	public CargoEntity getCargoEntity() {
		return cargoEntity;
	}

	public void setCargoEntity(CargoEntity cargoEntity) {
		this.cargoEntity = cargoEntity;
	}

	public UnidadeEntity getUnidadeEntity() {
		return unidadeEntity;
	}

	public void setUnidadeEntity(UnidadeEntity unidadeEntity) {
		this.unidadeEntity = unidadeEntity;
	}

	public VinculoEntity getVinculoEntity() {
		return vinculoEntity;
	}

	public void setVinculoEntity(VinculoEntity vinculoEntity) {
		this.vinculoEntity = vinculoEntity;
	}

	public List<PermissaoEntity> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoEntity> permissoes) {
		this.permissoes = permissoes;
	}

	public Long getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(Long idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return (Collection<? extends GrantedAuthority>) this.permissoes;
	}
	@Override
	public String getPassword() {
			   
		return this.senha;
	   
	}
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	
	
    

	
}
