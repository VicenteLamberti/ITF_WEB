package converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import dao.EmpresaLancamentoDAO;

import models.Empresa;


@FacesConverter(forClass = Empresa.class)
public class ObjectConverter implements Converter {
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		Empresa retorno = null;
		if(value != null) {
			
			EmpresaLancamentoDAO empresaLancamentoDAO = new EmpresaLancamentoDAO();
			retorno = empresaLancamentoDAO.listEmpresaById(new Integer(value));
			
		}
		return retorno;
	}
	
	@Override
	public String getAsString(FacesContext arg0, UIComponent component, Object value) {
		if(value != null) {
			return String.valueOf(((Empresa)value).getCodigo());
		}
		else {
			return null;
		}
		
		
		
	}
}
