package beans;

import models.Empresa;
import models.Lancamento;
import models.TipoLancamento;
import dao.EmpresaLancamentoDAO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@ViewScoped
public class CadastroLancamentoBean implements Serializable {

	private Lancamento lancamento = new Lancamento();
	private List<Empresa> empresas = new ArrayList<Empresa>();

	@PostConstruct
	public void init() {
		EmpresaLancamentoDAO empresaLancamentoDAO = new EmpresaLancamentoDAO();
		this.empresas = empresaLancamentoDAO.list();
	}

	public void cadastrar() {
		EmpresaLancamentoDAO empresaLancamentoDAO = new EmpresaLancamentoDAO();
		empresaLancamentoDAO.insertLancamento(this.lancamento);
		this.lancamento = new Lancamento();
		String msg = "Cadastro efetuado com sucesso";

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
	}

	public TipoLancamento[] getTiposLancamento() {
		return TipoLancamento.values();
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public List<Empresa> getEmpresas() {
		return empresas;
	}

	public void setEmpresas(List<Empresa> empresas) {
		this.empresas = empresas;
	}

}
