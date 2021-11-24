package dao;
import models.Lancamento; 
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import models.TipoLancamento;
import models.Empresa;


public class EmpresaLancamentoDAO {

	public List<Empresa> list() {
		List<Empresa> empresas = new ArrayList<Empresa>();

		try (Connection conn = AcessBD.getConnection()) { // Dessa forma a conexão
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM empresa");
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {
				Empresa empresa = new Empresa();
				empresa.setNome(resultSet.getString("nome"));
				empresa.setCodigo(resultSet.getInt("codigo"));
				empresas.add(empresa);
			}
			return empresas;

		} catch (Exception e) {
			return null;
		}

	}

	public List<Lancamento> listLancamento() {
		List<Lancamento> lancamentos = new ArrayList<Lancamento>();

		try (Connection conn = AcessBD.getConnection()) { // Dessa forma a conexão
			PreparedStatement preparedStatement = conn.prepareStatement("SELECT * FROM lancamento");
			ResultSet resultSet = preparedStatement.executeQuery();
			Empresa empresa = new Empresa();

			while (resultSet.next()) {

				Lancamento lancamento = new Lancamento();
				lancamento.setCodigo(resultSet.getInt("codigo"));

				lancamento.setTipo(TipoLancamento.valueOf(resultSet.getString("tipo").toUpperCase()));
				empresa = listEmpresaById(resultSet.getInt("codigo_empresa"));
				lancamento.setEmpresa(empresa);

				lancamento.setDescricao(resultSet.getString("descricao"));
				lancamento.setValor(resultSet.getDouble("valor"));
				lancamento.setPago(resultSet.getBoolean("pago"));
				if (resultSet.getDate("data_pagamento") != null) {
					lancamento.setDataPagamento(new java.util.Date(resultSet.getDate("data_pagamento").getTime()));
				}

				lancamento.setDataVencimento(new java.util.Date(resultSet.getDate("data_vencimento").getTime()));

				lancamentos.add(lancamento);
			}
			return lancamentos;

		} catch (Exception e) {
			return null;
		}

	}

	public Empresa listEmpresaById(int codigo) {
		Empresa empresa = new Empresa();
		try (Connection conn = AcessBD.getConnection()) {
			String query = "SELECT * FROM empresa WHERE codigo=?";
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setInt(1, codigo);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				empresa.setNome(rs.getString("nome"));
				empresa.setCodigo(rs.getInt("codigo"));
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return empresa;
	}

	public void insertLancamento(Lancamento lancamento) {
		try (Connection conn = AcessBD.getConnection()) {
			if (lancamento.getDataPagamento() != null) {
				String query = "INSERT INTO lancamento (tipo,codigo_empresa,descricao,valor,pago,data_pagamento,data_vencimento) VALUES (?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, lancamento.getTipo().getDescricao());
				ps.setInt(2, lancamento.getEmpresa().getCodigo());
				ps.setString(3, lancamento.getDescricao());
				ps.setDouble(4, lancamento.getValor());
				ps.setBoolean(5, lancamento.isPago());
				ps.setDate(6, new java.sql.Date(lancamento.getDataVencimento().getTime()));
				ps.setDate(7, new java.sql.Date(lancamento.getDataPagamento().getTime()));

				ps.executeUpdate();
			} else {
				String query = "INSERT INTO lancamento (tipo,codigo_empresa,descricao,valor,pago,data_vencimento) VALUES (?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(query);
				ps.setString(1, lancamento.getTipo().getDescricao());
				ps.setInt(2, lancamento.getEmpresa().getCodigo());
				ps.setString(3, lancamento.getDescricao());
				ps.setDouble(4, lancamento.getValor());
				ps.setBoolean(5, lancamento.isPago());

				ps.setDate(6, new java.sql.Date(lancamento.getDataVencimento().getTime()));

				ps.executeUpdate();
			}

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public int excluir(Lancamento lancamento) {
			
			try(Connection conn = AcessBD.getConnection()){
				
				if(lancamento != null) {
					String query = "DELETE FROM lancamento WHERE codigo = ?";
					PreparedStatement ps= conn.prepareStatement(query);
					ps.setInt(1, lancamento.getCodigo());
											
					ps.executeUpdate();
					
				}
				else {
					return 0;
				}					
			
			}
			catch (Exception e) {
				return 0;
			}
			return 1;
	}	
	
		

}